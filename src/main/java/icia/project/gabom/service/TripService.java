package icia.project.gabom.service;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import icia.project.gabom.dao.ITripplanDao;
import icia.project.gabom.dto.Food;
import icia.project.gabom.dto.Member;
import icia.project.gabom.dto.Sns_friend;
import icia.project.gabom.dto.Trip_plan;

@Service
public class TripService {
   private ModelAndView mav;
   
   @Autowired
   private ITripplanDao tpDao;
   
   

   public String savetripplan(Trip_plan tp, Principal ppl) throws ParseException {
      String json =null;
      System.out.println("저장하러 오니?");
      String DATE_PATTERN = "yyyy-MM-dd";//날짜 패턴 선언
      
      String trip_id = ppl.getName();
      String trip_title = tp.getTrip_title();
      String trip_start_date = tp.getTrip_start_date();
      String trip_end_date = tp.getTrip_end_date();
      String trip_area = tp.getTrip_area();
      
      Trip_plan tpl = new Trip_plan();
      
      tpl.setTrip_area(trip_area).setTrip_id(trip_id).setTrip_title(trip_title);
      tpl.setTrip_start_date(trip_start_date).setTrip_end_date(trip_end_date);
      
      int trip_number = tpDao.savetripplan(tpl);
      
      System.out.println("currval값="+tpl.getTrip_number());
      
      int trip_number2=tpl.getTrip_number();
      System.out.println("여행번호:"+trip_number2);
      
       SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN); //날짜 패턴 형식으로 변환
        
       Date startDate = sdf.parse(trip_start_date);//시작날짜
        
       Date endDate = sdf.parse(trip_end_date);//종료날짜
        
       ArrayList<String> day = new ArrayList<String>();//날짜를 리스트에 담음

       Date currentDate = startDate;//날짜 current

        while (currentDate.compareTo(endDate) <= 0) {
           day.add(sdf.format(currentDate));
               Calendar c = Calendar.getInstance();
               c.setTime(currentDate);
               c.add(Calendar.DAY_OF_MONTH, 1);
               currentDate = c.getTime();
           }
           int index=1;
           for (String date : day) {
               System.out.println(date);
            boolean d = tpDao.savetripdate(trip_number2,date,index);
            index++;
           }
           
           System.out.println("여행 날짜 전부"+day);

      json = new Gson().toJson(tpl);
      
      return json;
   }


   //내 여행 목록 
   public ModelAndView myplan(Principal ppl) {
      String json = null;
      String json2 = null;
      String json3 = null;
      mav = new ModelAndView();
      String view = null;
      
      String trip_id = ppl.getName();
      
      
      List<Trip_plan> myplanlist = tpDao.getmyplan(trip_id);//내 여행목록
      List<Member> memberinfo = tpDao.getmemberinfo(trip_id);//회원정보
      List<Sns_friend> friendlist = tpDao.getfriendlist(trip_id);//회원의 친구목록
      
      view="Trip/myplan";
      
      json = new Gson().toJson(myplanlist);
      json2 = new Gson().toJson(memberinfo);
      json3 = new Gson().toJson(friendlist);
      mav.addObject("myplanlist", json); // key,value
      mav.addObject("memberinfo", json2); // key,value
      mav.addObject("friendlist", json3); // key,value
      
      mav.setViewName(view); //view에 url로 이동
      return mav;
   
   }
   
   

}
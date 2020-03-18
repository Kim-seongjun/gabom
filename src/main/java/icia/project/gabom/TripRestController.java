package icia.project.gabom;

import java.security.Principal;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import icia.project.gabom.dao.ITripplanDao;
import icia.project.gabom.dto.Trip_plan;
import icia.project.gabom.service.TripService;

@RestController
@RequestMapping(value = "/tprest")
public class TripRestController {
   
   @Autowired
   private ITripplanDao tpDao;
   
   @Autowired
   private TripService trs;
   
   //여행1단계 저장
   @PostMapping(value = "/savetripplan" ,produces = "application/json;charset=utf-8")
   public String savetripplan(Trip_plan tp, Principal ppl ) throws ParseException {
      System.out.println("여행플랜 저장");
      
//      System.out.println("목적지"+tp.getTrip_area());
//      System.out.println("여행자 아이디"+ppl.getName());
//      System.out.println("시작날짜"+tp.getTrip_start_date());
//      System.out.println("끝나는날 "+tp.getTrip_end_date());
//      System.out.println("여행제목 "+tp.getTrip_title());
      
      String json = trs.savetripplan(tp,ppl);
      
      System.out.println(json);
      
      return json;
   }

}
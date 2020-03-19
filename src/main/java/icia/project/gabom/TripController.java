package icia.project.gabom;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import icia.project.gabom.dao.ITripplanDao;
import icia.project.gabom.dto.Trip_member;
import icia.project.gabom.dto.Trip_plan;
import icia.project.gabom.service.TripService;

@Controller
public class TripController {
   
   private ModelAndView mav;
   
//   @Autowired
//   private ITripplanDao tpDao;
   
   @Autowired
   private TripService trs;
   
   
   @RequestMapping(value = "/trip", method = RequestMethod.GET)
   public String trip() {
      System.out.println("여행계획");
      
      return "Trip/Tripplan";
   }
   
   @RequestMapping(value = "/myplan", method = RequestMethod.GET)
   public ModelAndView myplan(Principal ppl,Trip_member tm) {
      System.out.println("내여행 목록");
      
      mav=trs.myplan(ppl,tm);
      return mav;
   }
   
   
   @RequestMapping(value = "/detailplan", method = RequestMethod.GET)
   public ModelAndView detailplan(Principal ppl,int trip_number) {
      System.out.println("상세 여행 플랜");
      
      //System.out.println("여행번호:"+trip_number);
      
      
      mav=trs.detailplan(trip_number,ppl );
      //System.out.println(mav);
      return mav;
   }

}
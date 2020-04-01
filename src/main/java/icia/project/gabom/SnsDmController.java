package icia.project.gabom;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SnsDmController {
	
	
	@RequestMapping(value = "/snsdm")
	public ModelAndView snsDm(Principal principal) {
		ModelAndView mav= new ModelAndView();
		mav.addObject("id", principal.getName());
		mav.setViewName("sns/snsDm");
		return mav;
	}
	
	
}

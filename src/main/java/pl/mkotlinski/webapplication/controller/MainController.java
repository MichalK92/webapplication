package pl.mkotlinski.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.mkotlinski.webapplication.service.SystemPropertiesService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	public SystemPropertiesService systemPropertiesService;
	
	@RequestMapping
	public ModelAndView defaultPage()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("systemVersion", systemPropertiesService.getSystemVersion());
		mnv.setViewName("index");
		return mnv;
	}

}

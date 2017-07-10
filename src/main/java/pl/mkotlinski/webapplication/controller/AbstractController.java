package pl.mkotlinski.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.mkotlinski.webapplication.service.SystemPropertiesService;

@Controller
public abstract class AbstractController {
	
	@Autowired
	private SystemPropertiesService systemProperties;
	
	@ModelAttribute("systemVersion")
	public String getSystemVersion()
	{
		return systemProperties.getSystemVersion();
	}

}

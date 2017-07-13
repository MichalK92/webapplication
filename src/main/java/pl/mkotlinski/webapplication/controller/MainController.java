package pl.mkotlinski.webapplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.mkotlinski.webapplication.exception.user.UserExistsException;
import pl.mkotlinski.webapplication.model.form.user.UserProfileForm;
import pl.mkotlinski.webapplication.model.user.UserProfile;
import pl.mkotlinski.webapplication.model.user.UserRole;
import pl.mkotlinski.webapplication.model.user.UserRoleEnum;
import pl.mkotlinski.webapplication.service.UserRoleService;
import pl.mkotlinski.webapplication.service.UserService;

@Controller
@RequestMapping("/")
public class MainController extends AbstractController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping
	public ModelAndView defaultPage()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("index");
		return mnv;
	}
	
	//Login && Logout [BEGIN]
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("loginPage/loginPage");
		return mnv;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	//Login && Logout [END]
	
	//Register User [BEGIN]
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerUserGET()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("userProfileForm", new UserProfileForm());
		mnv.setViewName("registerUser/registerUser");
		return mnv;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUserPOST(Model model, @ModelAttribute("userProfileForm") @Validated UserProfileForm userProfileForm, BindingResult bindingResult)
	{
		ModelAndView mnv = new ModelAndView();
		
		if(bindingResult.hasErrors())
		{
			mnv.setViewName("registerUser/registerUser");
			return mnv;
		}
		
		try {
			UserProfile user = userProfileForm.getUser();
			user.addRole(userRoleService.findByName(UserRoleEnum.USER.getUserRole()));
			userService.addUser(user);
		} catch (UserExistsException e) {
			mnv.addObject("userExistError", "userExistError");
			mnv.setViewName("registerUser/registerUser");
			return mnv;
		}
		
		mnv.setViewName("index");
		return mnv;
	}	
	//Register User [END]
	
	//UserProfile[BEGIN]
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView loggedUserProfile()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("userProfile/userProfile");
		return mnv;
	}
	
	//UserProfile[END]
	
	//

}

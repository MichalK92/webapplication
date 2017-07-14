package pl.mkotlinski.webapplication.vaildator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.mkotlinski.webapplication.model.form.user.UserProfileForm;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UserFormValidator implements Validator{
	
	private static final String EMAIL_PATTERN =	"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Pattern pattern;
	private Matcher matcher;
	
	public UserFormValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return UserProfileForm.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "userFormValidator.login.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "userFormValidator.login.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "userFormValidator.login.null");
				
		UserProfileForm userProfileForm = (UserProfileForm) target;		
		if(!userProfileForm.getPassword().equals(userProfileForm.getPasswordConfirm()))
		{
			errors.rejectValue("password", "userFormValidator.notmatch.password");
			errors.rejectValue("passwordConfirm", "userFormValidator.notmatch.password");
		}
		
		//email validate
		matcher = pattern.matcher(userProfileForm.getEmailAddress());
		
		if(!matcher.matches())
		{
			errors.rejectValue("emailAddress", "userFormValidator.incorrect");
		}
		
	}
}

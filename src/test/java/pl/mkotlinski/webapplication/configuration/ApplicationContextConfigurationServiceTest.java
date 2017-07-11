package pl.mkotlinski.webapplication.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mkotlinski.webapplication.repository.UserServiceDao;
import pl.mkotlinski.webapplication.service.UserService;
import pl.mkotlinski.webapplication.service.impl.user.UserServiceImpl;

@Configuration
public class ApplicationContextConfigurationServiceTest {
	
	//Bean for testing service layer
	@Bean(name = "mockitoUserDaoService")
	public UserServiceDao mockitoUserDaoService()
	{
		return Mockito.mock(UserServiceDao.class);
	}
	
	@Bean(name = "userServiceTest")
	public UserService getUserService()
	{
		return new UserServiceImpl();
	}

}

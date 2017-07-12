package pl.mkotlinski.webapplication.service.impl.user;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.mkotlinski.webapplication.configuration.ApplicationContextConfigurationServiceTest;
import pl.mkotlinski.webapplication.exception.user.UserExistsException;
import pl.mkotlinski.webapplication.model.user.UserProfile;
import pl.mkotlinski.webapplication.repository.UserServiceDao;
import pl.mkotlinski.webapplication.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContextConfigurationServiceTest.class})
@WebAppConfiguration
public class UserServiceImplTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Autowired
	@Qualifier("userServiceTest")
	private UserService userService;
	
	@Autowired
	@Qualifier(value = "mockitoUserDaoService")
	private UserServiceDao mockUserDao;
	
	@Before
	public void setUp()
	{
		Mockito.reset(mockUserDao);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testFindUserById() {
		
	}

	@Test
	public void testFindByLogin() {
		
		UserProfile user1 = new UserProfile();
		user1.setLogin("MichalK92Test");
		
		Mockito.when(mockUserDao.findByLogin(user1.getLogin())).thenReturn(user1);
		
		assertEquals(user1, userService.findByLogin(user1.getLogin()));
	}
	
	@Test
	public void testAddUser() throws UserExistsException {
		
		UserProfile user1 = new UserProfile();
		user1.setLogin("MichalK92Test");
		
		//WHEN THEN
		Mockito.when(mockUserDao.findByLogin(user1.getLogin())).thenReturn(null);

		userService.addUser(user1);		
	}

	@Test(expected = UserExistsException.class)
	public void testAddUserException() throws UserExistsException {
		
		UserProfile user1 = new UserProfile();
		user1.setLogin("MichalK92Test");
		
		//WHEN THEN
		Mockito.when(mockUserDao.findByLogin(user1.getLogin())).thenReturn(user1);
		
		//Throw Excpetion
		userService.addUser(user1);		
	}
}

package pl.mkotlinski.webapplication.repository.impl.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.mkotlinski.webapplication.configuration.ApplicationContextConfigurationDaoTest;
import pl.mkotlinski.webapplication.exception.user.UserExistsException;
import pl.mkotlinski.webapplication.exception.user.UserNotFoundException;
import pl.mkotlinski.webapplication.model.user.UserProfile;
import pl.mkotlinski.webapplication.repository.UserServiceDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContextConfigurationDaoTest.class})
public class UserServiceDaoImplTest {
	
	@Autowired
	@Qualifier("userDaoService")
	private UserServiceDao userDao;

	@Test
	@Transactional
	public void testFindUserById() throws UserExistsException, UserNotFoundException {
		UserProfile userProfile = new UserProfile();
		userProfile.setLogin("MichalK92Test");
		
		userDao.addUser(userProfile);
		
		UserProfile userFoundByLogin = userDao.findByLogin(userProfile.getLogin());
		UserProfile userFoundById = userDao.findUserById(userFoundByLogin.getId());
		
		assertEquals(userProfile, userFoundById);
	}

	@Test
	@Transactional
	public void testFindByLogin() throws UserExistsException {
		UserProfile userProfile = new UserProfile();
		userProfile.setLogin("MichalK92Test");
		
		userDao.addUser(userProfile);
		
		assertEquals(userProfile, userDao.findByLogin(userProfile.getLogin()));
	}

	@Test
	@Transactional
	public void testAddUser() throws UserExistsException {		
		UserProfile userProfile = new UserProfile();
		userProfile.setLogin("MichalK92Test");		
		userDao.addUser(userProfile);
	}	
}

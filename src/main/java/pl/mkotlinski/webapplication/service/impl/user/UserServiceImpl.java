package pl.mkotlinski.webapplication.service.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mkotlinski.webapplication.exception.user.UserExistsException;
import pl.mkotlinski.webapplication.exception.user.UserNotFoundException;
import pl.mkotlinski.webapplication.model.user.UserProfile;
import pl.mkotlinski.webapplication.repository.UserServiceDao;
import pl.mkotlinski.webapplication.service.UserService;

@Service("userService")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserServiceDao userDao;

	@Override
	public UserProfile findUserById(long id_user) throws UserNotFoundException {
		
		UserProfile userProfile = userDao.findUserById(id_user);
		
		if(userProfile == null)
		{
			throw new UserNotFoundException(id_user);
		}
		
		return userProfile;
	}

	@Override
	public UserProfile findByLogin(String sso) {
		return userDao.findByLogin(sso);
	}

	@Override
	public void addUser(UserProfile newUser) throws UserExistsException {
				
		UserProfile userProfile = findByLogin(newUser.getLogin());
		
		if(userProfile != null)
		{
			throw new UserExistsException(newUser.getLogin());
		}
		
		userDao.addUser(newUser);
	}

}

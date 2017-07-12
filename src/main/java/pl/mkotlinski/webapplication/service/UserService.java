package pl.mkotlinski.webapplication.service;

import org.omg.CORBA.UserException;

import pl.mkotlinski.webapplication.exception.user.UserExistsException;
import pl.mkotlinski.webapplication.exception.user.UserNotFoundException;
import pl.mkotlinski.webapplication.model.user.UserProfile;

/**
 * @author MichalK92
 *
 */
public interface UserService {
	
	/**
	 * This method try find user by his login. If user doesn't exist, method return null.
	 * @param id_user
	 * @return UserProfile
	 * @throws UserNotFoundException
	 */
	public UserProfile findUserById(long id_user) throws UserNotFoundException;
	
	/** 
	 * This method try find user by his login. If user doesn't exist, method return null.
	 * 
	 * @param sso String value
	 * @return UserAccount
	 * @throws UserNotFoundException 
	 */
	
	public UserProfile findByLogin(String sso);
	
	/**
	 * This method try add user to repository.
	 * @param user
	 * @throws UserExistsException 
	 */
	public void addUser(UserProfile user) throws UserExistsException;
}

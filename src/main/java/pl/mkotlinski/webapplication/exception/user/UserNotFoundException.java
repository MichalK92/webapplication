package pl.mkotlinski.webapplication.exception.user;

public class UserNotFoundException extends UserException{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(long message) {
		super(String.valueOf(message));
	}
}

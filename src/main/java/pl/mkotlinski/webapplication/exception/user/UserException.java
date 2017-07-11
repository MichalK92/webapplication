package pl.mkotlinski.webapplication.exception.user;

import pl.mkotlinski.webapplication.exception.WebApplicationException;

public class UserException extends WebApplicationException{

	private static final long serialVersionUID = 1L;

	public UserException(String message) {
		super(message);
	}
}

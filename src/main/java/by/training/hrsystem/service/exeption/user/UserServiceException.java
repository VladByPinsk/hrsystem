package by.training.hrsystem.service.exeption.user;

import by.training.hrsystem.service.exeption.ServiceException;

public class UserServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public UserServiceException(String message) {
		super(message);
	}

	public UserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}

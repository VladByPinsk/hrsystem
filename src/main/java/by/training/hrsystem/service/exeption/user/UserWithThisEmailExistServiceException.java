package by.training.hrsystem.service.exeption.user;

public class UserWithThisEmailExistServiceException extends UserServiceException {

	private static final long serialVersionUID = 1L;

	public UserWithThisEmailExistServiceException(String message) {
		super(message);
	}

	public UserWithThisEmailExistServiceException(String message, Throwable cause) {
		super(message, cause);
	}


}

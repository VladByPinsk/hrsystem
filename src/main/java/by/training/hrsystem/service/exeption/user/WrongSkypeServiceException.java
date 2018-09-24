package by.training.hrsystem.service.exeption.user;

public class WrongSkypeServiceException extends UserServiceException {
	private static final long serialVersionUID = 1L;

	public WrongSkypeServiceException(String message) {
		super(message);
	}

	public WrongSkypeServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

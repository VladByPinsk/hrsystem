package by.training.hrsystem.service.exeption.user;

public class WrongNameServiceException extends UserServiceException {
	private static final long serialVersionUID = 1L;

	public WrongNameServiceException(String message) {
		super(message);
	}

	public WrongNameServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

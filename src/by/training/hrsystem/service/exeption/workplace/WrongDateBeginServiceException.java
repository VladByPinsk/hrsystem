package by.training.hrsystem.service.exeption.workplace;

public class WrongDateBeginServiceException extends WorkPlaceServiceException {

	private static final long serialVersionUID = 1L;

	public WrongDateBeginServiceException(String message) {
		super(message);
	}

	public WrongDateBeginServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}

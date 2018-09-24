package by.training.hrsystem.service.exeption.workplace;

public class WrongCompanyNameServiceException extends WorkPlaceServiceException {

	private static final long serialVersionUID = 1L;

	public WrongCompanyNameServiceException(String message) {
		super(message);
	}

	public WrongCompanyNameServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

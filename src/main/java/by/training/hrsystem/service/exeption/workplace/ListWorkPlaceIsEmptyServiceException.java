package by.training.hrsystem.service.exeption.workplace;

public class ListWorkPlaceIsEmptyServiceException extends WorkPlaceServiceException {

	private static final long serialVersionUID = 1L;

	public ListWorkPlaceIsEmptyServiceException(String message) {
		super(message);
	}

	public ListWorkPlaceIsEmptyServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

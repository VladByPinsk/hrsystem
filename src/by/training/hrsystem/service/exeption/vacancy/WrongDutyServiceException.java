package by.training.hrsystem.service.exeption.vacancy;

public class WrongDutyServiceException extends VacancyServiceException {

	private static final long serialVersionUID = 1L;

	public WrongDutyServiceException(String message) {
		super(message);
	}

	public WrongDutyServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

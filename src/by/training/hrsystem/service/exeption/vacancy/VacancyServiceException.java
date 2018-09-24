package by.training.hrsystem.service.exeption.vacancy;

import by.training.hrsystem.service.exeption.ServiceException;

public class VacancyServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public VacancyServiceException(String message) {
		super(message);
	}

	public VacancyServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}

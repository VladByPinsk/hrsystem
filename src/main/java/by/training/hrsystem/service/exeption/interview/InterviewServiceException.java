package by.training.hrsystem.service.exeption.interview;

import by.training.hrsystem.service.exeption.ServiceException;

public class InterviewServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public InterviewServiceException(String message) {
		super(message);
	}

	public InterviewServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

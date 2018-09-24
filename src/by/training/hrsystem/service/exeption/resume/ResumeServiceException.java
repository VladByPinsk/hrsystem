package by.training.hrsystem.service.exeption.resume;

import by.training.hrsystem.service.exeption.ServiceException;

public class ResumeServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ResumeServiceException(String message) {
		super(message);
	}

	public ResumeServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

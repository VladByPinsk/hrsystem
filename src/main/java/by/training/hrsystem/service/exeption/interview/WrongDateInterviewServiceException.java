package by.training.hrsystem.service.exeption.interview;

public class WrongDateInterviewServiceException extends InterviewServiceException {
	private static final long serialVersionUID = 1L;

	public WrongDateInterviewServiceException(String message) {
		super(message);
	}

	public WrongDateInterviewServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

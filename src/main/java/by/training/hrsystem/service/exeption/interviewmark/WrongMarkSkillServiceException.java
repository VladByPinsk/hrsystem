package by.training.hrsystem.service.exeption.interviewmark;

public class WrongMarkSkillServiceException extends InterviewMarkServiceException {

	private static final long serialVersionUID = 1L;

	public WrongMarkSkillServiceException(String message) {
		super(message);
	}

	public WrongMarkSkillServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}

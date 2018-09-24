package by.training.hrsystem.service.exeption.skill;

public class ListSkillIsEmptyServiceException extends SkillServiceException {

	private static final long serialVersionUID = 1L;

	public ListSkillIsEmptyServiceException(String message) {
		super(message);
	}

	public ListSkillIsEmptyServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}

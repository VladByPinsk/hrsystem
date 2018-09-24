package by.training.hrsystem.service.exeption.skill;

import by.training.hrsystem.service.exeption.ServiceException;

public class SkillServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public SkillServiceException(String message) {
		super(message);
	}

	public SkillServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

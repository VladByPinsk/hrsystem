package by.training.hrsystem.service.exeption.education;

public class WrongPostGraduateServiceException extends EducationServiceException {

	private static final long serialVersionUID = 1L;

	public WrongPostGraduateServiceException(String message) {
		super(message);
	}

	public WrongPostGraduateServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}

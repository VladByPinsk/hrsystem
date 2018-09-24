package by.training.hrsystem.service.exeption.education;

public class WrongFacultyServiceException extends EducationServiceException {

	private static final long serialVersionUID = 1L;

	public WrongFacultyServiceException(String message) {
		super(message);
	}

	public WrongFacultyServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

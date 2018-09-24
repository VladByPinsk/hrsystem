package by.training.hrsystem.service.exeption.education;

public class WrongDepartmentServiceException extends EducationServiceException {

	private static final long serialVersionUID = 1L;

	public WrongDepartmentServiceException(String message) {
		super(message);
	}

	public WrongDepartmentServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}

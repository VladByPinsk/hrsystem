package by.training.hrsystem.service.exeption.education;

public class WrongGradYearServiceException  extends EducationServiceException {

	private static final long serialVersionUID = 1L;

	public WrongGradYearServiceException(String message) {
		super(message);
	}

	public WrongGradYearServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

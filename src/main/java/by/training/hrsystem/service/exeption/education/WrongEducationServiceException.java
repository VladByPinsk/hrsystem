package by.training.hrsystem.service.exeption.education;

public class WrongEducationServiceException extends EducationServiceException {

  private static final long serialVersionUID = 1L;

  public WrongEducationServiceException(String message) {
    super(message);
  }

  public WrongEducationServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

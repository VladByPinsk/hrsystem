package by.training.hrsystem.service.exeption.education;

public class WrongCourseServiceException extends EducationServiceException {

  private static final long serialVersionUID = 1L;

  public WrongCourseServiceException(String message) {
    super(message);
  }

  public WrongCourseServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

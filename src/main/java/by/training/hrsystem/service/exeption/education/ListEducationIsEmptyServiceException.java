package by.training.hrsystem.service.exeption.education;

public class ListEducationIsEmptyServiceException extends EducationServiceException {

  private static final long serialVersionUID = 1L;

  public ListEducationIsEmptyServiceException(String message) {
    super(message);
  }

  public ListEducationIsEmptyServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

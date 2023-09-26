package by.training.hrsystem.service.exeption.vacancy;

public class WrongDescriptionServiceException extends VacancyServiceException {

  private static final long serialVersionUID = 1L;

  public WrongDescriptionServiceException(String message) {
    super(message);
  }

  public WrongDescriptionServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

package by.training.hrsystem.service.exeption.vacancy;

public class WrongConditionsServiceException extends VacancyServiceException {

  private static final long serialVersionUID = 1L;

  public WrongConditionsServiceException(String message) {
    super(message);
  }

  public WrongConditionsServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

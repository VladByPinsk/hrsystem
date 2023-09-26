package by.training.hrsystem.service.exeption.vacancy;

public class WrongVacancyNameServiceException extends VacancyServiceException {

  private static final long serialVersionUID = 1L;

  public WrongVacancyNameServiceException(String message) {
    super(message);
  }

  public WrongVacancyNameServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

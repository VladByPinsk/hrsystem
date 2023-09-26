package by.training.hrsystem.service.exeption.vacancy;

public class WrongSalaryServiceException extends VacancyServiceException {

  private static final long serialVersionUID = 1L;

  public WrongSalaryServiceException(String message) {
    super(message);
  }

  public WrongSalaryServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

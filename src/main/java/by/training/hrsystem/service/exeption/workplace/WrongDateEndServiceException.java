package by.training.hrsystem.service.exeption.workplace;

public class WrongDateEndServiceException extends WorkPlaceServiceException {

  private static final long serialVersionUID = 1L;

  public WrongDateEndServiceException(String message) {
    super(message);
  }

  public WrongDateEndServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

package by.training.hrsystem.service.exeption.workplace;

public class WrongDateServiceException extends WorkPlaceServiceException {

  private static final long serialVersionUID = 1L;

  public WrongDateServiceException(String message) {
    super(message);
  }

  public WrongDateServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

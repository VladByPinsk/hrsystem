package by.training.hrsystem.service.exeption.workplace;

public class WrongPositionServiceException extends WorkPlaceServiceException {

  private static final long serialVersionUID = 1L;

  public WrongPositionServiceException(String message) {
    super(message);
  }

  public WrongPositionServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

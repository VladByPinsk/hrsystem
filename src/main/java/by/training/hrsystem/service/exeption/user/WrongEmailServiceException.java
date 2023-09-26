package by.training.hrsystem.service.exeption.user;

public class WrongEmailServiceException extends UserServiceException {

  private static final long serialVersionUID = 1L;

  public WrongEmailServiceException(String message) {
    super(message);
  }

  public WrongEmailServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

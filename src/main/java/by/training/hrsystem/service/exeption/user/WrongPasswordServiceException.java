package by.training.hrsystem.service.exeption.user;

public class WrongPasswordServiceException extends UserServiceException {

  private static final long serialVersionUID = 1L;

  public WrongPasswordServiceException(String message) {
    super(message);
  }

  public WrongPasswordServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

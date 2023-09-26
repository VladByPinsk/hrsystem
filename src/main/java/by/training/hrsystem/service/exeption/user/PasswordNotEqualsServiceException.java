package by.training.hrsystem.service.exeption.user;

public class PasswordNotEqualsServiceException extends UserServiceException {

  private static final long serialVersionUID = 1L;

  public PasswordNotEqualsServiceException(String message) {
    super(message);
  }

  public PasswordNotEqualsServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

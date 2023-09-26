package by.training.hrsystem.service.exeption.user;

public class WrongSecondnameServiceException extends UserServiceException {
  private static final long serialVersionUID = 1L;

  public WrongSecondnameServiceException(String message) {
    super(message);
  }

  public WrongSecondnameServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

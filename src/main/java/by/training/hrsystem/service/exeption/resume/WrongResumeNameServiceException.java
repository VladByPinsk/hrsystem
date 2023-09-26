package by.training.hrsystem.service.exeption.resume;

public class WrongResumeNameServiceException extends ResumeServiceException {

  private static final long serialVersionUID = 1L;

  public WrongResumeNameServiceException(String message) {
    super(message);
  }

  public WrongResumeNameServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

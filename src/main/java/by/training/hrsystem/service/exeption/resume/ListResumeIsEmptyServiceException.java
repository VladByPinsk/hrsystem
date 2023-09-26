package by.training.hrsystem.service.exeption.resume;

public class ListResumeIsEmptyServiceException extends ResumeServiceException {

  private static final long serialVersionUID = 1L;

  public ListResumeIsEmptyServiceException(String message) {
    super(message);
  }

  public ListResumeIsEmptyServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

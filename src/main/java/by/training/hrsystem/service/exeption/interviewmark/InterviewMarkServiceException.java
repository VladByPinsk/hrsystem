package by.training.hrsystem.service.exeption.interviewmark;

import by.training.hrsystem.service.exeption.ServiceException;

public class InterviewMarkServiceException extends ServiceException {

  private static final long serialVersionUID = 1L;

  public InterviewMarkServiceException(String message) {
    super(message);
  }

  public InterviewMarkServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

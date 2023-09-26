package by.training.hrsystem.service.exeption.workplace;

import by.training.hrsystem.service.exeption.ServiceException;

public class WorkPlaceServiceException extends ServiceException {
  private static final long serialVersionUID = 1L;

  public WorkPlaceServiceException(String message) {
    super(message);
  }

  public WorkPlaceServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

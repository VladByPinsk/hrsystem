package by.training.hrsystem.service.exeption.skill;

public class WrongRaitingServiceException extends SkillServiceException {

  private static final long serialVersionUID = 1L;

  public WrongRaitingServiceException(String message) {
    super(message);
  }

  public WrongRaitingServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

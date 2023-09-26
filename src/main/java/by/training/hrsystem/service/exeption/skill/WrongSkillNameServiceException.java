package by.training.hrsystem.service.exeption.skill;

public class WrongSkillNameServiceException extends SkillServiceException {

  private static final long serialVersionUID = 1L;

  public WrongSkillNameServiceException(String message) {
    super(message);
  }

  public WrongSkillNameServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

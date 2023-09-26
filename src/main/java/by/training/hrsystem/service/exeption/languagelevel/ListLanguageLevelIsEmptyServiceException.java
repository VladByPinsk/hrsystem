package by.training.hrsystem.service.exeption.languagelevel;

public class ListLanguageLevelIsEmptyServiceException extends ResumeLanguageServiceException {

  private static final long serialVersionUID = 1L;

  public ListLanguageLevelIsEmptyServiceException(String message) {
    super(message);
  }

  public ListLanguageLevelIsEmptyServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

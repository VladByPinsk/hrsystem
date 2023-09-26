package by.training.hrsystem.domain.type;

public enum LanguageLevelType {
  NOT_SPECIFIED("not specified"),
  A1("A1"),
  A1_PLUS("A1 plus"),
  A2("A2"),
  A2_PLUS("A2 plus"),
  B1("B1"),
  B1_PLUS("B1 plus"),
  B2("B2"),
  B2_PLUS("B2 plus"),
  C1("C1"),
  C1_PLUS("C1 plus"),
  C2("C2"),
  NATIVE_SPEAKER("Native speaker");

  private final String languageLevelType;

  LanguageLevelType(final String languageLevelType) {
    this.languageLevelType = languageLevelType;
  }

  public String getLanguageLevelType() {
    return languageLevelType;
  }
}

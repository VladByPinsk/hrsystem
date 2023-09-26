package by.training.hrsystem.domain.type;

public enum PostgraduateType {
  NOT_ASSIGNED("not assigned"),
  ASSIGNED("assigned"),
  TO_BE_ASSIGNED("to be assigned");

  private final String postgraduateType;

  PostgraduateType(final String postgraduateType) {
    this.postgraduateType = postgraduateType;
  }

  public String getPostgraduateType() {
    return postgraduateType;
  }
}

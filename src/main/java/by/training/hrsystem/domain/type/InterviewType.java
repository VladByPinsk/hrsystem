package by.training.hrsystem.domain.type;

public enum InterviewType {
  TECHICAL("techical"),
  PRELIMINARY("preliminary");

  private final String interviewType;

  InterviewType(final String interviewType) {
    this.interviewType = interviewType;
  }

  public String getInterviewType() {
    return interviewType;
  }
}

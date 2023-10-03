package by.training.hrsystem.domain.type;

import lombok.Getter;

@Getter
public enum InterviewType {
  TECHNICAL_SKILL("technical"),
  SOFT_SKILL("soft"),
  PRELIMINARY("preliminary");

  private final String interview;

  InterviewType(final String interview) {
    this.interview = interview;
  }
}

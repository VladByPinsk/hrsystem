package by.training.hrsystem.domain.type;

import lombok.Getter;

@Getter
public enum EducationType {
  NOT_SPECIFIED("not specified"),
  UNIVERSITY_INCOMPLETE("university incomplete"),
  HIGHER("higher"),
  MASTER("master"),
  PHD("PhD"),
  SECONDARY("secondary"),
  TECHNICAL_SCHOOL("technical school"),
  PHD_CANDIDATE("PhD candidate");

  private final String education;

  EducationType(final String education) {
    this.education = education;
  }
}

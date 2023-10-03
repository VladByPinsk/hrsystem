package by.training.hrsystem.domain.type;

import lombok.Getter;

@Getter
public enum EmploymentType {
  FULL_TIME("full time"),
  PART_TIME("part time"),
  REMOTE("remote"),
  CONTRACTUAL("contractual");

  private final String employment;

  EmploymentType(final String employment) {
    this.employment = employment;
  }

}

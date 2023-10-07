package by.training.hrsystem.domain.type;

import lombok.Getter;

@Getter
public enum Status {

  ASSIGN("assign"),
  PENDING("pending"),
  IN_REVIEW("in review"),
  APPROVED("approved"),
  CANCELED("canceled");

  private final String statusType;

  Status(final String statusType) {
    this.statusType = statusType;
  }
}

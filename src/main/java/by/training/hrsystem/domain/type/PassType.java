package by.training.hrsystem.domain.type;

import lombok.Getter;

@Getter
public enum PassType {
  PASS("pass"),
  NOT_PASS("not pass"),
  UNKNOWN("unknown");

  private final String pass;

  PassType(final String pass) {
    this.pass = pass;
  }
}

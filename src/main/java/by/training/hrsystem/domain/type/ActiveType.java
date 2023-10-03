package by.training.hrsystem.domain.type;

import lombok.Getter;

@Getter
public enum ActiveType {
  ACTIVE("active"),
  NON_ACTIVE("non active");

  private final String active;

  ActiveType(final String active) {
    this.active = active;
  }
}

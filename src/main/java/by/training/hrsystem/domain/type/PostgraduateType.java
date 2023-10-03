package by.training.hrsystem.domain.type;

import lombok.Getter;

@Getter
public enum PostgraduateType {
  NOT_ASSIGNED("not assigned"),
  ASSIGNED("assigned"),
  TO_BE_ASSIGNED("to be assigned");

  private final String postgraduate;

  PostgraduateType(final String postgraduate) {
    this.postgraduate = postgraduate;
  }
}

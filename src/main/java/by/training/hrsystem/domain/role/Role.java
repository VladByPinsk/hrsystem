package by.training.hrsystem.domain.role;

import lombok.Getter;

@Getter
public enum Role {
  APPLICANT("applicant"),
  HR("hr");

  final String roleValue;

  Role(final String roleValue) {
    this.roleValue = roleValue;
  }
}

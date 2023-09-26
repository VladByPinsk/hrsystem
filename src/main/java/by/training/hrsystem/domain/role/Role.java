package by.training.hrsystem.domain.role;

public enum Role {
  APPLICANT {
    {
      role = "applicant";
    }
  },
  HR {
    {
      role = "hr";
    }
  };

  String role;

  public String getRole() {
    return role;
  }
}

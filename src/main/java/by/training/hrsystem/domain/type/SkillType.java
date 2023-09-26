package by.training.hrsystem.domain.type;

public enum SkillType {
  NOVICE("novice"),
  INTERMEDIATE("intermediate"),
  ADVANCED("advanced"),
  EXPERT("expert");

  private final String skillType;

  SkillType(final String skillType) {
    this.skillType = skillType;
  }

  public String getSkillType() {
    return skillType;
  }
}

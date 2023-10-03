package by.training.hrsystem.domain.type;

import lombok.Getter;

@Getter
public enum SkillType {
  NOVICE("novice"),
  INTERMEDIATE("intermediate"),
  ADVANCED("advanced"),
  EXPERT("expert");

  private final String skill;

  SkillType(final String skill) {
    this.skill = skill;
  }
}

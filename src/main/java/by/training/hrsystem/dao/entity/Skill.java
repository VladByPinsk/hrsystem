package by.training.hrsystem.dao.entity;

import by.training.hrsystem.domain.type.SkillType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@ToString
@Entity(name = "SKILL")
public class Skill {

  @Id
  @UuidGenerator
  @Column(name = "S_ID_SKILL", nullable = false)
  private String idSkill;

  @Column(name = "R_ID_RESUME", nullable = false)
  private String idResume;

  @Column(name = "S_NAME", nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "S_SKILL_TYPE", nullable = false)
  private SkillType skillType;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Skill skill = (Skill) o;
    return Objects.equals(idSkill, skill.idSkill)
        && Objects.equals(idResume, skill.idResume)
        && Objects.equals(name, skill.name)
        && skillType == skill.skillType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSkill, idResume, name, skillType);
  }
}

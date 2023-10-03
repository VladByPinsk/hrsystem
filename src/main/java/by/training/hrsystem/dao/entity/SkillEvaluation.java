package by.training.hrsystem.dao.entity;

import by.training.hrsystem.domain.type.SkillType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@ToString
@Entity(name = "SKILL_EVALUATION")
public class SkillEvaluation {

  @Id
  @UuidGenerator
  @Column(name = "SE_ID_SKILL_EVALUATION", nullable = false)
  private String idEducation;

  @Column(name = "I_ID_INTERVIEW", nullable = false)
  private String idInterview;

  @Column(name = "SE_NAME", nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "SE_SKILL_TYPE", nullable = false)
  private SkillType skillType;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SkillEvaluation that = (SkillEvaluation) o;
    return Objects.equals(idEducation, that.idEducation)
        && Objects.equals(idInterview, that.idInterview)
        && Objects.equals(name, that.name)
        && skillType == that.skillType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(idEducation, idInterview, name, skillType);
  }
}

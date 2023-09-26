package by.training.hrsystem.domain;

import by.training.hrsystem.domain.type.SkillType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Skill implements Serializable {

  private static final long serialVersionUID = 3051395711744352982L;

  private int idSkill;
  private String name;
  private SkillType raiting;
  private int idResume;
}

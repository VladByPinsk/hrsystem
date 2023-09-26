package by.training.hrsystem.domain;

import by.training.hrsystem.domain.type.LanguageLevelType;
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
public class ResumeLanguage implements Serializable {

  private static final long serialVersionUID = -3957407860323858751L;

  private int idLanguage;
  private String name;
  private LanguageLevelType raiting;
  private int idResume;
}

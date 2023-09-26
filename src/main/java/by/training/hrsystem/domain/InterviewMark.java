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
public class InterviewMark implements Serializable {

	private static final long serialVersionUID = 6239229876210029009L;

	private int idMark;
	private String skill;
	private SkillType mark;
	private int idInterview;
}

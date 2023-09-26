package by.training.hrsystem.domain;

import by.training.hrsystem.domain.type.ActiveType;
import by.training.hrsystem.domain.type.MilitaryType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class Resume implements Serializable {

	private static final long serialVersionUID = -5223362080259007779L;

	private int idResume;
	private String name;
	private Date publishDate;
	private MilitaryType militatyType;
	private ActiveType activeType;

	private User applicant;
	private List<Education> educationList;
	private List<Skill> skillList;
	private List<ResumeLanguage> languageList;
	private List<WorkPlace> workPlaceList;

}

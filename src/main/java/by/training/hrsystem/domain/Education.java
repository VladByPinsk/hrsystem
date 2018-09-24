package by.training.hrsystem.domain;

import java.io.Serializable;
import by.training.hrsystem.domain.type.EducationType;
import by.training.hrsystem.domain.type.PostgraduateType;
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
public class Education implements Serializable {

	private static final long serialVersionUID = 8686405203355662547L;

	private int idEducation;
	private String institution;
	private String faculty;
	private String department;
	private EducationType education;
	private int course;
	private int gradYear;
	private PostgraduateType postGraduate;
	private int idResume;

}

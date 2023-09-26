package by.training.hrsystem.domain;

import by.training.hrsystem.domain.type.EducationType;
import by.training.hrsystem.domain.type.PostgraduateType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
@Entity(name = "Education")
public class Education implements Serializable {

	private static final long serialVersionUID = 8686405203355662547L;

	@Id
	private int idEducation;
	
	@Column
	private String institution;
	private String faculty;
	private String department;
	
	@Enumerated(EnumType.STRING)
	@Column
	private EducationType education;
	private int course;
	private int gradYear;
	
	@Enumerated(EnumType.STRING)
	@Column
	private PostgraduateType postGraduate;
	private int idResume;

}

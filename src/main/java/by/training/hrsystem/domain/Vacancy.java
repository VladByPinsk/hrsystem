package by.training.hrsystem.domain;

import java.io.Serializable;
import java.util.Date;

import by.training.hrsystem.domain.type.ActiveType;
import by.training.hrsystem.domain.type.CurrencyType;
import by.training.hrsystem.domain.type.EmploymentType;
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
public class Vacancy implements Serializable {

	private static final long serialVersionUID = -1818575473655456286L;

	private int idVacancy;
	private String name;
	private int salary;
	private CurrencyType currency;
	private Date publishDate;
	private String description;
	private String duty;
	private String condition;
	private EmploymentType employmentType;
	private ActiveType active;
	private boolean isHot;
	private String hrEmail;

}

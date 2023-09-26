package by.training.hrsystem.domain;

import java.io.Serializable;
import java.util.Date;
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
public class WorkPlace implements Serializable {

	private static final long serialVersionUID = 2763967571085356739L;

	private int idWorkPlace;
	private String companyName;
	private String position;
	private Date dateBegin;
	private Date dateEnd;
	private int idResume;

}

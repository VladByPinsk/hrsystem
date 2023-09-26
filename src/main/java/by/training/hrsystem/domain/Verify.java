package by.training.hrsystem.domain;

import by.training.hrsystem.domain.type.PassType;
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
public class Verify implements Serializable {

	private static final long serialVersionUID = -3057614338988817055L;

	private int idVerify;
	private PassType pass;

	private Vacancy vacancy;
	private Resume resume;

}

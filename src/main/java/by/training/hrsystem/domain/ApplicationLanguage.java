package by.training.hrsystem.domain;

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
public class ApplicationLanguage implements Serializable {

	private static final long serialVersionUID = 7287177338372583134L;

	private String lang;
	private String langName;

}

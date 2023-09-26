package by.training.hrsystem.domain;

import java.io.Serializable;
import java.util.Date;

import by.training.hrsystem.domain.role.Role;
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
public class User implements Serializable {

  private static final long serialVersionUID = 4086813463692982999L;

  private String email;
  private String password;
  private String surname;
  private String name;
  private String secondName;
  private String photo;
  private String skype;
  private int contactPhone;
  private Date birthDate;
  private Role role;
}

package by.training.hrsystem.dao.entity;

import by.training.hrsystem.domain.role.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@ToString
@Entity(name = "USER")
public class UserEntity {

  @Id
  @UuidGenerator
  @Column(name = "U_ID_USER", nullable = false)
  private String idUser;

  @Column(name = "U_EMAIL", nullable = false)
  private String email;

  @Column(name = "U_USERNAME", nullable = false)
  private String username;

  @Column(name = "U_PASSWORD", nullable = false)
  private String password;

  @Column(name = "U_SURNAME", nullable = false)
  private String surname;

  @Column(name = "U_NAME", nullable = false)
  private String name;

  @Column(name = "U_SECOND_NAME", nullable = false)
  private String secondName;

  @Column(name = "U_PATH_TO_PHOTO")
  private String pathToPhoto;

  @Column(name = "U_CONATCT_INFO")
  private String contactInfo;

  @Column(name = "U_CONTACT_PHONE", nullable = false)
  private int contactPhone;

  @Temporal(TemporalType.DATE)
  @Column(name = "U_BIRTH_DATE")
  private Date birthDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "U_ROLE", nullable = false)
  private Role role;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserEntity user = (UserEntity) o;
    return contactPhone == user.contactPhone
        && Objects.equals(idUser, user.idUser)
        && Objects.equals(email, user.email)
        && Objects.equals(username, user.username)
        && Objects.equals(password, user.password)
        && Objects.equals(surname, user.surname)
        && Objects.equals(name, user.name)
        && Objects.equals(secondName, user.secondName)
        && Objects.equals(pathToPhoto, user.pathToPhoto)
        && Objects.equals(contactInfo, user.contactInfo)
        && Objects.equals(birthDate, user.birthDate)
        && role == user.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        idUser,
        email,
        username,
        password,
        surname,
        name,
        secondName,
        pathToPhoto,
        contactInfo,
        contactPhone,
        birthDate,
        role);
  }
}

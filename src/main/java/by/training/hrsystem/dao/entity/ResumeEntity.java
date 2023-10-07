package by.training.hrsystem.dao.entity;

import by.training.hrsystem.domain.type.ActiveType;
import by.training.hrsystem.domain.type.MilitaryType;
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
@Entity(name = "RESUME")
public class ResumeEntity {

  @Id
  @UuidGenerator
  @Column(name = "R_ID_RESUME", nullable = false)
  private String idResume;

  @Column(name = "U_ID_USER", nullable = false)
  private String idUser;

  @Column(name = "R_NAME", nullable = false)
  private String name;

  @Column(name = "R_DESCRIPTION")
  private String description;

  @Temporal(TemporalType.DATE)
  @Column(name = "R_PUBLISH_DATE")
  private Date publishDate;

  @Column(name = "R_PATH_TO_FILE")
  private String pathToFile;

  @Enumerated(EnumType.STRING)
  @Column(name = "R_MILITARY_TYPE")
  private MilitaryType militaryType;

  @Enumerated(EnumType.STRING)
  @Column(name = "R_ACTIVE_TYPE", nullable = false)
  private ActiveType activeType;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ResumeEntity resume = (ResumeEntity) o;
    return Objects.equals(idResume, resume.idResume) && Objects.equals(idUser, resume.idUser) &&
        Objects.equals(name, resume.name) && Objects.equals(description, resume.description) &&
        Objects.equals(publishDate, resume.publishDate) &&
        Objects.equals(pathToFile, resume.pathToFile) && militaryType == resume.militaryType &&
        activeType == resume.activeType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(idResume, idUser, name, description, publishDate, pathToFile, militaryType,
        activeType);
  }
}

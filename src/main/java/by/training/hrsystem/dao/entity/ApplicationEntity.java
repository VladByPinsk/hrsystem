package by.training.hrsystem.dao.entity;

import by.training.hrsystem.domain.type.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@ToString
@Entity(name = "APPLICATION")
public class ApplicationEntity {

  @Id
  @UuidGenerator
  @Column(name = "A_ID_APPLICATION", nullable = false)
  private String idApplication;

  @Column(name = "V_ID_VACANCY", nullable = false)
  private String idVacancy;

  @Column(name = "R_ID_RESUME", nullable = false)
  private String idResume;

  @Enumerated(EnumType.STRING)
  @Column(name = "A_STATUS", nullable = false)
  private Status status;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ApplicationEntity that = (ApplicationEntity) o;
    return Objects.equals(idApplication, that.idApplication)
        && Objects.equals(idVacancy, that.idVacancy)
        && Objects.equals(idResume, that.idResume)
        && status == that.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(idApplication, idVacancy, idResume, status);
  }
}

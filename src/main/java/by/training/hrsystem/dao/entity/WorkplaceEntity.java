package by.training.hrsystem.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity(name = "WORKPLACE")
public class WorkplaceEntity {

  @Id
  @UuidGenerator
  @Column(name = "W_ID_WORKPALCE", nullable = false)
  private String idWorkplace;

  @Column(name = "R_ID_RESUME", nullable = false)
  private String idResume;

  @Column(name = "W_COMPANY_NAME", nullable = false)
  private String companyName;

  @Column(name = "W_POSITION", nullable = false)
  private String position;

  @Temporal(TemporalType.DATE)
  @Column(name = "W_DATE_BEGIN", nullable = false)
  private Date dateBegin;

  @Column(name = "W_DATE_END")
  private Date dateEnd;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkplaceEntity workplace = (WorkplaceEntity) o;
    return Objects.equals(idWorkplace, workplace.idWorkplace)
        && Objects.equals(idResume, workplace.idResume)
        && Objects.equals(companyName, workplace.companyName)
        && Objects.equals(position, workplace.position)
        && Objects.equals(dateBegin, workplace.dateBegin)
        && Objects.equals(dateEnd, workplace.dateEnd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idWorkplace, idResume, companyName, position, dateBegin, dateEnd);
  }
}

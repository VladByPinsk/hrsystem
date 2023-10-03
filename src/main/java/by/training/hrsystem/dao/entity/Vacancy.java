package by.training.hrsystem.dao.entity;

import by.training.hrsystem.domain.type.ActiveType;
import by.training.hrsystem.domain.type.CurrencyType;
import by.training.hrsystem.domain.type.EmploymentType;
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
@Entity(name = "VACANCY")
public class Vacancy {
  @Id
  @UuidGenerator
  @Column(name = "V_ID_VACANCY", nullable = false)
  private String idVacancy;

  @Column(name = "U_ID_USER", nullable = false)
  private String idUser;

  @Column(name = "V_NAME", nullable = false)
  private String name;

  @Column(name = "V_LOW_SALARY")
  private Double lowSalary;

  @Column(name = "V_HIGH_SALARY")
  private Double highSalary;

  @Enumerated(EnumType.STRING)
  @Column(name = "V_CURRENCY_TYPE")
  private CurrencyType currencyType;

  @Temporal(TemporalType.DATE)
  @Column(name = "V_PUBLISH_DATE")
  private Date publishDate;

  @Column(name = "V_DESCRIPTION")
  private String description;

  @Column(name = "V_DUTY")
  private String duty;

  @Column(name = "V_CONDITION")
  private String condition;

  @Enumerated(EnumType.STRING)
  @Column(name = "V_EMPLOYMENT_TYPE")
  private EmploymentType employmentType;

  @Enumerated(EnumType.STRING)
  @Column(name = "V_ACTIVE_TYPE")
  private ActiveType activeType;

  @Column(name = "V_IS_HOT_VACANCY")
  private Boolean isHot;

  @Column(name = "V_PATH_TO_FILE")
  private String pathToFile;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vacancy vacancy = (Vacancy) o;
    return Objects.equals(idVacancy, vacancy.idVacancy)
        && Objects.equals(idUser, vacancy.idUser)
        && Objects.equals(name, vacancy.name)
        && Objects.equals(lowSalary, vacancy.lowSalary)
        && Objects.equals(highSalary, vacancy.highSalary)
        && currencyType == vacancy.currencyType
        && Objects.equals(publishDate, vacancy.publishDate)
        && Objects.equals(description, vacancy.description)
        && Objects.equals(duty, vacancy.duty)
        && Objects.equals(condition, vacancy.condition)
        && employmentType == vacancy.employmentType
        && activeType == vacancy.activeType
        && Objects.equals(isHot, vacancy.isHot)
        && Objects.equals(pathToFile, vacancy.pathToFile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        idVacancy,
        idUser,
        name,
        lowSalary,
        highSalary,
        currencyType,
        publishDate,
        description,
        duty,
        condition,
        employmentType,
        activeType,
        isHot,
        pathToFile);
  }
}

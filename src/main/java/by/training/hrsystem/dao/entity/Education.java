package by.training.hrsystem.dao.entity;

import by.training.hrsystem.domain.type.EducationType;
import by.training.hrsystem.domain.type.PostgraduateType;
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
@Entity(name = "EDUCATION")
public class Education {
  @Id
  @UuidGenerator
  @Column(name = "E_ID_EDUCATION", nullable = false)
  private String idEducation;

  @Column(name = "R_ID_RESUME", nullable = false)
  private String idResume;

  @Column(name = "E_INSTITUTION")
  private String institution;

  @Column(name = "E_FACULTY")
  private String faculty;

  @Column(name = "E_DEPARTMENT")
  private String department;

  @Enumerated(EnumType.STRING)
  @Column(name = "E_EDUCATION_TYPE", nullable = false)
  private EducationType educationType;

  @Column(name = "E_COURSE")
  private int course;

  @Temporal(TemporalType.DATE)
  @Column(name = "E_GRAD_YEAR")
  private Date gradYear;

  @Enumerated(EnumType.STRING)
  @Column(name = "E_POST_GRADUATE_TYPE", nullable = false)
  private PostgraduateType postGraduate;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Education education = (Education) o;
    return course == education.course && Objects.equals(idEducation, education.idEducation) &&
        Objects.equals(idResume, education.idResume) &&
        Objects.equals(institution, education.institution) &&
        Objects.equals(faculty, education.faculty) &&
        Objects.equals(department, education.department) &&
        educationType == education.educationType && Objects.equals(gradYear, education.gradYear) &&
        postGraduate == education.postGraduate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(idEducation, idResume, institution, faculty, department, educationType,
        course, gradYear, postGraduate);
  }
}

package by.training.hrsystem.dao.entity;

import by.training.hrsystem.domain.type.InterviewType;
import by.training.hrsystem.domain.type.PassType;
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
@Entity(name = "INTERVIEW")
public class InterviewEntity {

  @Id
  @UuidGenerator
  @Column(name = "I_ID_INTERVIEW", nullable = false)
  private String idInterview;

  @Column(name = "A_ID_APPLICATION", nullable = false)
  private String idApplication;

  @Enumerated(EnumType.STRING)
  @Column(name = "I_INTERVIEW_TYPE", nullable = false)
  private InterviewType interviewType;

  @Temporal(TemporalType.DATE)
  @Column(name = "I_DATE_BEGIN")
  private Date dateBegin;

  @Column(name = "A_FEEDBACK")
  private String feedback;

  @Column(name = "A_IS_FEEDBACK_SHARED")
  private Boolean isFeedbackShared;

  @Temporal(TemporalType.DATE)
  @Column(name = "I_DATE_END")
  private Date dateEnd;

  @Column(name = "I_PASS_TYPE")
  private PassType passType;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InterviewEntity interview = (InterviewEntity) o;
    return Objects.equals(idInterview, interview.idInterview) &&
        Objects.equals(idApplication, interview.idApplication) &&
        interviewType == interview.interviewType &&
        Objects.equals(dateBegin, interview.dateBegin) &&
        Objects.equals(feedback, interview.feedback) &&
        Objects.equals(isFeedbackShared, interview.isFeedbackShared) &&
        Objects.equals(dateEnd, interview.dateEnd) && passType == interview.passType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(idInterview, idApplication, interviewType, dateBegin, feedback,
        isFeedbackShared, dateEnd, passType);
  }
}

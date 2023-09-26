package by.training.hrsystem.domain;

import java.io.Serializable;
import java.util.Date;

import by.training.hrsystem.domain.type.InterviewType;
import by.training.hrsystem.domain.type.PassType;
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
public class Interview implements Serializable {

  private static final long serialVersionUID = -1371940779462637843L;

  private int idInterview;
  private InterviewType interviewType;
  private Date dateBegin;
  private PassType pass;
  private Verify verify;
}

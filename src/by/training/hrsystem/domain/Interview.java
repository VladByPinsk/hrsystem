package by.training.hrsystem.domain;

import java.io.Serializable;
import java.util.Date;

import by.training.hrsystem.domain.type.InterviewType;
import by.training.hrsystem.domain.type.PassType;

public class Interview implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idInterview;
	private InterviewType interviewType;
	private Date dateBegin;
	private PassType pass;

	private Verify verify;

	public Interview() {
		
	}

	public int getIdInterview() {
		return idInterview;
	}

	public void setIdInterview(int idInterview) {
		this.idInterview = idInterview;
	}

	public InterviewType getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(InterviewType interviewType) {
		this.interviewType = interviewType;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public PassType getPass() {
		return pass;
	}

	public void setPass(PassType pass) {
		this.pass = pass;
	}

	public Verify getVerify() {
		return verify;
	}

	public void setVerify(Verify verify) {
		this.verify = verify;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateBegin == null) ? 0 : dateBegin.hashCode());
		result = prime * result + idInterview;
		result = prime * result + ((interviewType == null) ? 0 : interviewType.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((verify == null) ? 0 : verify.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interview other = (Interview) obj;
		if (dateBegin == null) {
			if (other.dateBegin != null)
				return false;
		} else if (!dateBegin.equals(other.dateBegin))
			return false;
		if (idInterview != other.idInterview)
			return false;
		if (interviewType != other.interviewType)
			return false;
		if (pass != other.pass)
			return false;
		if (verify == null) {
			if (other.verify != null)
				return false;
		} else if (!verify.equals(other.verify))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Interview [idInterview=" + idInterview + ", interviewType=" + interviewType + ", dateBegin=" + dateBegin
				+ ", pass=" + pass + ", verify=" + verify + "]";
	}

}

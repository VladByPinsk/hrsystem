package by.training.hrsystem.domain;

import java.io.Serializable;
import by.training.hrsystem.domain.type.EducationType;
import by.training.hrsystem.domain.type.PostgraduateType;

public class Education implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idEducation;
	private String institution;
	private String faculty;
	private String department;
	private EducationType education;
	private int course;
	private int gradYear;
	private PostgraduateType postGraduate;
	private int idResume;

	public Education() {
	}

	public int getIdEducation() {
		return idEducation;
	}

	public void setIdEducation(int idEducation) {
		this.idEducation = idEducation;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public EducationType getEducation() {
		return education;
	}

	public void setEducation(EducationType education) {
		this.education = education;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public int getGradYear() {
		return gradYear;
	}

	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}

	public PostgraduateType getPostGraduate() {
		return postGraduate;
	}

	public void setPostGraduate(PostgraduateType postGraduate) {
		this.postGraduate = postGraduate;
	}

	public int getIdResume() {
		return idResume;
	}

	public void setIdResume(int idResume) {
		this.idResume = idResume;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + course;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((education == null) ? 0 : education.hashCode());
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + gradYear;
		result = prime * result + idEducation;
		result = prime * result + idResume;
		result = prime * result + ((institution == null) ? 0 : institution.hashCode());
		result = prime * result + ((postGraduate == null) ? 0 : postGraduate.hashCode());
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
		Education other = (Education) obj;
		if (course != other.course)
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (education != other.education)
			return false;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (gradYear != other.gradYear)
			return false;
		if (idEducation != other.idEducation)
			return false;
		if (idResume != other.idResume)
			return false;
		if (institution == null) {
			if (other.institution != null)
				return false;
		} else if (!institution.equals(other.institution))
			return false;
		if (postGraduate != other.postGraduate)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Education [idEducation=" + idEducation + ", institution=" + institution + ", faculty=" + faculty
				+ ", department=" + department + ", education=" + education + ", course=" + course + ", gradYear="
				+ gradYear + ", postGraduate=" + postGraduate + ", idResume=" + idResume + "]";
	}

}

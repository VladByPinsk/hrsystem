package by.training.hrsystem.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import by.training.hrsystem.domain.type.ActiveType;
import by.training.hrsystem.domain.type.MilitaryType;

public class Resume implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idResume;
	private String name;
	private Date publishDate;
	private MilitaryType militatyType;
	private ActiveType activeType;

	private User applicant;
	private List<Education> educationList;
	private List<Skill> skillList;
	private List<ResumeLanguage> languageList;
	private List<WorkPlace> workPlaceList;

	public Resume() {
		
	}

	public int getIdResume() {
		return idResume;
	}

	public void setIdResume(int idResume) {
		this.idResume = idResume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public MilitaryType getMilitatyType() {
		return militatyType;
	}

	public void setMilitatyType(MilitaryType militatyType) {
		this.militatyType = militatyType;
	}

	public ActiveType getActiveType() {
		return activeType;
	}

	public void setActiveType(ActiveType activeType) {
		this.activeType = activeType;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	public List<Education> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<Education> educationList) {
		this.educationList = educationList;
	}

	public List<Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<Skill> skillList) {
		this.skillList = skillList;
	}

	public List<ResumeLanguage> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<ResumeLanguage> languageList) {
		this.languageList = languageList;
	}

	public List<WorkPlace> getWorkPlaceList() {
		return workPlaceList;
	}

	public void setWorkPlaceList(List<WorkPlace> workPlaceList) {
		this.workPlaceList = workPlaceList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activeType == null) ? 0 : activeType.hashCode());
		result = prime * result + ((applicant == null) ? 0 : applicant.hashCode());
		result = prime * result + ((educationList == null) ? 0 : educationList.hashCode());
		result = prime * result + idResume;
		result = prime * result + ((languageList == null) ? 0 : languageList.hashCode());
		result = prime * result + ((militatyType == null) ? 0 : militatyType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + ((skillList == null) ? 0 : skillList.hashCode());
		result = prime * result + ((workPlaceList == null) ? 0 : workPlaceList.hashCode());
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
		Resume other = (Resume) obj;
		if (activeType != other.activeType)
			return false;
		if (applicant == null) {
			if (other.applicant != null)
				return false;
		} else if (!applicant.equals(other.applicant))
			return false;
		if (educationList == null) {
			if (other.educationList != null)
				return false;
		} else if (!educationList.equals(other.educationList))
			return false;
		if (idResume != other.idResume)
			return false;
		if (languageList == null) {
			if (other.languageList != null)
				return false;
		} else if (!languageList.equals(other.languageList))
			return false;
		if (militatyType != other.militatyType)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (publishDate == null) {
			if (other.publishDate != null)
				return false;
		} else if (!publishDate.equals(other.publishDate))
			return false;
		if (skillList == null) {
			if (other.skillList != null)
				return false;
		} else if (!skillList.equals(other.skillList))
			return false;
		if (workPlaceList == null) {
			if (other.workPlaceList != null)
				return false;
		} else if (!workPlaceList.equals(other.workPlaceList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Resume [idResume=" + idResume + ", name=" + name + ", publishDate=" + publishDate + ", militatyType="
				+ militatyType + ", activeType=" + activeType + ", applicant=" + applicant + ", educationList="
				+ educationList + ", skillList=" + skillList + ", languageList=" + languageList + ", workPlaceList="
				+ workPlaceList + "]";
	}

}

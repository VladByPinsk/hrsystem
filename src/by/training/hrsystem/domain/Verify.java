package by.training.hrsystem.domain;

import java.io.Serializable;

import by.training.hrsystem.domain.type.PassType;

public class Verify implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idVerify;
	private PassType pass;

	private Vacancy vacancy;
	private Resume resume;

	public Verify() {
		
	}

	public int getIdVerify() {
		return idVerify;
	}

	public void setIdVerify(int idVerify) {
		this.idVerify = idVerify;
	}

	public PassType getPass() {
		return pass;
	}

	public void setPass(PassType pass) {
		this.pass = pass;
	}

	public Vacancy getVacancy() {
		return vacancy;
	}

	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idVerify;
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((resume == null) ? 0 : resume.hashCode());
		result = prime * result + ((vacancy == null) ? 0 : vacancy.hashCode());
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
		Verify other = (Verify) obj;
		if (idVerify != other.idVerify)
			return false;
		if (pass != other.pass)
			return false;
		if (resume == null) {
			if (other.resume != null)
				return false;
		} else if (!resume.equals(other.resume))
			return false;
		if (vacancy == null) {
			if (other.vacancy != null)
				return false;
		} else if (!vacancy.equals(other.vacancy))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Verify [idVerify=" + idVerify + ", pass=" + pass + ", vacancy=" + vacancy + ", resume=" + resume + "]";
	}

}

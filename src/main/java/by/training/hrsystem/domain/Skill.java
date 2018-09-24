package by.training.hrsystem.domain;

import java.io.Serializable;

import by.training.hrsystem.domain.type.SkillType;

public class Skill implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idSkill;
	private String name;
	private SkillType raiting;
	private int idResume;

	public Skill() {
		
	}

	public int getIdSkill() {
		return idSkill;
	}

	public void setIdSkill(int idSkill) {
		this.idSkill = idSkill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SkillType getRaiting() {
		return raiting;
	}

	public void setRaiting(SkillType raiting) {
		this.raiting = raiting;
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
		result = prime * result + idResume;
		result = prime * result + idSkill;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((raiting == null) ? 0 : raiting.hashCode());
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
		Skill other = (Skill) obj;
		if (idResume != other.idResume)
			return false;
		if (idSkill != other.idSkill)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (raiting != other.raiting)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Skill [idSkill=" + idSkill + ", name=" + name + ", raiting=" + raiting + ", idResume=" + idResume + "]";
	}

}

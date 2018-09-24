package by.training.hrsystem.domain;

import java.io.Serializable;
import java.util.Date;

import by.training.hrsystem.domain.type.ActiveType;
import by.training.hrsystem.domain.type.CurrencyType;
import by.training.hrsystem.domain.type.EmploymentType;
import by.training.hrsystem.domain.type.HotType;

public class Vacancy implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idVacancy;
	private String name;
	private int salary;
	private CurrencyType currency;
	private Date publishDate;
	private String description;
	private String duty;
	private String condition;
	private EmploymentType employmentType;
	private ActiveType active;
	private HotType hotType;
	private String hrEmail;

	public Vacancy() {

	}

	public int getIdVacancy() {
		return idVacancy;
	}

	public void setIdVacancy(int idVacancy) {
		this.idVacancy = idVacancy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public CurrencyType getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public EmploymentType getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(EmploymentType employmentType) {
		this.employmentType = employmentType;
	}

	public ActiveType getActive() {
		return active;
	}

	public void setActive(ActiveType active) {
		this.active = active;
	}

	public HotType getHotType() {
		return hotType;
	}

	public void setHotType(HotType hotType) {
		this.hotType = hotType;
	}

	public String getHrEmail() {
		return hrEmail;
	}

	public void setHrEmail(String hrEmail) {
		this.hrEmail = hrEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((duty == null) ? 0 : duty.hashCode());
		result = prime * result + ((employmentType == null) ? 0 : employmentType.hashCode());
		result = prime * result + ((hotType == null) ? 0 : hotType.hashCode());
		result = prime * result + ((hrEmail == null) ? 0 : hrEmail.hashCode());
		result = prime * result + idVacancy;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + salary;
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
		Vacancy other = (Vacancy) obj;
		if (active != other.active)
			return false;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		if (currency != other.currency)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (duty == null) {
			if (other.duty != null)
				return false;
		} else if (!duty.equals(other.duty))
			return false;
		if (employmentType != other.employmentType)
			return false;
		if (hotType != other.hotType)
			return false;
		if (hrEmail == null) {
			if (other.hrEmail != null)
				return false;
		} else if (!hrEmail.equals(other.hrEmail))
			return false;
		if (idVacancy != other.idVacancy)
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
		if (salary != other.salary)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vacancy [idVacancy=" + idVacancy + ", name=" + name + ", salary=" + salary + ", currency=" + currency
				+ ", publishDate=" + publishDate + ", description=" + description + ", duty=" + duty + ", condition="
				+ condition + ", employmentType=" + employmentType + ", active=" + active + ", hotType=" + hotType
				+ ", hrEmail=" + hrEmail + "]";
	}

}

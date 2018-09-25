package by.training.hrsystem.domain.type;

public enum EmploymentType {
	FULL_TIME("full time"), PART_TIME("part time"), CONTRACTUAL("contractual");

	private final String employmentType;

	private EmploymentType(final String employmentType) {
		this.employmentType = employmentType;
	}

	public String getEmploymentType() {
		return employmentType;
	}

}

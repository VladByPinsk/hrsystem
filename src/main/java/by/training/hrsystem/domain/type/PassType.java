package by.training.hrsystem.domain.type;

public enum PassType {
	PASS("pass"), NOT_PASS("not pass"), UNKNOWN("unknown");

	private final String passType;

	private PassType(final String passType) {
		this.passType = passType;
	}

	public String getPassType() {
		return passType;
	}

}

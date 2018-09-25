package by.training.hrsystem.domain.type;

public enum ActiveType {
	ACTIVE("active"), NON_ACTIVE("non active");

	private final String activeType;

	private ActiveType(final String activeType) {
		this.activeType = activeType;
	}

	public String getActiveType() {
		return activeType;
	}

}

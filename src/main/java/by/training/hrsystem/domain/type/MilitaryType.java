package by.training.hrsystem.domain.type;

public enum MilitaryType {

	NOT_SPECIFIED("not specified"), FIT("fit"), NOT_FIT("not fit"), MILITARY_DEPARTMENT(
			"military department"), NOT_BOUND("not bound");

	String millatryType;

	private MilitaryType(final String millatryType) {
		this.millatryType = millatryType;
	}

	public String getMillatryType() {
		return millatryType;
	}

}

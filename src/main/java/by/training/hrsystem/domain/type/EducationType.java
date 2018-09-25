package by.training.hrsystem.domain.type;

public enum EducationType {
	NOT_SPECIFIED("not specified"),
	UNIVERSITY_INCOMPLITE("university incomplete"),
	HIGHER("higher"),
	MASTER("master"),
	PHD("PhD"),
	SECONDARY("secondary"),
	TECHNICAL_SHOOL("technical school"),
	PHD_CANDIDATE("PhD candidate");
	
	private final String educationType;
	
	private EducationType(final String educationType) {
		this.educationType = educationType;
	}

	public String getEducationType() {
		return educationType;
	}

}

package by.training.hrsystem.domain.type;

public enum LanguageLevelType {
	NOT_SPECIFIED {
		{
			languageLevelType = "not specified";
		}
	},
	A1 {
		{
			languageLevelType = "A1";
		}
	},
	A1_PLUS {
		{
			languageLevelType = "A1 plus";
		}
	},
	A2 {
		{
			languageLevelType = "A2";
		}
	},
	A2_PLUS {
		{
			languageLevelType = "A2 plus";
		}
	},
	B1 {
		{
			languageLevelType = "B1";
		}
	},
	B1_PLUS {
		{
			languageLevelType = "B1 plus";
		}
	},
	B2 {
		{
			languageLevelType = "B2";
		}
	},
	B2_PLUS {
		{
			languageLevelType = "B2 plus";
		}
	},
	C1 {
		{
			languageLevelType = "C1";
		}
	},
	C1_PLUS {
		{
			languageLevelType = "C1 plus";
		}
	},
	C2 {
		{
			languageLevelType = "C2";
		}
	},
	NATIVE_SPEAKER {
		{
			languageLevelType = "Native speaker";
		}
	};

	String languageLevelType;

	public String getLanguageLevelType() {
		return languageLevelType;
	}
}

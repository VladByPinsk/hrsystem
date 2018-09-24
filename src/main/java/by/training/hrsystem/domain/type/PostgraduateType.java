package by.training.hrsystem.domain.type;

public enum PostgraduateType {
	NOT_ASSIGNED {
		{
			postgraduateType = "not assigned";
		}
	},
	ASSIGNED {
		{
			postgraduateType = "assigned";
		}
	},
	TO_BE_ASSIGNED {
		{
			postgraduateType = "to be assigned";
		}
	};

	String postgraduateType;

	public String getPostgraduateType() {
		return postgraduateType;
	}

}

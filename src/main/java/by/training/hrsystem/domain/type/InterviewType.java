package by.training.hrsystem.domain.type;

public enum InterviewType {

	TECHICAL {
		{
			interviewType = "techical";
		}
	},
	PRELIMINARY {
		{
			interviewType = "preliminary";
		}
	};

	String interviewType;

	public String getInterviewType() {
		return interviewType;
	}

}

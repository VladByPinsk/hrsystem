package by.training.hrsystem.domain.type;

public enum EmploymentType {
	FULL_TIME {
		{
			currencyType = "full time";
		}
	},
	PART_TIME {
		{
			currencyType = "part time";
		}
	},
	CONTRACTUAL {
		{
			currencyType = "contractual";
		}
	};

	String currencyType;

	public String getCurrencyType() {
		return currencyType;
	}

}

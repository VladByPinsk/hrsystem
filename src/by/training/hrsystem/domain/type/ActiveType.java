package by.training.hrsystem.domain.type;

public enum ActiveType {
	ACTIVE {
		{
			currencyType = "active";
		}
	},
	NON_ACTIVE {
		{
			currencyType = "non active";
		}
	};

	String currencyType;

	public String getCurrencyType() {
		return currencyType;
	}

}

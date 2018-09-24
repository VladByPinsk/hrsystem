package by.training.hrsystem.domain.type;

public enum HotType {
	HOT {
		{
			currencyType = "hot";
		}
	},
	NON_HOT {
		{
			currencyType = "non hot";
		}
	};

	String currencyType;

	public String getCurrencyType() {
		return currencyType;
	}


}

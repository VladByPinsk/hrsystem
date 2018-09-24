package by.training.hrsystem.domain.type;

public enum CurrencyType {
	RUB {
		{
			currencyType = "rub";
		}
	},
	DOLAR {
		{
			currencyType = "dolar";
		}
	};

	String currencyType;

	public String getCurrencyType() {
		return currencyType;
	}

}

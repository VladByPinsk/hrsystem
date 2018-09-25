package by.training.hrsystem.domain.type;

public enum CurrencyType {
	RUB("rub"), DOLAR("dolar");

	private final String currencyType;

	private CurrencyType(final String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrencyType() {
		return currencyType;
	}

}

package by.training.hrsystem.domain.type;

import lombok.Getter;

@Getter
public enum CurrencyType {
  RUB("rub"),
  DOLLAR("dollar");

  private final String currency;

  CurrencyType(final String currency) {
    this.currency = currency;
  }
}

package by.training.hrsystem.domain.type;

import lombok.Getter;

@Getter
public enum MilitaryType {
  NOT_SPECIFIED("not specified"),
  FIT("fit"),
  NOT_FIT("not fit"),
  MILITARY_DEPARTMENT("military department"),
  NOT_BOUND("not bound");

  final String military;

  MilitaryType(final String military) {
    this.military = military;
  }
}

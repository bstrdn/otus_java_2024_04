package ru.atm;

import lombok.Getter;

@Getter
public enum Denomination {
  THOUSAND(1000,3),
  HUNDRED(100, 2),
  TEN(10, 1);

  private final int value;
  private final int cell;

  Denomination(int value, int cell) {
    this.cell = cell;
    this.value = value;
  }
}

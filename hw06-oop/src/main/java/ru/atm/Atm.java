package ru.atm;

import java.util.Map;

public interface Atm {

  void putBanknote(Map<Denomination, Integer> denomination);

  Map<Denomination, Integer> getBanknote(Integer sum);

  Integer getBalance();
}

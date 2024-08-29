package ru.atm;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtmImpl implements Atm {

  private final Map<Denomination, Integer> deposit = new HashMap<>();


  @Override
  public void putBanknote(Map<Denomination, Integer> denomination) {
    denomination.keySet().forEach(key -> deposit.merge(key, denomination.get(key), Integer::sum));
  }

  @Override
  public Map<Denomination, Integer> getBanknote(Integer sum) {
    Map<Denomination, Integer> withdraw = new HashMap<>();
    for (int i = 0; i < Denomination.values().length; i++) {
      Denomination key = Denomination.values()[i];
      Integer cash = deposit.get(key);
      if (cash != null) {
        int bill = sum / key.getValue();
        withdraw.put(key, bill);
        sum -= bill * key.getValue();
        deposit.put(key, deposit.get(key) - bill);
      }
    }
    return withdraw;
  }

  @Override
  public Integer getBalance() {
    return deposit.keySet().stream()
        .map(den -> deposit.get(den) * den.getValue())
        .mapToInt(i -> i)
        .sum();
  }
}

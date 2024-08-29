package ru.atm;

import java.util.Map;

public class Main {

  public static void main(String[] args) {
    Atm atm = new AtmImpl();
    System.out.println("Balance: " + atm.getBalance());

    atm.putBanknote(Map.of(Denomination.TEN, 2));

    System.out.println("Balance: " + atm.getBalance());

    atm.putBanknote(Map.of(Denomination.TEN, 1));

    System.out.println("Balance: " + atm.getBalance());

    atm.getBanknote(10);

    System.out.println("Balance: " + atm.getBalance());

    atm.putBanknote(Map.of(Denomination.HUNDRED, 10));

    atm.putBanknote(Map.of(Denomination.THOUSAND, 10));

    System.out.println("Balance: " + atm.getBalance());

    System.out.println(atm.getBanknote(2222));

    System.out.println("Balance: " + atm.getBalance());
  }

}

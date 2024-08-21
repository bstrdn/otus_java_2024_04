package ru.aop;

import ru.aop.data.TestLoggingInterface;
import ru.aop.proxy.Ioc;

public class Demo {

  public static void main(String[] args) {
    TestLoggingInterface testLoggingInterface = Ioc.createTestLoggingInterface();
    testLoggingInterface.calculation(6);
    testLoggingInterface.calculation2(6, 3);
    testLoggingInterface.calculation3(6, 0, "Test");
    testLoggingInterface.calculation(6);
    testLoggingInterface.calculation2(6, 3);
    testLoggingInterface.calculation3(6, 0, "Test");
    testLoggingInterface.calculation(6);
    testLoggingInterface.calculation2(6, 3);
    testLoggingInterface.calculation3(6, 0, "Test");
    testLoggingInterface.calculation("Test");
  }
}


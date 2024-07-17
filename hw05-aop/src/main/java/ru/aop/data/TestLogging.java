package ru.aop.data;

import ru.aop.annotation.Log;

public class TestLogging implements TestLoggingInterface {

  @Log
  public void calculation(Integer param) {
  }

  @Log
  @Override
  public void calculation2(Integer param, Integer param2) {

  }

  @Override
  public void calculation3(Integer param, Integer param2, String param3) {

  }

  @Override
  public void calculation(String string) {

  }
}

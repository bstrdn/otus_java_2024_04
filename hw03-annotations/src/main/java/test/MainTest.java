package test;

import annotation.After;
import annotation.Before;
import annotation.Test;
import java.util.Random;

public class MainTest {

  private final Random random = new Random();

  @Before
  public void before() {
    System.out.println("Before");
    if (random.nextBoolean()) {
      throw new RuntimeException();
    }
  }

  @After
  public void after() {
    System.out.println("After");
  }

  @Test
  public void test1() {
    System.out.println("Test 1");
  }

  @Test
  public void test2() {
    System.out.println("Test 2");
    throw new RuntimeException();
  }

  @Test
  public void test3() {
    System.out.println("Test 3");
  }

  @Test
  public void test4() {
    System.out.println("Test 4");
  }
}

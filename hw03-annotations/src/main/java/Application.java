import java.lang.reflect.InvocationTargetException;
import test.MainTest;
import test.TestEngine;

public class Application {

  public static void main(String[] args)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    TestEngine.start(MainTest.class.getCanonicalName());
  }
}

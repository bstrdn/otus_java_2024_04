package ru.aop.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import ru.aop.annotation.Log;
import ru.aop.data.TestLogging;
import ru.aop.data.TestLoggingInterface;

public class Ioc {

  static private final Set<Method> logAnnotatedMethods = new HashSet<>();
  static private final Set<Method> logNotAnnotatedMethods = new HashSet<>();

  private Ioc() {
  }

  public static TestLoggingInterface createTestLoggingInterface() {
    InvocationHandler invocationHandler = new TestInvocationHandler(new TestLogging());

    return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, invocationHandler);
  }

  static class TestInvocationHandler implements InvocationHandler {

    private final TestLogging testLogging;

    public TestInvocationHandler(TestLoggingInterface testLogging) {
      this.testLogging = (TestLogging) testLogging;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (logAnnotatedMethods.contains(method)) {
        printLogMessage(method, args);
        return method.invoke(testLogging, args);
      } else if (logNotAnnotatedMethods.contains(method)) {
        return method.invoke(testLogging, args);
      }

      Method[] declaredMethods = testLogging.getClass().getDeclaredMethods();
      for (Method m : declaredMethods) {
        if (m.getName().equals(method.getName())) {
          Class<?>[] parameterTypes = m.getParameterTypes();

          if (parameterTypes.length == method.getParameterTypes().length) {
            for (int i = 0; i < parameterTypes.length; i++) {
              if (parameterTypes[i] == method.getParameterTypes()[i]) {
                Annotation[] declaredAnnotations = m.getDeclaredAnnotations();
                for (Annotation annotation : declaredAnnotations) {
                  if (annotation instanceof Log) {
                    logAnnotatedMethods.add(method);
                    break;
                  }
                }
              }
            }
          }
        }
      }

      if (logAnnotatedMethods.contains(method)) {
        printLogMessage(method, args);
      } else
        logNotAnnotatedMethods.add(method);
      return method.invoke(testLogging, args);
    }

    @Override
    public String toString() {
      return "DemoInvocationHandler{" + "myClass=" + testLogging + '}';
    }
  }

  static private void printLogMessage(Method method, Object[] args) {
    System.out.printf("executed method: %s, param: %s \n", method.getName(), Arrays.toString(args));
  }
}

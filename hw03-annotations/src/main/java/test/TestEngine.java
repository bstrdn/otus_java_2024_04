package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestEngine {

  public static void start(String classPath)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    Map<String, Set<Method>> methods = new HashMap<>();
    Class<?> clazz = Class.forName(classPath);
    Method[] declaredMethods = clazz.getDeclaredMethods();
    for (Method method : declaredMethods) {
      Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
      for (Annotation annotation : declaredAnnotations) {
        if (methods.containsKey(annotation.annotationType().getName())) {
          Set<Method> methodSet = methods.get(annotation.annotationType().getName());
          methodSet.add(method);
        } else {
          Set<Method> methodSet = new HashSet<>();
          methodSet.add(method);
          methods.put(annotation.annotationType().getName(), methodSet);
        }
      }
    }

    Set<Method> tests = methods.get("annotation.Test");
    Constructor<?> constructor = clazz.getDeclaredConstructor();

    int success = 0;

    for (Method testMethod : tests) {
      Object testObject = constructor.newInstance();
      boolean beforeSuccess = before(methods.get("annotation.Before"), testObject);
      if (beforeSuccess) {
        boolean testSuccess = testInvoke(testMethod, testObject);
        if (testSuccess) {
          success++;
        }
      }
      after(methods.get("annotation.After"), testObject);
    }

    log.info("Tests run: {}, Successfully: {}, Failures: {}", tests.size(), success,
        tests.size() - success);

  }

  private static boolean before(Set<Method> beforeMethods, Object o) {
    for (Method m : beforeMethods) {
      try {
        m.invoke(o);
      } catch (IllegalAccessException | InvocationTargetException e) {
        return false;
      }
    }
    return true;
  }

  private static boolean testInvoke(Method method, Object o) {
    try {
      method.invoke(o);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  private static void after(Set<Method> afterMethods, Object o) {
    for (Method m : afterMethods) {
      try {
        m.invoke(o);
      } catch (Exception e) {

      }
    }
  }
}

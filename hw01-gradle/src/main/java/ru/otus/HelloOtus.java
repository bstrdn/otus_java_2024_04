package ru.otus;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

public class HelloOtus {

  public static void main(String[] args) {
    String s = "Hello, Otus!";
    Iterable<String> split = Splitter.on(CharMatcher.anyOf(" ,!")).omitEmptyStrings().split(s);
    split.forEach(System.out::println);
  }
}

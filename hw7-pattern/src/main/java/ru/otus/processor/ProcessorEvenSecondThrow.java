package ru.otus.processor;

import java.time.LocalDateTime;
import ru.otus.model.Message;

public class ProcessorEvenSecondThrow implements Processor {

  @Override
  public Message process(Message message) {
    LocalDateTime now = LocalDateTime.now();
    if (now.getSecond() % 2 != 0) {
      int mills = (1_000_000_000 - now.getNano()) / 1_000_000 + 1;
      try {
        Thread.sleep(mills);
        System.out.println("Sleep " + mills);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    throw new EvenException("Even Second");
  }
}
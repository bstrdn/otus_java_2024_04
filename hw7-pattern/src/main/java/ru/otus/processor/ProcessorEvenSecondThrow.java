package ru.otus.processor;

import lombok.Getter;
import ru.otus.model.Message;
import ru.otus.processor.homework.DateTimeProvider;

@Getter
public class ProcessorEvenSecondThrow implements Processor {

  private final DateTimeProvider dateTimeProvider;

  public ProcessorEvenSecondThrow(DateTimeProvider dateTimeProvider) {
    this.dateTimeProvider = dateTimeProvider;
  }


  @Override
  public Message process(Message message) {
    if (dateTimeProvider.getDate().getSecond() % 2 == 0) {
      throw new EvenException("Even Second");
    }
    return message;
  }
}
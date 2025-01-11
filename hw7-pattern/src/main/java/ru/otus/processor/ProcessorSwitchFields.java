package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorSwitchFields implements Processor {

  @Override
  public Message process(Message message) {
    String temp = message.getField12();
    return message.toBuilder().field12(message.getField11()).field11(temp).build();
  }
}

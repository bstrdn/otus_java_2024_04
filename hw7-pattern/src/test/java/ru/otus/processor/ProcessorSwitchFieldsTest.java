package ru.otus.processor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import ru.otus.handler.ComplexProcessor;
import ru.otus.model.Message;

class ProcessorSwitchFieldsTest {

  @Test
  void process() {
    var field11 = "field11";
    var field12 = "field12";
    var processor = new ProcessorSwitchFields();
    var complexProcessor = new ComplexProcessor(List.of(processor), e -> fail());
    var message = new Message.Builder(1L).field11(field11).field12(field12).build();
    Message handle = complexProcessor.handle(message);
    assertEquals(field11, handle.getField12());
    assertEquals(field12, handle.getField11());
  }
}
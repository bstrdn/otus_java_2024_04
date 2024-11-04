package ru.otus.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import ru.otus.handler.ComplexProcessor;
import ru.otus.model.Message;

class ProcessorEvenSecondThrowTest {

  @BeforeEach
  void setUp() throws InterruptedException {
    Thread.sleep((int) (Math.random() * 1500));
  }

  @RepeatedTest(10)
  void process() {
    var message = new Message.Builder(1L).build();
    Processor processor1 = new ProcessorEvenSecondThrow();
    var processors = List.of(processor1);
    Consumer<Exception> checkEvenConsumer = e -> {
      assertInstanceOf(EvenException.class, e);
      assertEquals(0, LocalDateTime.now().getSecond() % 2);
    };
    var complexProcessor = new ComplexProcessor(processors, checkEvenConsumer);
    complexProcessor.handle(message);
  }
}
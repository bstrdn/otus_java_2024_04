package ru.otus.processor;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.otus.handler.ComplexProcessor;
import ru.otus.model.Message;

class ProcessorEvenSecondThrowTest {

  @BeforeEach
  void setUp() throws InterruptedException {
    Thread.sleep((int) (Math.random() * 1500));
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3, 44, 55})
  void process(int second) {
    LocalDateTime evenSecondDateTime = LocalDateTime.now().withSecond(second);
    var message = new Message.Builder(1L).build();
    Processor processor1 = new ProcessorEvenSecondThrow(() -> evenSecondDateTime);
    var processors = List.of(processor1);
    Consumer<Exception> checkEvenConsumer = e -> {
      if (e instanceof EvenException) {
        throw (EvenException) e;
      }
    };
    var complexProcessor = new ComplexProcessor(processors, checkEvenConsumer);
    if (second % 2 == 0) {
      assertThrows(EvenException.class, () -> complexProcessor.handle(message));
    }
  }
}
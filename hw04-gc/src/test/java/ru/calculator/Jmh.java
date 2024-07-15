package ru.calculator;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Jmh {

  public static void main(String[] args) throws RunnerException {
    var opt = new org.openjdk.jmh.runner.options.OptionsBuilder()
        .include(Jmh.class.getSimpleName())
        .forks(10)
        .warmupTime(TimeValue.seconds(10))
        .build();
    new org.openjdk.jmh.runner.Runner(opt).run();
  }

  private static final long counter = 100_000_000;
  Summator summator;

  @Setup
  public void setup() {
    summator = new Summator();
  }

  @Benchmark
  public long test() {
    for (var idx = 0; idx < counter; idx++) {
      var data = new Data(idx);
      summator.calc(data);

      if (idx % 10_000_000 == 0) {
        System.out.println(LocalDateTime.now() + " current idx:" + idx);
      }
    }

    System.out.println(summator.getPrevValue());
    System.out.println(summator.getPrevPrevValue());
    System.out.println(summator.getSumLastThreeValues());
    System.out.println(summator.getSomeValue());
    System.out.println(summator.getSum());
    return summator.getSum();
  }
}

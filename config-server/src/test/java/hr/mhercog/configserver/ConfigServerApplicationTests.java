package hr.mhercog.configserver;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Execution(ExecutionMode.CONCURRENT)
@RequiredArgsConstructor
public class ConfigServerApplicationTests {

  private final ApplicationContext applicationContext;

  @Test
  public void contextLoads() {
    Assert.notNull(this.applicationContext,
        "[Assertion failed] - applicationContext argument is required; it must not be null");
  }
}

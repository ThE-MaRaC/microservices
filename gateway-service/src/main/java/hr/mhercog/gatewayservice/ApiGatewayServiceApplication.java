package hr.mhercog.gatewayservice;

import io.micrometer.core.instrument.Tags;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.web.reactive.server.WebFluxTagsContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.server.ServerWebExchange;

@SpringBootApplication
public class ApiGatewayServiceApplication {

  @Bean
  WebFluxTagsContributor uriTagContributorForConnectionProblems() {
    return (exchange, ex) -> willExchangeUriBeingParsed(exchange) ? Tags.empty()
        : Tags.of("uri", exchange.getRequest().getPath().value());
  }

  private static boolean willExchangeUriBeingParsed(final ServerWebExchange exchange) {
    return exchange.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE) != null;
  }

  public static void main(final String[] args) {
    SpringApplication.run(ApiGatewayServiceApplication.class, args);
  }
}

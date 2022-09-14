package hr.mhercog.usermanagementservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  public static final String API_USER_MANAGEMENT = "Alarm Management";

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI().info(
            new Info().title("User Management").description("User Management").version("1.0").license(
                new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
        .components(this.components())
        .addSecurityItem(new SecurityRequirement().addList("Authorization"));
  }

  private Components components() {
    final SecurityScheme scheme = new SecurityScheme().name("Basic").type(Type.HTTP).in(In.HEADER)
        .scheme("basic");
    return new Components().addSecuritySchemes("Basic", scheme);
  }

  @Bean
  public GroupedOpenApi alarmApi() {
    return GroupedOpenApi.builder().group("User Management").pathsToMatch("/service/**").build();
  }
}

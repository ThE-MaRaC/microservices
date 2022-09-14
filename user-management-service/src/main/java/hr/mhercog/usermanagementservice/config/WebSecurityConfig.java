package hr.mhercog.usermanagementservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@Configuration
@EnableWebSecurity
@EnableJdbcHttpSession
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    http.authorizeRequests(
            auth -> auth.requestMatchers(EndpointRequest.toAnyEndpoint(), PathRequest.toH2Console())
                .permitAll().antMatchers("/service/register", "/v3/api-docs/**", "/swagger-ui/**",
                    "/swagger-ui.html").permitAll().anyRequest().fullyAuthenticated())
        .exceptionHandling()
        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and().logout()
        .permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/service/logout", "POST"))
        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
        .deleteCookies("SESSION").invalidateHttpSession(true).and().formLogin()
        .loginPage("/service/login").and().httpBasic().and().cors().disable().csrf().disable()
        .userDetailsService(this.userDetailsService).headers().frameOptions().disable();
    return http.build();
  }
}

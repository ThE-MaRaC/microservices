package hr.mhercog.usermanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserManagementServiceApplication {

  public static void main(final String[] args) {
    SpringApplication.run(UserManagementServiceApplication.class, args);
  }
}

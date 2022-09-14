package hr.mhercog.usermanagementservice.controller;

import hr.mhercog.usermanagementservice.config.OpenApiConfig;
import hr.mhercog.usermanagementservice.model.Role;
import hr.mhercog.usermanagementservice.model.User;
import hr.mhercog.usermanagementservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  @Operation(tags = OpenApiConfig.API_USER_MANAGEMENT, summary = "Register new user", description = "Register new user")
  public ResponseEntity<?> register(@RequestBody final User user) {
    if (this.userService.findByUsername(user.getUsername()) != null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    user.setRole(Role.USER);
    return new ResponseEntity<>(this.userService.save(user), HttpStatus.CREATED);
  }

  @GetMapping("/login")
  @Operation(tags = OpenApiConfig.API_USER_MANAGEMENT, summary = "Login user", description = "Login user")
  public ResponseEntity<?> login(final Principal principal) {
    if (principal == null || principal.getName() == null) {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return ResponseEntity.ok(this.userService.findByUsername(principal.getName()));
  }

  @GetMapping("/user/{id}")
  @Operation(tags = OpenApiConfig.API_USER_MANAGEMENT, summary = "Finds user identified by an id", description = "Finds user identified by an id")
  public ResponseEntity<?> getUser(@PathVariable final Long id) {
    return ResponseEntity.ok(this.userService.findById(id));
  }

  @GetMapping("/users")
  @Operation(tags = OpenApiConfig.API_USER_MANAGEMENT, summary = "Returns all users", description = "Returns all users")
  public ResponseEntity<?> getUsers() {
    return ResponseEntity.ok(this.userService.findAll());
  }
}

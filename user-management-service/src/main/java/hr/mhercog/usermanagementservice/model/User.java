package hr.mhercog.usermanagementservice.model;

import lombok.Data;

@Data
public class User {

  private Long id;

  private String name;

  private String username;

  private String password;

  private Role role;

  private String email;
}

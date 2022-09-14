package hr.mhercog.usermanagementservice.service;

import hr.mhercog.usermanagementservice.mapper.EntityMapper;
import hr.mhercog.usermanagementservice.model.User;
import hr.mhercog.usermanagementservice.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final EntityMapper entityMapper;

  public User save(final User user) {
    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    return this.entityMapper.map(this.userRepository.save(this.entityMapper.map(user)));
  }

  public User findById(final Long id) {
    return this.entityMapper.map(this.userRepository.findById(id).orElse(null));
  }

  public List<User> findAll() {
    return this.entityMapper.map(this.userRepository.findAll());
  }

  public User findByUsername(final String username) {
    return this.entityMapper.map(this.userRepository.findByUsername(username).orElse(null));
  }
}

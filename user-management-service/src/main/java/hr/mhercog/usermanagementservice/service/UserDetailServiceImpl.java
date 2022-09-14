package hr.mhercog.usermanagementservice.service;

import hr.mhercog.usermanagementservice.mapper.EntityMapper;
import hr.mhercog.usermanagementservice.model.User;
import hr.mhercog.usermanagementservice.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  private final EntityMapper entityMapper;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final User user = this.entityMapper.map(
        this.userRepository.findByUsername(username).orElse(null));
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    final Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

    return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
        authorities);
  }
}

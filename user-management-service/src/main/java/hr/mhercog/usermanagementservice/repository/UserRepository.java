package hr.mhercog.usermanagementservice.repository;

import hr.mhercog.usermanagementservice.repository.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);
}

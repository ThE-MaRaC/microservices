package hr.mhercog.usermanagementservice.mapper;

import hr.mhercog.usermanagementservice.model.User;
import hr.mhercog.usermanagementservice.repository.entity.UserEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {

  UserEntity map(User model);

  User map(UserEntity entity);

  List<User> map(List<UserEntity> entity);
}

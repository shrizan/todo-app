package shrizan.com.github.todoapp.mapper;

import org.mapstruct.*;
import shrizan.com.github.todoapp.domain.user.User;
import shrizan.com.github.todoapp.dto.UserDTO;
import shrizan.com.github.todoapp.dto.UsersDTO;

import java.util.List;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Mapper
public interface UserMapper {
    UserDTO toDTO(User user);

    List<UserDTO> toDTOList(List<User> users);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updateItem(UserDTO userDTO, @MappingTarget User user);

    User toObject(UserDTO userDTO);
}

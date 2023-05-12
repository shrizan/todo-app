package shrizan.com.github.todoapp.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shrizan.com.github.todoapp.dto.UserDTO;
import shrizan.com.github.todoapp.exception.EntityNotFoundException;
import shrizan.com.github.todoapp.mapper.UserMapper;
import shrizan.com.github.todoapp.service.UserService;

import java.util.List;
import java.util.UUID;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserMapper userMapper;
    private final UserService userService;

    public UserDTO create(UserDTO userDTO){
        return userMapper.toDTO(userService.create(userMapper.toObject(userDTO)));
    }
    public UserDTO findById(UUID id){
        return userService.findById(id).map(userMapper::toDTO).orElseThrow(()->new EntityNotFoundException("item_not_found"));
    }

    public List<UserDTO> findAll(){
        return userMapper.toDTOList(userService.findAll());
    }

    public UserDTO findByUserName(String userName){
        return userService.findByUserName(userName).map(userMapper::toDTO).orElseThrow(()->new EntityNotFoundException("item_not_found"));
    }
}

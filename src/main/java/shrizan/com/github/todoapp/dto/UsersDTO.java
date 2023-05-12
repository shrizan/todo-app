package shrizan.com.github.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Data
@AllArgsConstructor
public class UsersDTO {
    private final List<UserDTO> users;
}

package shrizan.com.github.todoapp.dto;

import lombok.Data;

import java.util.UUID;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Data
public class UserDTO {
    private String id;
    private String userName;
    private String password;
}

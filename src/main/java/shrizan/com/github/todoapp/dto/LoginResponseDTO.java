package shrizan.com.github.todoapp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@RequiredArgsConstructor
@Getter
public class LoginResponseDTO {
    private final String userName;
    private final String token;

}

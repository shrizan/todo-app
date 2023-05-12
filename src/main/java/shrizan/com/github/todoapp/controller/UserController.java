package shrizan.com.github.todoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shrizan.com.github.todoapp.dto.LoginResponseDTO;
import shrizan.com.github.todoapp.dto.UserDTO;
import shrizan.com.github.todoapp.facade.UserFacade;
import shrizan.com.github.todoapp.utils.JWTUtil;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final JWTUtil jwtUtil;
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        UserDTO databaseUser = userFacade.findByUserName(userDTO.getUserName());
        if (passwordEncoder.matches( userDTO.getPassword(),databaseUser.getPassword())) {
            return ResponseEntity.ok(new LoginResponseDTO(userDTO.getUserName(), jwtUtil.generateJWTToken(databaseUser)));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return ResponseEntity.ok(userFacade.create(userDTO));
    }

    @GetMapping("/hello")
    public String helloThere(){
        return "Hi there";
    }
}

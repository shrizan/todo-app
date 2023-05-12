package shrizan.com.github.todoapp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import shrizan.com.github.todoapp.domain.user.User;
import shrizan.com.github.todoapp.repos.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Component
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public User create(User item) {
        return userRepo.save(item);
    }

    public Optional<User> findById(UUID id) {
        return userRepo.findById(id);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User update(User item) {
        return userRepo.save(item);
    }

    public void delete(UUID uuid) {
        userRepo.deleteById(uuid);
    }

    public Optional<User> findByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }

    public Optional<User> getCurrentUser() {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUserName(userName);
    }
}

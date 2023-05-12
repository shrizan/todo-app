package shrizan.com.github.todoapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import shrizan.com.github.todoapp.domain.user.User;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);
}

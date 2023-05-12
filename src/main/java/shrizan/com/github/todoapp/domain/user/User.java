package shrizan.com.github.todoapp.domain.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private String id;

    @Column(unique = true)
    private String userName;

    @Column
    private String password;

    @PrePersist
    public void setId(){
        this.id = UUID.randomUUID().toString();
    }
}

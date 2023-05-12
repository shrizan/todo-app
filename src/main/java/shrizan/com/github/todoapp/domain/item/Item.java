package shrizan.com.github.todoapp.domain.item;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import shrizan.com.github.todoapp.domain.user.User;

import java.util.UUID;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Data
@Entity
@Table(name = "item")
public class Item {
    @Id
    private String id;

    @Column
    private String title;

    @Column
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void setId(){
        this.id = UUID.randomUUID().toString();
    }
}

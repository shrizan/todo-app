package shrizan.com.github.todoapp.dto;

import lombok.Data;

/**
 * @author Shreejan Acharya on 5/10/2023
 * @project todo-app
 */
@Data
public class ItemDTO {
    private String id;
    private String title;
    private boolean completed;
}

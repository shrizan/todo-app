package shrizan.com.github.todoapp.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import shrizan.com.github.todoapp.domain.item.Item;

import java.util.List;

/**
 * @author Shreejan Acharya on 5/10/2023
 * @project todo-app
 */
public interface ItemRepo extends JpaRepository<Item,String> {
    Page<Item> findByCompletedAndUser_Id(boolean completed, String userId, Pageable pageable);
}

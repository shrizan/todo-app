package shrizan.com.github.todoapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shrizan.com.github.todoapp.domain.item.Item;
import shrizan.com.github.todoapp.domain.user.User;
import shrizan.com.github.todoapp.exception.EntityNotFoundException;
import shrizan.com.github.todoapp.repos.ItemRepo;

/**
 * @author Shreejan Acharya on 5/10/2023
 * @project todo-app
 */
@Component
@RequiredArgsConstructor
@Transactional
public class ItemService {
    private final ItemRepo itemRepo;
    private final UserService userService;

    public Page<Item> search(boolean completed, Pageable pageable) {
        return userService.getCurrentUser().map(user->itemRepo.findByCompletedAndUser_Id(completed, user.getId(), pageable))
                .orElseGet(Page::empty);
    }

    public Item create(Item item) {
        return userService
                .getCurrentUser()
                .map(user -> create(user, item))
                .orElseThrow(() -> new EntityNotFoundException("item_not_found"));
    }

    private Item create(User user, Item item) {
        item.setUser(user);
        return itemRepo.save(item);
    }

}

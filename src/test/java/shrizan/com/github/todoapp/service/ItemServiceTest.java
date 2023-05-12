package shrizan.com.github.todoapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import shrizan.com.github.todoapp.domain.item.Item;
import shrizan.com.github.todoapp.domain.user.User;
import shrizan.com.github.todoapp.repos.ItemRepo;

import java.util.List;
import java.util.Optional;

/**
 * @author Shreejan Acharya on 5/10/2023
 * @project todo-app
 */

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    @Mock
    private ItemRepo itemRepo;

    @Mock
    private UserService userService;
    @InjectMocks
    private ItemService itemService;

    @Test
    void create(){
        Item item = new Item();
        User user = new User();
        Mockito.when(itemRepo.save(item)).thenReturn(item);
        Mockito.when(userService.getCurrentUser()).thenReturn(Optional.of(user));
        Item result = itemService.create(item);
        Assertions.assertEquals(item,result);
        Mockito.verify(itemRepo).save(item);
    }

    @Test
    void search(){
        User user = new User();
        Mockito.when(userService.getCurrentUser()).thenReturn(Optional.of(user));
        Item item1 = new Item();
        Item item2 = new Item();
        Page<Item> items = new PageImpl<Item>(List.of(item1,item2), PageRequest.of(1,10),2);
        Mockito.when(itemRepo.findByCompletedAndUser_Id(true,user.getId(),PageRequest.of(1,10))).thenReturn(items);
        Page<Item> itemsReturnFromService = itemService.search(true,PageRequest.of(1,10));
        Assertions.assertEquals(items,itemsReturnFromService);
        Mockito.verify(itemRepo).findByCompletedAndUser_Id(true,user.getId(),PageRequest.of(1,10));
    }
}

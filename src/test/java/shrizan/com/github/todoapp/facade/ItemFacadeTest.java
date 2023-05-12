package shrizan.com.github.todoapp.facade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import shrizan.com.github.todoapp.domain.item.Item;
import shrizan.com.github.todoapp.dto.ItemDTO;
import shrizan.com.github.todoapp.mapper.ItemMapper;
import shrizan.com.github.todoapp.service.ItemService;

/**
 * @author Shreejan Acharya on 5/11/2023
 * @project todo-app
 */
@ExtendWith(MockitoExtension.class)
public class ItemFacadeTest {
    @Mock
    private ItemService itemService;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemFacade itemFacade;

    @Test
    void create(){
        Item item = new Item();
        ItemDTO itemDTO = new ItemDTO();

        Mockito.when(itemService.create(item)).thenReturn(item);
        Mockito.when(itemMapper.toDTO(item)).thenReturn(itemDTO);
        Mockito.when(itemMapper.toObject(itemDTO)).thenReturn(item);

        ItemDTO dtoResult = itemFacade.create(itemDTO);
        Assertions.assertEquals(itemDTO,dtoResult);
        Mockito.verify(itemService).create(item);
        Mockito.verify(itemMapper).toDTO(item);
        Mockito.verify(itemMapper).toObject(itemDTO);
    }

    @Test
    void search(){
        
    }
}

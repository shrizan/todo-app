package shrizan.com.github.todoapp.mapper;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import shrizan.com.github.todoapp.domain.item.Item;
import shrizan.com.github.todoapp.dto.ItemDTO;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Shreejan Acharya on 5/12/2023
 * @project todo-app
 */
public class ItemMapperTest {
    ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

    @Test
    public void toDTO(){
        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setTitle("item1");
        item.setCompleted(true);
        ItemDTO itemDTO = itemMapper.toDTO(item);
        assertAll("item",
                () -> assertEquals(item.getId(), itemDTO.getId()),
                () -> assertEquals(item.getTitle(), itemDTO.getTitle()),
                () -> assertEquals(item.isCompleted(), itemDTO.isCompleted())
        );
    }
}

package shrizan.com.github.todoapp.mapper;

import org.mapstruct.Mapper;
import shrizan.com.github.todoapp.domain.item.Item;
import shrizan.com.github.todoapp.dto.ItemDTO;

import java.util.List;

/**
 * @author Shreejan Acharya on 5/10/2023
 * @project todo-app
 */
@Mapper
public interface ItemMapper {
    ItemDTO toDTO(Item item);
    List<ItemDTO> toDTOS(List<Item> items);
    Item toObject(ItemDTO itemDTO);
}

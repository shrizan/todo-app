package shrizan.com.github.todoapp.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import shrizan.com.github.todoapp.domain.item.Item;
import shrizan.com.github.todoapp.dto.ItemDTO;
import shrizan.com.github.todoapp.mapper.ItemMapper;
import shrizan.com.github.todoapp.service.ItemService;

/**
 * @author Shreejan Acharya on 5/10/2023
 * @project todo-app
 */
@Component
@RequiredArgsConstructor
public class ItemFacade {
    private final ItemService itemService;
    private final ItemMapper mapper;

    public Page<ItemDTO> search(boolean completed, Pageable pageable) {
        return itemService.search(completed, pageable).map(mapper::toDTO);
    }

    public ItemDTO create(ItemDTO itemDTO) {
        Item item = mapper.toObject(itemDTO);
        return mapper.toDTO(itemService.create(item));
    }
}

package shrizan.com.github.todoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shrizan.com.github.todoapp.dto.ItemDTO;
import shrizan.com.github.todoapp.facade.ItemFacade;


/**
 * @author Shreejan Acharya on 5/10/2023
 * @project todo-app
 */
@RequestMapping("/item")
@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemFacade itemFacade;

    @GetMapping
    public ResponseEntity<Page<ItemDTO>> search(@RequestParam(value = "completed", defaultValue = "false") boolean completed,
                                                @RequestParam(value = "page", defaultValue = "1") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(this.itemFacade.search(completed, PageRequest.of(page, size)));
    }

    @PostMapping
    public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO item) {
        return new ResponseEntity<>(itemFacade.create(item), HttpStatus.CREATED);
    }
}

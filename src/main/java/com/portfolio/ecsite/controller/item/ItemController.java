package com.portfolio.ecsite.controller.item;

import com.portfolio.ecsite.controller.ItemsApi;
import com.portfolio.ecsite.model.ItemDTO;
import com.portfolio.ecsite.model.ItemForm;
import com.portfolio.ecsite.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ItemController implements ItemsApi {

    private final ItemService itemService;
    @Override
    public ResponseEntity<ItemDTO> showItem(Long itemId) {

        var entity = itemService.find(itemId);

        var dto = new ItemDTO();
        //EntityをDTOに詰めかえる
        dto.setId(entity.getItemId());
        dto.setItemName(entity.getItemName());
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ItemDTO> createItem(ItemForm form) {
        var entity = itemService.create(form.getItemName());

        var dto = new ItemDTO();
        dto.setId(entity.getItemId());
        dto.setItemName(entity.getItemName());

        return ResponseEntity
                .created(URI.create("/items/" + dto.getId()))
                .body(dto);
    }
}

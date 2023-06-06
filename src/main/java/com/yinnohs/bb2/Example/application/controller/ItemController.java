package com.yinnohs.bb2.Example.application.controller;

import com.yinnohs.bb2.Example.application.dto.item.CreateItemDTO;
import com.yinnohs.bb2.Example.application.dto.item.ItemGetDTO;
import com.yinnohs.bb2.Example.application.dto.item.UpdateItemDTO;
import com.yinnohs.bb2.Example.application.enums.ItemState;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.Item;
import com.yinnohs.bb2.Example.application.service.ItemService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@NoArgsConstructor
@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    private ItemService itemService;
    private BaseMapper mapper;

    @Autowired
    public ItemController(ItemService itemService, BaseMapper mapper) {
        this.itemService = itemService;
        this.mapper = mapper;
    }


    @GetMapping("/all")
    public ResponseEntity<List<ItemGetDTO>> findAllItems(@RequestParam(name = "state") String stateStr) {
        try {
            ItemState state = ItemState.getFromString(stateStr);
            List<Item> items = null;
            if (state != null) {
                items = this.itemService.findAllItems();
            }

            items = this.itemService.findAllItemsByState(state);

            List<ItemGetDTO> response = new ArrayList<>();

            for (Item item : items) {
                ItemGetDTO itemGetDTO = this.mapper.itemToGetDTO(item);
                response.add(itemGetDTO);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemGetDTO> findOneItemById(@PathVariable("itemId") Long itemId) {
        try {
            if (itemId == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            Item item = this.itemService.findItemById(itemId);

            ItemGetDTO response = this.mapper.itemToGetDTO(item);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<ItemGetDTO>> createItem(@RequestBody() CreateItemDTO createItemDTO) {
        try {
            if (
                    createItemDTO == null
                            && createItemDTO.getCode() == null
                            && createItemDTO.getDescription().isEmpty()
                            || createItemDTO.getDescription() == null
            ) {
              return CompletableFuture.supplyAsync(()->  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
            }

            CompletableFuture<Item> createdItemFuture = this.itemService.createItem(createItemDTO);

           return createdItemFuture.thenApply((item)-> {
                ItemGetDTO response = this.mapper.itemToGetDTO(item);

                return new ResponseEntity<>(response, HttpStatus.OK);
            });

        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.supplyAsync(()-> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ItemGetDTO> updateItem(@RequestBody()UpdateItemDTO updateItemDTO){
        try {
            if(updateItemDTO == null){
                return  new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }

            Item item = this.itemService.updateItem(updateItemDTO);
            ItemGetDTO response = this.mapper.itemToGetDTO(item);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

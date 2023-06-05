package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.enums.ItemState;
import com.yinnohs.bb2.Example.application.model.Item;
import com.yinnohs.bb2.Example.application.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    public Item createItem (Item item){
        if (item == null){
            return null;
        }

        return this.repository.save(item);
    }

    public Item findItemById (Long itemId){
        if (itemId == null){
            return null;
        }

        return this.repository.findById(itemId).orElse(null);
    }

    public List<Item> findAllItems(){
        return this.repository.findAll();
    }

    public List<Item> findAllItemsByState(ItemState state){
        return  this.repository.findItemsByItemState(state);
    }

    public Item updateItem(){
        return  null;
    }

}

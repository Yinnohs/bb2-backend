package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.item.UpdateItemDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.enums.ItemState;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.Item;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;
    @Autowired
    private BaseMapper mapper;

    public Item createItem (Item item){
        if (item == null){
            return null;
        }
        LocalDate currentDate = LocalDate.now();

        item.setCreationDate(currentDate);

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

    public Item updateItem(UpdateItemDTO updateItemDTO){
        if (updateItemDTO == null){
            return null;
        }

        Item currentItem = this.findItemById(updateItemDTO.getItemId());

        ItemState itemState = updateItemDTO.getItemState();
        if(itemState != null){
            currentItem.setItemState(updateItemDTO.getItemState());
        }

        String description  = updateItemDTO.getDescription();
        if(description != null && !description.isEmpty()){
            currentItem.setDescription(description);
        }

        LocalDate creationDate = updateItemDTO.getCreationDate();
        if(creationDate != null){
            currentItem.setCreationDate(creationDate);
        }

        UserGetDTO creator = updateItemDTO.getCreator();
        if (creator != null ){
            User user = this.mapper.userGetDTOToUser(creator);
            currentItem.setCreator(user);
        }

        //TODO: add here logic to transform suppliers and price

        return currentItem;

    }

}

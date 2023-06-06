package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.item.UpdateItemDTO;
import com.yinnohs.bb2.Example.application.dto.pricereduction.PriceReductionGetDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.SupplierGetDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.enums.ItemState;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.Item;
import com.yinnohs.bb2.Example.application.model.PriceReduction;
import com.yinnohs.bb2.Example.application.model.Supplier;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
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

        Collection<SupplierGetDTO> supplierGetDTOS = updateItemDTO.getSuppliers();
        if (supplierGetDTOS != null && supplierGetDTOS.size() > 0){
            Collection<Supplier> suppliers = new HashSet<>();
            for (SupplierGetDTO supplierGetDTO : supplierGetDTOS) {
                Supplier supplier = this.mapper.supplierGetDTOToSupplier(supplierGetDTO);
                suppliers.add(supplier);
            }
            currentItem.setSuppliers(suppliers);
        }

        Collection<PriceReductionGetDTO> priceReductionGetDTOS = updateItemDTO.getPriceReductions();
        if (priceReductionGetDTOS != null && priceReductionGetDTOS.size() > 0){
            Collection<PriceReduction> priceReductions = new HashSet<>();
            for (PriceReductionGetDTO priceReductionGetDTO : priceReductionGetDTOS) {
                PriceReduction priceReduction = this.mapper.priceReductionGetDTOToPriceReduction(priceReductionGetDTO);
                priceReductions.add(priceReduction);
            }
            currentItem.setPriceReductions(priceReductions);
        }

        return currentItem;

    }

}

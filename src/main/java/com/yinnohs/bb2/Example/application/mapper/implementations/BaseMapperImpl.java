package com.yinnohs.bb2.Example.application.mapper.implementations;

import com.yinnohs.bb2.Example.application.dto.item.CreateItemDTO;
import com.yinnohs.bb2.Example.application.dto.item.ItemGetDTO;
import com.yinnohs.bb2.Example.application.dto.pricereduction.CreatePriceReductionDTO;
import com.yinnohs.bb2.Example.application.dto.pricereduction.PriceReductionGetDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.CreateSupplierDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.SupplierGetDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserCreateDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.Item;
import com.yinnohs.bb2.Example.application.model.PriceReduction;
import com.yinnohs.bb2.Example.application.model.Supplier;
import com.yinnohs.bb2.Example.application.model.User;

import java.util.Collection;
import java.util.HashSet;

public class BaseMapperImpl implements BaseMapper  {
    @Override
    public UserGetDTO userToGetDTO(User user) {
        if (user == null){
            return null;
        }

        UserGetDTO  userDto = new UserGetDTO();
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setCreationDate(user.getCreationDate());
        userDto.setEmail(user.getEmail());
        return userDto;


    }

    @Override
    public User userCreateDTOTouser(UserCreateDTO userCreateDTO) {
        if (userCreateDTO == null){
            return null;
        }

        User  user = new User();
        user.setPassword((userCreateDTO.getPassword()));
        user.setName(userCreateDTO.getName());
        user.setSurname(userCreateDTO.getSurname());
        user.setEmail(userCreateDTO.getEmail());
        return user;
    }

    @Override
    public User userGetDTOToUser(UserGetDTO userGetDTO) {
        if(userGetDTO == null){
            return null;
        }

        User user = new User();
        user.setUserId(userGetDTO.getUserId());
        user.setName(userGetDTO.getName());
        user.setSurname(userGetDTO.getSurname());
        user.setEmail(userGetDTO.getEmail());
        user.setCreationDate(userGetDTO.getCreationDate());

        return user;
    }

    @Override
    public Supplier createSupplierDTOToSupplier(CreateSupplierDTO createSupplierDTO) {
        if (createSupplierDTO == null){
            return  null;
        }

        Supplier supplier = new Supplier();
        supplier.setCountry(createSupplierDTO.getCountry());
        supplier.setName(createSupplierDTO.getName());

        return supplier;
    }

    @Override
    public SupplierGetDTO supplierToGetDTO(Supplier supplier) {
        if (supplier == null){
            return  null;
        }

        SupplierGetDTO supplierGetDTO = new SupplierGetDTO();
        supplierGetDTO.setSupplierId(supplier.getSupplierId());
        supplierGetDTO.setCountry(supplier.getCountry());
        supplierGetDTO.setName(supplier.getName());

        return supplierGetDTO;
    }

    @Override
    public Supplier supplierGetDTOToSupplier(SupplierGetDTO supplierGetDTO) {

        if(supplierGetDTO == null){
            return null;
        }

        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierGetDTO.getSupplierId());
        supplier.setName(supplierGetDTO.getName());
        supplier.setCountry(supplierGetDTO.getCountry());

        return supplier;

    }

    @Override
    public PriceReduction createPriceReductionToPriceReduction(CreatePriceReductionDTO createPriceReductionDTO) {
        if (createPriceReductionDTO == null){
            return null;
        }
        PriceReduction priceReduction = new PriceReduction();

        priceReduction.setReducedPrice(createPriceReductionDTO.getReducedPrice());
        priceReduction.setEndDate(createPriceReductionDTO.getEndDate());
        priceReduction.setStartDate(createPriceReductionDTO.getStartDate());

        return priceReduction;
    }

    @Override
    public PriceReductionGetDTO priceReductionToGetDTO(PriceReduction priceReduction) {
        if(priceReduction == null){
            return null;
        }

        PriceReductionGetDTO priceReductionGetDTO = new PriceReductionGetDTO();

        priceReductionGetDTO.setPriceReductionId(priceReduction.getPriceReductionId());
        priceReductionGetDTO.setReducedPrice(priceReduction.getReducedPrice());
        priceReductionGetDTO.setStartDate(priceReduction.getStartDate());
        priceReductionGetDTO.setEndDate(priceReduction.getEndDate());

        return  priceReductionGetDTO;
    }

    @Override
    public PriceReduction priceReductionGetDTOToPriceReduction(PriceReductionGetDTO priceReductionGetDTO) {
        if(priceReductionGetDTO == null){
            return null;
        }

        PriceReduction priceReduction= new PriceReduction();
        priceReduction.setPriceReductionId(priceReductionGetDTO.getPriceReductionId());
        priceReduction.setReducedPrice(priceReductionGetDTO.getReducedPrice());
        priceReduction.setStartDate(priceReductionGetDTO.getStartDate());
        priceReduction.setEndDate(priceReductionGetDTO.getEndDate());

        return priceReduction;
    }

    @Override
    public ItemGetDTO itemToGetDTO(Item item) {
        if (item == null){
            return  null;
        }

        ItemGetDTO itemDto = new ItemGetDTO();

        itemDto.setItemId(item.getItemId());
        itemDto.setCode(item.getCode());
        itemDto.setDescription(item.getDescription());
        itemDto.setItemState(item.getItemState());
        itemDto.setCreationDate(item.getCreationDate());

        User creator = item.getCreator();
        if(creator != null){
            UserGetDTO userGetDTO = this.userToGetDTO(creator);
            itemDto.setCreator(userGetDTO);
        }

        Collection<Supplier> suppliers = item.getSuppliers();
        if (suppliers != null || suppliers.size() > 0){
            Collection<SupplierGetDTO> supplierGetDTOS = new HashSet<>();
            for (Supplier supplier : suppliers){
                SupplierGetDTO supplierGetDTO = this.supplierToGetDTO(supplier);
                supplierGetDTOS.add(supplierGetDTO);
            }
            itemDto.setSuppliers(supplierGetDTOS);
        }

        Collection<PriceReduction> priceReductions = item.getPriceReductions();
        if (priceReductions!= null || priceReductions.size() > 0){
            Collection<PriceReductionGetDTO> priceReductionGetDTOS = new HashSet<>();
            for (PriceReduction priceReduction : priceReductions){
                PriceReductionGetDTO priceReductionGetDTO = this.priceReductionToGetDTO(priceReduction);
                priceReductionGetDTOS.add(priceReductionGetDTO);
            }
            itemDto.setPriceReductions(priceReductionGetDTOS);
        }

        return  itemDto;
    }

    @Override
    public Item createItemDTOToItem(CreateItemDTO createItemDTO) {
        if (createItemDTO == null){
            return  null;
        }

        Item item = new Item();

        item.setCode(createItemDTO.getCode());
        item.setDescription(createItemDTO.getDescription());
        item.setItemState(createItemDTO.getItemState());
        item.setCreationDate(createItemDTO.getCreationDate());



        return  item;
    }
}

package com.yinnohs.bb2.Example.application.mapper.implementations;

import com.yinnohs.bb2.Example.application.dto.pricereduction.CreatePriceReductionDTO;
import com.yinnohs.bb2.Example.application.dto.pricereduction.PriceReductionGetDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.CreateSupplierDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.SupplierGetDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserCreateDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.PriceReduction;
import com.yinnohs.bb2.Example.application.model.Supplier;
import com.yinnohs.bb2.Example.application.model.User;

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
}

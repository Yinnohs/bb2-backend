package com.yinnohs.bb2.Example.application.mapper.interfaces;

import com.yinnohs.bb2.Example.application.dto.item.CreateItemDTO;
import com.yinnohs.bb2.Example.application.dto.item.ItemGetDTO;
import com.yinnohs.bb2.Example.application.dto.pricereduction.CreatePriceReductionDTO;
import com.yinnohs.bb2.Example.application.dto.pricereduction.PriceReductionGetDTO;
import com.yinnohs.bb2.Example.application.dto.role.RoleGetDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.CreateSupplierDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.SupplierGetDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserCreateDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.model.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseMapper {

    UserGetDTO userToGetDTO (User user);
    User userCreateDTOTouser(UserCreateDTO userCreateDTO);
    User userGetDTOToUser(UserGetDTO userGetDTO);

    Supplier createSupplierDTOToSupplier(CreateSupplierDTO createSupplierDTO);
    SupplierGetDTO supplierToGetDTO (Supplier supplier);
    Supplier supplierGetDTOToSupplier (SupplierGetDTO supplierGetDTO);

    PriceReduction createPriceReductionToPriceReduction(CreatePriceReductionDTO createPriceReductionDTO);
    PriceReductionGetDTO priceReductionToGetDTO(PriceReduction priceReduction);

    PriceReduction priceReductionGetDTOToPriceReduction(PriceReductionGetDTO priceReductionGetDTO);

    ItemGetDTO itemToGetDTO(Item item);
    Item createItemDTOToItem(CreateItemDTO createItemDTO);

    RoleGetDTO roleToGetRoleDTO(Role role);
    Role roleGetDTOToRole(RoleGetDTO roleGetDTO);

}

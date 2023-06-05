package com.yinnohs.bb2.Example.application.mapper.interfaces;

import com.yinnohs.bb2.Example.application.dto.pricereduction.CreatePriceReductionDTO;
import com.yinnohs.bb2.Example.application.dto.pricereduction.PriceReductionGetDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.CreateSupplierDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.SupplierGetDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserCreateDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserUpdateDTO;
import com.yinnohs.bb2.Example.application.model.PriceReduction;
import com.yinnohs.bb2.Example.application.model.Supplier;
import com.yinnohs.bb2.Example.application.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseMapper {

    UserGetDTO userToGetDTO (User user);
    User userCreateDTOTouser(UserCreateDTO userCreateDTO);

    Supplier createSupplierDTOToSupplier(CreateSupplierDTO createSupplierDTO);
    SupplierGetDTO supplierToGetDTO (Supplier supplier);

    PriceReduction createPriceReductionToPriceReduction(CreatePriceReductionDTO createPriceReductionDTO);
    PriceReductionGetDTO priceReductionToGetDTO(PriceReduction priceReduction);

}

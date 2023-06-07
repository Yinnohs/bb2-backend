package com.yinnohs.bb2.Example.application.controller;

import com.yinnohs.bb2.Example.application.dto.pricereduction.CreatePriceReductionDTO;
import com.yinnohs.bb2.Example.application.dto.pricereduction.PriceReductionGetDTO;
import com.yinnohs.bb2.Example.application.dto.pricereduction.UpdatePriceReductionDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.CreateSupplierDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.SupplierGetDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.UpdateSupplierDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.PriceReduction;
import com.yinnohs.bb2.Example.application.model.Supplier;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.service.ItemService;
import com.yinnohs.bb2.Example.application.service.PriceReductionService;
import com.yinnohs.bb2.Example.application.service.SupplierService;
import com.yinnohs.bb2.Example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private UserService userService;
    private PriceReductionService priceReductionService;
    private SupplierService supplierService;
    private ItemService itemService;

    private BaseMapper mapper;

    @Autowired
    public AdminController(ItemService itemService,SupplierService supplierService, PriceReductionService priceReductionService, UserService userService, BaseMapper mapper ){
        this.itemService = itemService;
        this.supplierService = supplierService;
        this.priceReductionService = priceReductionService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @PutMapping("/user/update/client/{id}")
    public ResponseEntity<UserGetDTO> delegateUserToClient(@PathVariable("id") Long id ){
        try {
            if (id == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            User updatedUser = this.userService.delegateUserToClient(id);

            UserGetDTO response = this.mapper.userToGetDTO(updatedUser);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/user/update/admin/{id}")
    public ResponseEntity<UserGetDTO> delegateUserToAdmin(@PathVariable("id") Long userId ){
        try {
            if (userId == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            User updatedUser = this.userService.delegateUserToAdmin(userId);

            UserGetDTO response = this.mapper.userToGetDTO(updatedUser);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        try {
            if (userId == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
             this.userService.deleteUser(userId);

            return new ResponseEntity<>("User Deleted with id :  " + userId, HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/supplier/create")
    public ResponseEntity<SupplierGetDTO> createSupplier (@RequestBody() CreateSupplierDTO createSupplierDTO){
        try{
            if(createSupplierDTO == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            Supplier newSupplier = mapper.createSupplierDTOToSupplier(createSupplierDTO);
            Supplier createdSupplier = this.supplierService.createSupplier(newSupplier);
            SupplierGetDTO response = this.mapper.supplierToGetDTO(createdSupplier);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/supplier/update")
    public ResponseEntity<SupplierGetDTO> updateSupplier (UpdateSupplierDTO updateSupplierDTO){
        try{
            if(updateSupplierDTO == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            Supplier updatedSupplier = this.supplierService.updateSupplier(updateSupplierDTO);
            SupplierGetDTO response = this.mapper.supplierToGetDTO(updatedSupplier);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/discount/create")
    public ResponseEntity<PriceReductionGetDTO> createPRiceReduction(@RequestBody() CreatePriceReductionDTO createPriceReductionDTO){
        try {

            PriceReduction newPriceReduction = this.mapper.createPriceReductionToPriceReduction(createPriceReductionDTO);
            PriceReduction createdPriceReduction = this.priceReductionService.createPriceReduction(newPriceReduction);
            PriceReductionGetDTO response = this.mapper.priceReductionToGetDTO(createdPriceReduction);

            return  new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/discount/update")
    public ResponseEntity<PriceReductionGetDTO> updatePriceReduction (@RequestBody UpdatePriceReductionDTO updatePriceReductionDTO){
        try {
            PriceReduction createdPriceReduction = this.priceReductionService.updatePriceReduction(updatePriceReductionDTO);
            PriceReductionGetDTO response = this.mapper.priceReductionToGetDTO(createdPriceReduction);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

package com.yinnohs.bb2.Example.application.controller;

import com.yinnohs.bb2.Example.application.dto.supplier.CreateSupplierDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.SupplierGetDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.UpdateSupplierDTO;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.Supplier;
import com.yinnohs.bb2.Example.application.service.SupplierService;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    private SupplierService supplierService;
    private BaseMapper mapper;
    @Autowired
    public  SupplierController(SupplierService supplierService, BaseMapper mapper){
        this.supplierService = supplierService;
        this.mapper = mapper;

    }

    @GetMapping("/all")
    public ResponseEntity<List<SupplierGetDTO>> findAllPriceReductions () {
        try {

            List<Supplier> suppliers = this.supplierService.findAllSuppliers();
            List<SupplierGetDTO> response = new ArrayList<>();

            for (Supplier supplier : suppliers) {
                SupplierGetDTO supplierGetDTO = this.mapper.supplierToGetDTO(supplier);
                response.add(supplierGetDTO);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierGetDTO> findOneSupplierById(@PathVariable("supplierId") long supplierId){
        try {
            Supplier supplier = this.supplierService.findSupplierById(supplierId);
            if (supplier == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            SupplierGetDTO response = mapper.supplierToGetDTO(supplier);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<SupplierGetDTO> createSupplier (@RequestBody()CreateSupplierDTO createSupplierDTO){
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

    @PutMapping("/update")
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
}

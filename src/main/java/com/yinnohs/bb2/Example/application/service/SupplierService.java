package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.supplier.UpdateSupplierDTO;
import com.yinnohs.bb2.Example.application.model.Supplier;
import com.yinnohs.bb2.Example.application.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;

    public Supplier createSupplier (Supplier supplier){
        Supplier newSupplier =  this.repository.save(supplier);
        if(newSupplier == null){
            return null;
        }

        return newSupplier;
    }

    public Supplier updateSupplier (UpdateSupplierDTO updateSupplierDTO){

        if (updateSupplierDTO == null){
            return null;
        }

        Supplier currentSupplier = this.getOneSupplier(updateSupplierDTO.getSupplierId());

        String name = updateSupplierDTO.getName();
        if (name != null || name.isEmpty()){
            currentSupplier.setName(updateSupplierDTO.getName());
        }

        String country = updateSupplierDTO.getCountry();
        if (country != null || !country.isEmpty() ){
            currentSupplier.setCountry(country);
        }

        return  this.repository.save(currentSupplier);

    }

    public Supplier getOneSupplier(long supplierId){
        return this.repository.findById(supplierId).orElse(null);
    }

    public List<Supplier> getAllSuppliers(){
        return this.repository.findAll();
    }
}

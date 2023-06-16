package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.supplier.UpdateSupplierDTO;
import com.yinnohs.bb2.Example.application.model.Supplier;
import com.yinnohs.bb2.Example.application.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

        Supplier currentSupplier = this.findSupplierById(updateSupplierDTO.getSupplierId());

        String name = updateSupplierDTO.getName();
        if (name != null && !name.isEmpty()){
            currentSupplier.setName(name);
        }

        String country = updateSupplierDTO.getCountry();
        if (country != null && !country.isEmpty() ){
            currentSupplier.setCountry(country);
        }

        return  this.repository.save(currentSupplier);

    }

    public Supplier findSupplierById(long supplierId){
        return this.repository.findById(supplierId).orElse(null);
    }

    public CompletableFuture<Collection<Supplier>> findSuppliersByIdFuture(Collection<Long> supplierIds ){
        if (supplierIds == null ||supplierIds.size() == 0){
            return null;
        }

        CompletableFuture<Collection<Supplier>> futureSuppliers = CompletableFuture.supplyAsync(()->{
           return this.repository.findAllById(supplierIds);
        });

        return futureSuppliers;
    }

    public Collection<Supplier> findSuppliersById(Collection<Long> supplierIds ){
        if (supplierIds == null ||supplierIds.size() == 0){
            return null;
        }
            return this.repository.findAllById(supplierIds);
    }

    public List<Supplier> findAllSuppliers(){
        return this.repository.findAll();
    }

    public void deleteSupplierById(Long id){
        if (id == null) return;

        this.repository.deleteById(id);

    }
}

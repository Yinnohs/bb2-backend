package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.model.Supplier;
import com.yinnohs.bb2.Example.application.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;

    public Supplier createSupplier (){
        return null;
    }

    public Supplier updateSupplier (){
        return null;
    }

    public Supplier getOneSupplier(){
        return null;
    }

    public List<Supplier> getAllSuppliers(){
        return null;
    }
}

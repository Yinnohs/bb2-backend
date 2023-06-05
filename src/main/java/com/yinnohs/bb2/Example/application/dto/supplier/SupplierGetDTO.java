package com.yinnohs.bb2.Example.application.dto.supplier;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yinnohs.bb2.Example.application.model.Item;
import jakarta.persistence.*;

import java.util.Collection;

public class SupplierGetDTO {

    @JsonProperty("supplier_id")
    private long supplierId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private String Country;

}

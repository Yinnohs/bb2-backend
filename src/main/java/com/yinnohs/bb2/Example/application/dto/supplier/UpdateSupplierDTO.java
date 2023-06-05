package com.yinnohs.bb2.Example.application.dto.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateSupplierDTO {
    @JsonProperty("supplier_id")
    private long supplierId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private String Country;
}

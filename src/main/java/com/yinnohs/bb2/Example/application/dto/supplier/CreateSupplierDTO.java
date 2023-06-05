package com.yinnohs.bb2.Example.application.dto.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateSupplierDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private String country;


}

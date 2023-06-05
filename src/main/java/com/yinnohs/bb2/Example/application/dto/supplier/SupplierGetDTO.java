package com.yinnohs.bb2.Example.application.dto.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplierGetDTO {

    @JsonProperty("supplier_id")
    private long supplierId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private String Country;

}

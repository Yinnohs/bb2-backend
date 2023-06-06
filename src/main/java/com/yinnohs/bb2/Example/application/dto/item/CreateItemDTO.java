package com.yinnohs.bb2.Example.application.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateItemDTO {

    @JsonProperty("code")
    private Long code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("creation_date")
    private LocalDate creationDate;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("creator_id")
    private Long creatorId;

    @JsonProperty("supplier_ids")
    private Collection<Long> supplierIds;

    @JsonProperty("price_reduction_ids")
    private Collection<Long> priceReductionIds;
}

package com.yinnohs.bb2.Example.application.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yinnohs.bb2.Example.application.dto.pricereduction.PriceReductionGetDTO;
import com.yinnohs.bb2.Example.application.dto.supplier.SupplierGetDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.enums.ItemState;
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
    private long code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("item_state")
    private ItemState itemState;

    @JsonProperty("creation_date")
    private LocalDate creationDate;

    @JsonProperty("creator")
    private UserGetDTO creator;

    @JsonProperty("supplier")
    private Collection<SupplierGetDTO> suppliers;

    @JsonProperty("priceReductions")
    private Collection <PriceReductionGetDTO> priceReductions;
}

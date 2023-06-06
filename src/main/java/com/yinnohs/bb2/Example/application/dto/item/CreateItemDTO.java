package com.yinnohs.bb2.Example.application.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
public class CreateItemDTO {

    @JsonProperty("code")
    private Long code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("creation_date")
    private LocalDate creationDate;

    @JsonProperty("creator_id")
    private Long creatorId;

    @JsonProperty("supplier_ids")
    private Collection<Long> supplierIds;

    @JsonProperty("price_reduction_ids")
    private Collection <Long> priceReductionIds;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Collection<Long> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(Collection<Long> supplierIds) {
        this.supplierIds = supplierIds;
    }

    public Collection<Long> getPriceReductionIds() {
        return priceReductionIds;
    }

    public void setPriceReductionIds(Collection<Long> priceReductionIds) {
        this.priceReductionIds = priceReductionIds;
    }
}

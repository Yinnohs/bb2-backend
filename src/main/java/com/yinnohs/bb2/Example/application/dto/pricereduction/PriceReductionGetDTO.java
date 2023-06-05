package com.yinnohs.bb2.Example.application.dto.pricereduction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PriceReductionGetDTO {
    @JsonProperty("price_reduction_id")
    private long priceReductionId;

    @JsonProperty("reduced_price")
    private double reducedPrice;
    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;
}

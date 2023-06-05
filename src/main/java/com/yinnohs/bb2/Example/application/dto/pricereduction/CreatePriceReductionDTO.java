package com.yinnohs.bb2.Example.application.dto.pricereduction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yinnohs.bb2.Example.application.model.Item;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Collection;

public class CreatePriceReductionDTO {
    @JsonProperty("reduced_price")
    private double reducedPrice;
    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;
}

package com.yinnohs.bb2.Example.domain.models.interfaces;

import java.time.LocalDate;

public interface IPriceReduction {
     long priceReductionId = 0;

     double reducedPrice = 0;

     LocalDate startDate = null;

     LocalDate endDate = null;
}

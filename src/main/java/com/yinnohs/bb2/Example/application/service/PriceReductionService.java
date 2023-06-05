package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.pricereduction.UpdatePriceReductionDTO;
import com.yinnohs.bb2.Example.application.model.PriceReduction;
import com.yinnohs.bb2.Example.application.repository.PriceReductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PriceReductionService {
    @Autowired
    private PriceReductionRepository repository;

    public PriceReduction createPriceReduction (PriceReduction priceReduction){
        if (priceReduction == null){
            return null;
        }
        return this.repository.save(priceReduction);
    }

    public PriceReduction getOnePriceReduction(long priceReductionId){
        return this.repository.findById(priceReductionId).orElse(null);
    }

    public List<PriceReduction> getAllPriceReductions(){
        return  this.repository.findAll();
    }

    public  PriceReduction updatePriceReduction(UpdatePriceReductionDTO updatePriceReductionDTO){
        if(updatePriceReductionDTO == null ){

        }

        PriceReduction currentPriceReduction = this.getOnePriceReduction(updatePriceReductionDTO.getPriceReductionId());

        if(currentPriceReduction == null){
            return null;
        }

        LocalDate startDate = updatePriceReductionDTO.getStartDate();
        if (startDate != null){
            currentPriceReduction.setStartDate(startDate);
        }

        LocalDate endDate = updatePriceReductionDTO.getEndDate();
        if (endDate != null){
            currentPriceReduction.setEndDate(endDate);
        }

        Double reducedPrice  = updatePriceReductionDTO.getReducedPrice();
        if (reducedPrice != null){
            currentPriceReduction.setReducedPrice(reducedPrice);
        }

        return this.repository.save(currentPriceReduction);
    }
}

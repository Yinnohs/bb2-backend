package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.pricereduction.UpdatePriceReductionDTO;
import com.yinnohs.bb2.Example.application.model.PriceReduction;
import com.yinnohs.bb2.Example.application.repository.PriceReductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PriceReductionService {
    @Autowired
    private PriceReductionRepository repository;

    public PriceReduction createPriceReduction(PriceReduction priceReduction){
        if (priceReduction == null){
            return null;
        }
        return this.repository.save(priceReduction);
    }

    public PriceReduction findPriceReductionById(long priceReductionId){
        return this.repository.findById(priceReductionId).orElse(null);
    }

    public CompletableFuture<Collection<PriceReduction>> findPriceReductionsByIdFuture(Collection<Long> priceReductionIds){
        if(priceReductionIds == null || priceReductionIds.size() == 0){
            return  null;
        }

       CompletableFuture<Collection<PriceReduction>> data = CompletableFuture.supplyAsync(()->{
           return this.repository.findAllById(priceReductionIds);
       });

       return  data;
    }

    public Collection<PriceReduction> findPriceReductionsById(Collection<Long> priceReductionIds){
        if(priceReductionIds == null || priceReductionIds.size() == 0){
            return  null;
        }
            return this.repository.findAllById(priceReductionIds);
    }

    public List<PriceReduction> findAllPriceReductions(){
        return  this.repository.findAll();
    }

    public  PriceReduction updatePriceReduction(UpdatePriceReductionDTO updatePriceReductionDTO){
        if(updatePriceReductionDTO == null ){

        }

        PriceReduction currentPriceReduction = this.findPriceReductionById(updatePriceReductionDTO.getPriceReductionId());

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

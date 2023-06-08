package com.yinnohs.bb2.Example.application.controller;

import com.yinnohs.bb2.Example.application.dto.pricereduction.CreatePriceReductionDTO;
import com.yinnohs.bb2.Example.application.dto.pricereduction.PriceReductionGetDTO;
import com.yinnohs.bb2.Example.application.dto.pricereduction.UpdatePriceReductionDTO;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.PriceReduction;
import com.yinnohs.bb2.Example.application.service.PriceReductionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/price-reduction")
public class PriceReductionController {

    private PriceReductionService priceReductionService;

    private BaseMapper mapper;

    public PriceReductionController( PriceReductionService priceReductionService, BaseMapper mapper){
        this.priceReductionService = priceReductionService;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PriceReductionGetDTO>> findAllPriceReductions(){
        try {

            List<PriceReduction> priceReductions = this.priceReductionService.findAllPriceReductions();
            List<PriceReductionGetDTO> response = new ArrayList<>();

            for (PriceReduction priceReduction : priceReductions) {
                PriceReductionGetDTO priceReductionGetDTO = this.mapper.priceReductionToGetDTO(priceReduction);
                response.add(priceReductionGetDTO);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/{priceReductionId}")
    public ResponseEntity<PriceReductionGetDTO> findOnePriceReductionById(@PathVariable("priceReductionId") Long priceReductionId){
        try {
            if(priceReductionId == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            PriceReduction priceReduction = this.priceReductionService.findPriceReductionById(priceReductionId);

            if(priceReduction == null){
                return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            PriceReductionGetDTO response = this.mapper.priceReductionToGetDTO(priceReduction);


            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}

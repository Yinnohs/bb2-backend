package com.yinnohs.bb2.Example.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PriceReduction {
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
     @Column(name = "price_reduction_id")
     private long priceReductionId;

     @Column(name = "reduction_price", nullable = false)
     private double reducedPrice;

     @CreatedDate
     @Column(name = "start_date")
     private LocalDate startDate;

     @Column(name = "end_date")
     private LocalDate endDate;

     @JsonBackReference
     @ManyToMany(mappedBy = "priceReductions", fetch = FetchType.LAZY)
     private Collection<Item> items;
}

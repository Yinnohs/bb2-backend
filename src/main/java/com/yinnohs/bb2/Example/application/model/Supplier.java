package com.yinnohs.bb2.Example.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "supplier_id")
    private long supplierId;


    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @JsonBackReference
    @ManyToMany(mappedBy = "suppliers", fetch = FetchType.LAZY)
    private Collection<Item> items;
}

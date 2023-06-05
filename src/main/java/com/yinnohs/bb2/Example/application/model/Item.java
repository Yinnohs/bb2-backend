package com.yinnohs.bb2.Example.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yinnohs.bb2.Example.application.enums.ItemState;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Collection;
import java.util.Date;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private long itemId;


    @Column(name = "code" , nullable = false, unique = true)
    private long code;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_state", nullable = false)
    private ItemState itemState;

    @CreatedDate
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "creator")
    private  User creator;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinTable(
            name = "items_suppliers",
            joinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "supplier_id")
    )
    private Collection<Supplier> suppliers;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinTable(
            name = "items_price_reductions",
            joinColumns = @JoinColumn(name = "price_reduction_id", referencedColumnName = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "price_reduction_id")
    )
    private Collection <PriceReduction> priceReductions;

}

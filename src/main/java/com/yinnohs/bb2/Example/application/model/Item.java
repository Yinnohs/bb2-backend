//package com.yinnohs.bb2.Example.application.model;
//
//import com.yinnohs.bb2.Example.application.enums.ItemState;
//import jakarta.persistence.*;
//
//import java.util.Collection;
//import java.util.Date;
//
//@Entity
//public class Item {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "item_id")
//    private long itemId;
//
//    private long code;
//
//    private String description;
//
//    private ItemState itemState;
//
//    private Date creationDate;
//
//    private  User creator;
//
//    private Collection<Supplier> suppliers;
//
//    private Collection <PriceReduction> priceReductions;
//
//}

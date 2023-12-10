package com.example.loanttv.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class SizeDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Sizes sizes;
    @Column(columnDefinition = "int default 0")
    private Integer quantity;

    public SizeDetails() {
    }

    public SizeDetails(Long id, Products products, Sizes sizes, Integer quantity) {
        this.id = id;
        this.products = products;
        this.sizes = sizes;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Sizes getSizes() {
        return sizes;
    }

    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

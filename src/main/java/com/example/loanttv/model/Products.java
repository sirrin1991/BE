package com.example.loanttv.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity

public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCode;
    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String productImage;
    @Column(columnDefinition = "LONGTEXT")
    private String productQRCode;
    private Boolean gender;
    private Double price;


    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATE")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;
    @OneToMany(mappedBy = "products")
    private List<SizeDetails> sizeDetails;

    public Products() {
    }

    public Products(Long id, String productCode, String name, String productImage,
                    String productQRCode, Boolean gender, Double price, Categories category,
                    List<SizeDetails> sizeDetails) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.productImage = productImage;
        this.productQRCode = productQRCode;
        this.gender = gender;
        this.price = price;
        this.category = category;
        this.sizeDetails = sizeDetails;
    }


    public List<SizeDetails> getSizeDetails() {
        return sizeDetails;
    }

    public void setSizeDetails(List<SizeDetails> sizeDetails) {
        this.sizeDetails = sizeDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductQRCode() {
        return productQRCode;
    }

    public void setProductQRCode(String productQRCode) {
        this.productQRCode = productQRCode;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}

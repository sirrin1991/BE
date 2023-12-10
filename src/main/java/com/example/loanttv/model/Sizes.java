package com.example.loanttv.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Sizes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "sizes")
    private List<SizeDetails> sizeDetails;
    public Sizes() {
    }

    public Sizes(Long id, String name, List<SizeDetails> sizeDetails) {
        this.id = id;
        this.name = name;
        this.sizeDetails = sizeDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SizeDetails> getSizeDetails() {
        return sizeDetails;
    }

    public void setSizeDetails(List<SizeDetails> sizeDetails) {
        this.sizeDetails = sizeDetails;
    }
}

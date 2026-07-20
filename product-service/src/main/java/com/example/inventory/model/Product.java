package com.example.product.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private String source;

    public Product() {
    }

    public Product(Long id, String name, double price, String source) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public void setId(Long id) {
    this.id = id;
}

    public void setName(String name) {
    this.name = name;
}

    public void setPrice(double price) {
    this.price = price;
}
}
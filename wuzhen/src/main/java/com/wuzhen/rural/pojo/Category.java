package com.wuzhen.rural.pojo;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    @Transient
    List<Product> products;
    @Transient
    List<List<Product>> productsByRow;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }

    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

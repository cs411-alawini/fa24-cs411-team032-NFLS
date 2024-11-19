package com.runtrack.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Product")
@Data
public class Product {
    @Id
    private String productId;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "ProductPrice")
    private double productPrice;
}

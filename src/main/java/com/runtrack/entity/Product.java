package com.runtrack.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Product")
public class Product {

    @Id
    private String productId;

    @Column("ProductName")
    private String productName;

    @Column("ProductPrice")
    private double productPrice;

}

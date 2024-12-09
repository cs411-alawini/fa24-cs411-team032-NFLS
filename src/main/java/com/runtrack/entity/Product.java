//package com.runtrack.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "Product")
//@Data
//public class Product {
//    @Id
//    private String productId;
//
//    @Column(name = "ProductName")
//    private String productName;
//
//    @Column(name = "ProductPrice")
//    private double productPrice;
//
//}

package com.runtrack.entity;

public class Product {
    private String productId;
    private String productName;
    private double productPrice;

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}

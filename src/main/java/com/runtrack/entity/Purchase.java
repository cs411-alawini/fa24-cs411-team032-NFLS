//package com.runtrack.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.math.BigDecimal;
//
//@Entity
//@Table(name = "Purchase")
//@Data
//public class Purchase {
//    @Id
//    @Column(name = "PurchaseId", nullable = false)
//    private String purchaseId;
//
//    @Column(name = "PurchasePrice", precision = 10, scale = 2)
//    private BigDecimal purchasePrice;
//
//    @Column(name = "Quantity")
//    private Integer quantity;
//
//    @Column(name = "UserId")
//    private String userId;
//
//    @Column(name = "ProductId")
//    private String productId;
//}

package com.runtrack.entity;

import java.math.BigDecimal;

public class Purchase {
    private String purchaseId;
    private BigDecimal purchasePrice;
    private Integer quantity;
    private String userId;
    private String productId;

    // Getters and Setters
    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}

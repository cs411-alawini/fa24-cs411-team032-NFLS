package com.runtrack.entity;

import java.math.BigDecimal;

public class Purchase {
    private String purchaseId;
    private BigDecimal purchasePrice;
    private int quantity;
    private String userId;
    private String productId;

    // 全参数构造函数
    public Purchase(String purchaseId, BigDecimal purchasePrice, int quantity, String userId, String productId) {
        this.purchaseId = purchaseId;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.userId = userId;
        this.productId = productId;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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


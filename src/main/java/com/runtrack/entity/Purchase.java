package com.runtrack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "Purchase")
@Data
public class Purchase {
    @Id
    @Column(name = "PurchaseId", nullable = false)
    private String purchaseId;

    @Column(name = "PurchasePrice", precision = 10, scale = 2)
    private BigDecimal purchasePrice;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "ProductId")
    private String productId;
}

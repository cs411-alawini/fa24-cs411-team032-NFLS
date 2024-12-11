package com.runtrack.controller;

import com.runtrack.entity.Purchase;
import com.runtrack.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/user/{userId}")
    public List<Purchase> getPurchasesByUserId(@PathVariable String userId) {
        return purchaseService.getPurchasesByUserId(userId);
    }

    @GetMapping("/product/{productId}")
    public List<Purchase> getPurchasesByProductId(@PathVariable String productId) {
        return purchaseService.getPurchasesByProductId(productId);
    }

//    @PostMapping
//    public Purchase createPurchase(@RequestBody Purchase purchase) {
//        return purchaseService.savePurchase(purchase);
//    }

    @DeleteMapping("/{purchaseId}")
    public void deletePurchase(@PathVariable String purchaseId) {
        purchaseService.deletePurchaseById(purchaseId);
    }
}

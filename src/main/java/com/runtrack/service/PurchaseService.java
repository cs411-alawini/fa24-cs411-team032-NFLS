package com.runtrack.service;

import com.runtrack.entity.Purchase;
import com.runtrack.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> getPurchasesByUserId(String userId) {
        return purchaseRepository.findByUserId(userId);
    }

    public List<Purchase> getPurchasesByProductId(String productId) {
        return purchaseRepository.findByProductId(productId);
    }

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public void deletePurchaseById(String purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }
}

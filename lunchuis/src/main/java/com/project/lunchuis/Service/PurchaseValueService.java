package com.project.lunchuis.Service;

import com.project.lunchuis.Model.PurchaseValue;
import com.project.lunchuis.Repository.PurchaseValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseValueService {
    @Autowired
    private PurchaseValueRepository purchaseValueRepository;

    public List<PurchaseValue> getAllPurchaseValues() {
        return purchaseValueRepository.findAll();
    }

    public PurchaseValue getPurchaseValueById(Long id) {
        return purchaseValueRepository.findById(id).orElse(null);
    }

    public PurchaseValue savePurchaseValue(PurchaseValue purchaseValue) {
        return purchaseValueRepository.save(purchaseValue);
    }

    public void deletePurchaseValue(Long id) {
        purchaseValueRepository.deleteById(id);
    }
}

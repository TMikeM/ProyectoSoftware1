package com.project.lunchuis.Controller;

import com.project.lunchuis.Model.PurchaseValue;
import com.project.lunchuis.Service.PurchaseValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-value")
public class PurchaseValueController {
    @Autowired
    private PurchaseValueService purchaseValueService;

    @GetMapping
    public List<PurchaseValue> getAllPurchaseValues() {
        return purchaseValueService.getAllPurchaseValues();
    }

    @GetMapping("/{id}")
    public PurchaseValue getPurchaseValueById(@PathVariable Long id) {
        return purchaseValueService.getPurchaseValueById(id);
    }

    @PostMapping
    public PurchaseValue createPurchaseValue(@RequestBody PurchaseValue purchaseValue) {
        return purchaseValueService.savePurchaseValue(purchaseValue);
    }

    @PutMapping("/{id}")
    public PurchaseValue updatePurchaseValue(@PathVariable Long id, @RequestBody PurchaseValue purchaseValue) {
        return purchaseValueService.updatePurchaseValue(id, purchaseValue);
    }

    @DeleteMapping("/{id}")
    public void deletePurchaseValue(@PathVariable Long id) {
        purchaseValueService.deletePurchaseValue(id);
    }
}

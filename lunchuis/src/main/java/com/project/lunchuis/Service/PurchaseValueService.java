package com.project.lunchuis.Service;

import com.project.lunchuis.Model.PurchaseValue;
import com.project.lunchuis.Repository.PurchaseValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public PurchaseValue updatePurchaseValue(Long id, PurchaseValue purchaseValue) {
        Optional<PurchaseValue> existingPurchaseValue = purchaseValueRepository.findById(id);
        
        if (existingPurchaseValue.isPresent()) {
            PurchaseValue updatedPurchaseValue = existingPurchaseValue.get();
            
            updatedPurchaseValue.setCantidadDiaria(purchaseValue.getCantidadDiaria());
            updatedPurchaseValue.setCantidadMensual(purchaseValue.getCantidadMensual());
            updatedPurchaseValue.setCantidadCena(purchaseValue.getCantidadCena());
            updatedPurchaseValue.setValorDiario(purchaseValue.getValorDiario());
            updatedPurchaseValue.setValorMensual(purchaseValue.getValorMensual());
            updatedPurchaseValue.setValorCena(purchaseValue.getValorCena());

            return purchaseValueRepository.save(updatedPurchaseValue);
        }
        
        return null; // O puedes lanzar una excepción si el registro no existe
    }

    public void deletePurchaseValue(Long id) {
        purchaseValueRepository.deleteById(id);
    }
}

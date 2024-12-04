package com.project.lunchuis.Controller;

import com.project.lunchuis.Model.Buy;
import com.project.lunchuis.Service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buy")
public class BuyController {

    @Autowired
    private BuyService buyService;

    @PostMapping
    public ResponseEntity<Buy> createBuy(@RequestBody Buy buy) {
        // El servicio crea el reporte y el QR automáticamente
        Buy newBuy = buyService.createBuy(buy);
        return ResponseEntity.ok(newBuy);
    }

    @GetMapping
    public ResponseEntity<List<Buy>> getAllBuys() {
        List<Buy> buys = buyService.getAllBuys();
        return ResponseEntity.ok(buys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buy> getBuyById(@PathVariable Long id) {
        Optional<Buy> buy = buyService.getBuyById(id);
        return buy.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buy> updateBuy(@PathVariable Long id, @RequestBody Buy buyDetails) {
        Optional<Buy> updatedBuy = buyService.updateBuy(id, buyDetails);
        return updatedBuy.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuy(@PathVariable Long id) {
        boolean deleted = buyService.deleteBuy(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

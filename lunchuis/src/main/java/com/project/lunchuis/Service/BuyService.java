package com.project.lunchuis.Service;

import com.project.lunchuis.Model.Buy;
import com.project.lunchuis.Model.Notification;
import com.project.lunchuis.Repository.BuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BuyService {

    @Autowired
    private BuyRepository buyRepository;

    @Autowired
    private NotificationService notificationService;

    public Buy createBuy(Buy buy) {
        if (buy.getUser() == null || buy.getUser().getId() == null) {
            throw new IllegalArgumentException("La compra debe estar asociada a un usuario válido");
        }
    
        // Continuar con la lógica de guardar la compra y crear la notificación
        Buy savedBuy = buyRepository.save(buy);
    
        Notification notification = new Notification();
        notification.setDate(LocalDate.now());
        notification.setMessage("Gracias por tu compra. ID: " + savedBuy.getId());
        notificationService.createNotification(notification, buy.getUser().getId());
    
        return savedBuy;
    }
    

    public List<Buy> getAllBuys() {
        return buyRepository.findAll();
    }

    public Optional<Buy> getBuyById(Long id) {
        return buyRepository.findById(id);
    }

    public Optional<Buy> updateBuy(Long id, Buy buyDetails) {
        return buyRepository.findById(id).map(buy -> {
            buy.setValue(buyDetails.getValue());
            buy.setDate(buyDetails.getDate());
            buy.setHour(buyDetails.getHour());
            buy.setDinner(buyDetails.isDinner());
            buy.setLunch(buyDetails.isLunch());
            buy.setMonthly(buyDetails.isMonthly());

            if (buyDetails.getQrcode() != null) {
                buy.setQrcode(buyDetails.getQrcode());
            }
            if (buyDetails.getReport() != null) {
                buy.setReport(buyDetails.getReport());
            }
            if (buyDetails.getUser() != null) {
                buy.setUser(buyDetails.getUser());
            }
            return buyRepository.save(buy);
        });
    }

    public boolean deleteBuy(Integer id) {
        if (buyRepository.existsById(id)) {
            buyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

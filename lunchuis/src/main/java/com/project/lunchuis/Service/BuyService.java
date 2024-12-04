package com.project.lunchuis.Service;

import com.project.lunchuis.Model.Buy;
import com.project.lunchuis.Model.Notification;
import com.project.lunchuis.Model.QrCode;
import com.project.lunchuis.Model.Report;
import com.project.lunchuis.Repository.BuyRepository;
import com.project.lunchuis.Repository.QrRepository;
import com.project.lunchuis.Repository.ReportRepository;
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
    private QrRepository qrRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private QrService qrService;  // Inyectamos el servicio para generar QR

    public Buy createBuy(Buy buy) {
        if (buy.getUser() == null || buy.getUser().getId() == null) {
            throw new IllegalArgumentException("La compra debe estar asociada a un usuario válido");
        }
    
        // Crear un Report automáticamente si no existe uno
        Report report = new Report();
        report.setDate(LocalDate.now());
        report = reportRepository.save(report);  // Guardamos el reporte en la base de datos
    
        // Asocia el Report a la compra
        buy.setReport(report);
    
        // Guardamos la compra (Buy) primero
        Buy savedBuy = buyRepository.save(buy);
    
        // Crear y asociar el código QR a la compra
        QrCode qrCode = qrService.generateQrCode(savedBuy); // Generamos el código QR con el servicio
    
        // Asocia el QrCode generado con la compra
        savedBuy.setQrcode(qrCode);
    
        // Guardamos el QrCode
        qrRepository.save(qrCode);
    
        // Crear y guardar la notificación
        Notification notification = new Notification();
        notification.setDate(LocalDate.now());
        notification.setMessage("Gracias por tu compra. ID: " + savedBuy.getId());
        notificationService.createNotification(notification, savedBuy.getUser().getId());
    
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

    public boolean deleteBuy(Long id) {
        if (buyRepository.existsById(id)) {
            buyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

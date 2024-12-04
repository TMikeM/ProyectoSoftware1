package com.project.lunchuis.Controller;

import com.project.lunchuis.Model.Buy;
import com.project.lunchuis.Model.QrCode;
import com.project.lunchuis.Service.BuyService;
import com.project.lunchuis.Service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.project.lunchuis.Repository.QrRepository;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/qrcode")
public class QrController {

    @Autowired
    private QrService qrCodeService;

    @Autowired
    private BuyService buyService;

    @Autowired
    private QrRepository qrRepository;

    @GetMapping("/{buyId}")
    public ResponseEntity<byte[]> getQrCodeImage(@PathVariable Long buyId) {
        QrCode qrCode = qrRepository.findById(buyId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "QR Code not found"));

        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(qrCode.getQrImage());
    }

    @PostMapping("/generate/{buyId}")
    public QrCode generateQrCode(@PathVariable Long buyId) {
        Buy buy = buyService.getBuyById(buyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Buy not found"));
        return qrCodeService.generateQrCode(buy);
    }
}
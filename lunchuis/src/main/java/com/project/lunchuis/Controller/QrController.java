package com.project.lunchuis.Controller;

import com.project.lunchuis.Model.Buy;
import com.project.lunchuis.Model.QrCode;
import com.project.lunchuis.Service.BuyService;
import com.project.lunchuis.Service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/qrcode")
public class QrController {

    @Autowired
    private QrService qrCodeService;

    @Autowired
    private BuyService buyService;

    @PostMapping("/generate/{buyId}")
    public QrCode generateQrCode(@PathVariable Long buyId) {
        Buy buy = buyService.getBuyById(buyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Buy not found"));
        return qrCodeService.generateQrCode(buy);
    }
}
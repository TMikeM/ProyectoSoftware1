package com.project.lunchuis.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.project.lunchuis.Model.Buy;
import com.project.lunchuis.Model.QrCode;
import com.project.lunchuis.Repository.QrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class QrService {

    @Autowired
    private QrRepository qrRepository;

    public byte[] generateQrImage(String content) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate QR Code", e);
        }
    }

    public QrCode generateQrCode(Buy buy) {
        QrCode qrCode = new QrCode();
        qrCode.setBuy(buy); // Asociamos el QR a la compra
        String content = "Compra ID: " + buy.getId(); // Aquí puedes agregar más información relevante
        qrCode.setQrImage(generateQrImage(content)); // Generamos el código QR con el contenido
        return qrRepository.save(qrCode); // Guardamos el QR en la base de datos
    }
}

package com.project.lunchuis.Model;

import jakarta.persistence.*;

public class QrCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] qrImage;

    @OneToOne
    @JoinColumn(name = "buy_id", nullable = false)
    private Buy buy;

    public void setBuy(Buy buy) {
        this.buy = buy;
        if (buy != null && buy.getQrcode() != this) {
            buy.setQrcode(this);
        }
    }
}

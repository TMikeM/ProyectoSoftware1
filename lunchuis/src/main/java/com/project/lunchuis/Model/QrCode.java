package com.project.lunchuis.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "qrcode")
public class QrCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    
    @Basic(fetch = FetchType.LAZY)
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

package com.project.lunchuis.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Buy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @PositiveOrZero(message = "El valor debe ser positivo")
    private int value;

    private LocalDate date;
    private LocalTime hour;

    private boolean dinner;
    private boolean lunch;
    private boolean monthly;

    // Relación con QR
    @ManyToOne
    @JoinColumn(name = "qr_id", nullable = false) // Campo qr_id en la base de datos
    private QRModel qr;

    // Relación con Report
    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false) // Campo report_id en la base de datos
    private Report report;

    // Relación con User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Campo user_id en la base de datos
    private UserModel user;
}

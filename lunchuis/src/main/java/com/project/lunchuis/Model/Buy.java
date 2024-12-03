package com.project.lunchuis.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Buy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private LocalTime hour;
    private int value;
    private boolean dinner;
    private boolean lunch;
    private boolean monthly;
    public void sendNotification() {
        // Implementar la lógica de notificación
    }
}

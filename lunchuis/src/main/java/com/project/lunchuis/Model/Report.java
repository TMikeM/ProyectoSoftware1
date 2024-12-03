package com.project.lunchuis.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;

    @OneToMany
    private List<Buy> historyPurchases;

    // Métodos para búsquedas
    public List<Buy> findByDate(LocalDate date) {
        // Implementar lógica
        return null;
    }

    public List<Buy> findById(int id) {
        // Implementar lógica
        return null;
    }

    public List<Buy> findByType(String type) {
        // Implementar lógica basada en dinner o lunch
        return null;
    }
}


package com.project.lunchuis.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reports")
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @OneToMany(mappedBy = "report") // Relación con la entidad Buy
    private List<Buy> historyPurchases;

    // Si necesitas métodos adicionales, como lógica de negocio
    public List<Buy> findByDate(LocalDate date) {
        // Implementa tu lógica aquí
        return null;
    }
}

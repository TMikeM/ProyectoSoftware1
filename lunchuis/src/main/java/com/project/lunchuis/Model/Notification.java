package com.project.lunchuis.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Clave foránea
    private User usuario;

    public Notification() {}

    public Notification(LocalDate date, String message, User usuario) {
        this.date = date;
        this.message = message;
        this.usuario = usuario;
    }
}

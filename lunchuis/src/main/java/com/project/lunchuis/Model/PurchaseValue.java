package com.project.lunchuis.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PurchaseValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidadDiaria;
    private Integer cantidadMensual;
    private Integer cantidadCena;
    private Double valorDiario;
    private Double valorMensual;
    private Double valorCena;

    public PurchaseValue() {
    }

    public PurchaseValue(Integer cantidadDiaria, Integer cantidadMensual, Integer cantidadCena, Double valorDiario, Double valorMensual, Double valorCena) {
        this.cantidadDiaria = cantidadDiaria;
        this.cantidadMensual = cantidadMensual;
        this.cantidadCena = cantidadCena;
        this.valorDiario = valorDiario;
        this.valorMensual = valorMensual;
        this.valorCena = valorCena;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadDiaria() {
        return cantidadDiaria;
    }

    public void setCantidadDiaria(Integer cantidadDiaria) {
        this.cantidadDiaria = cantidadDiaria;
    }

    public Integer getCantidadMensual() {
        return cantidadMensual;
    }

    public void setCantidadMensual(Integer cantidadMensual) {
        this.cantidadMensual = cantidadMensual;
    }

    public Integer getCantidadCena() {
        return cantidadCena;
    }

    public void setCantidadCena(Integer cantidadCena) {
        this.cantidadCena = cantidadCena;
    }

    public Double getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(Double valorDiario) {
        this.valorDiario = valorDiario;
    }

    public Double getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(Double valorMensual) {
        this.valorMensual = valorMensual;
    }

    public Double getValorCena() {
        return valorCena;
    }

    public void setValorCena(Double valorCena) {
        this.valorCena = valorCena;
    }
}

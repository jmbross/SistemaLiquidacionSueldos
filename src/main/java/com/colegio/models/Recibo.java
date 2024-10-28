package com.colegio.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Recibo {
    private int idRecibo;
    private int idTrabajador;
    private LocalDate fecha;
    private BigDecimal sueldoNeto;

    // Constructor vacÃ­o
    public Recibo() {}

    // Constructor completo
    public Recibo(int idRecibo, int idTrabajador, LocalDate fecha, BigDecimal sueldoNeto) {
        this.idRecibo = idRecibo;
        this.idTrabajador = idTrabajador;
        this.fecha = fecha;
        this.sueldoNeto = sueldoNeto;
    }

    // Getters y Setters
    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(BigDecimal sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }
}
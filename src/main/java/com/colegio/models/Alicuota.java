package com.colegio.models;

import java.math.BigDecimal;

public class Alicuota {
    private int idAlicuota;
    private String descripcion;
    private BigDecimal porcentaje;
    private int idTrabajador;

    // Constructor vacÃ­o
    public Alicuota() {}

    // Constructor completo
    public Alicuota(int idAlicuota, String descripcion, BigDecimal porcentaje, int idTrabajador) {
        this.idAlicuota = idAlicuota;
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
        this.idTrabajador = idTrabajador;
    }

    // Getters y Setters
    public int getIdAlicuota() {
        return idAlicuota;
    }

    public void setIdAlicuota(int idAlicuota) {
        this.idAlicuota = idAlicuota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }
}
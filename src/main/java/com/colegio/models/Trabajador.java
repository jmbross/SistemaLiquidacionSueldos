package com.colegio.models;

import java.math.BigDecimal;

public class Trabajador {
    private int idTrabajador;
    private String nombre;
    private String apellido;
    private String dni;
    private BigDecimal sueldoBruto;
    private String email;
    private String telefono;
    private int idUsuario;

    // Constructor vacÃ­o
    public Trabajador() {}

    // Constructor completo
    public Trabajador(int idTrabajador, String nombre, String apellido, String dni, 
                     BigDecimal sueldoBruto, String email, String telefono, int idUsuario) {
        this.idTrabajador = idTrabajador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.sueldoBruto = sueldoBruto;
        this.email = email;
        this.telefono = telefono;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public BigDecimal getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(BigDecimal sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
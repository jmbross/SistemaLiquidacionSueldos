package com.colegio.models;

public class UsuarioTrabajador {
    private int idUsuarioTrabajador;
    private int idUsuario;
    private int idTrabajador;

    // Constructor vacÃ­o
    public UsuarioTrabajador() {}

    // Constructor completo
    public UsuarioTrabajador(int idUsuarioTrabajador, int idUsuario, int idTrabajador) {
        this.idUsuarioTrabajador = idUsuarioTrabajador;
        this.idUsuario = idUsuario;
        this.idTrabajador = idTrabajador;
    }

    // Getters y Setters
    public int getIdUsuarioTrabajador() {
        return idUsuarioTrabajador;
    }

    public void setIdUsuarioTrabajador(int idUsuarioTrabajador) {
        this.idUsuarioTrabajador = idUsuarioTrabajador;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    @Override
    public String toString() {
        return "UsuarioTrabajador{" +
                "idUsuarioTrabajador=" + idUsuarioTrabajador +
                ", idUsuario=" + idUsuario +
                ", idTrabajador=" + idTrabajador +
                '}';
    }
}
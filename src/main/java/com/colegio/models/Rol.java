package com.colegio.models;

public enum Rol {
    ADMIN("admin"),
    USUARIO("usuario");

    private final String valor;

    Rol(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static Rol fromString(String texto) {
        for (Rol rol : Rol.values()) {
            if (rol.valor.equalsIgnoreCase(texto)) {
                return rol;
            }
        }
        throw new IllegalArgumentException("Rol no vÃ¡lido: " + texto);
    }
}
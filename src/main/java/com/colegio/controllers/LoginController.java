package com.colegio.controllers;

import com.colegio.dao.UsuarioDAO;
import com.colegio.models.Usuario;
import com.colegio.util.SecurityUtil;

public class LoginController {
    private final UsuarioDAO usuarioDAO;

    public LoginController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario autenticar(String dni, String password) throws Exception {
        Usuario usuario = usuarioDAO.obtenerPorDNI(dni);
        
        if (usuario != null && SecurityUtil.verificarPassword(password, usuario.getContrasena())) {
            return usuario;
        }
        
        return null;
    }

    public boolean esAdministrador(Usuario usuario) {
        return "admin".equals(usuario.getRol());
    }
}
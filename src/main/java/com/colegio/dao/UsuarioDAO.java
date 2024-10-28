package com.colegio.dao;

import com.colegio.models.Usuario;
import com.colegio.models.Rol;
import com.colegio.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {
    
    @Override
    public Usuario obtenerPorId(int id) throws Exception {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extraerUsuarioDeResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<Usuario> obtenerTodos() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                usuarios.add(extraerUsuarioDeResultSet(rs));
            }
        }
        return usuarios;
    }

    @Override
    public void guardar(Usuario usuario) throws Exception {
        String sql = "INSERT INTO usuario (nombre, apellido, dni, matricula, email, contrasena, rol) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getDni());
            stmt.setString(4, usuario.getMatricula());
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getContrasena());
            stmt.setString(7, usuario.getRol().getValor());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void actualizar(Usuario usuario) throws Exception {
        String sql = "UPDATE usuario SET nombre=?, apellido=?, dni=?, matricula=?, " +
                    "email=?, contrasena=?, rol=? WHERE id_usuario=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getDni());
            stmt.setString(4, usuario.getMatricula());
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getContrasena());
            stmt.setString(7, usuario.getRol().getValor());
            stmt.setInt(8, usuario.getIdUsuario());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Usuario extraerUsuarioDeResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setDni(rs.getString("dni"));
        usuario.setMatricula(rs.getString("matricula"));
        usuario.setEmail(rs.getString("email"));
        usuario.setContrasena(rs.getString("contrasena"));
        usuario.setRol(Rol.fromString(rs.getString("rol")));
        return usuario;
    }

    // MÃ©todo adicional para login
    public Usuario login(String dni, String contrasena) throws Exception {
        String sql = "SELECT * FROM usuario WHERE dni = ? AND contrasena = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, dni);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extraerUsuarioDeResultSet(rs);
            }
        }
        return null;
    }
}
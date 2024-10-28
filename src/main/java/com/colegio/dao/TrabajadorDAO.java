package com.colegio.dao;

import com.colegio.models.Trabajador;
import com.colegio.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO implements DAO<Trabajador> {

    @Override
    public Trabajador obtenerPorId(int id) throws Exception {
        String sql = "SELECT * FROM trabajador WHERE id_trabajador = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extraerTrabajadorDeResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<Trabajador> obtenerTodos() throws Exception {
        List<Trabajador> trabajadores = new ArrayList<>();
        String sql = "SELECT * FROM trabajador";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                trabajadores.add(extraerTrabajadorDeResultSet(rs));
            }
        }
        return trabajadores;
    }

    @Override
    public void guardar(Trabajador trabajador) throws Exception {
        String sql = "INSERT INTO trabajador (nombre, apellido, dni, sueldo_bruto) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, trabajador.getNombre());
            stmt.setString(2, trabajador.getApellido());
            stmt.setString(3, trabajador.getDni());
            stmt.setDouble(4, trabajador.getSueldoBruto());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void actualizar(Trabajador trabajador) throws Exception {
        String sql = "UPDATE trabajador SET nombre=?, apellido=?, dni=?, sueldo_bruto=? WHERE id_trabajador=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, trabajador.getNombre());
            stmt.setString(2, trabajador.getApellido());
            stmt.setString(3, trabajador.getDni());
            stmt.setDouble(4, trabajador.getSueldoBruto());
            stmt.setInt(5, trabajador.getIdTrabajador());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM trabajador WHERE id_trabajador = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Trabajador extraerTrabajadorDeResultSet(ResultSet rs) throws SQLException {
        Trabajador trabajador = new Trabajador();
        trabajador.setIdTrabajador(rs.getInt("id_trabajador"));
        trabajador.setNombre(rs.getString("nombre"));
        trabajador.setApellido(rs.getString("apellido"));
        trabajador.setDni(rs.getString("dni"));
        trabajador.setSueldoBruto(rs.getDouble("sueldo_bruto"));
        return trabajador;
    }

    // MÃ©todo adicional para obtener trabajadores por usuario
    public List<Trabajador> obtenerTrabajadoresPorUsuario(int idUsuario) throws Exception {
        List<Trabajador> trabajadores = new ArrayList<>();
        String sql = "SELECT t.* FROM trabajador t " +
                    "INNER JOIN usuario_trabajador ut ON t.id_trabajador = ut.id_trabajador " +
                    "WHERE ut.id_usuario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                trabajadores.add(extraerTrabajadorDeResultSet(rs));
            }
        }
        return trabajadores;
    }
}
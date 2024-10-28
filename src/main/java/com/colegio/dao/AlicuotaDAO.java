package com.colegio.dao;

import com.colegio.models.Alicuota;
import com.colegio.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlicuotaDAO implements DAO<Alicuota> {

    @Override
    public Alicuota obtenerPorId(int id) throws Exception {
        String sql = "SELECT * FROM alicuota WHERE id_alicuota = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extraerAlicuotaDeResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<Alicuota> obtenerTodos() throws Exception {
        List<Alicuota> alicuotas = new ArrayList<>();
        String sql = "SELECT * FROM alicuota";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                alicuotas.add(extraerAlicuotaDeResultSet(rs));
            }
        }
        return alicuotas;
    }

    @Override
    public void guardar(Alicuota alicuota) throws Exception {
        String sql = "INSERT INTO alicuota (nombre, porcentaje) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, alicuota.getNombre());
            stmt.setDouble(2, alicuota.getPorcentaje());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void actualizar(Alicuota alicuota) throws Exception {
        String sql = "UPDATE alicuota SET nombre=?, porcentaje=? WHERE id_alicuota=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, alicuota.getNombre());
            stmt.setDouble(2, alicuota.getPorcentaje());
            stmt.setInt(3, alicuota.getIdAlicuota());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM alicuota WHERE id_alicuota = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Alicuota extraerAlicuotaDeResultSet(ResultSet rs) throws SQLException {
        Alicuota alicuota = new Alicuota();
        alicuota.setIdAlicuota(rs.getInt("id_alicuota"));
        alicuota.setNombre(rs.getString("nombre"));
        alicuota.setPorcentaje(rs.getDouble("porcentaje"));
        return alicuota;
    }
}
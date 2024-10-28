package com.colegio.dao;

import com.colegio.models.Recibo;
import com.colegio.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ReciboDAO implements DAO<Recibo> {

    @Override
    public Recibo obtenerPorId(int id) throws Exception {
        String sql = "SELECT * FROM recibo WHERE id_recibo = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extraerReciboDeResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<Recibo> obtenerTodos() throws Exception {
        List<Recibo> recibos = new ArrayList<>();
        String sql = "SELECT * FROM recibo";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                recibos.add(extraerReciboDeResultSet(rs));
            }
        }
        return recibos;
    }

    @Override
    public void guardar(Recibo recibo) throws Exception {
        String sql = "INSERT INTO recibo (id_trabajador, fecha, sueldo_bruto, sueldo_neto) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, recibo.getIdTrabajador());
            stmt.setDate(2, new java.sql.Date(recibo.getFecha().getTime()));
            stmt.setDouble(3, recibo.getSueldoBruto());
            stmt.setDouble(4, recibo.getSueldoNeto());
            
            stmt.executeUpdate();
            
            // Obtener el ID generado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                recibo.setIdRecibo(rs.getInt(1));
            }
        }
    }

    @Override
    public void actualizar(Recibo recibo) throws Exception {
        String sql = "UPDATE recibo SET id_trabajador=?, fecha=?, sueldo_bruto=?, sueldo_neto=? WHERE id_recibo=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, recibo.getIdTrabajador());
            stmt.setDate(2, new java.sql.Date(recibo.getFecha().getTime()));
            stmt.setDouble(3, recibo.getSueldoBruto());
            stmt.setDouble(4, recibo.getSueldoNeto());
            stmt.setInt(5, recibo.getIdRecibo());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM recibo WHERE id_recibo = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Recibo extraerReciboDeResultSet(ResultSet rs) throws SQLException {
        Recibo recibo = new Recibo();
        recibo.setIdRecibo(rs.getInt("id_recibo"));
        recibo.setIdTrabajador(rs.getInt("id_trabajador"));
        recibo.setFecha(rs.getDate("fecha"));
        recibo.setSueldoBruto(rs.getDouble("sueldo_bruto"));
        recibo.setSueldoNeto(rs.getDouble("sueldo_neto"));
        return recibo;
    }

    // MÃ©todos adicionales especÃ­ficos para Recibos

    public List<Recibo> obtenerRecibosPorTrabajador(int idTrabajador) throws Exception {
        List<Recibo> recibos = new ArrayList<>();
        String sql = "SELECT * FROM recibo WHERE id_trabajador = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idTrabajador);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                recibos.add(extraerReciboDeResultSet(rs));
            }
        }
        return recibos;
    }

    public List<Recibo> obtenerRecibosPorFecha(Date fechaInicio, Date fechaFin) throws Exception {
        List<Recibo> recibos = new ArrayList<>();
        String sql = "SELECT * FROM recibo WHERE fecha BETWEEN ? AND ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                recibos.add(extraerReciboDeResultSet(rs));
            }
        }
        return recibos;
    }

    // MÃ©todo para obtener el Ãºltimo recibo de un trabajador
    public Recibo obtenerUltimoReciboPorTrabajador(int idTrabajador) throws Exception {
        String sql = "SELECT * FROM recibo WHERE id_trabajador = ? ORDER BY fecha DESC LIMIT 1";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idTrabajador);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extraerReciboDeResultSet(rs);
            }
        }
        return null;
    }

    // MÃ©todo para calcular el total de sueldos netos pagados en un perÃ­odo
    public double calcularTotalSueldosNetos(Date fechaInicio, Date fechaFin) throws Exception {
        String sql = "SELECT SUM(sueldo_neto) as total FROM recibo WHERE fecha BETWEEN ? AND ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        return 0.0;
    }
}
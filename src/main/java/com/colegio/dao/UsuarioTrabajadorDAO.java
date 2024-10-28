import com.colegio.util.DatabaseConnection;

public class UsuarioTrabajadorDAO {
    
    public void asignarTrabajadorAUsuario(int idUsuario, int idTrabajador) throws Exception {
        String sql = "INSERT INTO usuario_trabajador (id_usuario, id_trabajador) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idTrabajador);
            stmt.executeUpdate();
        }
    }

    public void desasignarTrabajadorDeUsuario(int idUsuario, int idTrabajador) throws Exception {
        String sql = "DELETE FROM usuario_trabajador WHERE id_usuario = ? AND id_trabajador = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idTrabajador);
            stmt.executeUpdate();
        }
    }

    public boolean existeAsignacion(int idUsuario, int idTrabajador) throws Exception {
        String sql = "SELECT COUNT(*) FROM usuario_trabajador WHERE id_usuario = ? AND id_trabajador = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idTrabajador);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
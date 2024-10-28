package com.colegio.views.controllers;

import com.colegio.models.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MenuPrincipalViewController {
    
    @FXML private Label labelUsuario;
    @FXML private Button btnGestionTrabajadores;
    @FXML private Button btnGestionAlicuotas;
    @FXML private Button btnGenerarRecibo;
    @FXML private Button btnGestionUsuarios;
    @FXML private StackPane contenidoPrincipal;
    
    private Usuario usuarioActual;
    
    public void inicializarConUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        labelUsuario.setText(usuario.getNombre() + " " + usuario.getApellido());
        
        // Configurar visibilidad de botones segÃºn el rol
        boolean esAdmin = "admin".equals(usuario.getRol());
        btnGestionUsuarios.setVisible(esAdmin);
        btnGestionAlicuotas.setVisible(esAdmin);
    }
    
    @FXML
    private void handleCerrarSesion() {
        try {
            // Cargar la vista de login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/colegio/views/login.fxml"));
            Parent root = loader.load();
            
            // Configurar la nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Sistema de LiquidaciÃ³n de Sueldos - Login");
            stage.setScene(new Scene(root));
            
            // Cerrar la ventana actual
            Stage currentStage = (Stage) labelUsuario.getScene().getWindow();
            currentStage.close();
            
            // Mostrar la ventana de login
            stage.show();
            
        } catch (Exception e) {
            mostrarError("Error al cerrar sesiÃ³n: " + e.getMessage());
        }
    }
    
    @FXML
    private void mostrarGestionTrabajadores() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/colegio/views/gestion_trabajadores.fxml"));
            Parent vista = loader.load();
            
            // Inicializar el controlador de la vista
            GestionTrabajadoresViewController controller = loader.getController();
            controller.inicializar(usuarioActual);
            
            // Mostrar la vista en el contenedor principal
            contenidoPrincipal.getChildren().clear();
            contenidoPrincipal.getChildren().add(vista);
            
        } catch (Exception e) {
            mostrarError("Error al cargar la gestiÃ³n de trabajadores: " + e.getMessage());
        }
    }
    
    @FXML
    private void mostrarGestionAlicuotas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/colegio/views/gestion_alicuotas.fxml"));
            Parent vista = loader.load();
            
            // Inicializar el controlador de la vista
            GestionAlicuotasViewController controller = loader.getController();
            controller.inicializar();
            
            // Mostrar la vista en el contenedor principal
            contenidoPrincipal.getChildren().clear();
            contenidoPrincipal.getChildren().add(vista);
            
        } catch (Exception e) {
            mostrarError("Error al cargar la gestiÃ³n de alÃ­cuotas: " + e.getMessage());
        }
    }
    
    @FXML
    private void mostrarGenerarRecibo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/colegio/views/generar_recibo.fxml"));
            Parent vista = loader.load();
            
            // Inicializar el controlador de la vista
            GenerarReciboViewController controller = loader.getController();
            controller.inicializar(usuarioActual);
            
            // Mostrar la vista en el contenedor principal
            contenidoPrincipal.getChildren().clear();
            contenidoPrincipal.getChildren().add(vista);
            
        } catch (Exception e) {
            mostrarError("Error al cargar la generaciÃ³n de recibos: " + e.getMessage());
        }
    }
    
    @FXML
    private void mostrarGestionUsuarios() {
        try {
            if (!"admin".equals(usuarioActual.getRol())) {
                mostrarError("No tiene permisos para acceder a esta funciÃ³n");
                return;
            }
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/colegio/views/gestion_usuarios.fxml"));
            Parent vista = loader.load();
            
            // Inicializar el controlador de la vista
            GestionUsuariosViewController controller = loader.getController();
            controller.inicializar();
            
            // Mostrar la vista en el contenedor principal
            contenidoPrincipal.getChildren().clear();
            contenidoPrincipal.getChildren().add(vista);
            
        } catch (Exception e) { mostrarError("Error al cargar la gestiÃ³n de usuarios: " + e.getMessage());
        }
    }
    
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
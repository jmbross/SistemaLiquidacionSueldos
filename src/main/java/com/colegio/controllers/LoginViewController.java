package com.colegio.views.controllers;

import com.colegio.controllers.LoginController;
import com.colegio.models.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginViewController {
    
    // InyecciÃ³n de los elementos de la interfaz definidos en el FXML
    @FXML private TextField dniField;
    @FXML private PasswordField passwordField;
    @FXML private Label mensajeError;
    
    // Controlador de la lÃ³gica de negocio
    private final LoginController loginController;
    
    public LoginViewController() {
        this.loginController = new LoginController();
    }
    
    @FXML
    private void handleLogin() {
        try {
            String dni = dniField.getText();
            String password = passwordField.getText();
            
            // Validaciones bÃ¡sicas
            if (dni.isEmpty() || password.isEmpty()) {
                mostrarError("Por favor, complete todos los campos");
                return;
            }
            
            // Intento de autenticaciÃ³n
            Usuario usuario = loginController.autenticar(dni, password);
            
            if (usuario != null) {
                // AutenticaciÃ³n exitosa
                abrirMenuPrincipal(usuario);
            } else {
                mostrarError("DNI o contraseÃ±a incorrectos");
            }
            
        } catch (Exception e) {
            mostrarError("Error al iniciar sesiÃ³n: " + e.getMessage());
        }
    }
    
    private void mostrarError(String mensaje) {
        mensajeError.setText(mensaje);
        mensajeError.setVisible(true);
    }
    
    private void abrirMenuPrincipal(Usuario usuario) {
        try {
            // Carga el menÃº principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/colegio/views/menu_principal.fxml"));
            Parent root = loader.load();
            
            // Obtiene el controlador del menÃº principal y le pasa el usuario
            MenuPrincipalViewController menuController = loader.getController();
            menuController.inicializarConUsuario(usuario);
            
            // Configura la nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Sistema de LiquidaciÃ³n de Sueldos - MenÃº Principal");
            stage.setScene(new Scene(root));
            
            // Cierra la ventana de login
            Stage loginStage = (Stage) dniField.getScene().getWindow();
            loginStage.close();
            
            // Muestra el menÃº principal
            stage.show();
            
        } catch (Exception e) {
            mostrarError("Error al abrir el menÃº principal: " + e.getMessage());
        }
    }
}
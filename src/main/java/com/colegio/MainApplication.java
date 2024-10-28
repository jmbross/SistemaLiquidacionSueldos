package com.colegio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Carga la vista de login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/colegio/views/login.fxml"));
            Parent root = loader.load();
            
            // Carga los estilos CSS
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/colegio/styles/styles.css").toExternalForm());
            
            // Configura la ventana principal
            primaryStage.setTitle("Sistema de LiquidaciÃ³n de Sueldos - Login");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
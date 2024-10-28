package com.colegio.controllers;

import com.colegio.models.Trabajador;
import com.colegio.dao.TrabajadorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.sql.SQLException;

public class GestionTrabajadoresController {
    
    @FXML private TableView<Trabajador> tablaTrabajadores;
    @FXML private TableColumn<Trabajador, Integer> colId;
    @FXML private TableColumn<Trabajador, String> colNombre;
    @FXML private TableColumn<Trabajador, String> colApellido;
    @FXML private TableColumn<Trabajador, String> colDni;
    @FXML private TableColumn<Trabajador, String> colEmail;
    @FXML private TableColumn<Trabajador, Double> colSueldoBruto;
    
    @FXML private TextField txtBuscar;
    @FXML private VBox formularioTrabajador;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtDni;
    @FXML private TextField txtEmail;
    @FXML private TextField txtSueldoBruto;
    
    private TrabajadorDAO trabajadorDAO;
    private Trabajador trabajadorSeleccionado;
    private ObservableList<Trabajador> listaTrabajadores;
    
    @FXML
    public void initialize() {
        trabajadorDAO = new TrabajadorDAO();
        listaTrabajadores = FXCollections.observableArrayList();
        
        // Configurar las columnas de la tabla
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        colDni.setCellValueFactory(cellData -> cellData.getValue().dniProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colSueldoBruto.setCellValueFactory(cellData -> cellData.getValue().sueldoBrutoProperty().asObject());
        
        // Cargar los datos
        cargarTrabajadores();
        
        // Listener para selecciÃ³n en la tabla
        tablaTrabajadores.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                trabajadorSeleccionado = newSelection;
                mostrarDatosTrabajador(newSelection);
            });
    }
    
    private void cargarTrabajadores() {
        try {
            listaTrabajadores.clear();
            listaTrabajadores.addAll(trabajadorDAO.obtenerTodos());
            tablaTrabajadores.setItems(listaTrabajadores);
        } catch (SQLException e) {
            mostrarError("Error al cargar trabajadores: " + e.getMessage());
        }
    }
    
    @FXML
    private void mostrarFormularioNuevo() {
        limpiarFormulario();
        trabajadorSeleccionado = null;
        formularioTrabajador.setVisible(true);
    }
    
    @FXML
    private void editarTrabajador() {
        if (trabajadorSeleccionado == null) {
            mostrarError("Por favor, seleccione un trabajador para editar");
            return;
        }
        mostrarDatosTrabajador(trabajadorSeleccionado);
        formularioTrabajador.setVisible(true);
    }
    
    @FXML
    private void eliminarTrabajador() {
        if (trabajadorSeleccionado == null) {
            mostrarError ("Por favor, seleccione un trabajador para eliminar");
            return;
        }
        try {
            trabajadorDAO.eliminar(trabajadorSeleccionado);
            listaTrabajadores.remove(trabajadorSeleccionado);
            mostrarMensaje("Trabajador eliminado correctamente");
        } catch (SQLException e) {
            mostrarError("Error al eliminar trabajador: " + e.getMessage());
        }
    }
    
    @FXML
    private void buscarTrabajador() {
        String textoBuscar = txtBuscar.getText().trim();
        if (textoBuscar.isEmpty()) {
            cargarTrabajadores();
        } else {
            try {
                listaTrabajadores.clear();
                listaTrabajadores.addAll(trabajadorDAO.buscarPorNombreODNI(textoBuscar));
                tablaTrabajadores.setItems(listaTrabajadores);
            } catch (SQLException e) {
                mostrarError("Error al buscar trabajadores: " + e.getMessage());
            }
        }
    }
    
    @FXML
    private void guardarTrabajador() {
        Trabajador trabajador = new Trabajador(
            txtNombre.getText(), txtApellido.getText(), txtDni.getText(), txtEmail.getText(), 
            Double.parseDouble(txtSueldoBruto.getText())
        );
        
        if (trabajadorSeleccionado == null) {
            try {
                trabajadorDAO.insertar(trabajador);
                listaTrabajadores.add(trabajador);
                mostrarMensaje("Trabajador agregado correctamente");
            } catch (SQLException e) {
                mostrarError("Error al agregar trabajador: " + e.getMessage());
            }
        } else {
            try {
                trabajadorDAO.actualizar(trabajadorSeleccionado, trabajador);
                mostrarMensaje("Trabajador actualizado correctamente");
            } catch (SQLException e) {
                mostrarError("Error al actualizar trabajador: " + e.getMessage());
            }
        }
        
        formularioTrabajador.setVisible(false);
    }
    
    @FXML
    private void cancelarEdicion() {
        formularioTrabajador.setVisible(false);
    }
    
    private void mostrarDatosTrabajador(Trabajador trabajador) {
        txtNombre.setText(trabajador.getNombre());
        txtApellido.setText(trabajador.getApellido());
        txtDni.setText(trabajador.getDni());
        txtEmail.setText(trabajador.getEmail());
        txtSueldoBruto.setText(String.valueOf(trabajador.getSueldoBruto()));
    }
    
    private void limpiarFormulario() {
        txtNombre.clear();
        txtApellido.clear();
        txtDni.clear();
        txtEmail.clear();
        txtSueldoBruto.clear();
 }
    
    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
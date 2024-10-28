package com.colegio.controllers;

import com.colegio.dao.TrabajadorDAO;
import com.colegio.dao.UsuarioTrabajadorDAO;
import com.colegio.models.Trabajador;
import java.util.List;

public class TrabajadorController {
    private final TrabajadorDAO trabajadorDAO;
    private final UsuarioTrabajadorDAO usuarioTrabajadorDAO;

    public TrabajadorController() {
        this.trabajadorDAO = new TrabajadorDAO();
        this.usuarioTrabajadorDAO = new UsuarioTrabajadorDAO();
    }

    public void guardarTrabajador(Trabajador trabajador) throws Exception {
        trabajadorDAO.guardar(trabajador);
    }

    public void actualizarTrabajador(Trabajador trabajador) throws Exception {
        trabajadorDAO.actualizar(trabajador);
    }

    public void eliminarTrabajador(int id) throws Exception {
        trabajadorDAO.eliminar(id);
    }

    public Trabajador obtenerTrabajador(int id) throws Exception {
        return trabajadorDAO.obtenerPorId(id);
    }

    public List<Trabajador> obtenerTodosTrabajadores() throws Exception {
        return trabajadorDAO.obtenerTodos();
    }

    public List<Trabajador> obtenerTrabajadoresPorUsuario(int idUsuario) throws Exception {
        return trabajadorDAO.obtenerTrabajadoresPorUsuario(idUsuario);
    }

    public void asignarTrabajadorAUsuario(int idUsuario, int idTrabajador) throws Exception {
        usuarioTrabajadorDAO.asignarTrabajadorAUsuario(idUsuario, idTrabajador);
    }

    public void desasignarTrabajadorDeUsuario(int idUsuario, int idTrabajador) throws Exception {
        usuarioTrabajadorDAO.desasignarTrabajadorDeUsuario(idUsuario, idTrabajador);
    }
}
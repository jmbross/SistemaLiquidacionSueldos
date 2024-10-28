package com.colegio.controllers;

import com.colegio.dao.AlicuotaDAO;
import com.colegio.models.Alicuota;
import java.util.List;

public class AlicuotaController {
    private final AlicuotaDAO alicuotaDAO;

    public AlicuotaController() {
        this.alicuotaDAO = new AlicuotaDAO();
    }

    public void guardarAlicuota(Alicuota alicuota) throws Exception {
        alicuotaDAO.guardar(alicuota);
    }

    public void actualizarAlicuota(Alicuota alicuota) throws Exception {
        alicuotaDAO.actualizar(alicuota);
    }

    public void eliminarAlicuota(int id) throws Exception {
        alicuotaDAO.eliminar(id);
    }

    public Alicuota obtenerAlicuota(int id) throws Exception {
        return alicuotaDAO.obtenerPorId(id);
    }

    public List<Alicuota> obtenerTodasAlicuotas() throws Exception {
        return alicuotaDAO.obtenerTodos();
    }
}
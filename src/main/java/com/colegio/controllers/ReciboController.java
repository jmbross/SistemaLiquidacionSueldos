package com.colegio.controllers;

import com.colegio.dao.ReciboDAO;
import com.colegio.dao.TrabajadorDAO;
import com.colegio.dao.AlicuotaDAO;
import com.colegio.models.Recibo;
import com.colegio.models.Trabajador;
import com.colegio.models.Alicuota;
import com.colegio.util.PDFGenerator;

import java.util.Date;
import java.util.List;

public class ReciboController {
    private final ReciboDAO reciboDAO;
    private final TrabajadorDAO trabajadorDAO;
    private final AlicuotaDAO alicuotaDAO;

    public ReciboController() {
        this.reciboDAO = new ReciboDAO();
        this.trabajadorDAO = new TrabajadorDAO();
        this.alicuotaDAO = new AlicuotaDAO();
    }

    public Recibo generarRecibo(int idTrabajador, Date fecha) throws Exception {
        Trabajador trabajador = trabajadorDAO.obtenerPorId(idTrabajador);
        List<Alicuota> alicuotas = alicuotaDAO.obtenerTodos();
        
        double sueldoBruto = trabajador.getSueldoBruto();
        double descuentos = calcularDescuentos(sueldoBruto, alicuotas);
        double sueldoNeto = sueldoBruto - descuentos;

        Recibo recibo = new Recibo();
        recibo.setIdTrabajador(idTrabajador);
        recibo.setFecha(fecha);
        recibo.setSueldoBruto(sueldoBruto);
        recibo.setSueldoNeto(sueldoNeto);

        reciboDAO.guardar(recibo);
        
        // Generar PDF del recibo
        generarPDFRecibo(recibo, trabajador, alicuotas);
        
        return recibo;
    }

    private double calcularDescuentos(double sueldoBruto, List<Alicuota> alicuotas) {
        double totalDescuentos = 0;
        for (Alicuota alicuota : alicuotas) {
            totalDescuentos += (sueldoBruto * alicuota.getPorcentaje() / 100);
        }
        return totalDescuentos;
    }

    private void generarPDFRecibo(Recibo recibo, Trabajador trabajador, List<Alicuota> alicuotas) {
        // AquÃ­ se implementarÃ¡ la generaciÃ³n del PDF usando PDFGenerator
        // Esta implementaciÃ³n dependerÃ¡ de la biblioteca PDF que decidamos usar
    }

    public List<Recibo> obtenerRecibosPorTrabajador(int idTrabajador) throws Exception {
        return reciboDAO.obtenerRecibosPorTrabajador(idTrabajador);
    }

    public List<Recibo> obtenerRecibosPorFecha(Date fechaInicio, Date fechaFin) throws Exception {
        return reciboDAO.obtenerRecibosPorFecha(fechaInicio, fechaFin);
    }

    public Recibo obtenerUltimoReciboPorTrabajador(int idTrabajador) throws Exception {
        return reciboDAO.obtenerUltimoReciboPorTrabajador(idTrabajador);
    }

    public double calcularTotalSueldosNetos(Date fechaInicio, Date fechaFin) throws Exception {
        return reciboDAO.calcularTotalSueldosNetos(fechaInicio, fechaFin);
    }
}
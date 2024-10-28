package com.colegio.dao;

import java.util.List;

public interface DAO<T> {
    T obtenerPorId(int id) throws Exception;
    List<T> obtenerTodos() throws Exception;
    void guardar(T t) throws Exception;
    void actualizar(T t) throws Exception;
    void eliminar(int id) throws Exception;
}
package com.educacionit.integrador.dao;

import java.util.List;

import com.educacionit.integrador.dao.exceptions.DBManagerException;
import com.educacionit.integrador.dao.models.Pelicula;

public interface PeliculaDao {


	List<Pelicula> buscarPorTitulo(String titulo) throws DBManagerException;
	
	List<Pelicula> obtenerTodas() throws DBManagerException;
	
	Pelicula obtener(Integer id) throws DBManagerException;

	int insertar(Pelicula pelicula) throws DBManagerException;
	
	void modificar(Pelicula pelicula) throws DBManagerException;
	
	void eliminar(Integer id) throws DBManagerException;	
	
}

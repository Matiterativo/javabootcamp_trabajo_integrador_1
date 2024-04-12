package com.educacionit.integrador.dao;

import java.util.List;

import com.educacionit.integrador.dao.exceptions.DBManagerException;
import com.educacionit.integrador.dao.models.Genero;
import com.educacionit.integrador.dao.models.Pelicula;

public interface PeliculaGeneroDao {


	List<Pelicula> buscarPeliculasPorGenero(int idGenero) throws DBManagerException;
	
	List<Genero> buscarGenerosPorPelicula(int codigoPelicula) throws DBManagerException;

	void agregarGenero(Integer codigoPelicula, Integer idGenero) throws DBManagerException;
	
	void eliminarGeneros(Integer codigoPelicula) throws DBManagerException;
	
}
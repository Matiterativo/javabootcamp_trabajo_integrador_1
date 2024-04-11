package com.educacionit.integrador.dao;

import java.util.List;

import com.educacionit.integrador.dao.exceptions.DBManagerException;
import com.educacionit.integrador.dao.models.Pelicula;

public interface PeliculaGeneroDao {


	List<Pelicula> buscarPorGenero(int idGenero) throws DBManagerException;

	
	
}
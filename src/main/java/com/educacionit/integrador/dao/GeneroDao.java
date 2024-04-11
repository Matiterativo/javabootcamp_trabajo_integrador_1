package com.educacionit.integrador.dao;

import java.util.List;
import com.educacionit.integrador.dao.exceptions.DBManagerException;
import com.educacionit.integrador.dao.models.Genero;

public interface GeneroDao {


	List<Genero> obtenerTodos() throws DBManagerException;

	
	
}

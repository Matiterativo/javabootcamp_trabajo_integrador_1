package com.educacionit.integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.integrador.dao.ds.ConnectionDB;
import com.educacionit.integrador.dao.exceptions.DBManagerException;
import com.educacionit.integrador.dao.models.Genero;
import com.educacionit.integrador.dao.models.Pelicula;

public class GeneroDaoImpl implements GeneroDao, ConnectionDB{

	@Override
	public List<Genero> obtenerTodos() throws DBManagerException {
		List<Genero> generos = new ArrayList<>();
	    String query = "SELECT g.id as id, "
	    		+ "g.nombre AS nombre "
	            + "FROM generos g";

	    try (
	    		Connection conn = getConnection();
	    		PreparedStatement statement = conn.prepareStatement(query)
	    	) {
	      
	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Integer id = resultSet.getInt("id");
	                String nombre = resultSet.getString("nombre");
	                Genero genero = new Genero(id, nombre);
	                generos.add(genero);
	            }
	        }

	        if (generos.isEmpty()) {
	            throw new DBManagerException(DBManagerException.ERROR_2, "No se encontraron peliculas para el titulo indicado.");
	        }

	    } catch (SQLException ex) {
	        throw new DBManagerException(DBManagerException.ERROR_2, "No se pudo realizar la consulta de peliculas por la siguiente razón: " + ex.getMessage(), ex);
	    }

	    return generos;
	}
	
	

}

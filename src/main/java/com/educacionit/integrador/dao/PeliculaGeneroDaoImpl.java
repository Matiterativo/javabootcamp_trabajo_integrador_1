package com.educacionit.integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.integrador.dao.ds.ConnectionDB;
import com.educacionit.integrador.dao.exceptions.DBManagerException;
import com.educacionit.integrador.dao.models.Pelicula;

public class PeliculaGeneroDaoImpl implements PeliculaGeneroDao, ConnectionDB{

	@Override
	public List<Pelicula> buscarPorGenero(int idGenero) throws DBManagerException {
		List<Pelicula> peliculas = new ArrayList<>();
	    String query = "SELECT p.id AS codigo, "
	    		+ "p.titulo AS titulo  "
	            + "FROM peliculas p, peliculas_generos pg "
	            + "WHERE p.id= pg.pelicula_id AND pg.genero_id = ? ";

	    try (
	    		Connection conn = getConnection();
	    		PreparedStatement statement = conn.prepareStatement(query)
	    	) {
	        statement.setInt(1, idGenero);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Integer codigo = resultSet.getInt("codigo");
	                String titulo = resultSet.getString("titulo");
	                Pelicula pelicula = new Pelicula(codigo, titulo);
	                peliculas.add(pelicula);
	            }
	        }

	        if (peliculas.isEmpty()) {
	            throw new DBManagerException(DBManagerException.ERROR_2, "No se encontraron peliculas para el titulo indicado.");
	        }

	    } catch (SQLException ex) {
	        throw new DBManagerException(DBManagerException.ERROR_2, "No se pudo realizar la consulta de peliculas por la siguiente razón: " + ex.getMessage(), ex);
	    }

	    return peliculas;
	}

}

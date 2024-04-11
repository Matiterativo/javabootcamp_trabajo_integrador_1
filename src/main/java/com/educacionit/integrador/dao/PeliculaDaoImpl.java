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

public class PeliculaDaoImpl implements PeliculaDao, ConnectionDB{

	@Override
	public List<Pelicula> buscarPorTitulo(String tituloBusqueda) throws DBManagerException {
		List<Pelicula> peliculas = new ArrayList<>();
	    String query = "SELECT p.id AS codigo, "
	    		+ "p.titulo AS titulo, p.sitio_oficial AS sitio_oficial "
	            + "FROM peliculas p "
	           // + "JOIN provincias pr ON p.id = pr.pais_id "
	            + "WHERE p.titulo LIKE ?";

	    try (
	    		Connection conn = getConnection();
	    		PreparedStatement statement = conn.prepareStatement(query)
	    	) {
	        statement.setString(1, "%" + tituloBusqueda + "%");

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Integer codigo = resultSet.getInt("codigo");
	                String titulo = resultSet.getString("titulo");
	                String sitioOficial = resultSet.getString("sitio_oficial");
	                Pelicula pelicula = new Pelicula(codigo, titulo, sitioOficial);
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

	@Override
	public int insertar(Pelicula pelicula) throws DBManagerException  {
		String query = "INSERT INTO peliculas (titulo, sitio_oficial, imagen_promocional) VALUES (?,?, ?)";
	    int idPeliculaInsertada = -1; // Valor predeterminado en caso de que no se pueda obtener el ID

	    try (
	        Connection conn = getConnection();
	        PreparedStatement statement = conn.prepareStatement(query);
	    ) {
	        statement.setString(1, pelicula.getTitulo());
	        statement.setString(2, pelicula.getUrlSitioOficial());
	        statement.setBytes(3, pelicula.getImagenPromocional());        

	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new DBManagerException(DBManagerException.ERROR_4,"No se pudo insertar la película: " + pelicula.getTitulo());
	        }       

	        // Obtener el último ID insertado mediante una consulta adicional
	        String queryLastInsertedId = "SELECT LAST_INSERT_ID()";
	        try (PreparedStatement lastIdStatement = conn.prepareStatement(queryLastInsertedId);
	             ResultSet rs = lastIdStatement.executeQuery()) {
	            if (rs.next()) {
	                idPeliculaInsertada = rs.getInt(1);
	            } else {
	                throw new DBManagerException(DBManagerException.ERROR_4, "No se pudo obtener el ID de la película insertada");
	            }
	        }

	    } catch (SQLException ex) {
	        throw new DBManagerException(DBManagerException.ERROR_4,"No se pudo insertar la película: " + pelicula.getTitulo(), ex);
	    }

	    return idPeliculaInsertada;
		
	}

	@Override
	public void modificar(Pelicula pelicula) throws DBManagerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) throws DBManagerException {
		// TODO Auto-generated method stub
		
	}

	
	
}

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

public class PeliculaDaoImpl implements PeliculaDao, ConnectionDB{

	@Override
	public List<Pelicula> buscarPorTitulo(String tituloBusqueda) throws DBManagerException {
		List<Pelicula> peliculas = new ArrayList<>();
	    String query = "SELECT p.id AS codigo, "
	    		+ "p.titulo AS titulo, p.sitio_oficial AS sitio_oficial "
	            + "FROM peliculas p "
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
	    int idPeliculaInsertada = -1;

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
		String query = "UPDATE peliculas SET titulo= ?, sitio_oficial=?, imagen_promocional=? "
				+ "WHERE peliculas.id = ?";
		 try (
	        Connection conn = getConnection();
	        PreparedStatement statement = conn.prepareStatement(query);
	    ) {
	        statement.setString(1, pelicula.getTitulo());
	        statement.setString(2, pelicula.getUrlSitioOficial());
	        statement.setBytes(3, pelicula.getImagenPromocional()); 
	        statement.setInt(4, pelicula.getCodigo());   

	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new DBManagerException(DBManagerException.ERROR_4,"No se pudo modificar la película: " + pelicula.getTitulo());
	        }       
	    } catch (SQLException ex) {
	        throw new DBManagerException(DBManagerException.ERROR_4,"No se pudo modificar la película: " + pelicula.getTitulo(), ex);
	    }

		
	}

	@Override
	public void eliminar(Integer id) throws DBManagerException {
	    String queryPeliculasGeneros = "DELETE FROM peliculas_generos WHERE pelicula_id = ?";
	    String queryPeliculas = "DELETE FROM peliculas WHERE id = ?";
	    
	    try (Connection conn = getConnection();
	         PreparedStatement statementPeliculasGeneros = conn.prepareStatement(queryPeliculasGeneros);
	         PreparedStatement statementPeliculas = conn.prepareStatement(queryPeliculas)) {
	        
	        // Comenzar transacción
	        conn.setAutoCommit(false);
	        
	        // Eliminar registros de peliculas_generos
	        statementPeliculasGeneros.setInt(1, id);
	        int rowsAffectedPeliculasGeneros = statementPeliculasGeneros.executeUpdate();
	        
	        // Eliminar registro de peliculas
	        statementPeliculas.setInt(1, id);
	        int rowsAffectedPeliculas = statementPeliculas.executeUpdate();
	        
	        // Confirmar la transacción si ambas eliminaciones fueron exitosas
	        if (rowsAffectedPeliculasGeneros > 0 && rowsAffectedPeliculas > 0) {
	            conn.commit();
	        } else {
	            conn.rollback(); // Revertir la transacción si ocurrió algún problema
	            throw new DBManagerException(DBManagerException.ERROR_4, "No se pudo eliminar la película");
	        }
	        
	    } catch (SQLException ex) {
	        throw new DBManagerException(DBManagerException.ERROR_4, "Error al eliminar la película", ex);
	    }
	}

	@Override
	public List<Pelicula> obtenerTodas() throws DBManagerException {
		List<Pelicula> peliculas = new ArrayList<>();
	    String query = "SELECT p.id AS codigo,p.sitio_oficial as sitio_oficial, "
	    		+ "p.titulo AS titulo "
	            + "FROM peliculas p ";

	    try (
	    		Connection conn = getConnection();
	    		PreparedStatement statement = conn.prepareStatement(query)
	    	) {

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
	            throw new DBManagerException(DBManagerException.ERROR_3, "No se encontraron peliculas.");
	        }

	    } catch (SQLException ex) {
	        throw new DBManagerException(DBManagerException.ERROR_3, "No se pudo realizar la consulta de peliculas por la siguiente razón: " + ex.getMessage(), ex);
	    }

	    return peliculas;
	}

	@Override
	public Pelicula obtener(Integer id) throws DBManagerException {
		Pelicula pelicula = null;
		
	    String query = "SELECT p.id AS codigo, p.titulo as titulo, p.sitio_oficial as sitio_oficial "
	            + "FROM peliculas p "
	            + "WHERE p.id = ?";

	    try (
	    		Connection conn = getConnection();
	    		PreparedStatement statement = conn.prepareStatement(query)
	    	) {
	        statement.setInt(1, id);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                String peliculaTitulo= resultSet.getString("titulo");
	                String peliculaSitioOficial= resultSet.getString("sitio_oficial");
	                pelicula = new Pelicula(id, peliculaTitulo,peliculaSitioOficial );
	                
	            }
	        }

	    } catch (SQLException ex) {
	        throw new DBManagerException(DBManagerException.ERROR_2, "No se pudo realizar la consulta de peliculas por la siguiente razón: " + ex.getMessage(), ex);
	    }

	    return pelicula;
	}

	
	
}

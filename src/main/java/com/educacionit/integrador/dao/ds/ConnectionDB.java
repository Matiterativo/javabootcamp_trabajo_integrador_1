package com.educacionit.integrador.dao.ds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.educacionit.integrador.dao.exceptions.DBManagerException;

public interface ConnectionDB {	
	
	default Connection getConnection() throws DBManagerException {
		try {
			
			final String DBURL = "jdbc:mysql://172.17.0.2/peligestiondb";
		    final String DBUSER = "root";
		    final String DBPASSWORD = "toor";
		    
			Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
			
			return conn;		
		
		} catch (SQLException ex) {
			
			throw new DBManagerException(DBManagerException.ERROR_1, "No se pudo conectar a la base de datos", ex);
			
		}
	}

}

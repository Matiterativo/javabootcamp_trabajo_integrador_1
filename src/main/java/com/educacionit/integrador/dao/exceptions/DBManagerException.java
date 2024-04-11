package com.educacionit.integrador.dao.exceptions;

public class DBManagerException extends Exception {
	
	/*
	 * Error 1: conectar a la db
	 * Error 2: buscar provincias por pais
	 * Error 3: obtener paises
	 * Error 4: insertar provincia
	 * Error 5: modificar provincia
	 * Error 6: eliminar provincia
	 * Error 7: cerrar conexion a la db
	 */
	
	public static final int ERROR_1= 1;
	public static final int ERROR_2= 2;
	public static final int ERROR_3= 3;
	public static final int ERROR_4= 4;
	public static final int ERROR_5= 5;
	public static final int ERROR_6= 6;
	public static final int ERROR_7= 7;

	private Integer error;
	
	public DBManagerException(Integer error, String message) {
		super(message);
		this.error = error;
	}

	public DBManagerException(Integer error, Throwable cause) {
		super(cause);
		this.error = error;

	}

	public DBManagerException(Integer error, String message, Throwable cause) {
		super(message, cause);
		this.error = error;

	}

	@Override
	public String getMessage() {
		
		switch (error) {		
		case ERROR_1: 
			return "Se produjo un error conectando a la base de datos: " + super.getMessage();
		case ERROR_2: 
			return "Se produjo un error al buscar peliculas por titulo: " + super.getMessage();
		case ERROR_3: 
			return "Se produjo un error al obtener los paises: " + super.getMessage();
		case ERROR_4: 
			return "Se produjo un error al agregar una provincia: " + super.getMessage();
		case ERROR_5: 
			return "Se produjo un error al modificar la provincia: " + super.getMessage();
		case ERROR_6: 
			return "Se produjo un error al eliminar la provincia: " + super.getMessage();
		case ERROR_7: 
			return "Se produjo un error cerrando la conexión a la base de datos: " + super.getMessage();		
		default:
			return super.getMessage();		
		}
		
	}
	
}

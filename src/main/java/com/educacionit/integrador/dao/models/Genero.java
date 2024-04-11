package com.educacionit.integrador.dao.models;

public class Genero {
	
	private int id;
    private String nombre;
	
    public Genero(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}
    

}

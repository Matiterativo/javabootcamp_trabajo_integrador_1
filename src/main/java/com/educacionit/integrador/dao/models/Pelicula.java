package com.educacionit.integrador.dao.models;

import java.util.List;

public class Pelicula {
	
	private int codigo;
    private String titulo;
    private String urlSitioOficial;
    private String imagenPromocional;
    private List<Genero> generos;
    
 
    
    
	public Pelicula(int codigo, String titulo) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
	}



	public Pelicula(int codigo, String titulo, String urlSitioOficial) {
		super();
		this.titulo = titulo;
		this.urlSitioOficial = urlSitioOficial;
		this.codigo = codigo;
	}
	
	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrlSitioOficial() {
		return urlSitioOficial;
	}

	public void setUrlSitioOficial(String urlSitioOficial) {
		this.urlSitioOficial = urlSitioOficial;
	}

	public String getImagenPromocional() {
		return imagenPromocional;
	}

	public void setImagenPromocional(String imagenPromocional) {
		this.imagenPromocional = imagenPromocional;
	}

	public int getCodigo() {
		return codigo;
	}

	public List<Genero> getGeneros() {
		return generos;
	}  
       
    
}

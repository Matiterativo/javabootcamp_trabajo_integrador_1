package com.educacionit.integrador.dao.models;

import java.util.List;

public class Pelicula {
	
	@Override
	public String toString() {
		return "Pelicula [codigo=" + codigo + ", titulo=" + titulo + ", urlSitioOficial=" + urlSitioOficial + "]";
	}

	private int codigo;
    private String titulo;
    private String urlSitioOficial;
    private byte[] imagenPromocional;
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


	public Pelicula(String titulo, String urlSitioOficial, byte[] imagenPromocional) {
		super();
		this.titulo = titulo;
		this.urlSitioOficial = urlSitioOficial;
		this.imagenPromocional = imagenPromocional;
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

	public byte[] getImagenPromocional() {
		return imagenPromocional;
	}

	public void setImagenPromocional(byte[] imagenPromocional) {
		this.imagenPromocional = imagenPromocional;
	}

	public int getCodigo() {
		return codigo;
	}

	public List<Genero> getGeneros() {
		return generos;
	}  
       
    
}

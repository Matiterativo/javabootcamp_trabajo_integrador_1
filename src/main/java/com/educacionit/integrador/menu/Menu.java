package com.educacionit.integrador.menu;

import java.util.Scanner;

public class Menu {
	
	//Opciones del menu principal
	public static final int MENU_PRINCIPAL_BUSCAR_POR_TITULO = 1;
    public static final int MENU_PRINCIPAL_BUSCAR_POR_GENERO =2;
    public static final int MENU_PRINCIPAL_ADMINISTRAR_PELICULAS = 3;
    public static final int MENU_PRINCIPAL_ADMINISTRAR_GENEROS = 4;
    
    //Opciones comunes de menu
    public static final int MENU_SALIR = 0;
    
    //Opciones del submenu de abm de peliculas
    public static final int MENU_ADMINISTRAR_PELICULAS_ALTA = 1;
    public static final int MENU_ADMINISTRAR_PELICULAS_MODIFICAR =2;
    public static final int MENU_ADMINISTRAR_PELICULAS_ELIMINAR=3;
	
	public static void imprimirMenuPrincipal() {
        System.out.println("\n****** Menú Principal ******\n");
        System.out.println("1. Buscar películas por título");
        System.out.println("2. Buscar películas por género");
        System.out.println("3. Administrar películas");
        System.out.println("0. Salir");
	        
	  }
	 
	public static void imprimirSubmenuAdministracionPeliculas(){
		
		System.out.println("\n***** Administrar películas *****\n");
        System.out.println("1. Agregar");
        System.out.println("2. Modificar");
        System.out.println("3. Eliminar");
        System.out.println("0. Volver al menú anterior");
		
	}
	

}

package com.educacionit.main;
import java.util.Scanner;

import com.educacionit.menu.Menu;

public class Main {
	
	private static final Scanner teclado = new Scanner(System.in);
	
    public static void main(String[] args) {
    	
    	imprimirCabeceraPrograma();
    	
    	Integer opcion = null;
    
        do {       
        	
        	Menu.imprimirMenuPrincipal();
        	
        	opcion = Menu.obtenerOpcion(teclado);
        	
        	switch (opcion) {
            case Menu.MENU_PRINCIPAL_BUSCAR_POR_TITULO:
            	buscarPeliculasPorTitulo();
                break;
            case Menu.MENU_PRINCIPAL_BUSCAR_POR_GENERO:
            	buscarPeliculasPorGenero();
                break;
            case Menu.MENU_PRINCIPAL_ADMINISTRAR_PELICULAS:
            	administrarPeliculas();
                break;
            case Menu.MENU_PRINCIPAL_ADMINISTRAR_GENEROS:
            	administrarGeneros();
                break;
            case Menu.MENU_SALIR:
            	System.out.println("\nGracias por utilizar PELIGESTION!");
                break;
            default:            	
                System.out.println("Opción inválida. Por favor ingrese una opción válida \n");
        	}        	
        	
        } while (opcion !=0);        

    	teclado.close();
    	
    }
    
    //Imprime el texto de bienvenida del programa
    private static void imprimirCabeceraPrograma(){
            System.out.println("****************************************************");
            System.out.println("*                Bienvenido a PELIGESTION          *");
            System.out.println("*               Administrador de Películas         *");
            System.out.println("*                    Versión 1.0                   *");
            System.out.println("****************************************************");
    }
    
    private static void buscarPeliculasPorTitulo() {
  
        Integer opcion = null;
           
        do {
        	
        	System.out.println("\n***** Búsqueda de películas por título *****");
        	System.out.println("\nIngrese el título o parte del mismo para buscar: ");
        	
        	String titulo = teclado.nextLine();
        	
        	//Realizo la busqueda de las peliculas por titulo
        	
        	//Si no hay resultados mostrar mensaje correspondiente
        	
        	//Si hay resultados
        	
        	System.out.println("\nResultados de su búsqueda para el texto \"" + titulo + "\": \n");
        	
        	//El siguiente listado es de ejemplo
        		
    		System.out.println("1. El padrino");
    		System.out.println("2. The Matrix");
    		System.out.println("3. Titanic");
    		System.out.println("4. V de venganza");
        	
        	Menu.imprimirSubmenuBusqueda();
        		
        	opcion= Menu.obtenerOpcion(teclado);
        	
        	switch (opcion) {
            case Menu.MENU_BUSQUEDA_BUSCAR_POR_TITULO:
                continue;
            case Menu.MENU_BUSQUEDA_DETALLES_PELICULA:
            	verDetallesPelicula();
                break;
            case Menu.MENU_SALIR:
            	break;
            default:            	
                System.out.println("Opción inválida. Por favor ingrese una opción válida \n");
        	}        	
        	
        }while (opcion !=0);    
    	
    }
    
    private static void buscarPeliculasPorGenero() {
    	
    	Integer opcion = null;
        do {
	    	System.out.println("\n***** Búsqueda de películas por género *****\n");
	    	
	    	//Obtener los generos e imprimirlos	    	
	    	//El siguiente listado es de ejemplo
			
			System.out.println("1. Acción");
			System.out.println("2. Romántica");
			System.out.println("3. Terror");
			System.out.println("4. Suspenso");
	    	
	    	Integer genero = Menu.obtenerOpcion(teclado);	    	
	    	
	    	//Cambiar genero al nombre del genero
	    	System.out.println("\nResultados de su búsqueda para el género \"" + genero + "\": \n");
	    	
	    	//El siguiente listado es de ejemplo
	    		
			System.out.println("1. El padrino");
			System.out.println("2. The Matrix");
			System.out.println("3. Titanic");
			System.out.println("4. V de venganza");        
        	
        	Menu.imprimirSubmenuBusqueda();
        		
        	opcion= Menu.obtenerOpcion(teclado);
        	
        	switch (opcion) {
            case Menu.MENU_BUSQUEDA_BUSCAR_POR_TITULO:
            	continue;
            case Menu.MENU_BUSQUEDA_DETALLES_PELICULA:
            	verDetallesPelicula();
                break;
            case Menu.MENU_SALIR:
            	break;
            default:            	
                System.out.println("Opción inválida. Por favor ingrese una opción válida \n");
        	}        	
        	
        }while (opcion !=0);    
		
    	
    };
    
    private static void administrarPeliculas() {
    	
    	Integer opcion = null;
    	
    	do {
    	
	    	//Imprimo menu de opciones ABML
	    	Menu.imprimirSubmenuAdministracionPeliculas();
	    	
	    	//Pido al usuario opcion
	    	opcion= Menu.obtenerOpcion(teclado);
	    	
	    	//Dependiendo de la opcion llamo al metodo correspondiente
	    	
	    	
	    	
	    	
    	
    	}while (opcion !=0);   	
    	
    }
        
    private static void administrarGeneros() {
    	
    	Integer opcion = null;
    	
    	do {
    	
	    	//Imprimo menu de opciones ABML
    		Menu.imprimirSubmenuAdministracionGeneros();
	    	
	    	//Pido al usuario opcion
	    	opcion= Menu.obtenerOpcion(teclado);
	    	
	    	//Dependiendo de la opcion llamo al metodo correspondiente
    	
    	}while (opcion !=0);
    	
    	
    }
    
    private static void verDetallesPelicula() {
    	
    }
    
}


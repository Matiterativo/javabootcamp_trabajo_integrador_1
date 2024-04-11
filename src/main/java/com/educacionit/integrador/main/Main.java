package com.educacionit.integrador.main;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.educacionit.integrador.dao.GeneroDao;
import com.educacionit.integrador.dao.GeneroDaoImpl;
import com.educacionit.integrador.dao.PeliculaDao;
import com.educacionit.integrador.dao.PeliculaDaoImpl;
import com.educacionit.integrador.dao.PeliculaGeneroDao;
import com.educacionit.integrador.dao.PeliculaGeneroDaoImpl;
import com.educacionit.integrador.dao.exceptions.DBManagerException;
import com.educacionit.integrador.dao.models.Genero;
import com.educacionit.integrador.dao.models.Pelicula;
import com.educacionit.integrador.menu.Menu;

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
        
        	String tituloBusqueda = teclado.nextLine();       	
        	
        	PeliculaDao peliculaDao = new PeliculaDaoImpl();
        	
        	List<Pelicula> peliculas;
        	
			try {
				peliculas = peliculaDao.buscarPorTitulo(tituloBusqueda);
				
	        	System.out.println("\nResultados de su búsqueda para el texto \"" + tituloBusqueda + "\": \n");
				
				for (Pelicula pelicula : peliculas) {
	                System.out.println(pelicula.getCodigo() + ". " + pelicula.getTitulo());
	            }
				
			} catch (DBManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
        	
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
	    	
	    	GeneroDao generoDao = new GeneroDaoImpl();
	    	
	    	List<Genero> generos;
			try {
				generos = generoDao.obtenerTodos();
				for (Genero genero : generos) {
	                System.out.println(genero.getId() + ". " + genero.getNombre());
	            }  
			} catch (DBManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    	
	    	  	
	    	
	    	PeliculaGeneroDao peliculaGeneroDao = new PeliculaGeneroDaoImpl();
	    	
	    	Integer idGenero = Menu.obtenerOpcion(teclado);
	    	
	    	List<Pelicula> peliculas;
	    	
	    	try {
				peliculas = peliculaGeneroDao.buscarPorGenero(idGenero);
				
	        	System.out.println("\nResultados de su búsqueda para el ǵenero " + idGenero + ": \n");
				
				for (Pelicula pelicula : peliculas) {
	                System.out.println(pelicula.getCodigo() + ". " + pelicula.getTitulo());
	            }
				
			} catch (DBManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    	
        	
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
	    	switch (opcion) {
            case Menu.MENU_ADMINISTRAR_PELICULAS_ALTA:
            	altaPelicula();
            	break;
            case Menu.MENU_ADMINISTRAR_PELICULAS_MODIFICAR:
            	modificarPelicula();
                break;
            case Menu.MENU_ADMINISTRAR_PELICULAS_ELIMINAR:
            	eliminarPelicula();
            	break;
            default:            	
                System.out.println("Opción inválida. Por favor ingrese una opción válida \n");
        	}        	
    	
    	}while (opcion !=0);   	
    	
    }
    
    
    private static void verDetallesPelicula() {
    	
    }
    private static void altaPelicula() {


        System.out.println("Ingrese el título de la película:");
        String titulo = teclado.nextLine();

        System.out.println("Ingrese la URL del sitio oficial:");
        String urlSitioOficial = teclado.nextLine();

        System.out.println("Ingrese la ruta de la imagen promocional:");
        String rutaImagen = teclado.nextLine();

        
        byte[] imagenPromocional = null;
        
        
        try {
            imagenPromocional = leerImagenComoBytes(rutaImagen);
        } catch (IOException e) {
            System.out.println("Error al leer la imagen: " + e.getMessage());
            return; 
        }
                    
            
        GeneroDao generoDao = new GeneroDaoImpl();
    	
    	List<Genero> generos=null;
    	
		try {
			generos = generoDao.obtenerTodos();
			for (Genero genero : generos) {
                System.out.println(genero.getId() + ". " + genero.getNombre());
            }  
		} catch (DBManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		
		boolean agregarMasGeneros = true;
		List<Genero> generosAlta=new ArrayList();
        
        do {
            System.out.println("\nIngrese el id del género para la película:");
            Integer idGenero = teclado.nextInt();
            teclado.nextLine();
            
            Genero genero;
            
			try {
				genero = generoDao.obtener(idGenero);
				generosAlta.add(genero);

			} catch (DBManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

           
            System.out.println("¿Desea agregar otro género? (S/N)");
            String respuesta = teclado.nextLine();

            if (!respuesta.equalsIgnoreCase("S")) {
                agregarMasGeneros = false;
            }
        } while (agregarMasGeneros);

        
        Pelicula pelicula = new Pelicula(titulo, urlSitioOficial, imagenPromocional);

        try {
            PeliculaDao peliculaDao = new PeliculaDaoImpl(); 
            
            int idInsertada = peliculaDao.insertar(pelicula);
            
            
            // Insertar las relaciones entre la película y sus géneros en la base de datos
            PeliculaGeneroDao peliculaGeneroDao = new PeliculaGeneroDaoImpl();
            for (Genero genero : generosAlta) {
                peliculaGeneroDao.agregarGenero(idInsertada, genero.getId());
            }
            
            System.out.println("La película se ha insertado correctamente.");
            
        } catch (DBManagerException e) {
            System.out.println("Error al insertar la película: " + e.getMessage());
        }   	
    	
    }

    
    private static byte[] leerImagenComoBytes(String rutaImagen) throws IOException {
        File file = new File(rutaImagen);
        byte[] bytesArray = new byte[(int) file.length()];

        try (InputStream inputStream = new FileInputStream(file)) {
            inputStream.read(bytesArray);
        }

        return bytesArray;
    }
    
    
    private static void modificarPelicula() {
    	
    }
    private static void eliminarPelicula() {
    	
    }
}


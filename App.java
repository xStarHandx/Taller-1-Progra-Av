package taller1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws FileNotFoundException{
		int contUsuario= contarUsuarios();
		int contCreador= contarCreador();
		int contIa= contarIa();
		String[] usuarios= crearListaUsuarios(contUsuario);
		String[] creadores= crearListaCreadores(contCreador);
		String[] ias= crearListaIa(contIa);
		
		
		
		//IMPRESIONES PARA VERIFICAR QUE FUNCIONA LA WEA
		imprimir(usuarios);
		System.out.println("--------------------");
		imprimir(creadores);
		System.out.println("--------------------");
		imprimir(ias);
		
		
		
	}
	//FUNCIONES PARA PODER CONTAR LA CANTIDAD DE USUARIOS, CREADORES E IAS QUE HAY EN EL ARCHIVO TXT
	private static int contarUsuarios() throws FileNotFoundException {
		File archivo= new File("datos_usuarios.txt");
		Scanner read= new Scanner(archivo,"UTF-8");
		int cont=0;
		while (read.hasNextLine()) {
			String linea= read.nextLine();
			cont++;
		}
		return cont;
	}
	
	private static int contarCreador() throws FileNotFoundException {
		File archivo= new File("datos_creadores.txt");
		Scanner read= new Scanner(archivo,"UTF-8");
		int cont=0;
		while (read.hasNextLine()) {
			String linea= read.nextLine();
			cont++;
		}
		return cont;
	}
	
	private static int contarIa() throws FileNotFoundException {
		File archivo= new File("datos_ia.txt");
		Scanner read= new Scanner(archivo,"UTF-8");
		int cont=0;
		while (read.hasNextLine()) {
			String linea= read.nextLine();
			cont++;
		}
		return cont;
	}
	
	//FUNCIONES PARA GUARDAR LOS DATOS DE LOS ARCHIVOS EN LISTAS
	private static String[] crearListaUsuarios(int cont) throws FileNotFoundException {
		File archivo= new File("datos_usuarios.txt");
		Scanner read = new Scanner(archivo,"UTF-8");
		String[]listaUsuarios= new String[cont];
		int aux=0;
		while (read.hasNextLine()) {
			String linea= read.nextLine();
			listaUsuarios[aux]=linea;
			aux++;
		}
		read.close();
		return listaUsuarios;
	}
	
	private static String[] crearListaCreadores(int cont) throws FileNotFoundException {
		File archivo= new File("datos_creadores.txt");
		Scanner read = new Scanner(archivo,"UTF-8");
		String[]listaCreadores= new String[cont];
		int aux=0;
		while (read.hasNextLine()) {
			String linea= read.nextLine();
			listaCreadores[aux]=linea;
			aux++;
		}
		read.close();
		return listaCreadores;
	}
	
	private static String[] crearListaIa(int cont) throws FileNotFoundException {
		File archivo= new File("datos_ia.txt");
		Scanner read = new Scanner(archivo,"UTF-8");
		String[]listaIa= new String[cont];
		int aux=0;
		while (read.hasNextLine()) {
			String linea= read.nextLine();
			listaIa[aux]=linea;
			aux++;
		}
		read.close();
		return listaIa;
	}
	
	
	//PROCEDIMIENTO PARA IMPRIMIR LAS LISTAS
	private static void imprimir(String[]lista) {
		for(int i=0;i<lista.length;i++) {
			System.out.println(lista[i]);
		}
	}

}

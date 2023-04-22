package taller1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws FileNotFoundException{
		int contUsuario= contador("datos_usuarios.txt");
		int contCreador= contador("datos_creadores.txt");
		int contIa= contador("datos_ia.txt");
		String[] usuarios= leerArchivo("datos_usuarios.txt",contUsuario);
		String[] creadores= leerArchivo("datos_creadores.txt",contCreador);
		String[] ias= leerArchivo("datos_ia.txt",contIa);
		
		
		
		//IMPRESIONES PARA VERIFICAR QUE FUNCIONA LA WEA
		imprimir(usuarios);
		System.out.println("--------------------");
		imprimir(creadores);
		System.out.println("--------------------");
		imprimir(ias);
	}
	
	//Función para conocer la cantidad de lineas que tiene el archivo, retornará la cantidad de lineas
	private static int contador(String arch) throws FileNotFoundException {
		File archivo= new File(arch);
		Scanner read= new Scanner(archivo,"UTF-8");
		int cont=0;
		while(read.hasNextLine()) {
			String linea= read.nextLine();
			cont++;
		}
		return cont;
	}
	
	//FUNCIONES PARA GUARDAR LOS DATOS DE LOS ARCHIVOS EN UNA LISTA
	private static String[] leerArchivo(String nombre,int cont) throws FileNotFoundException {
		File archivo= new File(nombre);
		Scanner read= new Scanner(archivo,"UTF-8");
		String[]lista= new String[cont];
		int aux=0;
		while(read.hasNextLine()) {
			String linea= read.nextLine();
			lista[aux]=linea;
			aux++;
		}
		read.close();
		return lista;
	}
	
	//PROCEDIMIENTO PARA IMPRIMIR LAS LISTAS
	private static void imprimir(String[]lista) {
		for(int i=0;i<lista.length;i++) {
			System.out.println(lista[i]);
		}
	}

}

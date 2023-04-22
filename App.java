package taller1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws FileNotFoundException{
		//CONTADORES DE LAS LINEAS
		int contUsuario= contador("datos_usuarios.txt");
		int contCreador= contador("datos_creadores.txt");
		int contIa= contador("datos_ia.txt");
		//CREACION DE LAS MATRICES
		String[][]mxUsuario= crearMatriz("datos_usuarios.txt",contUsuario,true);
		String[][]mxCreador= crearMatriz("datos_creadores.txt",contCreador,true);
		String[][]mxIa= crearMatriz("datos_ia.txt",contIa,false);
		
		
		
		
		//IMPRECIONES DE LAS MATRICES
		imprimirMx(mxUsuario);
		System.out.println("--------------------");
		imprimirMx(mxCreador);
		System.out.println("--------------------");
		imprimirMx(mxIa);
		
		
	}
	//Función para conocer la cantidad de lineas que tiene el archivo, retornará la cantidad de lineas
	private static int contador(String arch) throws FileNotFoundException {
		File archivo= new File(arch);
		Scanner read= new Scanner(archivo,"UTF-8");
		int cont=0;
		while(read.hasNextLine()) {
			read.nextLine();
			cont++;
		}
		read.close();
		return cont;
	}

	//FUNCION PARA CREAR UNA MATRIZ CON LOS DATOS DEL ARCHIVO
	private static String[][] crearMatriz(String nombre,int cont,boolean x) throws FileNotFoundException{
		int columna;
		//cambiará el tamaño de la matriz segun los datos
		if(x==true) {
			columna=4;
		}else {
			columna=5;
		}
		//LECTURA DEL ARCHIVO Y CREACION DE LA MATRIZ
		File archivo= new File(nombre);
		Scanner read= new Scanner(archivo,"UTF-8");
		String[][]mx= new String[cont][columna];
		int aux=0;//ESTO NOS MOVERÁ DE FILA
		while(read.hasNextLine()) {
			String linea= read.nextLine();
			String[]partes= linea.split(",");
			//INGRESAR LAS PARTES A LA MATRIZ COMO STRING
			for(int i=0;i<columna;i++) {
				mx[aux][i]=partes[i];
			}
			aux++;
		}
		read.close();
		return mx;
	}
	
	
	private static void imprimirMx(String[][] mx) {
		for(int i=0;i<mx.length;i++) {//RECORRER FILAS
			for(int j=0;j<mx[i].length;j++) {//RECORRER COLUMNAS
				System.out.print(mx[i][j]+",");
			}
			System.out.println();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-----------------------------------------PAPELERA RECICLAJE----------------------------------------------------
	//FUNCIONES PARA GUARDAR LOS DATOS DE LOS ARCHIVOS EN UNA LISTA
	
	/*
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
	
	*/

}

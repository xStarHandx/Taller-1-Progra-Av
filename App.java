package taller1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
QUE HACE CADA FUNCION:
-------------------------------Lectura-------------------------------------------------
1)contador------->Cuenta la cantidad de lineas que tiene el archivo
2)crearMatriz---->Crea una matriz de String (textos) con las lineaas del archivo
------------------------Extracción de informacion--------------------------------------
3)extraerInfo---->Crea una matriz de Int (numeros enteros) con la matriz de String (textos)

------------------------Impresiones por pantalla---------------------------------------
4)imprimirMx----->Imprime una matriz de String (textos)
5)imprimirMxNum-->Imprime una matriz de Int (números enteros)
6)imprimirLista-->Imprime una lista de Int (números enteros)

*/




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
		int[][]infoCreadores= extraerInfo(mxCreador,contCreador,true);
		int[][]infoIas= extraerInfo(mxIa,contIa,false);
		
		
		
		imprimirMxNum(infoCreadores);
		imprimirMxNum(infoIas);
		/*
		//IMPRECIONES DE LAS MATRICES
		imprimirMx(mxUsuario);
		System.out.println("--------------------");
		imprimirMx(mxCreador);
		System.out.println("--------------------");
		//imprimirMx(mxIa);
		*/
		
	}
	
	//------------------EXTRAER INFORMACION CREADORES-----------------
	private static int[][] extraerInfo(String[][]mx,int cont,boolean x) {
		int col1,col2;
		//Esto nos permitirá leer los 3 archivos de distintos tamaños
		if(x==true) {
			col1=1;
			col2=3;
		}else{
			col1=2;
			col2=5;
		}
		int[][]mxInfo= new int[cont][2];
		for(int i=0;i<mx.length;i++) {
			mxInfo[i][0]= Integer.valueOf(mx[i][col1]);
			mxInfo[i][1]=Integer.valueOf(mx[i][col2]);
		}
		return mxInfo;
	}
	
	//----------------Función para conocer la cantidad de lineas que tiene el archivo, retornará la cantidad de lineas
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
	
	//-----------------------FUNCION PARA CREAR UNA MATRIZ CON LOS DATOS DEL ARCHIVO
	private static String[][] crearMatriz(String nombre,int cont,boolean x) throws FileNotFoundException{
		//cambiará el tamaño de la matriz segun los datos
		int columna;
		if(x==true) {
			columna=4;
		}else {
			columna=6;
		}
		String[][]mx= new String[cont][columna];
		//LECTURA DEL ARCHIVO Y CREACION DE LA MATRIZ
		try {
			Scanner read= new Scanner(new File(nombre));
			int aux=0;//ESTO NOS MOVERÁ DE FILA
			while(read.hasNextLine()) {
				String linea= read.nextLine().replaceAll(" ", "");
				String[]partes= linea.split(",");
				//INGRESAR LAS PARTES A LA MATRIZ COMO STRING
				for(int i=0;i<columna;i++) {
					mx[aux][i]=partes[i];
				}
				aux++;
			}
			read.close();
			return mx;
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return mx;
	}
	
	//-------------------------IMPRESIONES---------------------\\
	//impresion de matriz con textos
	private static void imprimirMx(String[][] mx) {
		for(int i=0;i<mx.length;i++) {//RECORRER FILAS
			for(int j=0;j<mx[i].length;j++) {//RECORRER COLUMNAS
				System.out.print(mx[i][j]+",");
			}
			System.out.println();
		}
	}
	//impresion de matriz con números
	private static void imprimirMxNum(int[][] mx) {
		for(int i=0;i<mx.length;i++) {//RECORRER FILAS
			for(int j=0;j<mx[i].length;j++) {//RECORRER COLUMNAS
				System.out.print(mx[i][j]+",");
			}
			System.out.println();
		}
	}
	//impresion de lista con números
	private static void imprimir(int[]lista) {
		for(int i=0;i<lista.length;i++) {
			System.out.println(lista[i]);
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

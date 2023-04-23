package taller1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
QUE HACE CADA FUNCION:
-------------------------------Lectura---------------------------------------------------------------------------------
1)contador------->Cuenta la cantidad de lineas que tiene el archivo
2)crearMatriz---->Crea una matriz de String (textos) con las lineaas del archivo
-------------------------------MENUS------------------------------------------------------------------------------------
1)menuR1--------->Procedimiento que reporte por pantalla los datos corruptos que deben borrarse
2)menuR2--------->Procedimiento para ingresar por pantalla el usuario, le sigue el identificador
3)buscarElemento---->Funcion para buscar el elemento registrado por pantalla en la matriz (Retornará la posicion en la matriz, de lo contrario, dará -1
4)verificadorUsuario->Procedimiento para verificar inicio de sesion, se despliegan los MENU'S de ADMIN y NORMAL
5)desplegarMenuAdmin-->Procedimiento
6)desplegarMenuNormal-->Procedimiento para desplegar el menu de usuario normal, despliega menu segun el tipo de creador
------------------------MENUS DE CREADORES------------------------------------------------------------------------
1)desplegarMejoras----->Procedimiento para ingresar la cantidad de mejoras a la IA (muestra las IA y cantidad de mejoras)
2)desplegarProgramador->Procedimiento para cambiar la velocidad de aprendizaje de una IA prograsivamente (muestra las IA y sus velocidades)
3)desplegarMaster------>Procedimiento para ingresar Mejoras y Velocidades a una IA (muestra las IA, mejoras y velocidades)
------------------------Extracción de informacion------------------------------------------------------------------------
1)extraerInfo---->Crea una matriz de Int (numeros enteros) con la matriz de String (textos)

------------------------Impresiones por pantalla------------------------------------------------------------------------
4)imprimirMx----->Imprime una matriz de String (textos)
5)imprimirMxNum-->Imprime una matriz de Int (números enteros)
6)imprimirLista-->Imprime una lista de Int (números enteros)

*/


public class App {
	public static void main(String[] args) throws FileNotFoundException{
		//CONTADORES DE LAS LINEAS------------------------
		int contUsuario= contador("datos_usuarios.txt");
		int contCreador= contador("datos_creadores.txt");
		int contIa= contador("datos_ia.txt");
		//CREACION DE LAS MATRICES-----------------------
		String[][]mxUsuario= crearMatriz("datos_usuarios.txt",contUsuario,true);
		String[][]mxCreador= crearMatriz("datos_creadores.txt",contCreador,true);
		String[][]mxIa= crearMatriz("datos_ia.txt",contIa,false);
		//ESTAS SE UTILIZARÁN (QUIZAS) PARA BUSCAR LOS MAYORES, MENORES Y PROMEDIOS
		int[][]infoCreadores= extraerInfo(mxCreador,contCreador,true);
		int[][]infoIas= extraerInfo(mxIa,contIa,false);
		//MENUS------------------------------------------
		//menuR1();
		menuR2(mxIa,mxCreador,mxUsuario);
		
	}
	
	private static void menuR2(String[][]mxIa,String[][]mxCreador,String[][]mxUsuario) {
		String nombre,contraseña;
		Scanner leer= new Scanner(System.in);
		System.out.println("Ingrese Usuario: ");
		nombre= leer.nextLine().replaceAll(" ", "");
		int pos= buscarElemento(mxUsuario,nombre);
		if(pos!=-1) {
			System.out.println("Ingrese contraseña: ");
			contraseña= leer.nextLine();
			verificadorUsuario(mxIa,mxCreador,mxUsuario,contraseña, pos);
			//identificacion(mxIa,mxCreador,mxUsuario,nombre,contraseña);			
		}else {
			System.out.println("No se encontró el usuario");
		}
		leer.close();
	}
	private static int buscarElemento(String[][]mx,String nombre) {
		for (int i=0;i<mx.length;i++) {
			if((mx[i][0].toLowerCase()).equals(nombre)) {
				return i;
			}
		}
		return -1;
	}
	
	private static void verificadorUsuario(String[][]mxIa,String[][]mxCreador,String[][]mxUsuario,String contraseña, int pos) {
		String aux= mxUsuario[pos][1].replaceAll(" ", "");
		if(contraseña.equals(aux)) {
			if(mxUsuario[pos][3].equals("administrador")) {
				//desplegarMenuAdmin();
			}else {
				String creador=mxUsuario[pos][3];
				desplegarMenuNormal(mxIa,mxCreador,creador);
			}
		}else {
			System.out.println("Contraseña incorrecta");
		}
	}
	
	//---------------------------------MENU ADMIN--------------------------------
	/*
	private static void desplegarMenuAdmin() {
		int opcion= submenuInicial();
		if(opcion==1) {
			submenuIA();
		}else {
			//submenuAdmin();
		}
	}
	
	//TERMINAR ORDENAMIENTO BURBUJA, PERO CON LISTAS, CON LA MATRIZ ESTA, ESTÁ DIFICL
	private static void submenuIA(String[][]mxIa) {
		int opcion= ordenIAS();
		if(opcion==1) {
			//burbuja(mxIa);
		}
	}
	*/

	//---------------------------------MENU NORMAL--------------------------------
	private static void desplegarMenuNormal(String[][]mxIa,String[][]mxCreador,String creador) {
		for(int i=0;i<mxCreador.length;i++) {
			if((mxCreador[i][0].toLowerCase()).equals(creador.toLowerCase())) {
				if((mxCreador[i][2].toLowerCase().replaceAll(" ", "")).equals("mejoradeia")) {
					//desplegarMejoras(mxIa);
					desplegarMejoras(mxIa);
				}else if((mxCreador[i][2].toLowerCase().replaceAll(" ", "")).equals("programador")) {
					desplegarProgramador(mxIa);
				}else if((mxCreador[i][2].toLowerCase().replaceAll(" ", "")).equals("iamaster")) {
					desplegarMaster(mxIa);
				}
			}
		}
	}
	
	//MENU DE LAS MEJORAS V2
	private static void desplegarMejoras(String[][]mxIa) {
		Scanner leer= new Scanner(System.in);
		for(int i=0;i<mxIa.length;i++) {
			System.out.println("nombre IA:"+mxIa[i][0]+" | cantidad de mejoras: "+mxIa[i][5]);
		}
		System.out.println("IA a mejorar: ");
		String nombre=leer.nextLine();
		int pos= buscarElemento(mxIa,nombre);
		while(pos==-1) {
			System.out.println("Nombre incorrecto");
			System.out.println("IA a mejorar: ");
			nombre=leer.nextLine();
			pos= buscarElemento(mxIa,nombre);
		}
		if((mxIa[pos][3].toLowerCase()).equals("simple")) {
			System.out.println("No se pueden ingresar mejoras");
		//-----------IA MEJORADA----------------------
		}else if((mxIa[pos][3].toLowerCase()).equals("media")) {
			//Puede tener limite 5
			int mejoras=Integer.valueOf(mxIa[pos][5]);
			if(mejoras<5) {
				int diferencia= 5-mejoras;
				System.out.println("La IA tiene una capacidad para mejora de: "+diferencia);
				System.out.println("Cantidad de mejoras a añadir: ");
				int cantidadMejoras=Integer.valueOf(leer.nextLine());
				while(cantidadMejoras>diferencia) {
					System.out.println("Ingrese un valor menor/igual a "+diferencia);
					cantidadMejoras=Integer.valueOf(leer.nextLine());
				}
				mxIa[pos][5]=String.valueOf(mejoras+=cantidadMejoras);
				System.out.println("Mejoras añadidas");
			}else {
				System.out.println("Tiene el máximo de mejoras disponibles");
			}
		//----------IA AVANZADA--------------
		}else if((mxIa[pos][3].toLowerCase()).equals("avanzada")) {
			//Puede tener limite 30
			int mejoras= Integer.valueOf(mxIa[pos][5]);
			if(mejoras<30) {
				int diferencia= 30-mejoras;
				System.out.println("La IA tiene una capacidad para mejora de: "+diferencia);
				System.out.println("Cantidad de mejoras a añadir: ");
				int cantidadMejoras=Integer.valueOf(leer.nextLine());
				while(cantidadMejoras>diferencia) {
					System.out.println("Ingrese un valor menor/igual a "+diferencia);
					cantidadMejoras=Integer.valueOf(leer.nextLine());
				}
				mxIa[pos][5]=String.valueOf(mejoras+=cantidadMejoras);
				añadirMejora(mxIa,pos);
				System.out.println("Mejoras añadidas");
			}else {
				System.out.println("Tiene el máximo de mejoras disponibles");
			}
		}
		leer.close();
	}
	
	//SUB-MENU PARA AÑADIR LAS MEJORAS
	private static void añadirMejora(String[][] mxIa, int i) {
		if((Integer.valueOf(mxIa[i][2]))!=0) {
			int diferencia= (((Integer.valueOf(mxIa[i][2]))*24)-((Integer.valueOf(mxIa[i][5]))*6));
			int velocidad= (diferencia/24);
			mxIa[i][2]= String.valueOf(velocidad);			
		}else {
			System.out.println("Alcanzó su maxima velocidad, añadir mejoras no significará nada");
		}
	}
	//MENU DEL PROGRAMADOR
	private static void desplegarProgramador(String[][] mxIa) {
		Scanner leer= new Scanner(System.in);
		for(int i=0;i<mxIa.length;i++) {
			System.out.println("nombre IA:"+mxIa[i][0]+" Velocidad de aprendizaje: "+mxIa[i][2]+" días");
		}
		System.out.println("IA a cambiar velocidad de aprendizaje: ");
		String nombre=leer.nextLine();
		int pos=buscarElemento(mxIa,nombre);
		while(pos==-1) {
			System.out.println("Nombre de IA incorrecto");
			System.out.println("IA a cambiar velocidad de aprendizaje: ");
			nombre=leer.nextLine();
			pos=buscarElemento(mxIa,nombre);
		}
		limiteVelocidad(mxIa,pos);
		leer.close();
	}
	
	//PROCEDIMIENTO PARA HACER LAS CONVERSIONES DE DIAS Y HORAS, CALCULAR LIMITE DE VELOCIDAD
	private static void limiteVelocidad(String[][] mxIa,int i) {
		double media=1.25,avanzada=7.5,cambioVelocidad;//HORAS MAXIMAS DE CADA TIPO DE IA
		Scanner leer= new Scanner(System.in);
		String tipo= mxIa[i][3];
		if((tipo.toLowerCase().replaceAll(" ", "")).equals("simple")) {
			System.out.println("Cambio de valor de velocidad: ");
			cambioVelocidad= Double.valueOf(leer.nextLine());
			while(cambioVelocidad<media) {
				System.out.println("El cambio de velocidad debe ser progresivo, porfavor ingresar un numero mayor/igual 1.25 días");
				cambioVelocidad= Double.valueOf(leer.nextLine());
			}
			mxIa[i][2]=String.valueOf(cambioVelocidad);
			System.out.println("El cambio se ha realizado correctamente");
		}else if((tipo.toLowerCase().replaceAll(" ", "")).equals("media")) {
			System.out.println("Cambio de valor de velocidad: ");
			cambioVelocidad= Double.valueOf(leer.nextLine());
			while(cambioVelocidad<avanzada) {
				System.out.println("El cambio de velocidad debe ser progresivo, porfavor ingresar un numero mayor/igual a 7.5 días");
				cambioVelocidad= Double.valueOf(leer.nextLine());
			}
			mxIa[i][2]=String.valueOf(cambioVelocidad);
			System.out.println("El cambio se ha realizado correctamente");
		}else if((tipo.toLowerCase().replaceAll(" ", "")).equals("avanzada")) {
			System.out.println("Cambio de valor de velocidad: ");
			cambioVelocidad= Double.valueOf(leer.nextLine());
			while(cambioVelocidad<0) {
				System.out.println("El cambio de velocidad debe ser progresivo, porfavor ingresar un numero mayor/igual a 0 días");
				cambioVelocidad= Double.valueOf(leer.nextLine());
			}
			mxIa[i][2]=String.valueOf(cambioVelocidad);
			System.out.println("El cambio se ha realizado correctamente");
		}
		leer.close();
	}
	//MENU DE IA MASTER------------------
	private static void desplegarMaster(String[][] mxIa) {
		Scanner leer= new Scanner(System.in);
		System.out.println("Opción 1: Añadir mejoras");
		System.out.println("Opcion 2: Cambiar velocidad de aprendizaje");
		System.out.println("Elección: ");
		int opcion= Integer.valueOf(leer.nextLine());
		if (opcion==1) {
			desplegarMejoras(mxIa);
		}else if(opcion==2) {
			desplegarProgramador(mxIa);
		}
		leer.close();
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
	//impresion de submenus-ADMIN
	private static int submenuInicial() {
		Scanner leer= new Scanner(System.in);
		System.out.println("----Bienvenido al Menu de Admin----");
		System.out.println("Opciones para ingresar (1/2):");
		System.out.println("1)Submenú IA");
		System.out.println("2)Submenú Usuarios y Creadores");
		int opcion= Integer.valueOf(leer.nextLine());
		while(opcion>2 || opcion<1) {
			System.out.println("Ingrese opcion valida (1 o 2):");
			opcion= Integer.valueOf(leer.nextLine());
		}leer.close();
		return opcion;
	}
	
	private static int ordenIAS() {
		Scanner leer= new Scanner(System.in);
		System.out.println("En qué orden desea ver las IA:");
		System.out.println("1)Nombre de IA");
		System.out.println("2)Año de creación");
		System.out.println("3)Velocidad de aprendizaje");
		System.out.println("4)Tipo");
		System.out.println("5)Creador");
		System.out.println("6)Cantidad de mejoras");
		int opcion=Integer.valueOf(leer.nextLine());
		while(opcion<1 || opcion>6) {
			System.out.println("Ingrese opcion valida del 1 al 6:");
			opcion=Integer.valueOf(leer.nextLine());
		}
		leer.close();
		return opcion;
	}
	
	//ORDENAMIENTO BURBUJA
	
	
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

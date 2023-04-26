package taller1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CodigoLimpio {

	public static void main(String[] args) throws FileNotFoundException {
		//CONTADORES DE LAS LINEAS------------------------
		int contUsuario= contador("datos_usuarios.txt");
		int contCreador= contador("datos_creadores.txt");
		int contIa= contador("datos_ia.txt");
		//CREACION DE LAS MATRICES-----------------------
		String[][]mxUsuario= crearMatriz("datos_usuarios.txt",contUsuario,true);
		String[][]mxCreador= crearMatriz("datos_creadores.txt",contCreador,true);
		String[][]mxIa= crearMatriz("datos_ia.txt",contIa,false);
		//ESTAS SE UTILIZARÁN (QUIZAS) PARA BUSCAR LOS MAYORES, MENORES Y PROMEDIOS
		//int[][]infoCreadores= extraerInfo(mxCreador,contCreador,true);
		//int[][]infoIas= extraerInfo(mxIa,contIa,false);
		
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
		}else {
			System.out.println("No se encontró el usuario");
		}
		leer.close();
	}
	
	//-------------------------------MENU R2--------------------------------------------------------
	private static int buscarElemento(String[][]mx,String nombre) {
		for (int i=0;i<mx.length;i++) {
			if((mx[i][0].toLowerCase()).equals(nombre)) {
				return i;
			}
		}
		return -1;
	}
	
	private static void verificadorUsuario(String[][]mxIa,String[][]mxCreador,String[][]mxUsuario,String contraseña, int pos) {
		String aux= mxUsuario[pos][1];
		if(contraseña.equals(aux)) {
			String tipo= mxUsuario[pos][2].toLowerCase();
			System.out.println(tipo);
			if(tipo.equals("administrador")) {
				desplegarMenuAdmin(mxIa);
			}
			if(tipo.equals("normal")) {
				String creador=mxUsuario[pos][3];
				System.out.println("HOLA NORMAL");
				desplegarMenuNormal(mxIa,mxCreador,creador);
			}
		}else {
			System.out.println("Contraseña incorrecta");
		}
	}
	
	//-----------------------------------------USUARIO NORMAL-----------------------------------
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
	
	//----------------------------------MENU DE MEJORAS A LA IA-------------------------------------------------------
	//--------------------INSERTAR Y DESLPEGAR MEJORAS
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
		}//------------------IA SIMPLE--------------
		if((mxIa[pos][3].toLowerCase()).equals("simple")) {
			cantidadMejoras(mxIa,pos,"simple",leer);
		//-----------IA MEJORADA----------------------
		}else if((mxIa[pos][3].toLowerCase()).equals("media")) {
			cantidadMejoras(mxIa,pos,"media",leer);
		//----------IA AVANZADA--------------
		}else if((mxIa[pos][3].toLowerCase()).equals("avanzada")) {
			//Puede tener limite 30
			cantidadMejoras(mxIa,pos,"media",leer);
		}
		leer.close();
	}
	
	//------------------------ASIGNAR CANTIDAD DE MEJORAS
	private static void cantidadMejoras(String[][]mxIa,int pos,String option,Scanner leer) {
		int mejoras=Integer.valueOf(mxIa[pos][5]);
		if(option.equals("simple")) {
			System.out.println("No se pueden ingresar mejoras");
		}else if(option.equals("media")) {
			calculoMejoras(mxIa,pos,mejoras,leer,"media");
			añadirMejora(mxIa,pos);
		}else if(option.equals("avanzada")) {
			calculoMejoras(mxIa,pos,mejoras,leer,"avanzada");
			añadirMejora(mxIa,pos);
		}
	}
	
	//--------------------------------CALCULAR LAS MEJORAS
	private static void calculoMejoras(String[][]mxIa,int pos,int mejoras,Scanner leer,String option) {
		int diferencia=0,cantMejoras;
		if(option.equals("media")) {
			diferencia=5;
		}else if (option.equals("avanzada")){
			diferencia=30;
		}
		if(mejoras<5) {
			diferencia-=mejoras;
			System.out.println("La IA tiene una capacidad para mejora de: "+diferencia);
			System.out.println("Cantidad de mejoras a añadir: ");
			cantMejoras=Integer.valueOf(leer.nextLine());
			while(cantMejoras>diferencia) {
				System.out.println("Ingrese un valor menor/igual a "+diferencia);
				cantMejoras=Integer.valueOf(leer.nextLine());
			}
			mxIa[pos][5]=String.valueOf(mejoras+=cantMejoras);
			System.out.println("Mejoras añadidas");
		}else {
			System.out.println("Tiene el máximo de mejoras disponibles");
		}
	}
	
	//-------------------SUB-MENU PARA AÑADIR LAS MEJORAS
	private static void añadirMejora(String[][] mxIa, int i) {
		if((Integer.valueOf(mxIa[i][2]))!=0) {
			int diferencia= (((Integer.valueOf(mxIa[i][2]))*24)-((Integer.valueOf(mxIa[i][5]))*6));
			int velocidad= (diferencia/24);
			mxIa[i][2]= String.valueOf(velocidad);			
		}else {
			System.out.println("Alcanzó su maxima velocidad, añadir mejoras no significará nada");
		}
	}
	//--------------------------------------------------MENU DEL PROGRAMADOR--------------------------------------------
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
		double simple=13.75,media=6.25,avanzada=0,cambioVelocidad;//HORAS MAXIMAS DE CADA TIPO DE IA
		Scanner leer= new Scanner(System.in);
		String tipo= mxIa[i][3];
		if((tipo.toLowerCase().replaceAll(" ", "")).equals("simple")) {
			cambioVelocidadPro(mxIa,i,simple,leer);
		}else if((tipo.toLowerCase().replaceAll(" ", "")).equals("media")) {
			cambioVelocidadPro(mxIa,i,media,leer);	
		}else if((tipo.toLowerCase().replaceAll(" ", "")).equals("avanzada")) {
			cambioVelocidadPro(mxIa,i,avanzada,leer);	
		}
		leer.close();
	}
	
	private static void cambioVelocidadPro(String[][]mxIa,int i,double limite,Scanner leer) {
		double cambioVelocidad;
		System.out.println("Cambio de valor de velocidad: ");
		cambioVelocidad= Double.valueOf(leer.nextLine());
		while(cambioVelocidad<=limite) {
			System.out.println("El cambio de velocidad debe ser progresivo, porfavor ingresar un numero mayor/igual a "+limite+" días");
			cambioVelocidad= Double.valueOf(leer.nextLine());
		}
		mxIa[i][2]=String.valueOf(cambioVelocidad);
		System.out.println("El cambio se ha realizado correctamente");
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
	//-----------------------------------------ADMINISTRADOR--------------------------------------
	private static void desplegarMenuAdmin(String[][]mxIa) {
		int opcion= submenuInicial();
		if(opcion==1) {
			submenuIA(mxIa);
		}
	}
	
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
	
	private static void submenuIA(String[][]mxIa) {
		int columna;
		//int opcion= 1;
		//ORDENAR LAS IAS ESTÁ MALA LA CAGA
		int opcion= ordenIAS();
		if(opcion==1) {
			columna=0;
			burbuja(mxIa,columna,true);
			imprimirMx(mxIa);
		}else if(opcion==2) {
			columna=1;
			burbuja(mxIa,columna,false);
			imprimirMx(mxIa);
		}else if(opcion==3) {
			//ARREGLAR ESTA WEA, PORQUE EL MAS CHICO ES MAYOR
			columna=2;
			burbuja(mxIa,columna,false);
			imprimirMx(mxIa);
		}else if(opcion==4) {
			columna=3;
			burbuja(mxIa,columna,true);
			imprimirMx(mxIa);
		}else if(opcion==5) {
			columna=4;
			burbuja(mxIa,columna,true);
			imprimirMx(mxIa);
		}else if(opcion==6) {
			columna=5;
			burbuja(mxIa,columna,false);
			imprimirMx(mxIa);
		}
	}
	
	private static void intercambiarFila(String[][]mxIa,int j) {
		String[]aux=mxIa[j];
		mxIa[j]=mxIa[j+1];
		mxIa[j+1]=aux;
	}
	private static void burbuja(String[][] mxIa,int columna,boolean x) {
		
		for(int i=0;i<mxIa.length;i++) {
			for(int j=0;j<mxIa.length-i-1;j++) {
				if(x==true) {
					if(mxIa[j][columna].compareTo(mxIa[j+1][columna]) <0) {
						intercambiarFila(mxIa,j);
					}
				}else if(x==false){
					if((Integer.parseInt(mxIa[j][columna]))<(Integer.parseInt(mxIa[j+1][columna]))) {
						intercambiarFila(mxIa,j);
					}
				}
			}
		}
	}
	
	private static int ordenIAS() {
		System.out.println("----Ordenar por----");
		System.out.println("1)Nombre de IA");
		System.out.println("2)Año de creación");
		System.out.println("3)Velocidad de aprendizaje");
		System.out.println("4)Tipo");
		System.out.println("5)Creador");
		System.out.println("6)Cantidad de mejoras");
		System.out.println("Elección: ");
		Scanner read= new Scanner(System.in);
		int opcion= Integer.parseInt(read.nextLine());	
		read.close();
		return opcion;
	}
	
	//-------------------------------LECTURA DE ARCHIVO-------------------------------------
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
	
	//impresion de matriz con textos
		private static void imprimirMx(String[][] mx) {
			for(int i=0;i<mx.length;i++) {//RECORRER FILAS
				for(int j=0;j<mx[i].length;j++) {//RECORRER COLUMNAS
					System.out.print(mx[i][j]+",");
				}
				System.out.println();
			}
		}
	

}

package limpieza;

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

------------------------------DESGLOCE DE MEJORAS 1----------------------------------------------------------------
1)cantidadMejoras--> Calcula la cantidad de mejoras y las añade utilizando subprogramas
2)calculoMejoras-->se le asigna por pantalla la cantidad de mejoras e identifica a cuáles IA's se le puede añadir
3)añadirMejoras-->Implementará las mejoras a la matriz
------------------------Extracción de informacion------------------------------------------------------------------------
1)extraerInfo---->Crea una matriz de Int (numeros enteros) con la matriz de String (textos)
------------------------Impresiones por pantalla------------------------------------------------------------------------
4)imprimirMx----->Imprime una matriz de String (textos)
5)imprimirMxNum-->Imprime una matriz de Int (números enteros)
6)imprimirLista-->Imprime una lista de Int (números enteros)
*/


public class App {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner leer= new Scanner(System.in);
		//CONTADORES DE LAS LINEAS------------------------
		int contUsuario= contador("datos_usuarios.txt");
		int contCreador= contador("datos_creadores.txt");
		int contIa= contador("datos_ia.txt");
		//CREACION DE LAS MATRICES-----------------------
		String[][]mxUsuario= crearMatriz("datos_usuarios.txt",contUsuario,true);
		String[][]mxCreador= crearMatriz("datos_creadores.txt",contCreador,true);
		String[][]mxIa= crearMatriz("datos_ia.txt",contIa,false);
		String[][]mxRevelaciones= new String[contIa][2];
		//ESTAS SE UTILIZARÁN PARA BUSCAR LOS MAYORES, MENORES Y PROMEDIOS
		double[][]infoCreadores= extraerInfo(mxCreador,contCreador,true);
		double[][]infoIas= extraerInfo(mxIa,contIa,false);
		//INICIALIZACION DE MENUS
		menuR2(mxRevelaciones,infoCreadores,infoIas,mxIa,mxCreador,mxUsuario,leer);
		leer.close();
	}
	
	private static void menuR2(String[][]mxRevelaciones,double[][]infoCreadores,double[][]infoIas,String[][]mxIa,String[][]mxCreador,String[][]mxUsuario,Scanner leer) {
		String nombre,contraseña;
		System.out.println("Ingrese Usuario: ");
		nombre= leer.nextLine().toLowerCase().replaceAll(" ", "");
		while((nombre.equals("-1")==false)) {
			int pos= buscarElemento(mxUsuario,nombre);
			if(pos!=-1) {
				System.out.println("Ingrese contraseña: ");
				contraseña= leer.nextLine();
				verificadorUsuario(mxRevelaciones,infoCreadores,infoIas,mxIa,mxCreador,mxUsuario,contraseña, pos,leer);
			}else {
				System.out.println("No se encontró el usuario");
			}
			//SE VUELVEN A CALCULAR LAS COSAS CON LOS DATOS APLICADOS
			infoCreadores= extraerInfo(mxCreador,mxCreador.length,true);
			infoIas= extraerInfo(mxIa,mxIa.length,false);
			System.out.println("Ingrese Usuario: ");
			nombre= leer.nextLine().toLowerCase().replaceAll(" ", "");
		}
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
	
	private static void verificadorUsuario(String[][]mxRevelaciones,double[][]infoCreadores,double[][]infoIas,String[][]mxIa,String[][]mxCreador,String[][]mxUsuario,String contraseña, int pos,Scanner leer) {
		String aux= mxUsuario[pos][1];
		if(contraseña.equals(aux)) {
			String tipo= mxUsuario[pos][2].toLowerCase();
			System.out.println(tipo);
			if(tipo.equals("administrador")) {
				desplegarMenuAdmin(mxRevelaciones,infoCreadores,infoIas,mxCreador,mxUsuario,mxIa,leer);
			}
			if(tipo.equals("normal")) {
				String creador=mxUsuario[pos][3];
				System.out.println("HOLA NORMAL");
				desplegarMenuNormal(mxIa,mxCreador,creador,leer);
				//SOBRE ESCRIBIR EN LOS ARCHIVOS
				infoCreadores= extraerInfo(mxCreador,mxCreador.length,true);
				infoIas= extraerInfo(mxIa,mxIa.length,false);
			}
			
		}else {
			System.out.println("Contraseña incorrecta");
		}
	}
	
	//-----------------------------------------USUARIO NORMAL-----------------------------------
	private static void desplegarMenuNormal(String[][]mxIa,String[][]mxCreador,String creador,Scanner leer) {
		for(int i=0;i<mxCreador.length;i++) {
			if((mxCreador[i][0].toLowerCase()).equals(creador.toLowerCase())) {
				if((mxCreador[i][2].toLowerCase().replaceAll(" ", "")).equals("mejoradeia")) {
					//desplegarMejoras(mxIa);
					desplegarMejoras(mxIa,leer);
				}else if((mxCreador[i][2].toLowerCase().replaceAll(" ", "")).equals("programador")) {
					desplegarProgramador(mxIa,leer);
				}else if((mxCreador[i][2].toLowerCase().replaceAll(" ", "")).equals("iamaster")) {
					desplegarMaster(mxIa,leer);
				}
			}
		}
	}
	
	//----------------------------------MENU DE MEJORAS A LA IA-------------------------------------------------------
	//--------------------INSERTAR Y DESLPEGAR MEJORAS
	private static void desplegarMejoras(String[][]mxIa,Scanner leer) {
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
	private static void desplegarProgramador(String[][] mxIa,Scanner leer) {
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
		limiteVelocidad(mxIa,pos,leer);
	}
	//PROCEDIMIENTO PARA HACER LAS CONVERSIONES DE DIAS Y HORAS, CALCULAR LIMITE DE VELOCIDAD
	private static void limiteVelocidad(String[][] mxIa,int i,Scanner leer) {
		double simple=13.75,media=6.25,avanzada=0;//HORAS MAXIMAS DE CADA TIPO DE IA
		String tipo= mxIa[i][3];
		if((tipo.toLowerCase().replaceAll(" ", "")).equals("simple")) {
			cambioVelocidadPro(mxIa,i,simple,leer);
		}else if((tipo.toLowerCase().replaceAll(" ", "")).equals("media")) {
			cambioVelocidadPro(mxIa,i,media,leer);	
		}else if((tipo.toLowerCase().replaceAll(" ", "")).equals("avanzada")) {
			cambioVelocidadPro(mxIa,i,avanzada,leer);	
		}
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
	private static void desplegarMaster(String[][] mxIa,Scanner leer) {
		System.out.println("Opción 1: Añadir mejoras");
		System.out.println("Opcion 2: Cambiar velocidad de aprendizaje");
		System.out.println("Elección: ");
		int opcion= Integer.valueOf(leer.nextLine());
		if (opcion==1) {
			desplegarMejoras(mxIa,leer);
		}else if(opcion==2) {
			desplegarProgramador(mxIa,leer);
		}
	}
	//-----------------------------------------ADMINISTRADOR---------------------------------------------------------------------------
	private static void desplegarMenuAdmin(String[][]mxRevelaciones,double[][]infoCreadores,double[][]infoIas,String[][]mxCreador,String[][]mxUsuario,String[][]mxIa,Scanner leer) {
		int opcion= submenuInicial(leer);
		if(opcion==1) {
			submenuIA(mxIa,leer);
			
			//vamos a recalcular los datos para los reportes en el caso de que haya modificaciones
			infoCreadores= extraerInfo(mxCreador,mxCreador.length,true);
			infoIas= extraerInfo(mxIa,mxIa.length,false);
		}else {
			submenuReportes(mxRevelaciones,infoCreadores,infoIas,mxCreador,mxIa,mxUsuario,leer);
		}
	}

	private static int submenuInicial(Scanner leer) {
		System.out.println("----Bienvenido al Menu de Admin----");
		System.out.println("Opciones para ingresar (1/2): \n 1)Submenú IA \n 2)Submenú Usuarios y Creadores");
		int opcion= Integer.valueOf(leer.nextLine());
		while(opcion>2 || opcion<1) {
			System.out.println("Ingrese opcion valida (1 o 2):");
			opcion= Integer.valueOf(leer.nextLine());
		}
		return opcion;
	}
	//----------------------------------SUB MENU REPORTES (ADMIN R3)-----------------------------------------------------------------
	private static void submenuReportes(String[][]mxRevelaciones,double[][]infoCreadores,double[][]infoIas,String[][]mxCreador,String[][]mxIa,String[][]mxUsuario,Scanner leer) {
		tipoUsuario(mxUsuario,leer);
		//añadirUsuarioCreador();
		//editarDatosUsuarioCreador();
		//eliminarUsuarioCreador();
		reportesPantalla(mxRevelaciones,infoCreadores,infoIas,mxCreador,mxIa);
		
	}
	private static void reportesPantalla(String[][]mxRevelaciones,double[][]infoCreadores,double[][]infoIas,String[][]mxCreador,String[][]mxIa) {
		edadMedia(infoCreadores,infoIas,true);
		edadMedia(infoCreadores,infoIas,false);
		promVelocidadAprendizajeIa(infoIas);
		cantidadIaTipos(mxIa);
		cantidadCreadoresEspecialidad(mxCreador);
		calcularRevelacion(mxRevelaciones,mxIa,mxCreador);
	}

	private static void tipoUsuario(String[][]mxUsuario,Scanner leer) {
		int cantAdmin=0,cantNorm=0,i;double porcentaje=0,total=mxUsuario.length;
		System.out.println("Tipo de usuario que desea ver: \n 1)Administrador \n 2)Normal");
		int opcion= Integer.valueOf(leer.nextLine());
		while(opcion>2 || opcion<1) {
			System.out.println("Ingrese opcion valida (1 o 2):");
			opcion= Integer.valueOf(leer.nextLine());
		}
		for(i=0;i<mxUsuario.length;i++) {
			if((mxUsuario[i][2].toLowerCase()).equals("administrador")) {
				cantAdmin++;
			}else if((mxUsuario[i][2].toLowerCase()).equals("normal")) {
				cantNorm++;
			}
		}
		System.out.println("Usuarios:");
		if(opcion==1) {
			porcentaje=((cantAdmin/total)*100);
			for(i=0;i<mxUsuario.length;i++) {
				if((mxUsuario[i][2].toLowerCase()).equals("administrador")) {
					System.out.println("  -"+mxUsuario[i][0]);
				}
			}
		}else if(opcion==2) {
			porcentaje=((cantNorm/total)*100);
			for(i=0;i<mxUsuario.length;i++) {
				if((mxUsuario[i][2].toLowerCase()).equals("normal")) {
					System.out.println("  -"+mxUsuario[i][0]);
				}
			}
		}
		System.out.println("Tienen un porcentaje del: "+porcentaje+"%");
	}

	//----------------------------------SUB MENU IA (ADMIN)
	private static void submenuIA(String[][]mxIa,Scanner leer) {
		int columna;
		int opcion= ordenIAS(leer);
		if(opcion==1) {
			columna=0;
			burbuja(mxIa,columna,true,false);
			imprimirMx(mxIa);
		}else if(opcion==2) {
			columna=1;
			burbuja(mxIa,columna,false,false);
			imprimirMx(mxIa);
		}else if(opcion==3) {
			//ARREGLAR ESTA WEA, PORQUE EL MAS CHICO ES MAYOR
			columna=2;
			burbuja(mxIa,columna,false,true);
			imprimirMx(mxIa);
		}else if(opcion==4) {
			columna=3;
			burbuja(mxIa,columna,true,false);
			imprimirMx(mxIa);
		}else if(opcion==5) {
			columna=4;
			burbuja(mxIa,columna,true,false);
			imprimirMx(mxIa);
		}else if(opcion==6) {
			columna=5;
			burbuja(mxIa,columna,false,false);
			imprimirMx(mxIa);
		}
	}
	
	//IMPRIMIR MENU DE OPCIONES----SE PUEDE UTILIZAR SWICH
	private static int ordenIAS(Scanner leer) {
		System.out.println("----Ordenar por----");
		System.out.println("1)Nombre de IA");
		System.out.println("2)Año de creación");
		System.out.println("3)Velocidad de aprendizaje");
		System.out.println("4)Tipo");
		System.out.println("5)Creador");
		System.out.println("6)Cantidad de mejoras");
		System.out.println("Elección: ");
		int opcion=Integer.valueOf(leer.nextLine());
		while(opcion<1 || opcion>6) {
			System.out.println("Ingrese opción válida (1 al 6)");
			opcion= Integer.valueOf(leer.nextLine());
		}
		return opcion;
	}
	
	//-------------------------ORDENAMIENTO BURBUJA E INTERCAMBIO DE VALORES
	private static void intercambiarFila(String[][]mxIa,int j) {
		String[]aux=mxIa[j];
		mxIa[j]=mxIa[j+1];
		mxIa[j+1]=aux;
	}
	
	private static void burbuja(String[][] mxIa,int columna,boolean x,boolean y) {
		for(int i=0;i<mxIa.length;i++) {
			for(int j=0;j<mxIa.length-i-1;j++) {
				if(x==true) {//SI LOS VALORES SON TIPO STRING
					if(mxIa[j][columna].compareTo(mxIa[j+1][columna]) <0) {
						intercambiarFila(mxIa,j);
					}
				}else if(x==false){//SI LOS VALORES SON TIPO INT
					if(y==true) {//CAMBIAR VELOCIDADES (MENOR= MAYOR)
						if((Double.parseDouble(mxIa[j][columna]))>(Double.parseDouble(mxIa[j+1][columna]))) {
							intercambiarFila(mxIa,j);						
						}
					}else if(y==false){//CAMBIAR POR EL NUMERO MÁS GRANDE)
						if((Double.parseDouble(mxIa[j][columna]))<(Double.parseDouble(mxIa[j+1][columna]))) {
							intercambiarFila(mxIa,j);						
						}
					}		
				}
			}
		}
	}
	
	//---------------------------------------------------------REPORTES---------------------------------------------------------
	private static void edadMedia(double[][]infoCreadores,double[][]infoIas,boolean x) {
		int i,suma=0,tamaño=1;double promedio=0;
		if(x==true) {
			tamaño=infoCreadores.length;
			for(i=0;i<infoCreadores.length;i++) {
				suma+=infoCreadores[i][1];
			}
			promedio=(suma/tamaño);
			System.out.println("El promedio de edad de los creadores es: " + promedio);
		}else if(x==false) {
			tamaño=infoIas.length;
			for(i=0;i<infoIas.length;i++) {
				suma+=infoIas[i][0];
			}
			promedio=(suma/tamaño);
			System.out.println("La edad promedio de las Ia es: " + promedio);
		}
	}
	//----------------------CALCULA el promedio de la velocidad de aprendizaje
	private static void promVelocidadAprendizajeIa(double[][] infoIas) {
		int suma=0;double promedio= 0;
		for(int i = 0; i < infoIas.length; i ++) {
			suma += infoIas[i][1];
		}
		promedio= suma/infoIas.length;			
		System.out.println("El promedio de la Velocidad de Aprendizaje de las IA es:  "+promedio);
	}
	
	//-----------------CUENTA CUANTOS TIPOS DE IA HAY
	private static void cantidadIaTipos(String[][] infoIas) {
		int contSimple=0,contMedia=0,contAvanzada=0;
		for(int i = 0; i < infoIas.length; i++) {
			if((infoIas[i][3].toLowerCase()).equals("simple")){
				contSimple++;
			}else if ((infoIas[i][3].toLowerCase()).equals("media")) {
				contMedia++;
			}else if ((infoIas[i][3].toLowerCase()).equals("avanzada")) {
				contAvanzada++;
			}		
		}
		System.out.println("Se encuentran los siguientes cantidades de tipos de IA: \n -"+contSimple+" IA simples\n -"+contMedia+" IA medias\n -"+contAvanzada+" IA avanzadas" );
		
	}
	//------------------CUENTA CUANTOS TIPOS DE CREADORES HAY------------------------
	private static void cantidadCreadoresEspecialidad(String[][] mxCreador) { //2
		int contMejorarIa=0,contProgramador=0,contIaMaster=0;
		for(int i = 0; i < mxCreador.length; i++) {
			if((mxCreador[i][2].toLowerCase()).equals("mejoraia")){
				contMejorarIa++;
			}else if((mxCreador[i][2].toLowerCase()).equals("programador")) {
				contProgramador++;
			}else if((mxCreador[i][2].toLowerCase()).equals("iamaster")) {
				contIaMaster++;
			}
		}
		System.out.println("Se encuentran los siguientes cantidades de creadores por especialidad:\n -"+contMejorarIa+" Mejorar Ia \n -"+contProgramador+" Programadores Ia \n -"+contIaMaster+" Ia masters");
	}
	
	//-------------------------------------------EXTRAER INFORMACION CREADORES PARA R3---------------------------------------
	private static double[][] extraerInfo(String[][]mx,int cont,boolean x) {
		double[][]mxInfo= new double[cont][2];
		if(x==true) {
			for(int i=0;i<mx.length;i++) {
				mxInfo[i][0]= Integer.valueOf(mx[i][1]);//exp
				mxInfo[i][1]=Integer.valueOf(mx[i][3]);//edad
			}
		}else{
			for(int i=0;i<mx.length;i++) {
				mxInfo[i][0]= 2023-(Double.valueOf(mx[i][1]));//edad
				mxInfo[i][1]=Double.valueOf(mx[i][2]);//velocidad
			}
		}
		return mxInfo;
	}
	//-------------------------------------------------LECTURA DE ARCHIVO---------------------------------------------------------------------
	ARREGLAR ESTA WEA
	private static void calcularRevelacion(String[][]mxRevelaciones,String[][]mxIas,String[][]mxCreadores) {
		double ptsRevelacion=0;
		for(int i=0;i<mxIas.length;i++) {
			if((mxIas[i][3].toLowerCase()).equals("simple")) {
				ptsRevelacion= ((Integer.valueOf(mxIas[i][1]))*5*(Integer.valueOf(mxCreadores[i][1])))/(Double.valueOf(mxIas[i][2]));
			}else if((mxIas[i][3].toLowerCase()).equals("media")) {
				ptsRevelacion= ((Integer.valueOf(mxIas[i][1]))*10*(Integer.valueOf(mxCreadores[i][1])))/(Double.valueOf(mxIas[i][2]));
			}else if((mxIas[i][3].toLowerCase()).equals("avanzada")) {
				ptsRevelacion= ((Integer.valueOf(mxIas[i][1]))*15*(Integer.valueOf(mxCreadores[i][1])))/(Double.valueOf(mxIas[i][2]));
			}
			if (ptsRevelacion==0) {
				mxRevelaciones[i][0]=mxIas[i][0];
				mxRevelaciones[i][1]=String.valueOf(ptsRevelacion);
			}
		}
		imprimirMx(mxRevelaciones);
	}
	
	
	//-------------------------------------------------LECTURA DE ARCHIVO---------------------------------------------------------------------
	//funcion que retornará la cantidad de lineas que tiene el archivo
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
	//FUNCION QUE RETORNARÁ UNA MATRIZ CON LOS DATOS EN FOMRA DE STRING
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
/*
---------------------------------------------------------------------------------------------------------------------------
										PROGRAMACIÓN AVANZADA - TALLER N°1
---------------------------------------------------------------------------------------------------------------------------
PROFESOR Y AYUDANTES:
	-Eric Ross Cortes
	-Pablo Ríos Ríos
	-Jaime Rivera Aguilar

INTEGRANTES:
	-Bruno Toro Elgueta | 20864066-6 | C1
	-Claudio Palta Contreras | 20788795-1 | C1
---------------------------------------------------------------------------------------------------------------------------
													DOCUMENTACIÓN
-------------------------------Lectura---------------------------------------------------------------------------------
1)contador------->Función que retorna la cantidad de líneas que tiene el archivo de texto asignado.
2)crearMatriz---->Arreglo en forma de matriz que almacena la información de los “creadores” en filas y columnas.
3)extraerInfo---->Función que retorna un arreglo en forma de matriz con la información de experiencia, 
edad y velocidad de aprendizaje de la matriz de Creadores e IA’s.
-------------------------------MENU INICIAL---------------------------------------------------------------------------------
4)menuR2---->Procedimiento que lee por pantalla el ingreso de usuario ejecuta las funciones n°5 y 6.
5)buscarElemento---->Función que retorna la posición del elemento buscado en un arreglo en forma de matriz.
6)verificadorUsuario---->Procedimiento que verifica si el usuario ingresado y la contraseña son correctas, además detectará el tipo de usuario.
7)desplegarMenuAdmin---->Procedimiento que ejecuta los subprogramas n°9,10 y11, además vuelve a ejecutar el subprograma n°3.
8)desplegarMenuNormal---->Procedimiento que ejecuta los subprogramas n°23,24 y 25.
-------------------------------DESPLEGAR MENU ADMIN---------------------------------------------------------------------------------
9)submenuInicial---->Función que muestra por pantalla las opciones que hay para escoger y la retorna como un número entero.
10)submenuIa---->Procedimiento que ejecuta los subprogramas n°12,14 y 15.
11)submenuReportes---->Procedimiento que ejecuta los subprogramas n°16,17 y 18.
12)ordenIAS---->Función que muestra por pantalla las opciones que hay y retorna la seleccionada en forma de número entero.
13)burbuja---->Procedimiento de ordenamiento para la matriz ingresada, se ejecuta junto al subprograma n°14.
14)intercambio---->Procedimiento que intercambia valores previamente asignados.
15)imprimirMx---->Procedimiento para imprimir por pantalla un arreglo en forma de matriz.
-------------------------------REPORTES POR PANTALLA---------------------------------------------------------------------------------
16)tipoUsuario---->Procedimiento que imprime por pantalla el porcentaje por tipo de usuario.
17)reportesPantalla---->Procedimiento que ejecuta los subprogramas n°18,19,20,21 y 22.
18)edadMedia---->Procedimiento que calcula e imprime por pantalla la edad media de los Creadores e IA.
19)promVelocidadAprendizaje---->Procedimiento que calcula e imprime por pantalla el promedio de las velocidades de aprendizaje de las IA.
20)cantidadIaTipos---->Procedimiento que calcula e imprime por pantalla la cantidad de los tipos de IA que se encuentran.
21)cantidadCreadoresEspecialidad---->Procedimiento que calcula e imprime por pantalla la cantidad de los tipos de Creadores que se encuentran.
22)calcularRevelacion---->Procedimiento que calcula e imprime por pantalla las IA más propensas a revelarse contra la humanidad.
-------------------------------DESPLEGAR MENU NORMAL---------------------------------------------------------------------------------
23)desplegarMejoras---->Procedimiento que imprime por pantalla el nombre de las IA junto a la cantidad de mejoras de cada una, 
además ejecuta los subprogramas n°26.
24)desplegarProgramador---->Procedimiento que muestra por pantalla las IA junto a sus velocidades de aprendizajes,
estas podrán ser editadas. Se ejecutan los subprogramas n°29.
25)desplegarMaster---->Procedimiento que muestra por pantalla la opción de entrar al menú de programador o al menú de mejorar IA.
Ejecuta los subprogramas n°23 y 24.
-------------------------------DESPLEGAR MEJORAS---------------------------------------------------------------------------------
26)cantidadMejoras---->Procedimiento que identifica el tipo de IA, y ejecuta los subprogramas n°27 y 28.
27)calculoMejoras---->Procedimiento que calcula la cantidad de mejoras disponibles para cada IA.
28)añadirMejoras---->Procedimiento que implementa las mejoras calculadas correctamente, es decir, hace la conversión de
las horas que se restan al añadir mejoras.
-------------------------------DESPLEGAR PROGRAMADOR---------------------------------------------------------------------------------
29)limiteVelocidad---->Procedimiento que identifica las horas máximas para cada tipo de IA e implementa el subprograma n°30.
30)cambioVelocidadPro---->Procedimiento que actúa como control de error para ingresar por pantalla de forma correcta y
progresiva (según el tipo de IA) el cambio de velocidad de aprendizaje.
*/

package limpieza; //PARA QUE EL PROGRAMA FUNCIONE CORRECTAMENTE, CAMBIAR NOMBRE DEL PAQUETE
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
	//MENU INICIAL DEL PROGRAMA - INGRESO DE USUARIO
	private static void menuR2(String[][]mxRevelaciones,double[][]infoCreadores,double[][]infoIas,String[][]mxIa,String[][]mxCreador,String[][]mxUsuario,Scanner leer) {
		String nombre,contraseña;
		System.out.println("------------------------");
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
			System.out.println("------------------------");
			System.out.println("Ingrese Usuario: ");
			nombre= leer.nextLine().toLowerCase().replaceAll(" ", "");
		}
	}
	//-------------------------------MENU R2--------------------------------------------------------
	//Funcion que retornará la posicion del elemento que se encuentra en la matriz
	private static int buscarElemento(String[][]mx,String nombre) {
		for (int i=0;i<mx.length;i++) {
			if((mx[i][0].toLowerCase()).equals(nombre)) {
				return i;
			}
		}
		return -1;
	}
	//Procedimiento que verifica los datos ingresados por pantalla y despliega su menú asignado
	private static void verificadorUsuario(String[][]mxRevelaciones,double[][]infoCreadores,double[][]infoIas,String[][]mxIa,String[][]mxCreador,String[][]mxUsuario,String contraseña, int pos,Scanner leer) {
		String aux= mxUsuario[pos][1];
		if(contraseña.equals(aux)) {
			String tipo= mxUsuario[pos][2].toLowerCase();
			if(tipo.equals("administrador")) {
				desplegarMenuAdmin(mxRevelaciones,infoCreadores,infoIas,mxCreador,mxUsuario,mxIa,leer);
			}
			if(tipo.equals("normal")) {
				String creador=mxUsuario[pos][3];
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
	//procedimiento que implementa las mejoras añadidas y recalcula la velocidad de aprendizaje
	private static void añadirMejora(String[][] mxIa, int i) {
		if((Integer.valueOf(mxIa[i][2]))!=0) {
			int diferencia= (((Integer.valueOf(mxIa[i][2]))*24)-((Integer.valueOf(mxIa[i][5]))*6));
			int velocidad= (diferencia/24);//transformación a días
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
	//Ingresa por pantalla el cambio de velocidad de aprendizaje de la IA seleccionada
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
	//Función que muestra por pantalla las opciones que hay para escoger y la retorna como un número entero.
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
		System.out.println("--------------------------------------------------");
		//Estas funciones comentadas no se tomaron en consideración en el informe, no supimos implementarlas, lo sentimos de ante mano.
		//añadirUsuarioCreador();
		//editarDatosUsuarioCreador();
		//eliminarUsuarioCreador();
		reportesPantalla(mxRevelaciones,infoCreadores,infoIas,mxCreador,mxIa);
		
	}
	//REPORTES POR PANTALLA (R3)
	private static void reportesPantalla(String[][]mxRevelaciones,double[][]infoCreadores,double[][]infoIas,String[][]mxCreador,String[][]mxIa) {
		edadMedia(infoCreadores,infoIas,true);
		System.out.println("--------------------------------------------------");
		edadMedia(infoCreadores,infoIas,false);
		System.out.println("--------------------------------------------------");
		promVelocidadAprendizajeIa(infoIas);
		System.out.println("--------------------------------------------------");
		cantidadIaTipos(mxIa);
		System.out.println("--------------------------------------------------");
		cantidadCreadoresEspecialidad(mxCreador);
		System.out.println("--------------------------------------------------");
		calcularRevelacion(mxRevelaciones,mxIa,mxCreador);
		System.out.println("--------------------------------------------------");
	}
	//Procedimiento que imprime por pantalla el porcentaje por tipo de usuario.
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
			imprimirMx(mxIa,true);
		}else if(opcion==2) {
			columna=1;
			burbuja(mxIa,columna,false,false);
			imprimirMx(mxIa,true);
		}else if(opcion==3) {
			//ARREGLAR ESTA WEA, PORQUE EL MAS CHICO ES MAYOR
			columna=2;
			burbuja(mxIa,columna,false,true);
			imprimirMx(mxIa,true);
		}else if(opcion==4) {
			columna=3;
			burbuja(mxIa,columna,true,false);
			imprimirMx(mxIa,true);
		}else if(opcion==5) {
			columna=4;
			burbuja(mxIa,columna,true,false);
			imprimirMx(mxIa,true);
		}else if(opcion==6) {
			columna=5;
			burbuja(mxIa,columna,false,false);
			imprimirMx(mxIa,true);
		}
	}
	
	//IMPRIMIR MENU DE OPCIONES
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
			Math.round(ptsRevelacion);
			if (ptsRevelacion!=0) {
				mxRevelaciones[i][0]=mxIas[i][0];
				mxRevelaciones[i][1]=String.valueOf(ptsRevelacion);
			}
		}
		System.out.println("IA más probable para revelarse");
		burbuja(mxRevelaciones, 1, true, false);
		imprimirMx(mxRevelaciones,false);
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
	private static void imprimirMx(String[][] mx,boolean x) {
		if(x==true) {
			for(int i=0;i<mx.length;i++) {//RECORRER FILAS
				for(int j=0;j<mx[i].length;j++) {//RECORRER COLUMNAS
					System.out.print(mx[i][j]+",");
				}
				System.out.println();
			}			
		}else if(x==false) {//impresion de matriz para los reportes de revelaciones
			for(int i=0;i<mx.length;i++) {//RECORRER FILAS
				System.out.println("  -"+mx[i][0]);
				
			}
		}
	}
}
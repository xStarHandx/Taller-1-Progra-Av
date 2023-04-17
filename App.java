import java.io.*;
import java.util.Scanner;
public class App {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Producto[] listaProducto= new Producto[16];
		Producto[] listaCarrito= new Producto[50];
		leerArchivo(listaProducto);
		Elecciones(listaProducto,listaCarrito);
		
	}
	
	public static void Elecciones(Producto[]listaProducto,Producto[]listaCarrito) {
		int x=0;
		Scanner leer= new Scanner(System.in);
		imprimirMenu();
		System.out.println("Ingrese una Opcion:");
		int opcion= Integer.valueOf(leer.nextLine());
		while (opcion!=6) {
			if(opcion==1) {
				for (int i=0;i<listaProducto.length;i++) {
					System.out.println(listaProducto[i].toString());
				}
			}else if(opcion==2) {
				for(int i=0;i<listaCarrito.length;i++) {
					if(listaCarrito[i]!=null) {
						System.out.println(listaCarrito[i].toString());
					}
				}
			}else if(opcion==3) {
				ingresarProducto(listaProducto,listaCarrito,x);
				x++;
			}else if(opcion==4) {
				eliminarProducto(listaCarrito);
				
			}else if(opcion==5) {
				int total= entregarPrecio(listaCarrito);
				System.out.println("Total de compra: "+total);
			}
			
			imprimirMenu();
			System.out.println("Ingrese una Opcion:");
			opcion= Integer.valueOf(leer.nextLine());
		}
		System.out.println("Logout... saliendo del programa!");
	}
	
	public static int entregarPrecio(Producto[]listaCarrito) {
		int suma=0;
		for(int i=0;i<listaCarrito.length;i++) {
			if (listaCarrito[i]!=null) {
				suma+=listaCarrito[i].getPrecio();
			}
		}
		return suma;
	}
	
	public static void ingresarProducto(Producto[]listaProducto,Producto[]listaCarrito,int x) {
		Scanner leer= new Scanner(System.in);
		System.out.println("Ingrese Codigo del Producto");
		String codigo= leer.nextLine();
		int posProducto= buscarProducto(listaProducto,codigo);
		if (posProducto==-1) {
			System.out.println("Producto no encontrado");
		}else {
			Producto producto= listaProducto[posProducto];
			listaCarrito[x]=producto;
			System.out.println("Producto Agregado");
		}
	}
	
	public static void eliminarProducto(Producto[]listaCarrito) {
		Scanner leer= new Scanner(System.in);
		System.out.println("Ingrese Codigo de Producto para Eliminar");
		String codigo= leer.nextLine();
		int posProducto= buscarProducto(listaCarrito,codigo);
		if (posProducto==-1) {
			System.out.println("Producto no encontrado");
		}else {
			listaCarrito[posProducto]=null;
			System.out.println("Producto Eliminado");
		}
	}
	
	public static int buscarProducto(Producto[]lista,String codigo) {
		for (int i=0;i<lista.length;i++) {
			if (lista[i].getCodigo().equalsIgnoreCase(codigo)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void leerArchivo(Producto[]listaProducto) throws FileNotFoundException {
		File archivo= new File("productos.txt");
		Scanner leer= new Scanner(archivo);
		int x=0;
		while (leer.hasNextLine()) {
			String linea= leer.nextLine();
			String[]partes= linea.split(",");
			Producto producto= new Producto(partes[0],partes[1],Integer.valueOf(partes[2]));
			listaProducto[x]=producto;
			x++;
		}
		leer.close();
	}
	
	public static void imprimirMenu() {
		System.out.println("** Menu **");
		System.out.println("1. Ver Productos.");
		System.out.println("2. Ver Carrito de Compras.");
		System.out.println("3. Agregar Producto al Carrito de Compras.");
		System.out.println("4. Eliminar un Producto del Carrito de Compras.");
		System.out.println("5. Calcular Total Compra.");
		System.out.println("6. Log Out.");
	}

}

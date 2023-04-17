import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class lecturaArchivos {

	public static void main(String[] args) {
		
		
		
		
	}
	
	public static void leerIa() throws FileNotFoundException {
		
		File datosIa = new File("datos_ia.txt");
		Scanner read = new Scanner(datosIa,"UTF-8");
		while(read.hasNextLine()) {
			String data = read.nextLine();
			String partes [] = data.split(",");
			String nombreIa = partes[0];
			int añoCreacion = Integer.parseInt(partes[1]);
			int velocidadAprendizaje = Integer.parseInt(partes[2]);
			String tipo = partes[3];
			String creador = partes[4];
			int cantMejoras = Integer.parseInt(partes[5]);
			
			
		}
	}
	
	public static void leerCreadores() throws FileNotFoundException {
		
		File datosCreadores = new File("datos_creadores.txt");
		Scanner read2 = new Scanner(datosCreadores, "UTF-8");
		while(read2.hasNextLine()) {
			String data2 = read2.nextLine();
			String partes [] = data2.split(",");
			String nombreCreador = partes[0];
			int experiencia = Integer.parseInt(partes[1]);
			String especialidad = partes[2];
			int edad = Integer.parseInt(partes[3]);
			
		}
	}
	
	public static void leerUsuarios() throws FileNotFoundException {
		
		File datosUsuarios = new File("datos_usuarios.txt");
		Scanner read3 = new Scanner(datosUsuarios,"UTF-8");
		while(read3.hasNextLine()) {
			String data3 = read3.nextLine();
			String partes [] = data3.split(",");
			String nombreUsuario = partes[0];
			String contraseña = partes[1];
			String categoria = partes[2];
			String nombreCreador = partes[3];
		
		}
		
	}

}

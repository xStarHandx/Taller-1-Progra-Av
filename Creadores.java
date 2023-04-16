package TALLERES;

public class Creadores {
	String nombreCreador;
	int experiencia;
	String especialidad;
	int edad;
	
	public Creadores(String nombreCreador, int experiencia, String especialidad, int edad) {
		this.nombreCreador = nombreCreador;
		this.experiencia = experiencia;
		this.especialidad = especialidad;
		this.edad = edad;
	}

	public String getNombreCreador() {
		return nombreCreador;
	}

	public void setNombreCreador(String nombreCreador) {
		this.nombreCreador = nombreCreador;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Creadores [nombreCreador=" + nombreCreador + ", experiencia=" + experiencia + ", especialidad="
				+ especialidad + ", edad=" + edad + "]";
	}
	
	

}

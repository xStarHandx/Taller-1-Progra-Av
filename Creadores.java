package TALLERES;

public class Creadores {
	String nombreIA;
	int añoCreacion;
	int velocidad;
	String tipo;
	String creador;
	int cantidadMejoras;
	
	public Creadores(String nombreIA, int añoCreacion, int velocidad, String tipo, String creador,int cantidadMejoras) {
		this.nombreIA = nombreIA;
		this.añoCreacion = añoCreacion;
		this.velocidad = velocidad;
		this.tipo = tipo;
		this.creador = creador;
		this.cantidadMejoras = cantidadMejoras;
	}

	public String getNombreIA() {
		return nombreIA;
	}

	public void setNombreIA(String nombreIA) {
		this.nombreIA = nombreIA;
	}

	public int getAñoCreacion() {
		return añoCreacion;
	}

	public void setAñoCreacion(int añoCreacion) {
		this.añoCreacion = añoCreacion;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}

	public int getCantidadMejoras() {
		return cantidadMejoras;
	}

	public void setCantidadMejoras(int cantidadMejoras) {
		this.cantidadMejoras = cantidadMejoras;
	}

	@Override
	public String toString() {
		return "Creadores [nombreIA=" + nombreIA + ", añoCreacion=" + añoCreacion + ", velocidad=" + velocidad+ ", tipo=" + tipo + ", creador=" + creador + ", cantidadMejoras=" + cantidadMejoras + "]";
	}
	
	
	

}

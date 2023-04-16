package TALLERES;

public class Usuarios {
	String nombreUsuario;
	String contraseña;
	String categoria;
	String nombreCreador;
	
	public Usuarios(String nombreUsuario, String contraseña, String categoria, String nombreCreador) {
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.categoria = categoria;
		this.nombreCreador = nombreCreador;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNombreCreador() {
		return nombreCreador;
	}

	public void setNombreCreador(String nombreCreador) {
		this.nombreCreador = nombreCreador;
	}

	@Override
	public String toString() {
		return "Usuarios [nombreUsuario=" + nombreUsuario + ", contraseña=" + contraseña + ", categoria=" + categoria+ ", nombreCreador=" + nombreCreador + "]";
	}
	
	
	
}

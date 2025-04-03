package gestion.clases;

public class Cliente {

	private String nombre;
	private String email;
	private String telefono;
	
	public Cliente (String nombre, String email) {
		this.setNombre(nombre);
		this.setEmail(email);
	}
	
	public Cliente (String nombre, String email, String telefono) {
		this.setNombre(nombre);
		this.setEmail(email);
		this.setTelefono(telefono);
	}
	
	public Cliente() {
		nombre = "Cliente contado";
		email = "Na";
		telefono = "Na";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
	    return nombre;
	}
}

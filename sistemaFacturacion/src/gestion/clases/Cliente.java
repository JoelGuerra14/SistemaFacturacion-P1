package gestion.clases;

public class Cliente extends Usuario{
	
	private String telefono;
	
	public Cliente (int id, String nombre, String apellido, String email, String telefono) {
		super(id, nombre, apellido, email);
		this.telefono = telefono;
	}
	
	public Cliente() {
		nombre = "Cliente contado";
		email = "N/A";
		telefono = "N/A";
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
	    return nombre + " " + apellido;
	}
}
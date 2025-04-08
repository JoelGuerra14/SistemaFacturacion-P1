package gestion.clases;

public class Proveedor {

	private int id;
	private String nombre;
	private String telefono;
	private String direccion;

	// Constructor
	public Proveedor (int id, String nombre, String telefono, String direccion) {
		this.setId(id);
		this.setNombre(nombre);
		this.setTelefono(telefono);
		this.direccion = direccion;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}

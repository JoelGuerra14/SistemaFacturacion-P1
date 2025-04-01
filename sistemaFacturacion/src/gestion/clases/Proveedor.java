package gestion.clases;

import java.util.ArrayList;

public class Proveedor {

	private String id;
	private String nombre;
	private String contacto;
	
	//ArrayList <Producto> listaProductos = new ArrayList<>();
	
	// Constructor
	public Proveedor (String id, String nombre, String contacto) {
		this.setId(id);
		this.setNombre(nombre);
		this.setContacto(contacto);
	}

	// Getters y Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
}

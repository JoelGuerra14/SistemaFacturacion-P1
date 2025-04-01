package gestion.clases;

import java.util.ArrayList;

public abstract class Transaccion {

	private String id;
	private String fecha;
	private double total;
	ArrayList <Producto> listaProductos = new ArrayList<>();
	
	// Setters y Getters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	// Metodos
	
	public void calcularTotal() {
		
	}
	public void mostrarDetalle() {
		
	}
	
}

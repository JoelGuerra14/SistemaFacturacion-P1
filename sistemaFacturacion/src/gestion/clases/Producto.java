package gestion.clases;

import java.util.ArrayList;

public class Producto {

	private int id;
	private String nombre;
	private double precio;
	private int stock;
	private Proveedor proveedor;
	private int codigo;
	public static ArrayList<Producto> listaProductos = new ArrayList<Producto>();
	
	// Constructores
	public Producto(int id, String nombre, double precio, int stock, int codigo) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.codigo = codigo;
	}
	public Producto(int id, String nombre, double precio, int stock, int codigo, Proveedor proveedor) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.codigo = codigo;
		this.proveedor = proveedor;
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}

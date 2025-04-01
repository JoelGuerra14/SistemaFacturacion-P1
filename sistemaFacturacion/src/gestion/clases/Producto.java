package gestion.clases;

public abstract class Producto {

	private String id;
	private String nombre;
	private double precio;
	private int stock;
	private Categoria categoria;
	private Proveedor proveedor;
	
	// Constructores
	public Producto(String id, String nombre, double precio, int stock) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}
	public Producto(String id, String nombre, double precio, int stock, Categoria categoria, Proveedor proveedor) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.categoria = categoria;
		this.proveedor = proveedor;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	//Metodos
	
	public void actualizarStock() {
		
	}
	
	public void mostrarInfo() {
		
	}
}

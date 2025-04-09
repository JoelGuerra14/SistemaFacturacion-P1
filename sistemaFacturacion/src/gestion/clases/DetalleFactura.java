package gestion.clases;

public class DetalleFactura {
    private int id; //
    private Producto producto;
    private int cantidad;
    private double precioUnitario; // Precio en el momento de la venta
    private double importe;
    private double impuesto;
    
    // Constructores
    public DetalleFactura() {}
    
    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
        calcularValores();
    }
    
    // Constructor para cuando el precio de venta difiere del precio del producto
    public DetalleFactura(Producto producto, int cantidad, double precioUnitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        calcularValores();
    }
    
    private void calcularValores() {
        this.importe = this.precioUnitario * this.cantidad;
        this.impuesto = this.importe * 0.18; // 
    }
    
    // Getters y setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularValores(); // Recalcula al cambiar cantidad
    }
    
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        calcularValores(); // Recalcula al cambiar precio
    }
    
    public double getImporte() {
        return importe;
    }
    
    public double getImpuesto() {
        return impuesto;
    }
    
    // Método para obtener el total (importe + impuesto)
    public double getTotal() {
        return importe + impuesto;
    }
}
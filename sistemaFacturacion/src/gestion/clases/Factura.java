package gestion.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {
	private int idFactura;
	private String numeroFactura;
	private Cliente cliente;
	private LocalDate fecha;
	private List<DetalleFactura> detalles;
	private double subtotal;
	private double totalImpuestos;
	private double total;
	public static ArrayList <Factura> listaFacturas  = new ArrayList<>();
	

    public Factura() {
        this.detalles = new ArrayList<>();
        this.setFecha(LocalDate.now());
    }
    
    public Factura(String numeroFactura, Cliente cliente) {
        this();
        this.numeroFactura = numeroFactura;
        this.setCliente(cliente);
    }
    
    public Factura(String numeroFactura, Cliente cliente, LocalDate fecha,
        List<DetalleFactura> detalles) {
        this.numeroFactura = numeroFactura;
        this.setCliente(cliente);
        this.setFecha(fecha != null ? fecha : LocalDate.now());
        this.detalles = detalles != null ? detalles : new ArrayList<>();
        calcularTotales();
    }
    
    public void calcularTotales() {
        this.subtotal = 0;
        this.totalImpuestos = 0;
        
        for (DetalleFactura detalle : detalles) {
            this.subtotal += detalle.getImporte();
            this.totalImpuestos += detalle.getImpuesto();
        }
        
        this.setTotal(this.subtotal + this.totalImpuestos);
    }
    
    public void agregarDetalle(DetalleFactura detalle) {
        if (detalle != null) {
            this.detalles.add(detalle);
            calcularTotales();
        }
    }
    
    public String getNumeroFactura() {
        return numeroFactura;
    }
    
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
}

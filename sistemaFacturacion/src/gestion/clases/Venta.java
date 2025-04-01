package gestion.clases;

import gestion.interfaces.ITransaccionable;

public class Venta extends Transaccion implements ITransaccionable{

	private String cliente;
	private String metodoPago;
	
	// Constructor
	public Venta () {
		
	}
	
	// Setters y Getters
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	
	// Metodos
	public void procesarVenta() {
			// Reducimos el stock
	}

	@Override
	public void realizarTransaccion() {
		// TODO Auto-generated method stub
		
	}

}

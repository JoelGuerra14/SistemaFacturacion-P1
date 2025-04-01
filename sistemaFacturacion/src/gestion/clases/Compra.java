package gestion.clases;

import gestion.interfaces.ITransaccionable;

public class Compra extends Transaccion implements ITransaccionable{

	private Proveedor proveedor;
	private double monto;
	
	// Constructor
	public Compra () {
		
	}

	// Setters y Getters
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	// Metodos
	public void procesarCompra() {
		
	}

	@Override
	public void realizarTransaccion() {
		// TODO Auto-generated method stub
		
	}
}

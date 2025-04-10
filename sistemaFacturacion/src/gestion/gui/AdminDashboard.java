package gestion.gui;

import javax.swing.JFrame; 
import javax.swing.JTabbedPane;

public class AdminDashboard extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminDashboard() {
		super("Panel de Administración");
		setSize(797, 682);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        PanelVentas panelVentas = new PanelVentas();
        tabbedPane.addTab("Ventas", panelVentas);
		
		//Añadimos cada panel independiente
        tabbedPane.addTab("Clientes", new PanelClientes());
        tabbedPane.addTab("Productos", new PanelProductos()); 
        tabbedPane.addTab("Proveedores", new PanelProveedores());
        tabbedPane.addTab("Usuarios", new PanelUsuarios());
        tabbedPane.addTab("Facturas", new PanelFacturas());
        
        getContentPane().add(tabbedPane);
        setVisible(true);
	}
}

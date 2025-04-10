package gestion.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class EmpleadoDashboard extends JFrame {
    
    private static final long serialVersionUID = 1L;

    public EmpleadoDashboard() {
        super("Panel de Empleado");
        setSize(797, 682);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        PanelVentas panelVentas = new PanelVentas();
        tabbedPane.addTab("Ventas", panelVentas);
        tabbedPane.addTab("Facturas", new PanelFacturas());
        getContentPane().add(tabbedPane);
    }
    
    public JFrame getFrame() {
        return this;
    }
}
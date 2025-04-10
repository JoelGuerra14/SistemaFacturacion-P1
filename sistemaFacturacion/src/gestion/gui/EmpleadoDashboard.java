package gestion.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
        
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("Opciones");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Cerrar sesión");
        mntmNewMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				java.awt.Point location = getLocation();
				dispose();
				Login loginf = new Login();
				loginf.getFrame().setLocation(location);
				loginf.getFrame().setVisible(true);
			}
        	
        });
        mnNewMenu.add(mntmNewMenuItem);
    }
    
    
    
    public JFrame getFrame() {
        return this;
    }
}
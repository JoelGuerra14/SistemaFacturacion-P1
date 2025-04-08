package gestion.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class PanelProductos extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTable table;

    public PanelProductos() {
        setPreferredSize(new Dimension(775, 618));
        setLayout(null);

        
        // ============ PRIMERA PARTE (PRODUCTOS) ============
        
        JPanel panelTituloLbl = new JPanel();
        panelTituloLbl.setBounds(0, 0, 330, 62);
		panelTituloLbl.setBackground(new Color(95, 170, 254));
		add(panelTituloLbl);
		panelTituloLbl.setLayout(null);
		
        JLabel lblNewLabel_1 = new JLabel("  Registro de Productos");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBorder(BorderFactory.createRaisedBevelBorder());
        lblNewLabel_1.setBounds(10, 11, 310, 40);
        panelTituloLbl.add(lblNewLabel_1);
		
        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setBounds(24, 95, 22, 29);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(56, 95, 73, 24);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(84, 130, 132, 24);
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_1.setColumns(10);
        add(textField_1);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(24, 126, 55, 29);
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblNombre);
        
        textField_2 = new JTextField();
        textField_2.setBounds(286, 99, 134, 24);
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_2.setColumns(10);
        add(textField_2);
        
        JLabel lblNombre_1 = new JLabel("Precio");
        lblNombre_1.setBounds(226, 95, 55, 29);
        lblNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblNombre_1);
        
        JLabel lblNombre_1_1 = new JLabel("Stock");
        lblNombre_1_1.setBounds(226, 129, 55, 29);
        lblNombre_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblNombre_1_1);
        
        textField_3 = new JTextField();
        textField_3.setBounds(286, 133, 134, 24);
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_3.setColumns(10);
        add(textField_3);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(502, 95, 115, 29);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Categoria 1", "Categoria 2", "Categoria 3", "Categoria 4"}));
        add(comboBox);
        
        JLabel lblCategoria = new JLabel("Categoria");
        lblCategoria.setBounds(430, 95, 65, 29);
        lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblCategoria);
        
        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setBounds(502, 127, 115, 29);
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Proveedor 1", "Proveedor 2", "Proveedor 3"}));
        comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(comboBox_1);
        
        JLabel lblProveedor = new JLabel("Proveedor");
        lblProveedor.setBounds(430, 127, 65, 29);
        lblProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblProveedor);
        
        JButton btnNewButton = new JButton("Agregar");
        btnNewButton.setBounds(138, 178, 92, 29);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(btnNewButton);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(295, 178, 92, 29);
        btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(460, 178, 187, 29);
        btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(btnEliminar);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 226, 765, 2);
        add(separator);
        
        // ============ TABLA DE PRODUCTOS ============
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 238, 755, 370);
        add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nombre", "Precio", "Stock", "Categoria", "Proveedor"}
        ));
        scrollPane.setViewportView(table);
        

        
        // ============ MENÚ ============
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 205, 22);
        add(menuBar);
        
        JMenu mnNewMenu = new JMenu("Archivo");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Exportar");
        mnNewMenu.add(mntmNewMenuItem);
        
        JMenuItem cerrarSesionMenu = new JMenuItem("Cerrar sesión");
        cerrarSesionMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Window ventanaP = SwingUtilities.getWindowAncestor(PanelProductos.this);
                Point location = ventanaP.getLocation();
                ventanaP.dispose();
                Login login = new Login();
                login.getFrame().setLocation(location);
                login.getFrame().setVisible(true);
            }
        });
        mnNewMenu.add(cerrarSesionMenu);
        
        JMenu mnNewMenu_1 = new JMenu("Ayuda");
        menuBar.add(mnNewMenu_1);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Documento Ayuda");
        mnNewMenu_1.add(mntmNewMenuItem_1);
        
        JMenu mnNewMenu_2 = new JMenu("Mas opciones");
        menuBar.add(mnNewMenu_2);
        
        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Crear Categoria");
        mnNewMenu_2.add(mntmNewMenuItem_2);

    }
}

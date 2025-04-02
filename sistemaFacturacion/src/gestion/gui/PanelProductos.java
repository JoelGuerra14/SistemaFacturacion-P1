package gestion.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTable table;

    public PanelProductos() {
        setPreferredSize(new Dimension(775, 618));
        setLayout(null);

        
        // ============ PRIMERA PARTE (PRODUCTOS) ============
        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(32, 74, 22, 29);
        add(lblNewLabel);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField.setBounds(64, 77, 73, 24);
        add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_1.setColumns(10);
        textField_1.setBounds(92, 117, 132, 24);
        add(textField_1);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(32, 113, 55, 29);
        add(lblNombre);
        
        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_2.setColumns(10);
        textField_2.setBounds(90, 153, 134, 24);
        add(textField_2);
        
        JLabel lblNombre_1 = new JLabel("Precio");
        lblNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre_1.setBounds(30, 149, 55, 29);
        add(lblNombre_1);
        
        JLabel lblNombre_1_1 = new JLabel("Stock");
        lblNombre_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre_1_1.setBounds(30, 183, 55, 29);
        add(lblNombre_1_1);
        
        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_3.setColumns(10);
        textField_3.setBounds(90, 187, 134, 24);
        add(textField_3);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Categoria 1", "Categoria 2", "Categoria 3", "Categoria 4"}));
        comboBox.setBounds(320, 74, 115, 29);
        add(comboBox);
        
        JLabel lblCategoria = new JLabel("Categoria");
        lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCategoria.setBounds(248, 74, 73, 29);
        add(lblCategoria);
        
        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Proveedor 1", "Proveedor 2", "Proveedor 3"}));
        comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox_1.setBounds(320, 114, 115, 29);
        add(comboBox_1);
        
        JLabel lblProveedor = new JLabel("Proveedor");
        lblProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProveedor.setBounds(248, 114, 73, 29);
        add(lblProveedor);
        
        JButton btnNewButton = new JButton("Agregar");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(248, 150, 92, 29);
        add(btnNewButton);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnEditar.setBounds(350, 150, 92, 29);
        add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnEliminar.setBounds(248, 187, 187, 29);
        add(btnEliminar);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 323, 1038, 2);
        add(separator);
        
        // ============ TABLA DE PRODUCTOS ============
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 335, 755, 273);
        add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nombre", "Precio", "Stock", "Categoria", "Proveedor"}
        ));
        scrollPane.setViewportView(table);
        
        // ============ SEGUNDA PARTE (PROVEEDORES) ============
        JLabel lblNewLabel_1 = new JLabel("Registro de Productos");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(10, 26, 159, 24);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("ID");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(497, 74, 22, 29);
        add(lblNewLabel_2);
        
        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_4.setColumns(10);
        textField_4.setBounds(539, 77, 73, 24);
        add(textField_4);
        
        JLabel lblNombre_2 = new JLabel("Nombre");
        lblNombre_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre_2.setBounds(497, 114, 55, 29);
        add(lblNombre_2);
        
        textField_5 = new JTextField();
        textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_5.setColumns(10);
        textField_5.setBounds(562, 117, 154, 24);
        add(textField_5);
        
        JLabel lblNombre_1_2 = new JLabel("Contacto");
        lblNombre_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre_1_2.setBounds(497, 150, 72, 29);
        add(lblNombre_1_2);
        
        textField_6 = new JTextField();
        textField_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_6.setColumns(10);
        textField_6.setBounds(572, 153, 144, 24);
        add(textField_6);
        
        JSeparator separator_1 = new JSeparator(SwingConstants.VERTICAL);
        separator_1.setBounds(473, 20, 7, 260);
        add(separator_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Registro de Proveedores");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(500, 10, 159, 24);
        add(lblNewLabel_1_1);
        
        // ============ MENÚ ============
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 471, 22);
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
        
        JButton btnNewButton_1 = new JButton("Agregar");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setBounds(522, 199, 92, 29);
        add(btnNewButton_1);
        
        JButton btnEditar_1 = new JButton("Editar");
        btnEditar_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnEditar_1.setBounds(624, 199, 92, 29);
        add(btnEditar_1);
        
        JButton btnEliminar_1 = new JButton("Eliminar");
        btnEliminar_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnEliminar_1.setBounds(522, 236, 187, 29);
        add(btnEliminar_1);

    }
}

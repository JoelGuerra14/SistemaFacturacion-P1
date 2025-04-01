package gestion.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class Ventana {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	ImageIcon imagenInventario = new ImageIcon("C:\\Users\\joel-\\eclipse-workspace\\sistemaInventario\\src\\Assets\\imagenInventario.png");
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private DefaultTableModel modelo;
	private JTable tablaProveedor;
	private JTextField textField_10;
	private JTextField textField_11;
	private JPasswordField passwordField;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTable table_1;
	DefaultTableModel modeloUsuario;
	/**
	 * Launch the application.
	 */
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 797, 682);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1063, 645);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(32, 74, 22, 29);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(64, 77, 73, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(92, 117, 132, 24);
		panel.add(textField_1);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(32, 113, 55, 29);
		panel.add(lblNombre);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(90, 153, 134, 24);
		panel.add(textField_2);
		
		JLabel lblNombre_1 = new JLabel("Precio");
		lblNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre_1.setBounds(30, 149, 55, 29);
		panel.add(lblNombre_1);
		
		JLabel lblNombre_1_1 = new JLabel("Stock");
		lblNombre_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre_1_1.setBounds(30, 183, 55, 29);
		panel.add(lblNombre_1_1);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(90, 187, 134, 24);
		panel.add(textField_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Categoria 1", "Categoria 2", "Categoria 3", "Categoria 4"}));
		comboBox.setBounds(320, 74, 115, 29);
		panel.add(comboBox);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoria.setBounds(248, 74, 73, 29);
		panel.add(lblCategoria);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Proveedor 1", "Proveedor 2", "Proveedor 3"}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setBounds(320, 114, 115, 29);
		panel.add(comboBox_1);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProveedor.setBounds(248, 114, 73, 29);
		panel.add(lblProveedor);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(248, 150, 92, 29);
		panel.add(btnNewButton);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditar.setBounds(350, 150, 92, 29);
		panel.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(248, 187, 187, 29);
		panel.add(btnEliminar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 323, 1038, 2);
		panel.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 335, 755, 273);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Precio", "Stock", "Categoria", "Proveedor"
			}
		));
		scrollPane.setViewportView(table);
		
		Image imgInv = imagenInventario.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		
		JLabel lblNewLabel_1 = new JLabel("Registro de Productos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 26, 159, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(497, 74, 22, 29);
		panel.add(lblNewLabel_2);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(539, 77, 73, 24);
		panel.add(textField_4);
		
		JLabel lblNombre_2 = new JLabel("Nombre");
		lblNombre_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre_2.setBounds(497, 114, 55, 29);
		panel.add(lblNombre_2);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_5.setColumns(10);
		textField_5.setBounds(562, 117, 154, 24);
		panel.add(textField_5);
		
		JLabel lblNombre_1_2 = new JLabel("Contacto");
		lblNombre_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre_1_2.setBounds(497, 150, 72, 29);
		panel.add(lblNombre_1_2);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_6.setColumns(10);
		textField_6.setBounds(572, 153, 144, 24);
		panel.add(textField_6);
		
		JSeparator separator_1 = new JSeparator(SwingConstants.VERTICAL);
		separator_1.setBounds(473, 20, 7, 260);
		panel.add(separator_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Registro de Proveedores");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(500, 10, 159, 24);
		panel.add(lblNewLabel_1_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 471, 22);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exportar");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem cerrarSesionMenu = new JMenuItem("Cerrar sesión");
		cerrarSesionMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Point location = frame.getLocation();
				
				frame.dispose();
				
				Login login = new Login();
				login.getFrame().setLocation(location);
				login.getFrame().setVisible(true);
			}
			
		});;
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
		panel.add(btnNewButton_1);
		
		JButton btnEditar_1 = new JButton("Editar");
		btnEditar_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditar_1.setBounds(624, 199, 92, 29);
		panel.add(btnEditar_1);
		
		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar_1.setBounds(522, 236, 187, 29);
		panel.add(btnEliminar_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Registro de Proveedores");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(24, 33, 159, 24);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("ID");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(21, 97, 22, 29);
		panel_1.add(lblNewLabel_2_1);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_7.setColumns(10);
		textField_7.setBounds(63, 100, 73, 24);
		panel_1.add(textField_7);
		
		JLabel lblNombre_2_1 = new JLabel("Nombre");
		lblNombre_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre_2_1.setBounds(158, 97, 55, 29);
		panel_1.add(lblNombre_2_1);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_8.setColumns(10);
		textField_8.setBounds(223, 100, 154, 24);
		panel_1.add(textField_8);
		
		JLabel lblNombre_1_2_1 = new JLabel("Contacto");
		lblNombre_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre_1_2_1.setBounds(400, 97, 72, 29);
		panel_1.add(lblNombre_1_2_1);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_9.setColumns(10);
		textField_9.setBounds(469, 100, 144, 24);
		panel_1.add(textField_9);
		
		JButton btnNewButton_1_1 = new JButton("Agregar");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(637, 97, 92, 29);
		panel_1.add(btnNewButton_1_1);
		
		String[] columnas = {"ID", "Nombre", "Contacto"};
		modelo = new DefaultTableModel(columnas, 0);
		
		tablaProveedor = new JTable(modelo);
		tablaProveedor.setCellSelectionEnabled(true);
		tablaProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		tablaProveedor.getColumnModel().getColumn(2).setCellRenderer(centro);
		
		
		JScrollPane spProveedor = new JScrollPane(tablaProveedor);
		spProveedor.setBounds(10, 164, 761, 444);
		panel_1.add(spProveedor);
		
		JPanel gestionUsuario = new JPanel();
		tabbedPane.addTab("New tab", null, gestionUsuario, null);
		gestionUsuario.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(20, 48, 60, 19);
		gestionUsuario.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Apellido");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(20, 90, 54, 19);
		gestionUsuario.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Correo");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(20, 131, 60, 19);
		gestionUsuario.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Usuario");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1.setBounds(263, 48, 54, 19);
		gestionUsuario.add(lblNewLabel_3_2_1);
		
		textField_10 = new JTextField();
		textField_10.setBounds(77, 46, 159, 27);
		gestionUsuario.add(textField_10);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(77, 88, 159, 27);
		gestionUsuario.add(textField_11);
		
		JLabel lblNewLabel_3_2_1_1 = new JLabel("Contraseña");
		lblNewLabel_3_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1_1.setBounds(263, 90, 82, 19);
		gestionUsuario.add(lblNewLabel_3_2_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(340, 89, 159, 24);
		gestionUsuario.add(passwordField);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(77, 132, 159, 27);
		gestionUsuario.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(314, 46, 159, 27);
		gestionUsuario.add(textField_13);
		
		JLabel lblNewLabel_3_2_1_1_1 = new JLabel("Rol");
		lblNewLabel_3_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1_1_1.setBounds(263, 135, 36, 19);
		gestionUsuario.add(lblNewLabel_3_2_1_1_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Admin");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNewRadioButton.setBounds(298, 134, 71, 23);
		gestionUsuario.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnEmpleado = new JRadioButton("Empleado");
		rdbtnEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnEmpleado.setBounds(389, 134, 95, 23);
		gestionUsuario.add(rdbtnEmpleado);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 247, 735, 345);
		gestionUsuario.add(scrollPane_1);
		
		String[] columnasUsr = {"Nombre", "Apellido", "Correo", "Usuario", "Rol", "Editar", "Eliminar"};
		modeloUsuario = new DefaultTableModel(columnasUsr, 0);
		table_1 = new JTable(modeloUsuario);
		scrollPane_1.setViewportView(table_1);
		
		JSeparator separator_2 = new JSeparator(SwingConstants.VERTICAL);
		separator_2.setBounds(20, 170, 743, 2);
		gestionUsuario.add(separator_2);
		
		JButton btnNewButton_2 = new JButton("Agregar");
		btnNewButton_2.setBounds(654, 209, 101, 27);
		gestionUsuario.add(btnNewButton_2);
	}
}

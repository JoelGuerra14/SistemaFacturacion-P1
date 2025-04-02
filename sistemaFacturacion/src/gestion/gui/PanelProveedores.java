package gestion.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelProveedores extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_7, textField_8, textField_9;
	private DefaultTableModel modelo;
	private JTable tablaProveedor;
	
	public PanelProveedores() {
		setPreferredSize(new Dimension(775, 618));
		setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Registro de Proveedores");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(24, 33, 159, 24);
		add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("ID");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(21, 97, 22, 29);
		add(lblNewLabel_2_1);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_7.setColumns(10);
		textField_7.setBounds(63, 100, 73, 24);
		add(textField_7);
		
		JLabel lblNombre_2_1 = new JLabel("Nombre");
		lblNombre_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre_2_1.setBounds(158, 97, 55, 29);
		add(lblNombre_2_1);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_8.setColumns(10);
		textField_8.setBounds(223, 100, 154, 24);
		add(textField_8);
		
		JLabel lblNombre_1_2_1 = new JLabel("Contacto");
		lblNombre_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre_1_2_1.setBounds(400, 97, 72, 29);
		add(lblNombre_1_2_1);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_9.setColumns(10);
		textField_9.setBounds(469, 100, 144, 24);
		add(textField_9);
		
		JButton btnNewButton_1_1 = new JButton("Agregar");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(637, 97, 92, 29);
		add(btnNewButton_1_1);
		
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
		add(spProveedor);
		
		
	}
}

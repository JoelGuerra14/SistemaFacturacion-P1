package gestion.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelVentas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblNewLabel;
	private JTextField tNoFactura;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField tPrecio;
	private JTextField tImpuesto;
	private JTextField textField_2;
	private JTable table;
	
	public PanelVentas() {
		setBackground(new Color(255, 255, 255));
		setPreferredSize(new Dimension(775, 618));
		setLayout(null);
		
		JPanel panelTituloLbl = new JPanel();
		panelTituloLbl.setBackground(new Color(95, 170, 254));
		panelTituloLbl.setBounds(0, 0, 330, 62);
		add(panelTituloLbl);
		panelTituloLbl.setLayout(null);
		
		lblNewLabel = new JLabel("  Facturación y Ventas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		lblNewLabel.setBounds(10, 11, 310, 40);
		panelTituloLbl.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("No. Factura");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 73, 187, 23);
		add(lblNewLabel_1);
		
		tNoFactura = new JTextField();
		tNoFactura.setBounds(207, 74, 98, 25);
		add(tNoFactura);
		tNoFactura.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cliente");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 107, 58, 23);
		add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(74, 108, 123, 25);
		add(textField);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha Fac.");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(552, 17, 80, 23);
		add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(642, 15, 123, 25);
		add(textField_1);
		
		JComboBox cbCliente = new JComboBox();
		cbCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbCliente.setBounds(207, 107, 98, 25);
		add(cbCliente);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(89, 168, 247));
		panel.setBounds(10, 156, 755, 73);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Artículo");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 11, 144, 17);
		panel.add(lblNewLabel_4);
		
		JSpinner spinnerCantidadProducto = new JSpinner();
		spinnerCantidadProducto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinnerCantidadProducto.setBounds(177, 32, 107, 30);
		panel.add(spinnerCantidadProducto);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 32, 144, 29);
		panel.add(comboBox);
		
		JLabel lblNewLabel_5 = new JLabel("Cantidad");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(177, 11, 107, 17);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Precio");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(306, 11, 107, 17);
		panel.add(lblNewLabel_6);
		
		tPrecio = new JTextField();
		tPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tPrecio.setColumns(10);
		tPrecio.setBounds(304, 32, 98, 30);
		panel.add(tPrecio);
		
		JLabel lblNewLabel_7 = new JLabel("Impuesto");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(423, 11, 107, 17);
		panel.add(lblNewLabel_7);
		
		tImpuesto = new JTextField();
		tImpuesto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tImpuesto.setColumns(10);
		tImpuesto.setBounds(421, 32, 98, 30);
		panel.add(tImpuesto);
		
		JLabel lblNewLabel_8 = new JLabel("Total");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(548, 11, 107, 17);
		panel.add(lblNewLabel_8);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(548, 32, 197, 30);
		panel.add(textField_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 240, 755, 210);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
	}	
}

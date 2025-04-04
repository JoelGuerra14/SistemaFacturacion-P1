package gestion.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import gestion.clases.Cliente;
import gestion.clases.Reloj;
import gestion.database.DatabaseConnection;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class PanelVentas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblNewLabel;
	private JTextField tNoFactura;
	private JTextField tfcliente;
	private JTextField tfFecha;
	private JTextField tfPrecio;
	private JTextField tImpuesto;
	private JTextField tfTotalDetalle;
	JSpinner spinnerCantidadProducto;
	JComboBox <Cliente> cbCliente;
	ArrayList <Cliente> listaClientes = new ArrayList<>();
	private JTable table;
	private DefaultTableModel modelo;
	private JTextField tfArticulo;
	private JTextField tfSubTotal;
	private JTextField tfImpuesto;
	private JTextField tfTotalFactura;
	private static Connection con = DatabaseConnection.getConnection();
	private JTextField tfCodigo;
	
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
		lblNewLabel_1.setBounds(10, 73, 98, 23);
		add(lblNewLabel_1);
		
		tNoFactura = new JTextField();
		tNoFactura.setEditable(false);
		tNoFactura.setBounds(110, 74, 195, 25);
		add(tNoFactura);
		tNoFactura.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cliente");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 107, 58, 23);
		add(lblNewLabel_2);
		
		tfcliente = new JTextField();
		tfcliente.setColumns(10);
		tfcliente.setBounds(74, 108, 123, 25);
		add(tfcliente);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha Fac.");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(552, 47, 80, 23);
		add(lblNewLabel_3);
		
		tfFecha = new JTextField();
		tfFecha.setEditable(false);
		tfFecha.setColumns(10);
		tfFecha.setBounds(642, 45, 123, 25);
		add(tfFecha);
		
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		tfFecha.setText(fechaActual.format(formatter));
		
		cbCliente = new JComboBox<>();
		cbCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbCliente.setBounds(207, 109, 134, 25);
		add(cbCliente);
		
		actualizarComboBox();
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(89, 168, 247));
		panel.setBounds(10, 375, 755, 73);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Artículo");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 11, 144, 17);
		panel.add(lblNewLabel_4);
		
		spinnerCantidadProducto = new JSpinner();
		spinnerCantidadProducto.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerCantidadProducto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinnerCantidadProducto.setBounds(222, 32, 107, 30);
		spinnerCantidadProducto.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				actualizarDetalle();
			}
			
		});
		panel.add(spinnerCantidadProducto);
		
		JLabel lblNewLabel_5 = new JLabel("Cantidad");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(222, 11, 107, 17);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Precio");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(351, 11, 107, 17);
		panel.add(lblNewLabel_6);
		
		tfPrecio = new JTextField();
		tfPrecio.setEditable(false);
		tfPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfPrecio.setColumns(10);
		tfPrecio.setBounds(349, 32, 98, 30);
		panel.add(tfPrecio);
		
		JLabel lblNewLabel_7 = new JLabel("Impuesto");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(468, 11, 107, 17);
		panel.add(lblNewLabel_7);
		
		tImpuesto = new JTextField();
		tImpuesto.setEditable(false);
		tImpuesto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tImpuesto.setColumns(10);
		tImpuesto.setBounds(466, 32, 98, 30);
		panel.add(tImpuesto);
		
		JLabel lblNewLabel_8 = new JLabel("Total");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(585, 11, 107, 17);
		panel.add(lblNewLabel_8);
		
		tfTotalDetalle = new JTextField();
		tfTotalDetalle.setEditable(false);
		tfTotalDetalle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfTotalDetalle.setColumns(10);
		tfTotalDetalle.setBounds(585, 32, 160, 30);
		panel.add(tfTotalDetalle);
		
		tfArticulo = new JTextField();
		tfArticulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfArticulo.setColumns(10);
		tfArticulo.setBounds(10, 32, 115, 30);
		panel.add(tfArticulo);
		
		JButton btnArticulo = new JButton("New button");
		btnArticulo.setBackground(new Color(89, 168, 247));
		btnArticulo.setBounds(124, 32, 30, 30);
		btnArticulo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				abrirVentanaProducto();
			}
			
		});
		panel.add(btnArticulo);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(160, 32, 52, 30);
		panel.add(tfCodigo);
		tfCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCodigo.setEditable(false);
		tfCodigo.setColumns(10);
		
		JLabel lblNewLabel_6_1 = new JLabel("Codigo");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6_1.setBounds(160, 11, 44, 17);
		panel.add(lblNewLabel_6_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 155, 755, 210);
		add(scrollPane);
		
		String[] columnas = {"Producto", "Nombre del Producto", "Unidad", "Cantidad", "Importe", "Impuesto", "Neto"};
		modelo = new DefaultTableModel(columnas, 0);
		
		
		// TRABAJANDO CON LA TABLA ------------------------------------------------
		table = new JTable(modelo) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
			
		};
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		//table.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		scrollPane.setViewportView(table);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 463, 98, 30);
		btnAgregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				agregarDetalle();
			}
			
		});
		add(btnAgregar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(110, 463, 98, 30);
		add(btnBorrar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(209, 463, 98, 30);
		add(btnEditar);
		
		JLabel lblNewLabel_2_1 = new JLabel("Sub-Total:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(503, 470, 80, 23);
		add(lblNewLabel_2_1);
		
		tfSubTotal = new JTextField();
		tfSubTotal.setText("0.0");
		tfSubTotal.setEditable(false);
		tfSubTotal.setColumns(10);
		tfSubTotal.setBounds(587, 469, 178, 25);
		add(tfSubTotal);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Impuesto:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(503, 499, 80, 23);
		add(lblNewLabel_2_1_1);
		
		tfImpuesto = new JTextField();
		tfImpuesto.setText("0.0");
		tfImpuesto.setEditable(false);
		tfImpuesto.setColumns(10);
		tfImpuesto.setBounds(587, 498, 178, 25);
		add(tfImpuesto);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Total:");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_2.setBounds(503, 528, 80, 23);
		add(lblNewLabel_2_1_2);
		
		tfTotalFactura = new JTextField();
		tfTotalFactura.setText("0.0");
		tfTotalFactura.setEditable(false);
		tfTotalFactura.setColumns(10);
		tfTotalFactura.setBounds(587, 527, 178, 25);
		add(tfTotalFactura);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(309, 463, 98, 30);
		add(btnLimpiar);
		
		JButton btnAgregarCliente = new JButton("New button");
		btnAgregarCliente.setBounds(341, 109, 25, 25);
		btnAgregarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				agregarCliente();
			}
			
		});
		add(btnAgregarCliente);
		
		JLabel lblReloj = new JLabel("Reloj");
		lblReloj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReloj.setBounds(642, 20, 123, 23);
		add(lblReloj);
		
		Reloj reloj = new Reloj(lblReloj);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHora.setBounds(551, 20, 68, 23);
		add(lblHora);
		reloj.iniciar();
		
		
	}	
	
	public void actualizarComboBox() {
		try{
			String sql = "SELECT nombre, email, telefono FROM clientes";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			
			listaClientes.clear();
			
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String telefono = rs.getString("telefono");
				listaClientes.add(new Cliente(nombre, email, telefono));
			}
			
			for(Cliente x : listaClientes) {
				cbCliente.addItem(x);
			}
			
		}catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al cargar usuarios: " + e.getMessage());
	    }
		
	}
	
	public void agregarCliente() {
		String cliente = cbCliente.getSelectedItem().toString();
		tfcliente.setText(cliente);
	}
	
	public void abrirVentanaProducto() {
		VentanaAgregarArticulo ventanaArticulo = new VentanaAgregarArticulo(this);
		ventanaArticulo.setLocation(getMousePosition());
		ventanaArticulo.setVisible(true);
	}
	
	public void setDatosProducto(String codigo, String nombre, String precio) {
		tfArticulo.setText(nombre);
		tfCodigo.setText(codigo);
		tfPrecio.setText(precio);
	}
	
	public void actualizarDetalle() {
		int cantidad = (int) spinnerCantidadProducto.getValue();
		double precio = Double.parseDouble(tfPrecio.getText());
		double impuestoUnitario = (precio * 0.18);
		
		if (cantidad == 0) {
			tImpuesto.setText(String.format("%.2f", impuestoUnitario));
            tfTotalDetalle.setText(String.format("%.2f", precio));
		} else {
		
			double impuestoTotal = impuestoUnitario * cantidad;
			double precioTotal = ((precio * cantidad) + impuestoTotal);
			
            tImpuesto.setText(String.format("%.2f", impuestoTotal));
            tfTotalDetalle.setText(String.format("%.2f", precioTotal));
		}	
	}
	
	public void agregarDetalle() {
		
		String producto = tfCodigo.getText();
		String nombre = tfArticulo.getText();
		double unidad = Double.parseDouble(tfPrecio.getText());
		int cantidad = (int) spinnerCantidadProducto.getValue();
		Double importe = unidad * cantidad;
		Double impuesto = Double.parseDouble(tImpuesto.getText());
		String neto = String.format("%.2f", importe + impuesto);
		
		modelo.addRow(new Object[]{producto, nombre, unidad, cantidad, importe, impuesto, neto});
		
		spinnerCantidadProducto.setValue(1);
		actualizarTotales();
	}
	
	public void actualizarTotales() {
		double subTotal = 0.0;
		double impuesto = 0.0;
		
	    for (int fila = 0; fila < modelo.getRowCount(); fila++) {
	        try {

	            Object valor = modelo.getValueAt(fila, 6);
	            double precio = Double.parseDouble(valor.toString());
	            subTotal += precio;
	            
	            Object valor2 = modelo.getValueAt(fila, 5);
	            double importe = Double.parseDouble(valor2.toString());
	            impuesto += importe;
	            
	        } catch (NumberFormatException e) {
	            System.err.println("Error en fila " + fila + ": " + e.getMessage());
	        }
	    }
	       
	    tfSubTotal.setText(String.format("%.2f",subTotal));
	    tfImpuesto.setText(String.format("%.2f",impuesto));
	    
	    tfTotalFactura.setText(String.format("%.2f",subTotal+impuesto));
	
	}
}

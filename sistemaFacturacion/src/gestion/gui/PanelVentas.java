package gestion.gui;

import java.awt.Dimension; 

import javax.swing.JPanel;
import javax.swing.AbstractButton;
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import gestion.clases.Cliente;
import gestion.clases.DetalleFactura;
import gestion.clases.Factura;
import gestion.clases.Producto;
import gestion.clases.Reloj;
import gestion.database.DatabaseConnection;
import gestion.util.Colors;
import gestion.util.GradientPanel;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class PanelVentas extends GradientPanel {
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
	static JComboBox <Cliente> cbCliente;
	static ArrayList <Cliente> listaClientes = new ArrayList<>();
	private JTable table;
	private DefaultTableModel modelo;
	private JTextField tfArticulo;
	private JTextField tfSubTotal;
	private JTextField tfImpuesto;
	private JTextField tfTotalFactura;
	private JTextField tfCodigo;
	private JButton btnAgregar;
	static Connection con = DatabaseConnection.getInstance().getConnection();
	
	private Factura facturaActual;
	private ArrayList<DetalleFactura> detallesTemporales = new ArrayList<>();
	private Producto productoSeleccionado;
	private boolean modoEdicion = false;
	private int indiceEdicion;
	
	
	public PanelVentas() {
		
		super(Colors.GRADIENT_START, Colors.GRADIENT_END);
		setPreferredSize(new Dimension(775, 618));
		setLayout(null);
		
		JPanel panelTituloLbl = new JPanel();
		panelTituloLbl.setBackground(Colors.PRIMARY);
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
		
		// Personalización del botón del ComboBox
		Component[] comps = cbCliente.getComponents();
		for (Component comp : comps) {
		    if (comp instanceof AbstractButton) {
		        AbstractButton button = (AbstractButton) comp;
		        
		        // Cambiar el color del botón
		        button.setBackground(Colors.BONE); // O usa AppColors.COMBOBOX_BUTTON si tienes la clase
		        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		    }
		}
		
		add(cbCliente);
		
		actualizarComboBox();
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Colors.SECONDARY);
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
		btnArticulo.setBackground(new Color(242, 235, 227));
		btnArticulo.setFocusPainted(false);
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
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 463, 98, 30);
		btnAgregar.setBackground(Colors.PASTEL_GREEN);
		btnAgregar.setFocusPainted(false);
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
		btnBorrar.setBackground(Colors.PASTEL_RED);
		btnBorrar.setFocusPainted(false);
		btnBorrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				borrarDetalle();
			}
			
		});
		add(btnBorrar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(209, 463, 98, 30);
		btnEditar.setBackground(Colors.PASTEL_YELLOW);
		btnEditar.setFocusPainted(false);
		btnEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				editarDetalle();
			}
			
		});
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
		btnLimpiar.setBackground(Colors.BONE);
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limpiarDetalles();
			}
			
		});
		add(btnLimpiar);
		
		JButton btnAgregarCliente = new JButton("New button");
		btnAgregarCliente.setBounds(341, 109, 25, 25);
		btnAgregarCliente.setBackground(Colors.BONE);
		btnAgregarCliente.setFocusPainted(false);
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
		
		JButton btnFactura = new JButton("Guardar");
		btnFactura.setBounds(614, 570, 98, 30);
		btnFactura.setBackground(new Color(136, 231, 136));
		btnFactura.setFocusPainted(false);
		btnFactura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				guardarFactura();
			}
			
		});
		add(btnFactura);
		reloj.iniciar();
		
		iniciarNuevaFactura();
		
		
	}	
	
	public static void actualizarComboBox() {
		try{
			cbCliente.removeAllItems();
			String sql = "SELECT id_cliente, nombre, apellido, email, telefono FROM clientes";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			
			listaClientes.clear();
			
			while(rs.next()) {
				int id = rs.getInt("id_cliente");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String telefono = rs.getString("telefono");
				listaClientes.add(new Cliente(id, nombre, apellido, email, telefono));
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
	    this.productoSeleccionado = Producto.listaProductos.stream()
	            .filter(p -> String.valueOf(p.getCodigo()).equals(codigo))
	            .findFirst()
	            .orElse(null);
	    
	    if (productoSeleccionado != null) {
	        tfArticulo.setText(nombre);
	        tfCodigo.setText(codigo);
	        tfPrecio.setText(precio);
	        actualizarDetalle();
	    }
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
		
	    if (productoSeleccionado == null) {
	        JOptionPane.showMessageDialog(this, "Debe seleccionar un producto primero");
	        return;
	    }
	    
	    int cantidad = (int) spinnerCantidadProducto.getValue();
	    
	    if (cantidad > productoSeleccionado.getStock() + (modoEdicion ? detallesTemporales.get(indiceEdicion).getCantidad() : 0)) {
	        JOptionPane.showMessageDialog(this, 
	            "No hay suficiente stock. Stock disponible: " + productoSeleccionado.getStock());
	        return;
	    }
	    
	    if (modoEdicion) {
	    	
	        DetalleFactura detalleEditado = detallesTemporales.get(indiceEdicion);
	        detalleEditado.setCantidad(cantidad);
	        detalleEditado.setPrecioUnitario(productoSeleccionado.getPrecio());
	        
	        actualizarTabla();
	        actualizarTotales();
		    limpiarCamposProducto(); 
	        
	        modoEdicion = false;
	        indiceEdicion = -1;
	    } else {

	        DetalleFactura detalle = new DetalleFactura(
	            productoSeleccionado, 
	            cantidad,
	            productoSeleccionado.getPrecio()
	        );
	        
	        detallesTemporales.add(detalle);
	        actualizarTabla();
	        actualizarTotales();
	    }

	    limpiarCamposProducto(); 
	}
	
	private void actualizarTabla() {
	    modelo.setRowCount(0);
	    
	    for (DetalleFactura detalle : detallesTemporales) {
	        
	    	// Formatear a 2 decimales
	        double importe = Math.round(detalle.getImporte() * 100.0) / 100.0;
	        double impuesto = Math.round(detalle.getImpuesto() * 100.0) / 100.0;
	        double neto = Math.round(detalle.getTotal() * 100.0) / 100.0;
	    	
	    	modelo.addRow(new Object[]{
	            detalle.getProducto().getCodigo(),
	            detalle.getProducto().getNombre(),
	            String.format("%.2f", detalle.getPrecioUnitario()),
	            detalle.getCantidad(),
	            String.format("%.2f", importe),  
	            String.format("%.2f", impuesto), 
	            String.format("%.2f", neto)      
	            
	        });
	    }
	}
	
	private void borrarDetalle() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila", "Error", JOptionPane.WARNING_MESSAGE);
		}else {
			int resp = JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar", "Confirmacion", JOptionPane.YES_NO_OPTION);
			
			if(resp == JOptionPane.YES_OPTION) {
				//modelo.removeRow(tabla.getSelectedRow());
				detallesTemporales.remove(table.getSelectedRow());
				actualizarTabla();
				actualizarTotales();
				
			}
		}
	}
	
	private void editarDetalle() {
		int fila = table.getSelectedRow();
		
		if (fila == -1) {
			
			JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila", "Error", JOptionPane.WARNING_MESSAGE);
			
		}else {
			
	        modoEdicion = true;
	        indiceEdicion = fila;
	      
	        DetalleFactura detalle = detallesTemporales.get(fila);
	        
	        productoSeleccionado = detalle.getProducto();
	        tfArticulo.setText(detalle.getProducto().getNombre());
	        tfCodigo.setText(String.valueOf(detalle.getProducto().getCodigo()));
	        tfPrecio.setText(String.format("%.2f", detalle.getPrecioUnitario()));
	        spinnerCantidadProducto.setValue(detalle.getCantidad());
	        
	        actualizarDetalle();
	        
	        btnAgregar.setText("Actualizar");
		}
	}

	public void actualizarTotales() {
	    double subTotal = detallesTemporales.stream().mapToDouble(DetalleFactura::getImporte).sum();
	    
	    double impuestos = detallesTemporales.stream().mapToDouble(DetalleFactura::getImpuesto).sum();
	            
	    double total = subTotal + impuestos;
	    
	    tfSubTotal.setText(String.format("%.2f", subTotal));
	    tfImpuesto.setText(String.format("%.2f", impuestos));
	    tfTotalFactura.setText(String.format("%.2f", total));
	}
	
	private void iniciarNuevaFactura() {
	    this.facturaActual = new Factura();
	    this.detallesTemporales.clear();
	    this.tNoFactura.setText(generarNumeroFactura());
	    this.tfFecha.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	    limpiarCamposProducto();
	    modelo.setRowCount(0); 
	    actualizarTotales();
	}
	
	private void guardarFactura() {
	    // Validaciones
	    if (detallesTemporales.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "No hay detalles para facturar");
	        return;
	    }
	    
	    if (tfcliente.getText().isEmpty()) {
	    	tfcliente.setText( String.valueOf(listaClientes.getFirst()));
	        return;
	    }
	    
	    facturaActual.setTotal(Double.parseDouble(tfTotalFactura.getText()));
	    
	    try {
	        con.setAutoCommit(false);
	        
	        String sqlFactura = "INSERT INTO facturas (id_cliente, fecha, total) VALUES (?, ?, ?)";
	        PreparedStatement stmtFactura = con.prepareStatement(sqlFactura, PreparedStatement.RETURN_GENERATED_KEYS);
	        
	        Cliente cliente = (Cliente) cbCliente.getSelectedItem();
	        stmtFactura.setInt(1, cliente.getId());
	        stmtFactura.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
	        stmtFactura.setDouble(3, facturaActual.getTotal());
	        
	        int affectedRows = stmtFactura.executeUpdate();
	        
	        if (affectedRows == 0) {
	            throw new SQLException("No se pudo guardar la factura");
	        }
	        
	        // Obtener ID generado
	        int idFactura;
	        try (ResultSet generatedKeys = stmtFactura.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                idFactura = generatedKeys.getInt(1);
	            } else {
	                throw new SQLException("No se obtuvo el ID de la factura");
	            }
	        }
	        
	        // 3. Guardar detalles
	        String sqlDetalle = "INSERT INTO detalle_factura (id_factura, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement stmtDetalle = con.prepareStatement(sqlDetalle);
	        
	        for (DetalleFactura detalle : detallesTemporales) {
	            stmtDetalle.setInt(1, idFactura);
	            stmtDetalle.setInt(2, detalle.getProducto().getId());
	            stmtDetalle.setInt(3, detalle.getCantidad());
	            stmtDetalle.setDouble(4, detalle.getPrecioUnitario());
	            stmtDetalle.setDouble(5, detalle.getImporte());
	            stmtDetalle.addBatch();
	        }
	        
	        stmtDetalle.executeBatch();
	        
	        // 4. Confirmar transacción
	        con.commit();
	        
	        // 5. Actualizar objeto factura
	        facturaActual.setIdFactura(idFactura);
	        facturaActual.setNumeroFactura(String.format("FAC-%05d", idFactura));
	        
	        // 6. Agregar a lista
	        Factura.listaFacturas.add(facturaActual);
	        
	        JOptionPane.showMessageDialog(this, "Factura guardada exitosamente. N°: " + facturaActual.getNumeroFactura());
	        
	        // 7. Iniciar nueva factura
	        PanelFacturas.recargarFactura();
	        iniciarNuevaFactura();
	        
	    } catch (SQLException e) {
	        try {
	            con.rollback();
	            JOptionPane.showMessageDialog(this, "Error al guardar factura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(this, "Error grave al hacer rollback: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } finally {
	        try {
	            con.setAutoCommit(true);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	private String generarNumeroFactura() {
	    try {
	        // Consulta para obtener el próximo ID de factura
	        String sql = "SELECT MAX(id_factura) + 1 AS next_id FROM facturas";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            int nextId = rs.getInt("next_id");
	            // Si es la primera factura, nextId será 0 + 1 = 1
	            if (nextId == 0) nextId = 1; // Para manejar cuando no hay facturas
	            
	            // Formatear el número de factura (ej: FAC-00001)
	            return String.format("FAC-%05d", nextId);
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, 
	            "Error al generar número de factura: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    // Fallback: Usar timestamp si hay error
	    return "FAC-" + System.currentTimeMillis();
	}
	
	private void limpiarCamposProducto() {
	    spinnerCantidadProducto.setValue(1);
	    
	    tfArticulo.setText("");  
	    tfCodigo.setText("");    
	    tfPrecio.setText("");    
	    tImpuesto.setText("");   
	    tfTotalDetalle.setText(""); 
	    productoSeleccionado = null;
	    
	    if (modoEdicion) {
	        modoEdicion = false;
	        indiceEdicion = -1;
	        btnAgregar.setText("Agregar");
	    }
	    
	}
	
	private void limpiarDetalles() {
		detallesTemporales.clear();
		actualizarTabla();
		actualizarTotales();	
	}
}

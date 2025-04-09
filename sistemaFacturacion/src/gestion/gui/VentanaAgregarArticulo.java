package gestion.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gestion.clases.Producto;
import gestion.database.DatabaseConnection;
import gestion.util.ButtonRenderer;
import gestion.util.Colors;
import gestion.util.GradientFrame;

public class VentanaAgregarArticulo extends GradientFrame{

	private JTable tabla;
	private DefaultTableModel modelo;
	private PanelVentas ventanaP;
	Connection con = DatabaseConnection.getInstance().getConnection();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaAgregarArticulo(PanelVentas ventanaP) {
        super(Colors.GRADIENT_START, Colors.GRADIENT_END);
		this.ventanaP = ventanaP;
		setBounds(100, 100, 741, 535);
		getContentPane().setLayout(null);
		
		JPanel panelTituloLbl = new JPanel();
		panelTituloLbl.setBackground(new Color(95, 170, 254));
		panelTituloLbl.setBounds(0, 0, 330, 62);
		getContentPane().add(panelTituloLbl);
		panelTituloLbl.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Productos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		lblNewLabel.setBounds(10, 11, 310, 40);
		panelTituloLbl.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 707, 402);
		getContentPane().add(scrollPane);
		
		String[] columnas = {"ID", "Nombre", "Precio", "Stock", "Codigo", "Agregar"};
		modelo = new DefaultTableModel(columnas, 0);
		
		tabla = new JTable(modelo) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
       
		tabla.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer( "Agregar", new Color(100, 200, 255)));
		
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());

                if (columna == 5) {
                	
                	String nombre = (String) tabla.getValueAt(fila, 1);
                	String precio = String.valueOf(tabla.getValueAt(fila, 2));
                	String codigo = String.valueOf(tabla.getValueAt(fila, 4));
                	
                	ventanaP.setDatosProducto(codigo, nombre, precio);
                	VentanaAgregarArticulo.this.dispose();
                	ventanaP.actualizarDetalle();
                	
                }
            }
        });
		
        scrollPane.setViewportView(tabla);
        cargarListaProductos();
        cargarTablaProductos();
		
	}
	
	public void cargarTablaProductos() {
	    modelo.setRowCount(0);

	    for (Producto p : Producto.listaProductos) {
	    	modelo.addRow(new Object[]{p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getCodigo(), "Agregar"});
	    }
	}
	
	public void cargarListaProductos() {
		Producto.listaProductos.clear();
	    try {
	        String query = "SELECT id_producto,  nombre, precio, stock, codigo FROM productos";
	        PreparedStatement statement = con.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id_producto");
	            String nombre = resultSet.getString("nombre");
	            double precio = resultSet.getDouble("precio");
	            int stock = resultSet.getInt("stock");
	            int codigo = resultSet.getInt("codigo");

	            Producto.listaProductos.add(new Producto(id, nombre, precio, stock, codigo));
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al cargar lista de productos: " + e.getMessage());
	    }
	}
}

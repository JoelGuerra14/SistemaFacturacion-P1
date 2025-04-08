package gestion.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import gestion.clases.Empleado;
import gestion.clases.Proveedor;
import gestion.database.DatabaseConnection;
import gestion.util.ButtonRenderer;

public class PanelProveedores extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfNombre, tfContacto;
	private static DefaultTableModel modelo;
	private JTable tablaProveedor;
	Window ventanaP = SwingUtilities.getWindowAncestor(PanelProveedores.this);
	static Connection con = DatabaseConnection.getInstance().getConnection();
	static ArrayList <Proveedor> listaProveedores = new ArrayList <Proveedor> ();
	private JTextField tfDireccion;
	
	public PanelProveedores() {
		setPreferredSize(new Dimension(775, 618));
		setLayout(null);
		
		JPanel panelTituloLbl = new JPanel();
		panelTituloLbl.setBackground(new Color(95, 170, 254));
		panelTituloLbl.setBounds(0, 0, 330, 62);
		add(panelTituloLbl);
		panelTituloLbl.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("  Registro de Proveedores");
		lblNewLabel_1_1_1.setBorder(BorderFactory.createRaisedBevelBorder());
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(10, 11, 310, 40);
		panelTituloLbl.add(lblNewLabel_1_1_1);
		
		JLabel lblNombre_2_1 = new JLabel("Nombre");
		lblNombre_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre_2_1.setBounds(10, 97, 55, 29);
		add(lblNombre_2_1);
		
		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfNombre.setColumns(10);
		tfNombre.setBounds(75, 100, 154, 24);
		add(tfNombre);
		
		JLabel lblNombre_1_2_1 = new JLabel("Telefono");
		lblNombre_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre_1_2_1.setBounds(239, 97, 72, 29);
		add(lblNombre_1_2_1);
		
		tfContacto = new JTextField();
		tfContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfContacto.setColumns(10);
		tfContacto.setBounds(308, 100, 120, 24);
		add(tfContacto);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAgregar.setBounds(662, 97, 92, 29);
		btnAgregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				agregarProveedor();
			}
			
		});
		add(btnAgregar);
		
		String[] columnas = {"ID", "Nombre", "Contacto", "Direccion", "Editar", "Eliminar"};
		modelo = new DefaultTableModel(columnas, 0);
		
		tablaProveedor = new JTable(modelo) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
		
		cargarProveedoresDesdeDB();
		mostrarProveedoresEnTabla();
		
		tablaProveedor.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer("Editar", new Color(100, 200, 255)));
		tablaProveedor.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer("Eliminar", new Color(255, 100, 100)));
		
		tablaProveedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaProveedor.rowAtPoint(e.getPoint());
                int columna = tablaProveedor.columnAtPoint(e.getPoint());

                if (columna == 4) {
                	int idProveedor = (int) tablaProveedor.getValueAt(fila, 0);
                    String nombre = (String) tablaProveedor.getValueAt(fila, 1);
                    String telefono = (String) tablaProveedor.getValueAt(fila, 2);
                    String direccion = (String) tablaProveedor.getValueAt(fila, 3);

                    
                    VentanaEditar(idProveedor, nombre, telefono, direccion);
                    //cargarUsuarios();
            		mostrarProveedoresEnTabla();
                }else if(columna == 5) {
                	int idUsuario = (int) tablaProveedor.getValueAt(fila, 0);
                	int confirmacion = JOptionPane.showConfirmDialog(null, 
                            "¿Estás seguro de que deseas eliminar este proveedor?", 
                            "Confirmar eliminación", 
                            JOptionPane.YES_NO_OPTION);
                	if (confirmacion == JOptionPane.YES_OPTION) {
                        eliminarProveedor(idUsuario);
                		mostrarProveedoresEnTabla();
                    }
                }
            }
        });
		
		tablaProveedor.setCellSelectionEnabled(true);
		tablaProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		tablaProveedor.getColumnModel().getColumn(2).setCellRenderer(centro);
		
		tablaProveedor.getColumnModel().getColumn(0).setPreferredWidth(30);  // ID
		tablaProveedor.getColumnModel().getColumn(1).setPreferredWidth(150); // Nombre
		tablaProveedor.getColumnModel().getColumn(2).setPreferredWidth(100); // Telefono
		tablaProveedor.getColumnModel().getColumn(3).setPreferredWidth(250); // Direccion
		
		
		JScrollPane spProveedor = new JScrollPane(tablaProveedor);
		spProveedor.setBounds(10, 164, 761, 444);
		add(spProveedor);
		

		tfDireccion = new JTextField();
		tfDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(502, 100, 149, 24);
		add(tfDireccion);
		
		JLabel labeldireccion = new JLabel("Direccion");
		labeldireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labeldireccion.setBounds(438, 97, 72, 29);
		add(labeldireccion);
		
		
	}
	
	public void VentanaEditar(int idProveedor, String nombre, String telefono, String direccion) {
		VentanaEditarProveedor ventanaEditarProv = new VentanaEditarProveedor();
		ventanaEditarProv.setDatos(idProveedor, nombre, telefono, direccion);
		ventanaEditarProv.setLocation(getMousePosition());
		ventanaEditarProv.setVisible(true);
	}
	
	public boolean faltanDatos(String nombre, String telefono, String direccion) {
		if (nombre.isBlank() || telefono.isBlank() || direccion.isBlank()) {
			return true;
		}
		return false;
	}
	
	public void agregarProveedor() {
		String nombre = tfNombre.getText();
		String telefono = tfContacto.getText();
		String direccion = tfDireccion.getText();
		
		
		if(!faltanDatos(nombre, telefono, direccion)) {
			
			String sql = "INSERT INTO proveedores (nombre, telefono, direccion) VALUES (?, ?, ?)";
			
	        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            ps.setString(1, nombre);
	            ps.setString(2, telefono);
	            ps.setString(3, direccion);

	           int filas = ps.executeUpdate();
	           if (filas > 0) {
	                ResultSet generatedKeys = ps.getGeneratedKeys();
	                if (generatedKeys.next()) {
	                    int id = generatedKeys.getInt(1);

	                    listaProveedores.add(new Proveedor(id, nombre, telefono, direccion));
	                    mostrarProveedoresEnTabla(); 
	                    clear();
	                }
	           }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(ventanaP, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
		} else {
	        JOptionPane.showMessageDialog(ventanaP, "Por favor, completa todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
	    }
		
	}
	
	private void cargarProveedoresDesdeDB() {
	    listaProveedores.clear();

	    try {
	        String query = "SELECT id_proveedor, nombre, telefono, direccion FROM proveedores";
	        PreparedStatement statement = con.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id_proveedor");
	            String nombre = resultSet.getString("nombre");
	            String telefono = resultSet.getString("telefono");
	            String direccion = resultSet.getString("direccion");

	            listaProveedores.add(new Proveedor(id, nombre, telefono, direccion));
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al cargar proveedores: " + e.getMessage());
	    }
	}
	
	public static void mostrarProveedoresEnTabla() {
	    modelo.setRowCount(0);

	    for (Proveedor p : listaProveedores) {
	        modelo.addRow(new Object[]{
	            p.getId(), p.getNombre(), p.getTelefono(), p.getDireccion(), "Editar", "Eliminar"
	        });
	    }
	}
	
	private void eliminarProveedor(int idProveedor) {
		String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, idProveedor);
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
	            JOptionPane.showMessageDialog(null, "Proveedor eliminado");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo eliminar el Proveedor.");
	        }
			
			for(Proveedor p : listaProveedores) {
				if (p.getId() == idProveedor) {
					listaProveedores.remove(p);
					break;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void clear() {
		tfNombre.setText("");
		tfContacto.setText("");
		tfDireccion.setText("");
	}
}

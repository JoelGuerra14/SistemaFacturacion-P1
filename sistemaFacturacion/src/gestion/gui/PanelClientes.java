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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import gestion.database.DatabaseConnection;
import gestion.interfaces.IGestionable;
import gestion.util.ButtonRenderer;
import gestion.util.Colors;
import gestion.util.GradientPanel;
import gestion.clases.Cliente;
import gestion.clases.Empleado;


public class PanelClientes extends GradientPanel implements IGestionable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nombreCliente, apellidoCliente, correoCliente, telCliente;
	static DefaultTableModel modeloCliente; 
	private JTable table_1;
	Window ventanaP = SwingUtilities.getWindowAncestor(PanelClientes.this);
	static Connection con = DatabaseConnection.getInstance().getConnection();
	static ArrayList <Cliente> listaClientes = new ArrayList<Cliente>();

	
	public PanelClientes() {
		super(Colors.GRADIENT_START, Colors.GRADIENT_END);
		setPreferredSize(new Dimension(775, 618));
		setLayout(null);
		
		JPanel panelTituloLbl = new JPanel();
		panelTituloLbl.setBackground(new Color(95, 170, 254));
		panelTituloLbl.setBounds(0, 0, 330, 62);
		add(panelTituloLbl);
		panelTituloLbl.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("  Registro de Clientes");
		lblNewLabel_1_1_1.setBorder(BorderFactory.createRaisedBevelBorder());
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(10, 11, 310, 40);
		panelTituloLbl.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(20, 137, 60, 19);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Apellido");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(20, 174, 54, 19);
		add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Correo");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(263, 174, 60, 19);
		add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Telefono");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1.setBounds(263, 137, 67, 19);
		add(lblNewLabel_3_2_1);
		
		nombreCliente = new JTextField();
		nombreCliente.setBounds(77, 133, 159, 27);
		add(nombreCliente);
		nombreCliente.setColumns(10);
		
		apellidoCliente = new JTextField();
		apellidoCliente.setColumns(10);
		apellidoCliente.setBounds(77, 170, 159, 27);
		add(apellidoCliente);
		
		correoCliente = new JTextField();
		correoCliente.setColumns(10);
		correoCliente.setBounds(320, 172, 159, 27);
		add(correoCliente);
		
		telCliente = new JTextField();
		telCliente.setColumns(10);
		telCliente.setBounds(320, 135, 159, 27);
		add(telCliente);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 247, 735, 345);
		add(scrollPane_1);
		
		String[] columnasUsr = {"ID", "Nombre", "Apellido", "Correo", "Telefono", "Editar", "Eliminar"};
		modeloCliente = new DefaultTableModel(columnasUsr, 0);
		
		//TRABAJANDO CON LA TABLA------------------------------------------------
		table_1 = new JTable(modeloCliente){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cargarDataDb();
        mostrarClientesEnTabla();
		//cargarUsuarios(); ///
		table_1.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer("Editar", new Color(100, 200, 255)));
		table_1.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer("Eliminar", Colors.PASTEL_RED));
        
        table_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = table_1.rowAtPoint(e.getPoint());
                int columna = table_1.columnAtPoint(e.getPoint());

                if (columna == 5) {
                	int idCliente = (int) table_1.getValueAt(fila, 0);
                    String nombre = (String) table_1.getValueAt(fila, 1);
                    String apellido = (String) table_1.getValueAt(fila, 2);
                    String correo = (String) table_1.getValueAt(fila, 3);
                    String telefono = (String) table_1.getValueAt(fila, 4);
                    
                    VentanaEditar(idCliente, nombre, apellido, correo, telefono);
                    //cargarUsuarios();
                    mostrarClientesEnTabla();
                }else if(columna == 6) {
                	int idCliente = (int) table_1.getValueAt(fila, 0);
                	int confirmacion = JOptionPane.showConfirmDialog(null, 
                            "¿Estás seguro de que deseas eliminar este cliente?", 
                            "Confirmar eliminación", 
                            JOptionPane.YES_NO_OPTION);
                	if (confirmacion == JOptionPane.YES_OPTION) {
                        eliminar(idCliente);
                        mostrarClientesEnTabla();
                    }
                }
            }
        });
        
		scrollPane_1.setViewportView(table_1);
		
		JSeparator separator_2 = new JSeparator(SwingConstants.VERTICAL);
		separator_2.setBounds(20, 170, 743, 2);
		add(separator_2);
		
		JButton btnAgregarCliente = new JButton("Agregar");
		btnAgregarCliente.setBounds(654, 209, 101, 27);
		btnAgregarCliente.setBackground(Colors.PASTEL_GREEN);
		btnAgregarCliente.setFocusPainted(false);
		btnAgregarCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				agregar();
			}
			
		});
		add(btnAgregarCliente);
	
	}
	
	public void VentanaEditar(int idCliente, String nombre, String apellido, String correo, String telefono) {
		VentanaEditarCliente ventanaEditarCliente = new VentanaEditarCliente();
		ventanaEditarCliente.setDatos(idCliente, nombre, apellido, correo, telefono);
		ventanaEditarCliente.setLocation(getMousePosition());
		ventanaEditarCliente.setVisible(true);
	}

	public boolean faltanDatos(String nombre, String apellido, String correo, String telefono) {
		if (nombre.isBlank() || apellido.isBlank() || correo.isBlank() || telefono.isBlank()) {
			return true;
		}
		return false;
	}
	
	/*public void agregarCliente() {
	    String nombre = nombreCliente.getText();
	    String apellido = apellidoCliente.getText();
	    String correo = correoCliente.getText();

	    String telefono = telCliente.getText();

	    if (!faltanDatos(nombre, apellido, correo, telefono)) {
	        String sql = "INSERT INTO clientes (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";

	        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            ps.setString(1, nombre);
	            ps.setString(2, apellido);
	            ps.setString(3, correo);
	            ps.setString(4, telefono);

	           int filas = ps.executeUpdate();
	           if (filas > 0) {
	                ResultSet generatedKeys = ps.getGeneratedKeys();
	                if (generatedKeys.next()) {
	                    int id = generatedKeys.getInt(1);

	                    listaClientes.add(new Cliente(id, nombre, apellido, correo, telefono));
	                    mostrarClientesEnTabla(); 
	                    PanelVentas.actualizarComboBox();
	                    clear();
	                }
	           }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(ventanaP, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(ventanaP, "Por favor, completa todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
	    }
	}*/
	
	/*private void cargarClientesDesdeDB() {
	    listaClientes.clear();

	    try {
	        String query = "SELECT id_cliente, nombre, apellido, email, telefono FROM clientes";
	        PreparedStatement statement = con.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id_cliente");
	            String nombre = resultSet.getString("nombre");
	            String apellido = resultSet.getString("apellido");
	            String correo = resultSet.getString("email");
	            String telefono = resultSet.getString("telefono");

	            listaClientes.add(new Cliente(id, nombre, apellido, correo, telefono));
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al cargar clientes: " + e.getMessage());
	    }
	}*/
	
	public static void mostrarClientesEnTabla() {
	    modeloCliente.setRowCount(0);

	    for (Cliente emp : listaClientes) {
	        modeloCliente.addRow(new Object[]{
	            emp.getId(), emp.getNombre(), emp.getApellido(),
	            emp.getEmail(), emp.getTelefono(),
	            "Editar", "Eliminar"
	        });
	    }
	}
	
	
	/*private void eliminarCliente(int idCliente) {
		String sql = "DELETE FROM clientes WHERE id_cliente = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, idCliente);
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
	            JOptionPane.showMessageDialog(null, "Cliente eliminado");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente.");
	        }
			
			for(Cliente x : listaClientes) {
				if (x.getId() == idCliente) {
					listaClientes.remove(x);
					break;
				}
			}
			
            PanelVentas.actualizarComboBox();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}*/
	
	public void clear() {
		nombreCliente.setText("");
		apellidoCliente.setText("");
		correoCliente.setText("");
		telCliente.setText("");
	}

	@Override
	public void agregar() {
		// TODO Auto-generated method stub
		String nombre = nombreCliente.getText();
	    String apellido = apellidoCliente.getText();
	    String correo = correoCliente.getText();

	    String telefono = telCliente.getText();

	    if (!faltanDatos(nombre, apellido, correo, telefono)) {
	        String sql = "INSERT INTO clientes (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";

	        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            ps.setString(1, nombre);
	            ps.setString(2, apellido);
	            ps.setString(3, correo);
	            ps.setString(4, telefono);

	           int filas = ps.executeUpdate();
	           if (filas > 0) {
	                ResultSet generatedKeys = ps.getGeneratedKeys();
	                if (generatedKeys.next()) {
	                    int id = generatedKeys.getInt(1);

	                    listaClientes.add(new Cliente(id, nombre, apellido, correo, telefono));
	                    mostrarClientesEnTabla();
	                    PanelVentas.actualizarComboBox();
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

	@Override
	public void editar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM clientes WHERE id_cliente = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
	            JOptionPane.showMessageDialog(null, "Cliente eliminado");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente.");
	        }
			
			for(Cliente x : listaClientes) {
				if (x.getId() == id) {
					listaClientes.remove(x);
					break;
				}
			}
			
            PanelVentas.actualizarComboBox();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void cargarDataDb() {
		// TODO Auto-generated method stub
		listaClientes.clear();

	    try {
	        String query = "SELECT id_cliente, nombre, apellido, email, telefono FROM clientes";
	        PreparedStatement statement = con.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id_cliente");
	            String nombre = resultSet.getString("nombre");
	            String apellido = resultSet.getString("apellido");
	            String correo = resultSet.getString("email");
	            String telefono = resultSet.getString("telefono");

	            listaClientes.add(new Cliente(id, nombre, apellido, correo, telefono));
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al cargar clientes: " + e.getMessage());
	    }
	}

}
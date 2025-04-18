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
import gestion.util.ButtonRenderer;
import gestion.util.Colors;
import gestion.util.GradientPanel;
import gestion.clases.Empleado;


public class PanelUsuarios extends GradientPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usertfNombre, usertfApellido, usertfCorreo, usertfUsuario;
	private JPasswordField usertfCont;
	static DefaultTableModel modeloUsuario; 
	private JTable table_1;
	JRadioButton rdbAdmin, rdbEmpleado;
	Window ventanaP = SwingUtilities.getWindowAncestor(PanelUsuarios.this);
	private ButtonGroup roles =  new ButtonGroup();
	static Connection con = DatabaseConnection.getInstance().getConnection();
	static ArrayList <Empleado> listaUsuarios = new ArrayList<Empleado>();

	
	public PanelUsuarios() {
		super(Colors.GRADIENT_START, Colors.GRADIENT_END);
		setPreferredSize(new Dimension(775, 618));
		setLayout(null);
		
		JPanel panelTituloLbl = new JPanel();
		panelTituloLbl.setBackground(new Color(95, 170, 254));
		panelTituloLbl.setBounds(0, 0, 330, 62);
		add(panelTituloLbl);
		panelTituloLbl.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("  Registro de Usuarios");
		lblNewLabel_1_1_1.setBorder(BorderFactory.createRaisedBevelBorder());
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(10, 11, 310, 40);
		panelTituloLbl.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(20, 111, 60, 19);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Apellido");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(20, 153, 54, 19);
		add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Correo");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(20, 195, 60, 19);
		add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Usuario");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1.setBounds(263, 111, 54, 19);
		add(lblNewLabel_3_2_1);
		
		usertfNombre = new JTextField();
		usertfNombre.setBounds(77, 107, 159, 27);
		add(usertfNombre);
		usertfNombre.setColumns(10);
		
		usertfApellido = new JTextField();
		usertfApellido.setColumns(10);
		usertfApellido.setBounds(77, 149, 159, 27);
		add(usertfApellido);
		
		JLabel lblNewLabel_3_2_1_1 = new JLabel("Contraseña");
		lblNewLabel_3_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1_1.setBounds(263, 153, 82, 19);
		add(lblNewLabel_3_2_1_1);
		
		usertfCont = new JPasswordField();
		usertfCont.setBounds(340, 149, 159, 24);
		add(usertfCont);
		
		usertfCorreo = new JTextField();
		usertfCorreo.setColumns(10);
		usertfCorreo.setBounds(77, 191, 159, 27);
		add(usertfCorreo);
		
		usertfUsuario = new JTextField();
		usertfUsuario.setColumns(10);
		usertfUsuario.setBounds(314, 107, 185, 27);
		add(usertfUsuario);
		
		JLabel lblNewLabel_3_2_1_1_1 = new JLabel("Rol");
		lblNewLabel_3_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1_1_1.setBounds(263, 195, 36, 19);
		add(lblNewLabel_3_2_1_1_1);
		
		rdbAdmin = new JRadioButton("Admin");
		rdbAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbAdmin.setBounds(298, 195, 71, 23);
		rdbAdmin.setContentAreaFilled(false);
		add(rdbAdmin);
		
		rdbEmpleado = new JRadioButton("Empleado");
		rdbEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbEmpleado.setBounds(389, 195, 95, 23);
		rdbEmpleado.setContentAreaFilled(false);
		add(rdbEmpleado);
		
		roles.add(rdbAdmin);
		roles.add(rdbEmpleado);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 247, 735, 345);
		add(scrollPane_1);
		
		String[] columnasUsr = {"ID", "Nombre", "Apellido", "Correo", "Usuario", "Rol", "Editar", "Eliminar"};
		modeloUsuario = new DefaultTableModel(columnasUsr, 0);
		
		//TRABAJANDO CON LA TABLA------------------------------------------------
		table_1 = new JTable(modeloUsuario){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cargarUsuariosDesdeDB();
        mostrarUsuariosEnTabla();
		//cargarUsuarios(); ///
		table_1.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer("Editar", new Color(100, 200, 255)));
		table_1.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer("Eliminar", Colors.PASTEL_RED));
        
        table_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = table_1.rowAtPoint(e.getPoint());
                int columna = table_1.columnAtPoint(e.getPoint());

                if (columna == 6) {
                	int idUsuario = (int) table_1.getValueAt(fila, 0);
                    String nombre = (String) table_1.getValueAt(fila, 1);
                    String apellido = (String) table_1.getValueAt(fila, 2);
                    String correo = (String) table_1.getValueAt(fila, 3);
                    String usuario = (String) table_1.getValueAt(fila, 4);
                    String rol = (String) table_1.getValueAt(fila, 5);
                    
                    VentanaEditar(idUsuario, nombre, apellido, correo, usuario, rol);
                    //cargarUsuarios();
                    mostrarUsuariosEnTabla(); 
                }else if(columna == 7) {
                	int idUsuario = (int) table_1.getValueAt(fila, 0);
                	int confirmacion = JOptionPane.showConfirmDialog(null, 
                            "¿Estás seguro de que deseas eliminar este usuario?", 
                            "Confirmar eliminación", 
                            JOptionPane.YES_NO_OPTION);
                	if (confirmacion == JOptionPane.YES_OPTION) {
                        eliminarUsuario(idUsuario);
                        mostrarUsuariosEnTabla();
                    }
                }
            }
        });
        
		scrollPane_1.setViewportView(table_1);
		
		JSeparator separator_2 = new JSeparator(SwingConstants.VERTICAL);
		separator_2.setBounds(20, 170, 743, 2);
		add(separator_2);
		
		JButton btnAgregarUsuario = new JButton("Agregar");
		btnAgregarUsuario.setBounds(654, 209, 101, 27);
		btnAgregarUsuario.setBackground(Colors.PASTEL_GREEN);
		btnAgregarUsuario.setFocusPainted(false);
		btnAgregarUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				agregarUsuario();
			}
			
		});
		add(btnAgregarUsuario);
	
	}
	
	public void VentanaEditar(int idUsuario, String nombre, String apellido, String correo, String usuario, String rol /*String valueAt*/) {
		VentanaEditarUsuario ventanaEditarUser = new VentanaEditarUsuario();
		ventanaEditarUser.setDatos(idUsuario, nombre, apellido, correo, usuario, rol);
		ventanaEditarUser.setLocation(getMousePosition());
		ventanaEditarUser.setVisible(true);
	}

	public boolean faltanDatos(String nombre, String apellido, String correo, String rol, String usuario, String password) {
		if (nombre.isBlank() || apellido.isBlank() || correo.isBlank() || rol.isBlank() || usuario.isBlank() || password.isBlank()) {
			return true;
		}
		return false;
	}
	
	public void agregarUsuario() {
	    String nombre = usertfNombre.getText();
	    String apellido = usertfApellido.getText();
	    String correo = usertfCorreo.getText();
	    String rol = rdbAdmin.isSelected() ? "admin" : "empleado";
	    String usuario = usertfUsuario.getText();
	    String password = new String(usertfCont.getPassword());
	    Login loginxd = new Login();
	    String passSHA = loginxd.generarHashSHA256(password);

	    if (!faltanDatos(nombre, apellido, correo, rol, usuario, password)) {
	        String sql = "INSERT INTO usuarios (username, password, rol, nombre, apellidos, correo) VALUES (?, ?, ?, ?, ?, ?)";

	        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            ps.setString(1, usuario);
	            ps.setString(2, passSHA);
	            ps.setString(3, rol);
	            ps.setString(4, nombre);
	            ps.setString(5, apellido);
	            ps.setString(6, correo);

	           int filas = ps.executeUpdate();
	           if (filas > 0) {
	                ResultSet generatedKeys = ps.getGeneratedKeys();
	                if (generatedKeys.next()) {
	                    int id = generatedKeys.getInt(1);

	                    listaUsuarios.add(new Empleado(id, nombre, apellido, correo, usuario, rol));
	                    mostrarUsuariosEnTabla(); 
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
	
	private void cargarUsuariosDesdeDB() {
	    listaUsuarios.clear();

	    try {
	        String query = "SELECT id_usuario, nombre, apellidos, correo, username, rol FROM usuarios";
	        PreparedStatement statement = con.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id_usuario");
	            String nombre = resultSet.getString("nombre");
	            String apellido = resultSet.getString("apellidos");
	            String correo = resultSet.getString("correo");
	            String usuario = resultSet.getString("username");
	            String rol = resultSet.getString("rol");

	            listaUsuarios.add(new Empleado(id, nombre, apellido, correo, usuario, rol));
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al cargar usuarios: " + e.getMessage());
	    }
	}
	
	public static void mostrarUsuariosEnTabla() {
	    modeloUsuario.setRowCount(0);

	    for (Empleado emp : listaUsuarios) {
	        modeloUsuario.addRow(new Object[]{
	            emp.getId(), emp.getNombre(), emp.getApellido(),
	            emp.getEmail(), emp.getUsername(), emp.getRol(),
	            "Editar", "Eliminar"
	        });
	    }
	}
	
	
	private void eliminarUsuario(int idUsuario) {
		String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, idUsuario);
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
	            JOptionPane.showMessageDialog(null, "Usuario eliminado");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario.");
	        }
			
			for(Empleado x : listaUsuarios) {
				if (x.getId() == idUsuario) {
					listaUsuarios.remove(x);
					break;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void clear() {
		usertfNombre.setText("");
		usertfApellido.setText("");
		usertfCorreo.setText("");
		roles.clearSelection();
		usertfUsuario.setText("");
		usertfCont.setText("");
	}
}
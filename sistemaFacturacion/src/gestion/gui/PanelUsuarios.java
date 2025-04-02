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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
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

public class PanelUsuarios extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usertfNombre, usertfApellido, usertfCorreo, usertfUsuario;
	private JPasswordField usertfCont;
	DefaultTableModel modeloUsuario;
	private JTable table_1;
	JRadioButton rdbAdmin, rdbEmpleado;
	Window ventanaP = SwingUtilities.getWindowAncestor(PanelUsuarios.this);
	private ButtonGroup roles =  new ButtonGroup();
	private Connection con = DatabaseConnection.getConnection();

	
	public PanelUsuarios() {
		setPreferredSize(new Dimension(775, 618));
		setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(20, 48, 60, 19);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Apellido");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(20, 90, 54, 19);
		add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Correo");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(20, 131, 60, 19);
		add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Usuario");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1.setBounds(263, 48, 54, 19);
		add(lblNewLabel_3_2_1);
		
		usertfNombre = new JTextField();
		usertfNombre.setBounds(77, 46, 159, 27);
		add(usertfNombre);
		usertfNombre.setColumns(10);
		
		usertfApellido = new JTextField();
		usertfApellido.setColumns(10);
		usertfApellido.setBounds(77, 88, 159, 27);
		add(usertfApellido);
		
		JLabel lblNewLabel_3_2_1_1 = new JLabel("Contraseña");
		lblNewLabel_3_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1_1.setBounds(263, 90, 82, 19);
		add(lblNewLabel_3_2_1_1);
		
		usertfCont = new JPasswordField();
		usertfCont.setBounds(340, 89, 159, 24);
		add(usertfCont);
		
		usertfCorreo = new JTextField();
		usertfCorreo.setColumns(10);
		usertfCorreo.setBounds(77, 132, 159, 27);
		add(usertfCorreo);
		
		usertfUsuario = new JTextField();
		usertfUsuario.setColumns(10);
		usertfUsuario.setBounds(314, 46, 185, 27);
		add(usertfUsuario);
		
		JLabel lblNewLabel_3_2_1_1_1 = new JLabel("Rol");
		lblNewLabel_3_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1_1_1.setBounds(263, 135, 36, 19);
		add(lblNewLabel_3_2_1_1_1);
		
		rdbAdmin = new JRadioButton("Admin");
		rdbAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbAdmin.setBounds(298, 134, 71, 23);
		add(rdbAdmin);
		
		rdbEmpleado = new JRadioButton("Empleado");
		rdbEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbEmpleado.setBounds(389, 134, 95, 23);
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
		cargarUsuarios(); ///
		table_1.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer("Editar", new Color(100, 200, 255)));
		table_1.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer("Eliminar", new Color(255, 100, 100)));
        
        table_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = table_1.rowAtPoint(e.getPoint());
                int columna = table_1.columnAtPoint(e.getPoint());
                int idUsuario = (int) table_1.getValueAt(fila, 0); // Obtener el ID del usuario

                if (columna == 6) {
                    abrirFrameEdicion(idUsuario, (String) table_1.getValueAt(fila, 1));
                } 
            }
        });
        
		scrollPane_1.setViewportView(table_1);
		
		JSeparator separator_2 = new JSeparator(SwingConstants.VERTICAL);
		separator_2.setBounds(20, 170, 743, 2);
		add(separator_2);
		
		JButton btnAgregarUsuario = new JButton("Agregar");
		btnAgregarUsuario.setBounds(654, 209, 101, 27);
		btnAgregarUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				agregarUsuario();
			}
			
		});
		add(btnAgregarUsuario);
	
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

	        try (PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, usuario);
	            ps.setString(2, passSHA);
	            ps.setString(3, rol);
	            ps.setString(4, nombre);
	            ps.setString(5, apellido);
	            ps.setString(6, correo);

	            ps.executeUpdate();

	            clear();
	            cargarUsuarios();

	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(ventanaP, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(ventanaP, "Por favor, completa todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
	    }
	}
	
	private void cargarUsuarios() {
	    modeloUsuario.setRowCount(0);

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

	            modeloUsuario.addRow(new Object[]{id, nombre, apellido, correo, usuario, rol, "Editar", "Eliminar"});
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al cargar usuarios: " + e.getMessage());
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
	
	private void abrirFrameEdicion(int id, String nombre) {
	    JFrame frameEditar = new JFrame("Editar Usuario");
	    frameEditar.setSize(400, 200);
	    frameEditar.setLocationRelativeTo(null);
	    frameEditar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frameEditar.setLayout(null);

	    JLabel lblTitulo = new JLabel("Editando usuario: " + nombre);
	    lblTitulo.setBounds(20, 20, 300, 20);
	    frameEditar.add(lblTitulo);

	    frameEditar.setVisible(true);
	}
}

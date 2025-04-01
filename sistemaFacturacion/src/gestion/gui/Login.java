package gestion.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;

import gestion.database.DatabaseConnection;
import java.awt.Color;

public class Login {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField usuarioField;
	private Connection con = DatabaseConnection.getConnection();

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public JFrame getFrame() {
		return this.frame;
	}
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Iniciar sesión");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
		lblNewLabel.setBounds(101, 60, 184, 73);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario *");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(53, 159, 60, 17);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(53, 273, 282, 38);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña *");
		lblNewLabel_1_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(53, 248, 90, 17);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setBackground(Color.DARK_GRAY);
		btnIniciarSesion.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnIniciarSesion.setBounds(53, 351, 282, 38);
		btnIniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				iniciarSesion();
			}
			
		});
		frame.getContentPane().add(btnIniciarSesion);
		
		usuarioField = new JTextField();
		usuarioField.setBounds(53, 186, 282, 38);
		frame.getContentPane().add(usuarioField);
		usuarioField.setColumns(10);
	}
	
	public void iniciarSesion() {
		
		String usuario = usuarioField.getText();
		String password = new String(passwordField.getPassword());
		
		if(usuario.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Usuario y contraseña son requeridos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
		}
		
		String passwordHash = generarHashSHA256(password);
		String sql = "SELECT username, password, rol FROM usuarios where username= '"+usuario+"'";
		
		try (PreparedStatement ps = con.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery(sql);
			
			if (rs.next()) {
				String p = rs.getString("password");
				String rol = rs.getString("rol");
				
				if (passwordHash.equals(p)) {
					
					Point location = frame.getLocation();
					frame.dispose();
					
					if (rol.equals("admin")) {
						Ventana ventanaAdmin = new Ventana();
						ventanaAdmin.getFrame().setLocation(location);
						ventanaAdmin.getFrame().setVisible(true);
					
					} else if (rol.equals("empleado")) {
						EmpleadoGUI ventanaEmpleado = new EmpleadoGUI();
						ventanaEmpleado.getFrame().setLocation(location);
						ventanaEmpleado.getFrame().setVisible(true);
					}
					
				}else {
					JOptionPane.showMessageDialog(frame, "CONTRASEÑA INCORRECTA", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(frame, "EL USUARIO NO EXISTE: ", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(SQLException e){
	        JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), 
		            "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String generarHashSHA256 (String password) {
		try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hash = md.digest(password.getBytes());
	        StringBuilder hexString = new StringBuilder();
	        
	        for (byte b : hash) {
	            String hex = Integer.toHexString(0xff & b);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        
	        return hexString.toString();
	    } catch (NoSuchAlgorithmException e) {
	        JOptionPane.showMessageDialog(frame, "Error al generar hash: " + e.getMessage(), 
	            "Error", JOptionPane.ERROR_MESSAGE);
	        return null;
	    }
	}
}

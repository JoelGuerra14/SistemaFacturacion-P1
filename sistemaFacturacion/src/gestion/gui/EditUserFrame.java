package gestion.gui;

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestion.database.DatabaseConnection;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class EditUserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection con = DatabaseConnection.getConnection();
	private JTextField tNombre;
	private JTextField tUsuario;
	private JTextField tApellido;
	private JPasswordField tContraseña;
	private JTextField tCorreo;
	private JButton confirmar, cancelar;
	JRadioButton rdbAdmin, rdbEmpleado;
	private ButtonGroup bg;

	public EditUserFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel nombreLbl = new JLabel("Nombre");
		nombreLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nombreLbl.setBounds(10, 34, 60, 19);
		panel.add(nombreLbl);
		
		tNombre = new JTextField();
		tNombre.setColumns(10);
		tNombre.setBounds(67, 32, 159, 27);
		panel.add(tNombre);
		
		JLabel usuarioLbl = new JLabel("Usuario");
		usuarioLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usuarioLbl.setBounds(253, 34, 54, 19);
		panel.add(usuarioLbl);
		
		tUsuario = new JTextField();
		tUsuario.setColumns(10);
		tUsuario.setBounds(304, 32, 185, 27);
		panel.add(tUsuario);
		
		JLabel apellidoLbl = new JLabel("Apellido");
		apellidoLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		apellidoLbl.setBounds(10, 76, 54, 19);
		panel.add(apellidoLbl);
		
		tApellido = new JTextField();
		tApellido.setColumns(10);
		tApellido.setBounds(67, 74, 159, 27);
		panel.add(tApellido);
		
		JLabel pswLbl = new JLabel("Contraseña");
		pswLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pswLbl.setBounds(253, 76, 82, 19);
		panel.add(pswLbl);
		
		tContraseña = new JPasswordField();
		tContraseña.setBounds(330, 75, 159, 24);
		panel.add(tContraseña);
		
		JLabel correoLbl = new JLabel("Correo");
		correoLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		correoLbl.setBounds(10, 117, 54, 19);
		panel.add(correoLbl);
		
		tCorreo = new JTextField();
		tCorreo.setColumns(10);
		tCorreo.setBounds(67, 112, 159, 27);
		panel.add(tCorreo);
		
		JLabel rolLbl = new JLabel("Rol");
		rolLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rolLbl.setBounds(253, 121, 36, 19);
		panel.add(rolLbl);
		
		rdbAdmin = new JRadioButton("Admin");
		rdbAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbAdmin.setBounds(288, 120, 71, 23);
		panel.add(rdbAdmin);
		
		rdbEmpleado = new JRadioButton("Empleado");
		rdbEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbEmpleado.setBounds(379, 120, 95, 23);
		panel.add(rdbEmpleado);
		
		confirmar = new JButton("Confirmar");
		confirmar.setBounds(330, 182, 82, 23);
		panel.add(confirmar);
		
		cancelar = new JButton("Cancelar");
		cancelar.setBounds(422, 182, 82, 23);
		panel.add(cancelar);
		
		
	}
}

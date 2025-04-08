package gestion.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gestion.clases.Empleado;
import gestion.clases.Proveedor;
import gestion.database.DatabaseConnection;

public class VentanaEditarProveedor extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Connection con = DatabaseConnection.getInstance().getConnection();
	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tTelefono;
	private JTextField tDireccion;
	private JButton confirmar, cancelar;
	
	private String idProveedor; //para la funcion setData

	public VentanaEditarProveedor() {
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
		tNombre.setBounds(87, 32, 159, 27);
		panel.add(tNombre);
		
		JLabel telefonoLbl = new JLabel("Telefono");
		telefonoLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		telefonoLbl.setBounds(10, 76, 67, 19);
		panel.add(telefonoLbl);
		
		tTelefono = new JTextField();
		tTelefono.setColumns(10);
		tTelefono.setBounds(87, 74, 159, 27);
		panel.add(tTelefono);
		
		JLabel DireccionLbl = new JLabel("Direccion");
		DireccionLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DireccionLbl.setBounds(10, 117, 67, 19);
		panel.add(DireccionLbl);
		
		tDireccion = new JTextField();
		tDireccion.setColumns(10);
		tDireccion.setBounds(87, 115, 159, 27);
		panel.add(tDireccion);
		
		confirmar = new JButton("Confirmar");
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			        String nuevoNombre = tNombre.getText();
			        String nuevoTelefono = tTelefono.getText();
			        String nuevoDireccion = tDireccion.getText();

			        String sql = "UPDATE proveedores SET nombre=?, telefono=?, direccion=? WHERE id_proveedor=?";
			        
			        PreparedStatement ps = con.prepareStatement(sql);
			        ps.setString(1, nuevoNombre);
			        ps.setString(2, nuevoTelefono);
			        ps.setString(3, nuevoDireccion);

			        ps.setString(4, idProveedor); // ID del usuario a actualizar

			        int filasActualizadas = ps.executeUpdate();
			        
			        if (filasActualizadas > 0) {
			        
			            JOptionPane.showMessageDialog(null, "Proveedor actualizado.");
			            dispose(); // Cerrar ventana de edición
			            
			            for(Proveedor x : PanelProveedores.listaProveedores) {
							if (x.getId() == Integer.parseInt(idProveedor)) {
								x.setNombre(nuevoNombre);
								x.setTelefono(nuevoTelefono);
								x.setDireccion(nuevoDireccion);

								break;
							}
						}
						PanelProveedores.mostrarProveedoresEnTabla(); // Esto recarga la tabla después de actualizar para que se vea correctamente los datos nuevos
			        } else {
			            JOptionPane.showMessageDialog(null, "Error al actualizar el proveedor.");
			        }
			        ps.close();

			    } catch (Exception ex) {
			        ex.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
			    }
			}
		});
		confirmar.setBackground(new Color(128, 255, 128));
		confirmar.setBounds(304, 182, 95, 23);
		panel.add(confirmar);
		
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelar.setBackground(new Color(255, 128, 128));
		cancelar.setBounds(409, 182, 95, 23);
		panel.add(cancelar);
		

	}
	
	public void setDatos(int id, String nombre, String telefono, String direccion) {
	    this.idProveedor = String.valueOf(id); // Guarda el ID para la actualización de la data
	    tNombre.setText(nombre);
	    tTelefono.setText(telefono);
	    tDireccion.setText(direccion);
	}
	
}

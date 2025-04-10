package gestion.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.PreparedStatement;

import gestion.clases.Cliente;
import gestion.clases.Empleado;
import gestion.database.DatabaseConnection;
import gestion.util.Colors;
import gestion.util.GradientFrame;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEditarCliente extends GradientFrame {

	private static final long serialVersionUID = 1L;
	Connection con = DatabaseConnection.getInstance().getConnection();
	private JPanel panelContenido;
	private JTextField tNombre;
	private JTextField tCorreo;
	private JTextField tApellido;
	private JButton confirmar, cancelar;
	
	private String idCliente; //para la funcion setData
	private JTextField tTelefono;

	public VentanaEditarCliente() {
        super(Colors.GRADIENT_START, Colors.GRADIENT_END);
		setBounds(100, 100, 548, 265);
		panelContenido = new JPanel(new BorderLayout()) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                    0, 0, Colors.GRADIENT_START, 
                    getWidth(), getHeight(), Colors.GRADIENT_END
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        // Panel para los componentes
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false); // Hacemos transparente este panel

		JLabel nombreLbl = new JLabel("Nombre");
		nombreLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nombreLbl.setBounds(10, 34, 60, 19);
		panel.add(nombreLbl);
		
		tNombre = new JTextField();
		tNombre.setColumns(10);
		tNombre.setBounds(67, 32, 159, 27);
		panel.add(tNombre);
		
		JLabel correoLbl = new JLabel("Correo");
		correoLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		correoLbl.setBounds(253, 34, 54, 19);
		panel.add(correoLbl);
		
		tCorreo = new JTextField();
		tCorreo.setColumns(10);
		tCorreo.setBounds(304, 32, 185, 27);
		panel.add(tCorreo);
		
		JLabel apellidoLbl = new JLabel("Apellido");
		apellidoLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		apellidoLbl.setBounds(10, 76, 54, 19);
		panel.add(apellidoLbl);
		
		tApellido = new JTextField();
		tApellido.setColumns(10);
		tApellido.setBounds(67, 74, 159, 27);
		panel.add(tApellido);
		
		JLabel pswLbl = new JLabel("Telefono");
		pswLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pswLbl.setBounds(253, 76, 82, 19);
		panel.add(pswLbl);
		
		confirmar = new JButton("Confirmar");
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			        String nuevoNombre = tNombre.getText();
			        String nuevoApellido = tApellido.getText();
			        String nuevoCorreo = tCorreo.getText();
			        String nuevoTelefono = tTelefono.getText();
			        
			        String sql = "UPDATE clientes SET nombre=?, apellido=?, email=?, telefono=? WHERE id_cliente=?";
			        
			        PreparedStatement ps = con.prepareStatement(sql);
			        ps.setString(1, nuevoNombre);
			        ps.setString(2, nuevoApellido);
			        ps.setString(3, nuevoCorreo);
			        ps.setString(4, nuevoTelefono);
			        ps.setString(5, idCliente); // ID del usuario a actualizar

			        int filasActualizadas = ps.executeUpdate();
			        
			        if (filasActualizadas > 0) {
			        
			            JOptionPane.showMessageDialog(null, "Cliente actualizado.");
			            PanelVentas.actualizarComboBox();
			            dispose(); // Cerrar ventana de edición
			            for(Cliente x : PanelClientes.listaClientes) {
							if (x.getId() == Integer.parseInt(idCliente)) {
								x.setNombre(nuevoNombre);
								x.setApellido(nuevoApellido);
								x.setEmail(nuevoCorreo);
								x.setTelefono(nuevoTelefono);
								break;
							}
						}
						PanelClientes.mostrarClientesEnTabla(); // Esto recarga la tabla después de actualizar para que se vea correctamente los datos nuevos
			        } else {
			            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente.");
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
		
		tTelefono = new JTextField();
		tTelefono.setBounds(330, 75, 159, 24);
		panel.add(tTelefono);
		tTelefono.setColumns(10);
		cancelar.setBackground(new Color(255, 128, 128));
		cancelar.setBounds(409, 182, 95, 23);
		panel.add(cancelar);
		
        panelContenido.add(panel, BorderLayout.CENTER);
        setContentPane(panelContenido);

	}
	
	public void setDatos(int id, String nombre, String apellido, String correo, String telefono) {
	    this.idCliente = String.valueOf(id); // Guarda el ID para la actualización de la data
	    tNombre.setText(nombre);
	    tApellido.setText(apellido);
	    tCorreo.setText(correo);
	    tTelefono.setText(telefono);
	}
}

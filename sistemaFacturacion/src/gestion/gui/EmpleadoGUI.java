package gestion.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class EmpleadoGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Create the application.
	 */
	public EmpleadoGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PANEL DE EMPLEADO");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 40));
		lblNewLabel.setBounds(30, 33, 379, 192);
		frame.getContentPane().add(lblNewLabel);
	}

}

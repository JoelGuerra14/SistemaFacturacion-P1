package gestion.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import gestion.clases.Proveedor;
import gestion.database.DatabaseConnection;
import gestion.util.Colors;
import gestion.util.GradientFrame;
import gestion.clases.Producto;

public class VentanaEditarProducto extends GradientFrame {
    private static final long serialVersionUID = 1L;
	private JPanel panelContenido;
    private JTextField tCodigo;
    private JTextField tNombre;
    private JTextField tPrecio;
    private JTextField tStock;
    JComboBox<Proveedor> cbProveedor;
    JButton btnConfirmar, btnCancelar;
    private Connection con = DatabaseConnection.getInstance().getConnection();
    private String idProducto; // Para almacenar el ID del producto que se va a editar

    public VentanaEditarProducto() {
        super(Colors.GRADIENT_START, Colors.GRADIENT_END);
    	setBounds(100, 100, 431, 265);
    	setResizable(false);
    	// Panel contenido principal (con gradiente)
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

        JLabel lblNewLabel = new JLabel("Codigo");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(20, 34, 57, 24);
        panel.add(lblNewLabel);

        tCodigo = new JTextField();
        tCodigo.setBounds(87, 36, 86, 24);
        panel.add(tCodigo);
        tCodigo.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(20, 69, 57, 24);
        panel.add(lblNombre);

        tNombre = new JTextField();
        tNombre.setColumns(10);
        tNombre.setBounds(87, 71, 127, 24);
        panel.add(tNombre);

        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrecio.setBounds(231, 34, 50, 24);
        panel.add(lblPrecio);

        tPrecio = new JTextField();
        tPrecio.setColumns(10);
        tPrecio.setBounds(288, 34, 100, 24);
        panel.add(tPrecio);

        JLabel lblStock = new JLabel("Stock");
        lblStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblStock.setBounds(231, 69, 50, 24);
        panel.add(lblStock);

        tStock = new JTextField();
        tStock.setColumns(10);
        tStock.setBounds(288, 73, 100, 24);
        panel.add(tStock);

        JLabel lblProveedor = new JLabel("Proveedor");
        lblProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProveedor.setBounds(20, 120, 86, 24);
        panel.add(lblProveedor);

        cbProveedor = new JComboBox<Proveedor>();
        cbProveedor.setBounds(102, 120, 127, 26);
        panel.add(cbProveedor);

        btnCancelar = new JButton("Cancelar");
        
        btnCancelar.addActionListener(e -> dispose());
        
        btnCancelar.setBackground(new Color(255, 128, 128));
        btnCancelar.setBounds(299, 180, 89, 26);
        panel.add(btnCancelar);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(new Color(101, 216, 138));

        btnConfirmar.setBounds(192, 180, 100, 26);
        panel.add(btnConfirmar);
        
        panelContenido.add(panel, BorderLayout.CENTER);
        setContentPane(panelContenido);
        
        cargarProveedores();
        
        // Acción del botón confirmar
        btnConfirmar.addActionListener(e -> {
            try {
                int nuevoCodigo = Integer.parseInt(tCodigo.getText());
             // Validación del nombre
                String nombre = tNombre.getText().trim();
                if(nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para el producto", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validación del precio
                double precio = 0;
                try {
                    precio = Double.parseDouble(tPrecio.getText());
                    if(precio <= 0) {
                        JOptionPane.showMessageDialog(null, "El precio debe ser mayor que cero", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El precio debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validación del stock
                int stock = 0;
                try {
                    stock = Integer.parseInt(tStock.getText());
                    if(stock < 0) {
                        JOptionPane.showMessageDialog(null, "El stock no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El stock debe ser un número entero válido", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validación del proveedor
                Proveedor proveedorSeleccionado = (Proveedor) cbProveedor.getSelectedItem();
                if(proveedorSeleccionado == null) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "UPDATE productos SET codigo=?, nombre=?, precio=?, stock=?, fk_id_proveedores=? WHERE id_producto=?";
                
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, nuevoCodigo);
                ps.setString(2, nombre);
                ps.setDouble(3, precio);
                ps.setInt(4, stock);
                ps.setInt(5, proveedorSeleccionado.getId());
                ps.setString(6, idProducto); // ID del producto a actualizar

                int filasActualizadas = ps.executeUpdate();
                if (filasActualizadas > 0) {
                    JOptionPane.showMessageDialog(null, "Producto actualizado.");
                    dispose(); // Cerrar ventana de edición

                    // Actualizamos el producto en la lista
                    for (Producto p : PanelProductos.productos) {
                        if (p.getId() == Integer.parseInt(idProducto)) {
                            p.setCodigo(nuevoCodigo);
                            p.setNombre(nombre);
                            p.setPrecio(precio);
                            p.setStock(stock);
                            p.setProveedor(proveedorSeleccionado);
                            break;
                        }
                    }
                    PanelProductos.actualizarTablaDesdeArrayList(); // Recargar la tabla de productos
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el producto.");
                }
                ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
    }

    // Método para cargar los datos del producto
    public void setDatos(Producto producto) {
        this.idProducto = String.valueOf(producto.getId()); 
        tCodigo.setText(String.valueOf(producto.getCodigo()));
        tNombre.setText(producto.getNombre());
        tPrecio.setText(String.valueOf(producto.getPrecio()));
        tStock.setText(String.valueOf(producto.getStock()));
        
        // Seleccionar el proveedor correcto en el JComboBox
        for (int i = 0; i < cbProveedor.getItemCount(); i++) {
            Proveedor p = cbProveedor.getItemAt(i);
            if (p.getId() == producto.getProveedor().getId()) {
                cbProveedor.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void cargarProveedores() {
        cbProveedor.removeAllItems();
        String sql = "SELECT id_proveedor, nombre FROM proveedores";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_proveedor");
                String nombre = rs.getString("nombre");
                cbProveedor.addItem(new Proveedor(id, nombre));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error cargando proveedores: " + e.getMessage());
        }
    }
}
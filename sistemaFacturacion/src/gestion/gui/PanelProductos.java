package gestion.gui;

import java.awt.Color; 
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import gestion.clases.Producto;
import gestion.clases.Proveedor;
import gestion.database.DatabaseConnection;
import gestion.util.ButtonRenderer;
import gestion.util.Colors;
import gestion.util.GradientPanel;

public class PanelProductos extends GradientPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tIdProducto;
    private JTextField tNombre;
    private JTextField tPrecio;
    private JTextField tStock;
	JComboBox<Proveedor> cbProveedor;
    private static JTable table;
    static DefaultTableModel modeloProd; 
    private JMenu mnNewMenu_1, mnNewMenu_2;
    private JMenuItem mntmNewMenuItem_1, mntmNewMenuItem_2;
    Connection con = DatabaseConnection.getInstance().getConnection();
    static ArrayList<Producto> productos = new ArrayList<Producto>();

    public PanelProductos() {
		super(Colors.GRADIENT_START, Colors.GRADIENT_END);
        setPreferredSize(new Dimension(775, 618));
        setLayout(null);

        
        // ============ PRIMERA PARTE (PRODUCTOS) ============
        
        JPanel panelTituloLbl = new JPanel();
        panelTituloLbl.setBounds(0, 0, 330, 62);
		panelTituloLbl.setBackground(new Color(95, 170, 254));
		add(panelTituloLbl);
		panelTituloLbl.setLayout(null);
		
        JLabel lblNewLabel_1 = new JLabel("  Registro de Productos");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBorder(BorderFactory.createRaisedBevelBorder());
        lblNewLabel_1.setBounds(10, 11, 310, 40);
        panelTituloLbl.add(lblNewLabel_1);
		
        JLabel lblNewLabel = new JLabel("Codigo");
        lblNewLabel.setBounds(24, 95, 55, 29);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblNewLabel);
        
        tIdProducto = new JTextField();
        tIdProducto.setBounds(84, 98, 73, 24);
        tIdProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(tIdProducto);
        tIdProducto.setColumns(10);
        
        tNombre = new JTextField();
        tNombre.setBounds(84, 130, 132, 24);
        tNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tNombre.setColumns(10);
        add(tNombre);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(24, 126, 55, 29);
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblNombre);
        
        tPrecio = new JTextField();
        tPrecio.setBounds(286, 99, 134, 24);
        tPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tPrecio.setColumns(10);
        add(tPrecio);
        
        JLabel lblNombre_1 = new JLabel("Precio");
        lblNombre_1.setBounds(226, 95, 55, 29);
        lblNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblNombre_1);
        
        JLabel lblNombre_1_1 = new JLabel("Stock");
        lblNombre_1_1.setBounds(226, 129, 55, 29);
        lblNombre_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblNombre_1_1);
        
        tStock = new JTextField();
        tStock.setBounds(286, 133, 134, 24);
        tStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tStock.setColumns(10);
        add(tStock);
        
        cbProveedor = new JComboBox<Proveedor>();
        cbProveedor.setBounds(440, 127, 115, 29);
        cbProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(cbProveedor);
        
        
        JLabel lblProveedor = new JLabel("Proveedor");
        lblProveedor.setBounds(442, 95, 65, 29);
        lblProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblProveedor);
        
        JButton registrar = new JButton("Agregar");
        registrar.setBackground(Colors.PASTEL_GREEN);
        registrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		agregarProducto();
        		actualizarTablaDesdeArrayList();
        	}
        });
        registrar.setBounds(673, 194, 92, 29);
        add(registrar);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 226, 765, 2);
        add(separator);
        
        // ============ TABLA DE PRODUCTOS ============
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 238, 755, 370);
        add(scrollPane);
        
        String[] columnasProd = {"ID", "Codigo","Nombre", "Precio", "Stock", "Proveedor", "Editar", "Eliminar"};
        modeloProd = new DefaultTableModel(columnasProd, 0);
        table = new JTable(modeloProd) {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
		table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer("Editar", new Color(100, 200, 255)));
		table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer("Eliminar", new Color(255, 100, 100)));
        
		table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = table.rowAtPoint(e.getPoint());
                int columna = table.columnAtPoint(e.getPoint());

                if (columna == 6) {  // Editar
                    int idProducto = (int) table.getValueAt(fila, 0);
                    int codigo = (int) table.getValueAt(fila, 1);
                    String nombre = (String) table.getValueAt(fila, 2);
                    double precio = (double) table.getValueAt(fila, 3);
                    int stock = (int) table.getValueAt(fila, 4);
                    Proveedor proveedor = (Proveedor) table.getValueAt(fila, 5);

                    // Abrir ventana de edición con los valores actuales
                    ventanaEditarProducto(idProducto, nombre, precio, stock, codigo, proveedor);
                } else if (columna == 7) {  // Eliminar
                    int idProducto = (int) table.getValueAt(fila, 0);
                    int confirmacion = JOptionPane.showConfirmDialog(null, 
                                "¿Estás seguro de que deseas eliminar este producto?", 
                                "Confirmar eliminación", 
                                JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        eliminarProducto(idProducto);
                    }
                }
            }
        });
		
		cargarProveedoresDesdeBD();
        cargarProductosDesdeBD();
        
		scrollPane.setViewportView(table);
        
        // ============ MENÚ ============
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 205, 22);
        add(menuBar);
        
        JMenu mnNewMenu = new JMenu("Archivo");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Exportar");
        mnNewMenu.add(mntmNewMenuItem);
        
        JMenuItem cerrarSesionMenu = new JMenuItem("Cerrar sesión");
        cerrarSesionMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Window ventanaP = SwingUtilities.getWindowAncestor(PanelProductos.this);
                Point location = ventanaP.getLocation();
                ventanaP.dispose();
                Login login = new Login();
                login.getFrame().setLocation(location);
                login.getFrame().setVisible(true);
            }
        });
        mnNewMenu.add(cerrarSesionMenu);
        
        mnNewMenu_1 = new JMenu("Ayuda");
        menuBar.add(mnNewMenu_1);
        
        mntmNewMenuItem_1 = new JMenuItem("Documento Ayuda");
        mnNewMenu_1.add(mntmNewMenuItem_1);
        
        mnNewMenu_2 = new JMenu("Mas opciones");
        menuBar.add(mnNewMenu_2);
        
        mntmNewMenuItem_2 = new JMenuItem("Crear Categoria");
        mnNewMenu_2.add(mntmNewMenuItem_2);

    }
    
    private void ventanaEditarProducto(int idProducto, String nombre, Double precio, int stock, int codigo, Proveedor prov) {
        VentanaEditarProducto ve = new VentanaEditarProducto();
        
        Producto producto = new Producto(idProducto, nombre, precio, stock, codigo, prov);
        ve.setDatos(producto);
        ve.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
        ve.setVisible(true);
    }
    
    private boolean camposNulos() {
        // Validamos si algún campo está vacío
        return tIdProducto.getText().isBlank() || 
               tNombre.getText().isBlank() || 
               tPrecio.getText().isBlank() || 
               tStock.getText().isBlank() || 
               cbProveedor.getSelectedItem() == null;
    }
    
    private void agregarProducto() {
        if (camposNulos()) {
            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int codigoProducto = 0;
        try {
            codigoProducto = Integer.parseInt(tIdProducto.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El codigo ingresado no es válido. Debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        String nombreP = tNombre.getText();

        // Validación del precio
        double precioP = 0;
        try {
            precioP = Double.parseDouble(tPrecio.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El precio ingresado no es válido. Debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int stock = 0;
        try {
            stock = Integer.parseInt(tStock.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El stock debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Proveedor proveedorSeleccionado = (Proveedor) cbProveedor.getSelectedItem();
        if (proveedorSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un proveedor.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idProveedor = proveedorSeleccionado.getId();
        String sql = "INSERT INTO productos (codigo, nombre, precio, stock, fk_id_proveedores) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, codigoProducto);
            ps.setString(2, nombreP);
            ps.setDouble(3, precioP);
            ps.setInt(4, stock);
            ps.setInt(5, idProveedor);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                // Obtener el ID generado por la BD
                ResultSet rs = ps.getGeneratedKeys();
                int idGenerado = 0;
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
                Producto nuevo = new Producto(idGenerado, nombreP, precioP, stock, codigoProducto,proveedorSeleccionado);
                productos.add(nuevo);
                actualizarTablaDesdeArrayList();
                JOptionPane.showMessageDialog(null, "Producto insertado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se insertó el producto.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void actualizarTablaDesdeArrayList() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpiar tabla

        for (Producto p : productos) {
            Object[] fila = {
                p.getId(),
                p.getCodigo(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock(),
                p.getProveedor(),
                "Editar", "Eliminar"
            };
            model.addRow(fila);
        }
    }
    
    private void cargarProveedoresDesdeBD() {
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
    
    private void cargarProductosDesdeBD() {
        productos.clear();
        
        String sql = "SELECT p.id_producto, p.nombre, p.precio, p.stock, pr.nombre AS proveedor, p.fk_id_proveedores, p.codigo " +
                "FROM productos p LEFT JOIN proveedores pr ON p.fk_id_proveedores = pr.id_proveedor";
        
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                String nombreProveedor = rs.getString("proveedor");
                int idProveedor = rs.getInt("fk_id_proveedores");
                int codigoProducto = rs.getInt("codigo");  //carga el código del producto

                Proveedor proveedor = new Proveedor(idProveedor, nombreProveedor);

                Producto producto = new Producto(idProducto, nombre, precio, stock, codigoProducto, proveedor);
                productos.add(producto);
            }
            actualizarTablaDesdeArrayList();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error cargando productos: " + e.getMessage());
        }
    }
    
    private void eliminarProducto(int idProducto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                productos.removeIf(producto -> producto.getId() == idProducto);
                actualizarTablaDesdeArrayList();
                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
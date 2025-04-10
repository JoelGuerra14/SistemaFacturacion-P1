package gestion.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import gestion.clases.Cliente;
import gestion.clases.Factura;
import gestion.database.DatabaseConnection;
import gestion.util.Colors;
import gestion.util.GradientPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class PanelFacturas extends GradientPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton btnImprimirFac;
	private static DefaultTableModel modelo;
	static Connection con = DatabaseConnection.getInstance().getConnection();
	
	public PanelFacturas() {
		super(Colors.GRADIENT_START, Colors.GRADIENT_END);
        setPreferredSize(new Dimension(775, 618));
        setLayout(null);
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(95, 170, 254));
        panelTitulo.setBounds(0, 0, 338, 66);
        add(panelTitulo);
        panelTitulo.setLayout(null);
        
        JLabel lblNewLbl = new JLabel("Reporte y Facturas");
        lblNewLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLbl.setBounds(10, 11, 318, 44);
        lblNewLbl.setBorder(BorderFactory.createRaisedBevelBorder());
        panelTitulo.add(lblNewLbl);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 134, 755, 451);
        add(scrollPane);
        
        String[] columnas = {"ID", "Cliente", "Fecha", "Total"};
        modelo = new DefaultTableModel(columnas, 0);
        table = new JTable(modelo);
        
        scrollPane.setViewportView(table);
        
        btnImprimirFac = new JButton("Imprimir");
        btnImprimirFac.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		imprimirFactura();
        	}
        });
        btnImprimirFac.setBackground(new Color(234, 243, 199));
        btnImprimirFac.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnImprimirFac.setBounds(657, 90, 108, 34);
        add(btnImprimirFac);
        
        cargarFacturasDesdeBD();
        mostrarEnTabla();
	}
	
	private static void cargarFacturasDesdeBD() {
	    Factura.listaFacturas.clear();
	    try {
	        String sql = "SELECT f.id_factura, f.fecha, f.total, " +
	                     "c.id_cliente, c.nombre, c.apellido, c.email, c.telefono " +
	                     "FROM facturas f " +
	                     "JOIN clientes c ON f.id_cliente = c.id_cliente";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            int idFactura = rs.getInt("id_factura");
	            LocalDate fecha = rs.getTimestamp("fecha").toLocalDateTime().toLocalDate();
	            double total = rs.getDouble("total");

	            int idCliente = rs.getInt("id_cliente");
	            String nombre = rs.getString("nombre");
	            String apellido = rs.getString("apellido");
	            String email = rs.getString("email");
	            String telefono = rs.getString("telefono");

	            Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono);

	            Factura factura = new Factura("", cliente, fecha, null);
	            factura.setIdFactura(idFactura);
	            factura.setTotal(total);

	            Factura.listaFacturas.add(factura);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private static void mostrarEnTabla() {
	    modelo.setRowCount(0);
	    for (Factura f : Factura.listaFacturas) {
	        modelo.addRow(new Object[]{
	            f.getIdFactura(),
	            f.getCliente().getNombre(),
	            f.getFecha().toString(),
	            f.getTotal()
	        });
	    }
	}
	
	private Factura obtenerFacturaSeleccionada() {
	    int filaSeleccionada = table.getSelectedRow();
	    if (filaSeleccionada != -1) {
	        int idFacturaSeleccionada = (int) modelo.getValueAt(filaSeleccionada, 0);
	        for (Factura factura : Factura.listaFacturas) {
	            if (factura.getIdFactura() == idFacturaSeleccionada) {
	                return factura;
	            }
	        }
	    }
	    return null;
	}
	
	private void imprimirFactura() {
	    Factura factura = obtenerFacturaSeleccionada();
	    if (factura != null) {
	        JFileChooser fileChooser = new JFileChooser();
	        
	        // Configurar el file chooser
	        fileChooser.setDialogTitle("Guardar factura como PDF");
	        fileChooser.setSelectedFile(new File("Factura_" + factura.getIdFactura() + ".pdf"));
	        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf"));
	        
	        // Mostrar diálogo de guardado
	        int userSelection = fileChooser.showSaveDialog(this);
	        
	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            File fileToSave = fileChooser.getSelectedFile();
	            String filePath = fileToSave.getAbsolutePath();
	            
	            // Asegurar que tenga extensión .pdf
	            if (!filePath.toLowerCase().endsWith(".pdf")) {
	                filePath += ".pdf";
	            }
	            
	            try {
	                Document document = new Document();
	                PdfWriter.getInstance(document, new FileOutputStream(filePath));
	                document.open();
	                Paragraph header = new Paragraph("DETALLE DE FACTURA");
	                header.setAlignment(Paragraph.ALIGN_CENTER);
	                document.add(header);
	                document.add(new Paragraph(" "));
	                document.add(new Paragraph("Factura N°: " + factura.getIdFactura()));
	                document.add(new Paragraph("Cliente: " + factura.getCliente().getNombre() + " " + 
	                    factura.getCliente().getApellido()));
	                document.add(new Paragraph("Fecha: " + factura.getFecha().toString()));
	                document.add(new Paragraph(" "));

	                String sqlDetalles = "SELECT df.id_detalle, p.nombre AS producto, " +
	                    "df.cantidad, df.precio_unitario, df.subtotal " +
	                    "FROM detalle_factura df " +
	                    "JOIN productos p ON df.id_producto = p.id_producto " +
	                    "WHERE df.id_factura = ?";
	                
	                PreparedStatement ps = con.prepareStatement(sqlDetalles);
	                ps.setInt(1, factura.getIdFactura());
	                ResultSet rs = ps.executeQuery();

	                PdfPTable tablaDetalles = new PdfPTable(5);
	                tablaDetalles.setWidthPercentage(100);
	                
	                tablaDetalles.addCell("Producto");
	                tablaDetalles.addCell("Cantidad");
	                tablaDetalles.addCell("Precio Unit.");
	                tablaDetalles.addCell("Impuesto");
	                tablaDetalles.addCell("Importe");
	                
	                double subTotalNeto = 0;
	                double totalFactura = 0;
	                double totalImpuestos = 0;
	                
	                while (rs.next()) {
	                    String producto = rs.getString("producto");
	                    int cantidad = rs.getInt("cantidad");
	                    double precioUnitario = rs.getDouble("precio_unitario");
	                    double subtotal = rs.getDouble("subtotal");
	                    double impuesto = subtotal * 0.18;
	                    
	                    tablaDetalles.addCell(producto);
	                    tablaDetalles.addCell(String.valueOf(cantidad));
	                    tablaDetalles.addCell(String.format("%.2f", precioUnitario));
	                    tablaDetalles.addCell(String.format("%.2f", impuesto));
	                    tablaDetalles.addCell(String.format("%.2f", subtotal));
	                    
	                    subTotalNeto += subtotal;
	                    totalImpuestos += impuesto;
	                    
	                    totalFactura = subTotalNeto + totalImpuestos;
	                }
	                
	                
	                document.add(tablaDetalles);
	                document.add(new Paragraph(" "));
	                
	                document.add(new Paragraph("Subtotal: " + String.format("%.2f", subTotalNeto)));
	                document.add(new Paragraph("Impuestos (18%): " + String.format("%.2f", totalImpuestos)));
	                document.add(new Paragraph("Total: " + String.format("%.2f", totalFactura)));
	                document.close();
	                
	                JOptionPane.showMessageDialog(null, 
	                    "Factura generada correctamente en:\n" + filePath,
	                    "Éxito", 
	                    JOptionPane.INFORMATION_MESSAGE);
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(null, 
	                    "Error generando la factura: " + e.getMessage(),
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, 
	            "Por favor, selecciona una factura.",
	            "Advertencia",
	            JOptionPane.WARNING_MESSAGE);
	    }
	}
	
	public static void recargarFactura() {
        cargarFacturasDesdeBD();
        mostrarEnTabla();
	}
	
}

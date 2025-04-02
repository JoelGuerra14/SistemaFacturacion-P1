package gestion.clases;

import java.awt.Color; 
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JLabel implements TableCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonRenderer(String texto, Color color) {
        setText(texto);
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setBackground(color);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 12));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
    		boolean hasFocus, int row, int column) {
        return this;
    }

}

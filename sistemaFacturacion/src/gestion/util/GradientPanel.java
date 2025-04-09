package gestion.util;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GradientPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color startColor;
    private Color endColor;
    
    // Constructor con colores por defecto
    public GradientPanel() {
        this(new Color(230, 240, 255), new Color(200, 220, 255));
    }
    
    // Constructor con colores personalizados
    public GradientPanel(Color startColor, Color endColor) {
        super();
        this.startColor = startColor;
        this.endColor = endColor;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        GradientPaint gradient = new GradientPaint(
            0, 0, startColor, 
            getWidth(), getHeight(), endColor
        );
        
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
    
    // Métodos para cambiar los colores dinámicamente
    public void setStartColor(Color startColor) {
        this.startColor = startColor;
        repaint();
    }
    
    public void setEndColor(Color endColor) {
        this.endColor = endColor;
        repaint();
    }
    
    public void setGradientColors(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
        repaint();
    }
	
}

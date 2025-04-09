package gestion.util;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GradientFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Color startColor;
    protected Color endColor;
    
    public GradientFrame() {
        this(new Color(230, 240, 255), new Color(200, 220, 255));
    }
    
    public GradientFrame(Color startColor, Color endColor) {
        super();
        this.startColor = startColor;
        this.endColor = endColor;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupGradientBackground();
    }
    
    private void setupGradientBackground() {
        // Configura un panel transparente como content pane
        setContentPane(new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (!(getParent() instanceof JPanel)) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    GradientPaint gradient = new GradientPaint(
                        0, 0, startColor, 
                        getWidth(), getHeight(), endColor
                    );
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.dispose();
                }
            }
            
            @Override
            public boolean isOpaque() {
                return false;
            }
        });
        
        // Asegura que el glass pane no bloquee el gradiente
        setGlassPane(new JPanel() {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isVisible() {
                return false;
            }
        });
        
        getContentPane().setLayout(null);
    }
    
    // Métodos para cambiar colores
    public void setGradientColors(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
        getContentPane().repaint();
    }
}
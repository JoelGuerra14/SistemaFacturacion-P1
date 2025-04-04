package gestion.clases;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Reloj {
    private JLabel labelReloj;
    private volatile boolean running;
    private DateTimeFormatter formato24h = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DateTimeFormatter formato12h = DateTimeFormatter.ofPattern("hh:mm:ss a");

    public Reloj(JLabel label) {
        this.labelReloj = label;
    }

    public void iniciar() {
        running = true;
        new Thread(() -> {
            while (running) {
                actualizarHora();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    public void detener() {
        running = false;
    }

    private void actualizarHora() {
        SwingUtilities.invokeLater(() -> {
            LocalTime ahora = LocalTime.now();
            // Usar formato12h para AM/PM o formato24h para formato 24 horas
            labelReloj.setText(ahora.format(formato12h)); 
        });
    }
}
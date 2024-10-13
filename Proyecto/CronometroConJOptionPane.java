package Proyecto;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CronometroConJOptionPane {
    private static int tiempoRestTotal;
    public static void main(String[] args) {
        String valor = mostrarDialogoConCronometro(5); // Ejemplo con 5 segundos
        System.out.println("Valor final ingresado: " + valor);
    }

    
    public static String mostrarDialogoConCronometro(int segundos) {
        // Crear un JDialog personalizado
        final String[] valorIngresado = {null};  // Para almacenar el valor ingresado
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Ingresar Comando");
        dialogo.setModal(true); // Bloquea otras ventanas hasta que se cierra
        dialogo.setSize(250, 120);
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Crear los componentes
        JLabel labelMensaje = new JLabel("Ingresa el número de Contenedor:");
        JTextField campoTexto = new JTextField(10);
        final Color VERY_DARK_GREEN = new Color(0, 102, 0);
        campoTexto.setCaretColor(VERY_DARK_GREEN);
        JLabel labelCronometro = new JLabel("Tiempo restante: " + segundos + " segundos");

        // Panel para organizar componentes
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(labelMensaje);
        panel.add(campoTexto);
        panel.add(labelCronometro);
        dialogo.add(panel);

        tiempoRestTotal=segundos;
        // Crear un Timer para actualizar el cronómetro
        Timer timer = new Timer(1000, new ActionListener() {
            int tiempoRestante = segundos;

            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                tiempoRestTotal=tiempoRestante;
                labelCronometro.setText("Tiempo restante: " + tiempoRestante + " segundos");
                if (tiempoRestante <= 0) {
                    ((Timer) e.getSource()).stop(); // Detener el temporizador
                    dialogo.dispose(); // Cerrar el diálogo cuando el tiempo llega a 0
                }
            }
        });

        // Iniciar el temporizador
        timer.start();

        // Agregar un ActionListener al campo de texto para cerrar cuando el usuario presione Enter
        campoTexto.addActionListener(e -> {
            timer.stop(); // Detener el temporizador si el usuario ingresa un valor
            valorIngresado[0] = campoTexto.getText(); // Almacenar el valor ingresado
            dialogo.dispose(); // Cerrar el diálogo
        });

        // Mostrar el diálogo
        dialogo.setLocationRelativeTo(null); // Centrar la ventana
        dialogo.setVisible(true);

        // Bloquea el hilo hasta que el diálogo se cierre
        while (dialogo.isVisible()) {
            try {
                Thread.sleep(100); // Pausa breve para no bloquear la interfaz
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Retornar el valor ingresado o null si no se ingresó nada
        return valorIngresado[0];
    }

    public static int getTiempoRest(){
        return tiempoRestTotal;
    }
}


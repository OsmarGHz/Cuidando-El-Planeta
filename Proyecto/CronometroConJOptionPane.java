package Proyecto;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CronometroConJOptionPane {

    public static void main(String[] args) {
        mostrarDialogoConCronometro(5); // Ejemplo con 10 segundos
    }

    public static void mostrarDialogoConCronometro(int segundos) {
        // Crear un JDialog personalizado
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Ingresar Comando");
        dialogo.setModal(true); // Bloquea otras ventanas hasta que se cierra
        dialogo.setSize(250, 120);
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Crear los componentes
        JLabel labelMensaje = new JLabel("Ingresa el número de Contenedor:");
        JTextField campoTexto = new JTextField(10);
        campoTexto.setCaretColor(Color.darkGray);
        JLabel labelCronometro = new JLabel("Tiempo restante: " + segundos + " segundos");

        // Panel para organizar componentes
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(labelMensaje);
        panel.add(campoTexto);
        panel.add(labelCronometro);
        dialogo.add(panel);

        // Crear un Timer para actualizar el cronómetro
        Timer timer = new Timer(1000, new ActionListener() {
            int tiempoRestante = segundos;

            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                labelCronometro.setText("Tiempo restante: " + tiempoRestante + " segundos");
                if (tiempoRestante <= 0) {
                    ((Timer)e.getSource()).stop(); // Detener el temporizador
                    dialogo.dispose(); // Cerrar el diálogo cuando el tiempo llega a 0
                }
            }
        });
        
        // Iniciar el temporizador
        timer.start();

        // Agregar un ActionListener al campo de texto para cerrar cuando el usuario presione Enter
        campoTexto.addActionListener(e -> {
            timer.stop(); // Detener el temporizador si el usuario ingresa un valor
            String valorIngresado = campoTexto.getText();
            System.out.println("Valor ingresado: " + valorIngresado); // Aquí puedes manejar el valor ingresado
            dialogo.dispose(); // Cerrar el diálogo
        });

        // Mostrar el diálogo
        dialogo.setLocationRelativeTo(null); // Centrar la ventana
        dialogo.setVisible(true);
    }
}

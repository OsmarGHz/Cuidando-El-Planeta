import java.util.Timer;
import java.util.TimerTask;

public class Cronometro {
    private Timer timer;
    private int tiempoRestante;

    // Método para iniciar el cronómetro sin imprimir cada segundo
    public void iniciarCronometro() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (tiempoRestante > 0) {
                    tiempoRestante--; // Resta el tiempo cada segundo
                } else {
                    detenerCronometro(); // Detiene el cronómetro automáticamente si se agota el tiempo
                }
            }
        }, 0, 1000); // Ejecuta cada 1 segundo (1000 ms)
    }

    // Método para detener el cronómetro
    public void detenerCronometro() {
        if (timer != null) {
            timer.cancel(); // Cancela el temporizador
        }
    }

    // Método para obtener el tiempo restante
    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRest(int tiempoRestante){
        this.tiempoRestante=tiempoRestante;
    }
}


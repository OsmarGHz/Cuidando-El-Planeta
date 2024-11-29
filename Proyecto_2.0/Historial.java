import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class Historial {
    private BufferedWriter bw;
    private static Historial instancia;

    public Historial() {
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Historial_del_juego.txt", true), "UTF-8"));
        } catch (IOException e) {
            System.out.println("No fue posible abrir el archivo");
        }
    }

    public static Historial Instancia(){
        if(instancia == null){ instancia = new Historial(); }
        return instancia;
    }

    // Método para poder escribir en el archivo de txt
    public void escribirTxt(Object LineaTxt) {
        try {
            // Verificar que bw no sea null antes de escribir
            if (bw != null) {
                bw.write(LineaTxt.toString());
                //bw.newLine();
                //flush para asegurar que se escriba inmediatamente
                bw.flush();
            }
       } catch (IOException e) {
            System.out.println("No fue posible escribir en el archivo");
        }
    }

    public void cerrarTxt() {
        try {
            if (bw != null) {
                bw.close();
            }
        } catch (IOException e) {
            System.out.println("No fue posible cerrar el archivo de texto");
            
        }
    }
}
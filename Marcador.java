import java.io.*;
import java.util.Arrays;

public class Marcador {
    private static Marcador instancia;
    public Marcador() {
    }

    public static Marcador Instancia(){
        if(instancia == null){ instancia = new Marcador(); }
        return instancia;
    }

    public int[] leerNumerosBinarios() throws IOException {
        String nombreArchivo = "Marcador.bin";
        int[] numeros = new int[5];
        DataInputStream entrada = new DataInputStream(new FileInputStream(nombreArchivo));

        for (int i = 0; i < 5; i++) {
            numeros[i] = entrada.readInt();
        }

        entrada.close();
        return numeros;
    }

    public int[] compararYAgregar(int[] numeros, int numeroAComparar) {
        int[] copia = Arrays.copyOf(numeros, 5);
        Arrays.sort(copia);
        int menor = copia[0];

        for (int i = 0; i < 5; i++) {
            if (numeroAComparar > copia[i]) {
                copia[0] = numeroAComparar;
                Arrays.sort(copia);
                break;
            }
        }
        return copia;
    }

    public void escribirNumerosBinarios(int[] numeros) throws IOException {
        String nombreArchivo = "Marcador.bin";
        DataOutputStream salida = new DataOutputStream(new FileOutputStream(nombreArchivo));

        for (int numero : numeros) { salida.writeInt(numero); }
        salida.close();
    }
}
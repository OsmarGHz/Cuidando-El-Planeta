package Proyecto;

import javax.swing.JOptionPane;

public class Juego {
    private final int noDesechosxNiv=10;
    private int nivelActual=0;
    private int numJugadores;
    private Nivel[]niveles=new Nivel[3];

    //Contructor de Juego para modificar requerimientos de los niveles
    Juego(){
        niveles[0]=new Nivel(10, 1, noDesechosxNiv, 7, "Baja");
        niveles[1]=new Nivel(15, 1, noDesechosxNiv, 8, "Media");
        niveles[2]=new Nivel(20, 2, noDesechosxNiv, 9, "Alta");
    }

    //Método para agregar a los jugadores especificados al principio
    public void setNumeroJugadores(){
        System.out.println("\t\t\t\t\t\t\"CUIDANDO EL PLANETA\"");
        do 
            numJugadores=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de JUGADORES (Máximo 5): "));
        while (numJugadores<1 || numJugadores>5);
    }

    public void limpiarPantalla(){
        for (int i=0;i<=9;i++)
            System.out.println();
    }

    public void iniciarJuego(){
        limpiarPantalla();
        System.out.println("NIVEL: "+(nivelActual+1)+"\n\nDificultad: "+
        (niveles[nivelActual].getDificultad()));
        
        limpiarPantalla();
        System.out.println();
    }

    //public void puedePasarNiv(){}

    public static void main(String[] args) {
        Juego j1=new Juego();

        j1.setNumeroJugadores();

        j1.iniciarJuego();
    }
}
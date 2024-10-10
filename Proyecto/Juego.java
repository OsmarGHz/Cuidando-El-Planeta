package Proyecto;

import javax.swing.JOptionPane;

public class Juego {
    private int nivelActual=1;
    private int numJugadores;

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

    //public void iniciarJuego(){}

    //public void puedePasarNiv(){}

    public static void main(String[] args) {
        Juego j1=new Juego();

        j1.setNumeroJugadores();

        //j1.iniciarJuego();
    }
}
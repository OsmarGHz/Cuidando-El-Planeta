package Proyecto;

import javax.swing.JOptionPane;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Juego {
    private int nivelActual=0;
    private int numJugadores;
    private Nivel[]niveles=new Nivel[3];
    private Jugador[]jugadores;

    Scanner entrada=new Scanner(System.in);

    //Contructor de Juego para modificar requerimientos de los niveles (Composición)
    Juego(){
        niveles[0]=new Nivel(10, 1, 7, "Baja",60);
        niveles[1]=new Nivel(15, 1, 8, "Media",40);
        niveles[2]=new Nivel(20, 2, 9, "Alta",30);
    }

    //Menú
    public boolean menuInicial(){
        int resp;
        System.out.println("\t\t\t\t\t\t\"CUIDANDO EL PLANETA\"");
        System.out.println();
        System.out.println("1: Iniciar Juego");
        System.out.println("0: Salir");
        do 
            resp=entrada.nextInt();
        while (resp<0 || resp>1);
        if (resp==1)
            return true;
        return false;
    }

    public void mostrarPantallaInicial(){
        limpiarPantalla();
        System.out.println("NIVEL: "+(nivelActual+1)+"\n\nDificultad: "+
        (niveles[nivelActual].getDificultad()));
        System.out.println("\n\nPresione ENTER para continuar...");
        entrada.nextLine();
        entrada.nextLine();
    }

    public void iniciarJuego(Contenedor[]contenedores){
        mostrarPantallaInicial();

        if (nivelActual>0) //Si el nivelActual es mayor a 0, se "retiran" los desechos
            for (Contenedor contenedor:contenedores)
                contenedor.getDesechosGuardados().clear();

        int contDes=0; //Para contar los desechos generados
        do{
            int contJug=0; //Para contar los jugadores
            do{
                if (jugadores[contJug].getNoVidas()>0){ //Se verifica que el jugador aun tenga vidas
                    int resp;
                    limpiarPantalla();
                    System.out.println("\n\nTurno de "+jugadores[contJug].getNombreJug());
        
                    System.out.println("Puntos: "+jugadores[contJug].getNoPuntos()+"\t\t\t\t\t\tVidas: "+
                    jugadores[contJug].getNoVidas());
        
                    System.out.println("\n\nTe topaste con: "+niveles[nivelActual].getDesecho(contDes,contJug).getNombreDesecho()+"\n");
                    System.out.println("\nDesechar en Contenedor:");
                    for (int i=0;i<contenedores.length;i++)
                        System.out.println((i+1)+": "+contenedores[i].getTipoDesecho());
                    
                    do
                        resp=Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número de Contenedor: "));
                    while (resp<1||resp>8);

                    jugadores[contJug].clasificarDesecho(niveles[nivelActual].getDesecho(contDes,contJug), contenedores[resp-1], niveles[nivelActual]);
                    System.out.println("Presione ENTER...");
                    entrada.nextLine();
                }

                contJug++;
            } while(contJug<jugadores.length);  
            contDes++;
        }while(contDes<niveles[nivelActual].getLeghtDesechosArr());
        Contenedor.mostrarMyrInsertados(contenedores,nivelActual);

        //Falta poner Planta Tratadora
    }

    public boolean puedePasarNivel(){ 
        for (Jugador jugador:jugadores)
            if ((niveles[nivelActual].verificarMinDesechosNiv(jugador))&&(jugador.getNoVidas()>0)) {
                nivelActual++;
                return true;
            }
        return false;
    }

    public void limpiarPantalla(){
        for (int i=0;i<=9;i++)
            System.out.println();
    }

    //Setters 
    //Para agregar a los jugadores especificados al principio
    public void setNumeroJugadores(){
        do 
            numJugadores=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de JUGADORES (Máximo 5): "));
        while (numJugadores<1 || numJugadores>5);
    }

    public void setJugadores(Jugador[]jugadores){
        this.jugadores=jugadores;
    }

    //Getters
    public int getNumJugadores(){
        return numJugadores;
    }

    public int getNivelActual(){
        return nivelActual;
    }
    
    public Jugador[] getJugadores(){
        return (jugadores);
    }

    public int getNumJugadoresRest(){
        int i=0;
        for (Jugador jugador:jugadores)
            if (jugador.getNoVidas()>0)
                i++;
        return i;
    }

    public static void main(String[] args) {
        Juego juego=new Juego();
        Contenedor[]contenedores=new Contenedor[8];
        //Se inicializan los contenedores
        for (int i=0;i<contenedores.length;i++)
            contenedores[i]=new Contenedor(i);

        if (juego.menuInicial()){
            //Agregación de Jugador con Juego
            juego.setNumeroJugadores();                              
            Jugador[]jugadoresIngresados=new Jugador[juego.getNumJugadores()];

            for (int i=0;i<juego.getNumJugadores();i++){
                jugadoresIngresados[i]=new Jugador(JOptionPane.showInputDialog("Ingrese su nombre (Jugador "+
                (i+1)+")"));
            }
            juego.setJugadores(jugadoresIngresados);

            do{
                //Se instancian los desechos
                juego.niveles[juego.getNivelActual()].generarDesechos(juego.getNumJugadoresRest(),juego.getNivelActual()); 

                //Inicio del Juego
                juego.iniciarJuego(contenedores);
            }while(juego.puedePasarNivel() && juego.getNivelActual()<3); 
        }
    }
}
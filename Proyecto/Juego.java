package Proyecto;

import javax.swing.JOptionPane;

import java.util.Scanner;

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
        System.out.println("\t\t\t\t\t\t        _____");
        System.out.println("\t\t\t\t\t\t    ,-:` \\;',`'-, ");
        System.out.println("\t\t\t\t\t\t  .'-;_,;  ':-;_,'.");
        System.out.println("\t\t\t\t\t\t /;   '/    ,  _`.-\\");
        System.out.println("\t\t\t\t\t\t| '`. (`     /` ` \\`|");
        System.out.println("\t\t\t\t\t\t|:.  `\\`-.   \\_   / |");
        System.out.println("\t\t\t\t\t\t|     (   `,  .`\\ ;'|");
        System.out.println("\t\t\t\t\t\t \\     | .'     `-'/");
        System.out.println("\t\t\t\t\t\t  `.   ;/        .'");
        System.out.println("\t\t\t\t\t\t    `'-._____.");
        System.out.println("\n");
        System.out.println("1: Iniciar Juego");
        System.out.println("0: Salir");
        System.out.println("Ingrese la opción que desee:");
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

    public int pasarTurno(int contJug){
        System.out.println("Se pasa el turno");
        jugadores[contJug].setSegundosRest(niveles[nivelActual].getSegundosTurno());
        contJug++;
        return contJug;
    }

    public void iniciarJuego(Contenedor[]contenedores,PlantaTratadora planta){
        mostrarPantallaInicial();

        if (nivelActual>0) //Si el nivelActual es mayor a 0, se "retiran" los desechos
            Contenedor.getDesechosGuardados().clear();

        for (Jugador jugador:jugadores){
            //Se reinician los valores para los segundos de turno y el num de desechos clasificados
            jugador.setSegundosRest(niveles[nivelActual].getSegundosTurno());
            jugador.setNumDesechosClasif(0);
        }   

        do{ 
            int contJug=0; //Para contar los jugadores

            do{
                if ((jugadores[contJug].getNoVidas()>0) && (jugadores[contJug].getNumDesechosClasif()<10) && (jugadores[contJug].getPasaNiv())){ //Se verifica que el jugador aun tenga vidas
                    int resp=-1;
                    limpiarPantalla();
                    System.out.print("\n\nTurno de ");    jugadores[contJug].mostrarStats();

                    //Se muestran los ontenedores disponibles
                    System.out.println("\n\nTe topaste con: "+niveles[nivelActual].getDesecho(jugadores[contJug].getNumDesechosClasif(),contJug).getNombreDesecho()+"\n");
                    System.out.println("\nDesechar en Contenedor:");
                    for (int i=0;i<contenedores.length;i++)
                        System.out.println((i+1)+": "+contenedores[i].getTipoDesecho());
                        

                    boolean respInvalida=false;
                    do{
                        String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugadores[contJug].getSegundosRest());
                        jugadores[contJug].setSegundosRest(CronometroConJOptionPane.getTiempoRest());

                        if(respString!=null)
                            resp=Integer.parseInt(respString);
                        else{
                            respInvalida=true;  resp=1;
                            contJug=pasarTurno(contJug);
                        }
                    }while (resp<1||resp>8);
                        
                    if (respInvalida==false){
                        jugadores[contJug].clasificarDesecho(niveles[nivelActual].getDesecho(jugadores[contJug].getNumDesechosClasif(),contJug), contenedores[resp-1], niveles[nivelActual]);
                        jugadores[contJug].setNumDesechosClasif(jugadores[contJug].getNumDesechosClasif()+1);


                        if (contenedores[resp-1].verificarDesecho(niveles[nivelActual].getDesecho(jugadores[contJug].getNumDesechosClasif()-1,contJug))==false){
                            contJug=pasarTurno(contJug);
                        }
                        else{
                            PlantaTratadora.setDesechosArrayList();
                            System.out.println("Enviando a la Planta Tratadora...\nPresione ENTER...");
                            entrada.nextLine();
                            planta.identificarDesecho(contenedores[resp-1], niveles[nivelActual], jugadores[contJug]);
                            //Falta poner Planta Tratadora
                        }

                        System.out.println("Presione ENTER...");
                        entrada.nextLine();
                    }        
                }
                else
                    contJug=pasarTurno(contJug);
            }while(contJug<jugadores.length);
        } while(quedanJugadoresQuierenDesechar());  
        
        Contenedor.mostrarMyrInsertados(contenedores,nivelActual);
        for (Jugador jugador:jugadores){
            System.out.println();
            jugador.mostrarStats();
        }
    }

    public boolean quedanJugadoresQuierenDesechar(){
        boolean seguir=false;
        for(Jugador jugador:jugadores)
            if ((jugador.getNoVidas()>0) && (jugador.getNumDesechosClasif()<10))
                seguir=true;
        return seguir;
    }

    public boolean puedePasarNivel(){ 
        //Tras cada nivel se revisa que el jugador cumpla con las condiciones para el siguiente
        for (Jugador jugador:jugadores){
            if ((niveles[nivelActual].verificarMinDesechosNiv(jugador)==false)||(jugador.getNoVidas()<=0)){
                jugador.setPasaNiv(false);
            }
        }
        //Si algun jugador restante todavía puede continuar entonces el juego continua
        for (Jugador jugador:jugadores){
            if (jugador.getPasaNiv()) {
                nivelActual++;
                return true;
            }
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
        PlantaTratadora planta=new PlantaTratadora();

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
                juego.iniciarJuego(contenedores,planta);
            }while(juego.puedePasarNivel() && juego.getNivelActual()<3); 
        }
    }
}
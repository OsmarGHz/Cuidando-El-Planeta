package Proyecto;

import java.util.Scanner;

public class Juego {
    private int nivelActual=0;
    private int numJugadores=0;
    private Nivel[]niveles=new Nivel[3];
    private Jugador[]jugadores;

    public Scanner entrada=new Scanner(System.in);

    //Contructor de Juego para modificar requerimientos de los niveles (Composición)
    Juego(){
        //Polimorfismo
        niveles[0]=new Nivel1(10, 1, 7,60);
        niveles[1]=new Nivel2(15, 1, 8,40);
        niveles[2]=new Nivel3(20, 2, 9,30);
    }

    public int menuInicial(){
        int resp;
        System.out.println("\t\t\t\t\t\t\t\"CUIDANDO EL PLANETA\"");
        System.out.println();
        System.out.println("\t\t\t\t\t\t\t        _____");
        System.out.println("\t\t\t\t\t\t\t    ,-:` \\;',`'-, ");
        System.out.println("\t\t\t\t\t\t\t  .'-;_,;  ':-;_,'.");
        System.out.println("\t\t\t\t\t\t\t /;   '/    ,  _`.-\\");
        System.out.println("\t\t\t\t\t\t\t| '`. (`     /` ` \\`|");
        System.out.println("\t\t\t\t\t\t\t|:.  `\\`-.   \\_   / |");
        System.out.println("\t\t\t\t\t\t\t|     (   `,  .`\\ ;'|");
        System.out.println("\t\t\t\t\t\t\t \\     | .'     `-'/");
        System.out.println("\t\t\t\t\t\t\t  `.   ;/        .'");
        System.out.println("\t\t\t\t\t\t\t    `'-._____.");
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t\t  1: Iniciar Juego");
        System.out.println("\t\t\t\t\t\t\t      0: Salir");
        System.out.println("\t\t\t\t\t\t    Ingrese la opción que desee:");
        System.out.print("\t\t\t\t\t\t\t          ");
        do 
            resp=entrada.nextInt();
        while (resp<0 || resp>1);
        if (resp==1)
            return 1;
        return 0;
    }

    public int pasarTurno(int contJug){
        System.out.println("Se pasa el turno");

        //Se reinicia el contador del jugador antes de pasar al siguiente
        jugadores[contJug].getCronometro().setTiempoRest(niveles[nivelActual].getSegundosTurno());
        contJug++;
        return contJug;
    }

    public void iniciarJuego(Contenedor[]contenedores){
        limpiarPantalla();
        //Método abstracto de Niveles y Polimorfismo
        niveles[nivelActual].presentacionNivel();
        System.out.println("\n\nPresione ENTER para continuar...");
        entrada.nextLine(); 
        //Las primeras lineas muestran especificaciones de Nivel

        if (nivelActual>0) //Si el nivelActual es mayor a 0, se "retiran" los desechos del contenedor
            for (Contenedor contenedor:contenedores)
                contenedor.getDesechosGuardados().clear();

        for (Jugador jugador:jugadores){
            //Se reinician los valores para los segundos de turno y el num de desechos clasificados
            jugador.getCronometro().setTiempoRest(niveles[nivelActual].getSegundosTurno());
            jugador.setNumDesechosClasif(0);
        }   

        do{ 
            int contJug=0; //Para contar los jugadores
            do{
                //Flujo para clasificar desechos:
                //(jugadores[contJug].getNoVidas()>0): revisa que el jugador siga con vida
                //(jugadores[contJug].getNumDesechosClasif()<10): revisa que todavía no haya clasificado todos sus desechos
                //(jugadores[contJug].getPasaNiv()): revisa que el jugador tenga la facultad de seguir jugando en niveles posteriores
                if ((jugadores[contJug].getNoVidas()>0) && (jugadores[contJug].getNumDesechosClasif()<10) && (jugadores[contJug].getPasaNiv())){
                    int resp=-1;
                    limpiarPantalla();
                    System.out.print("\n\n>> Turno de ");    jugadores[contJug].mostrarStats();

                    //Se muestran los contenedores disponibles
                    System.out.println("\n\nTe topaste con: "+niveles[nivelActual].getDesecho(jugadores[contJug].getNumDesechosClasif(),contJug).getNombreDesecho()+"\n");
                    System.out.println("\nDesechar en Contenedor:");
                    for (int i=0;i<contenedores.length;i++)
                        System.out.println((i+1)+": "+contenedores[i].getTipoDesecho());
                        

                    boolean respInvalida=false;
                    do{
                        System.out.println("\nTienes "+jugadores[contJug].getCronometro().getTiempoRestante()+" segundos para contestar.");
                        System.out.println("Ingresa el número de Contenedor:");

                        jugadores[contJug].getCronometro().iniciarCronometro();
                        String respString=entrada.nextLine();
                        jugadores[contJug].getCronometro().detenerCronometro();
                        
                        System.out.println("\nTe quedaron "+jugadores[contJug].getCronometro().getTiempoRestante()+" segundos.");

                        if(jugadores[contJug].getCronometro().getTiempoRestante()>0) //Si responde a tiempo la respuesta se pasa a Entero
                            resp=Integer.parseInt(respString);
                        else{   //Si no responde en el tiempo indicado no se toma en cuenta la respuesta y se pasa el turno
                            respInvalida=true;  resp=1;
                            contJug=pasarTurno(contJug);
                        }
                    }while (resp<1||resp>13);
                        
                    if (respInvalida==false){ //Si es que ya había respondido...
                        jugadores[contJug].clasificarDesecho(niveles[nivelActual].getDesecho(jugadores[contJug].getNumDesechosClasif(),contJug), contenedores[resp-1], niveles[nivelActual]);
                        jugadores[contJug].setNumDesechosClasif(jugadores[contJug].getNumDesechosClasif()+1);

                        //Si la respuesta enviada fue incorrecta se pasa el turno
                        if (contenedores[resp-1].verificarDesecho(niveles[nivelActual].getDesecho(jugadores[contJug].getNumDesechosClasif()-1,contJug))==false){
                            contJug=pasarTurno(contJug);
                        }
                        else{ //Sino solo se agrega a la planta Tratadora
                            jugadores[contJug].getPlanta().addDesecho(contJug, contenedores[resp-1].getUltimoDesecho());
                        }
                    }        
                }
                //Flujo para tratar desechos
                //(contJug<jugadores.length): verifica que el método pasarTurnos no haya sobrepasado al número de jugadores
                //(jugadores[contJug].getNumDesechosTrat()<jugadores[contJug].getPlanta().getSizeArr_ArrayListDesecho(contJug): verifica que el jugador haya tratado todos los desechos 
                if ((contJug<jugadores.length)&&((jugadores[contJug].getNoVidas()>0) && (jugadores[contJug].getNumDesechosTrat()<jugadores[contJug].getPlanta().getSizeArr_ArrayListDesecho(contJug)) && jugadores[contJug].getPasaNiv())){
                    System.out.println("\n\nPlanta Tratadora...\nPresione ENTER...");
                    entrada.nextLine();
                    System.out.print("\n>> Turno de ");    jugadores[contJug].mostrarStats();
                    System.out.println();
                    //llama al método de planta
                    if (jugadores[contJug].getPlanta().identificarDesecho(niveles[nivelActual], jugadores[contJug],contJug)==0)
                        contJug=pasarTurno(contJug);
                }   
                if ((contJug<jugadores.length && jugadores[contJug].getNumDesechosClasif()==10 && jugadores[contJug].getNumDesechosClasif()==10 && jugadores[contJug].getPlanta().getSizeArr_ArrayListDesecho(contJug)==10)||(contJug<jugadores.length && jugadores[contJug].getNoVidas()<=0)){
                    contJug=pasarTurno(contJug);
                }
                System.out.println("Presione ENTER...");
                entrada.nextLine();
            }while(contJug<jugadores.length); //Siempre que contJug no haya rebasado el número de jugadores sigue
        } while(quedanJugadoresQuierenDesechar()||quedanJugadoresQuierenTratar());

        //Se muestra que contenedor(es) tuvo más desechos
        Contenedor.mostrarMyrInsertados(contenedores,nivelActual);
        //Se imprimen stats finales por nivel
        for (Jugador jugador:jugadores){
            System.out.println();
            jugador.mostrarStats();
        }
        System.out.println("Presione ENTER...");
        entrada.nextLine();
    }

    //Verifica que haya al menos un jugador con vida al que le falte clasificar sus desechos
    public boolean quedanJugadoresQuierenDesechar(){
        boolean seguir=false;
        for(Jugador jugador:jugadores)
            if ((jugador.getNoVidas()>0) && (jugador.getNumDesechosClasif()<10))
                seguir=true;
        return seguir;
    }

    //Verifica que haya al menos un jugador al que le haga falta tratar desechos en la planta 
    public boolean quedanJugadoresQuierenTratar(){
        boolean seguir=false;
        for (int i=0;i<jugadores.length;i++)
            if ((jugadores[i].getNoVidas()>0) && (jugadores[i].getNumDesechosTrat()<jugadores[i].getPlanta().getSizeArr_ArrayListDesecho(i)))
                seguir=true;
        return(seguir);
    }

    public boolean puedePasarNivel(){ 
        //Tras cada nivel se revisa que al menos un jugador cumpla con las condiciones para el siguiente nivel
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
    
    //Se hacen varios "saltos de línea" para simular una limpieza de pantalla
    public void limpiarPantalla(){
        for (int i=0;i<=15;i++)
            System.out.println();
    }  

    //Setters 
    //Para agregar a los jugadores especificados al principio
    public void setNumeroJugadores(){
        do {
            System.out.println("\n\nIngrese el número de JUGADORES (Máximo 5): ");
            numJugadores=entrada.nextInt();
        }   
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

    public void muestraGanadores(){
        int myr=jugadores[0].getNoPuntos();
        System.out.println("---------------------------------------------------------------------------------------------------");
        for (Jugador jugador:jugadores)
            if (jugador.getNoPuntos()>myr)
                myr=jugador.getNoPuntos();

        if (myr==0)
            System.out.println("Perdieron");
        else
            for (Jugador jugador:jugadores)
                if (jugador.getNoPuntos()==myr)
                    System.out.println("\n\t\t\t\t\t\t¡¡"+jugador.getNombreJug()+" has GANADO!!");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner entrada=new Scanner(System.in);
        Juego juego=new Juego(); //Se crea un objeto de tipo Juego para "activar" el constructor
        Contenedor[]contenedores=new Contenedor[13]; //Se crean los contenedores

        //Se inicializan los contenedores
        for (int i=0;i<contenedores.length;i++)
            contenedores[i]=new Contenedor(i);

        if (juego.menuInicial()==1){
            
            juego.setNumeroJugadores();                              
            Jugador[]jugadoresIngresados=new Jugador[juego.getNumJugadores()];

            System.out.println();
            for (int i=0;i<juego.getNumJugadores();i++){
                System.out.println("Ingrese su nombre (Jugador "+(i+1)+"): ");
                String nombre=entrada.nextLine();
                jugadoresIngresados[i]=new Jugador(nombre);
                System.out.println("Presione ENTER...");
                entrada.nextLine();
            }
            //Agregación de Jugador con Juego
            juego.setJugadores(jugadoresIngresados);
            
            //Relación Jugador con Planta
            PlantaTratadora planta=new PlantaTratadora();   //Se crea la planta
            for (int i=0;i<juego.jugadores.length;i++)
                juego.jugadores[i].setPlanta(planta);       //Todos los jugadores comparten planta
            planta.setArregloArrListDesechos(juego.getNumJugadores());

            //Relación Jugador con Cronometro
            Cronometro[] cronometros=new Cronometro[juego.getNumJugadores()];
            for (int i=0;i<juego.jugadores.length;i++)
                cronometros[i]=new Cronometro();

            for (int i=0;i<juego.jugadores.length;i++)
                juego.jugadores[i].setCronometro(cronometros[i]);

            do{
                juego.niveles[juego.getNivelActual()].setDesechos(juego.getNumJugadoresRest());
                //Se instancian los desechos
                juego.niveles[juego.getNivelActual()].generarDesechos(juego.getNumJugadoresRest()); 

                //Inicio del Juego
                juego.iniciarJuego(contenedores);
            }while(juego.puedePasarNivel() && juego.getNivelActual()<3); 

            if (juego.getNumJugadores()!=0){
                juego.muestraGanadores();
            }

        }
    }
}
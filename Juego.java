import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Juego {
    private int nivelActual=0;
    private int numJugadores=0;

    private int resp;
    private int contJug;
    private boolean respInvalida;

    private InterfazGraficaJuego iGraficaJuego;
    private Nivel[]niveles=new Nivel[3];
    private Jugador[]jugadores;

    private InputStreamReader isrJuego=new InputStreamReader(System.in);
    private BufferedReader brJuego=new BufferedReader(isrJuego);

    public Scanner entrada=new Scanner(System.in);
    
    Historial historial = Historial.Instancia();

    //Contructor de Juego para modificar requerimientos de los niveles y la interfaz (Composición com ambas)
    Juego(){
        iGraficaJuego=new InterfazGraficaJuego();
        //Polimorfismo
        niveles[0]=new Nivel1(10, 1, 7,60);
        niveles[1]=new Nivel2(15, 1, 8,40);
        niveles[2]=new Nivel3(20, 2, 9,30);
    }

    public int pasarTurno(int contJug){
        System.out.println("Se pasa el turno");
        mandarAHistorial("\nSe pasa el turno\n");

        //Se reinicia el contador del jugador antes de pasar al siguiente
        jugadores[contJug].getCronometro().setTiempoRest(niveles[nivelActual].getSegundosTurno());
        contJug++;
        return contJug;
    }

    public void iniciarJuego(Contenedor[]contenedores, int nivelActual){
        historial.escribirTxt("\n\n\t---Nivel "+nivelActual+"---\n\n");
        iniciaTrasStart(contenedores);
    }

    public void iniciaTrasStart(Contenedor[] contenedores){
        contJug=0;
     
        if (quedanJugadoresQuierenDesechar()||quedanJugadoresQuierenTratar())
            iniciaTrasVerifQuieren(contenedores);
        else
            //Aqui pasar el caso para imprimir el contenedor con más desechos
            iniciaImprimeMyr(contenedores);      
        /*do{ 
            int contJug=0; //Para contar los jugadores

            do{
                //Flujo para clasificar desechos:
                //(jugadores[contJug].getNoVidas()>0): revisa que el jugador siga con vida
                //(jugadores[contJug].getNumDesechosClasif()<10): revisa que todavía no haya clasificado todos sus desechos
                //(jugadores[contJug].getPasaNiv()): revisa que el jugador tenga la facultad de seguir jugando en niveles posteriores
                if ((jugadores[contJug].getNoVidas()>0) && (jugadores[contJug].getNumDesechosClasif()<10) && (jugadores[contJug].getPasaNiv())){
                    int resp=-1;
                    //Se presenta al Jugador en turno
                    iGraficaJuego.getLTurno().setText(">> Turno de "+jugadores[contJug].getNombreJug());
                    iGraficaJuego.getLStats().setText(jugadores[contJug].mostrarStats());

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
                if ((contJug<jugadores.length && jugadores[contJug].getNumDesechosClasif()==10 && jugadores[contJug].getNumDesechosTrat()==jugadores[contJug].getPlanta().getSizeArr_ArrayListDesecho(contJug))||(contJug<jugadores.length && jugadores[contJug].getNoVidas()<=0)){
                    System.out.println("+ Para el jugador "+contJug);
                    contJug=pasarTurno(contJug);
                }
                System.out.println("Presione ENTER...");
                entrada.nextLine();
                             
            }while(contJug<jugadores.length); //Siempre que contJug no haya rebasado el número de jugadores sigue
        } while(quedanJugadoresQuierenDesechar()||quedanJugadoresQuierenTratar());*/
    }

    public void iniciaTrasVerifQuieren(Contenedor[] contenedores){
        final boolean[] responde={false,false};

        final Lock lock=new ReentrantLock();
        final Condition condicionLock= lock.newCondition();

        if (contJug<jugadores.length){
            //Flujo para clasificar desechos:
            //(jugadores[contJug].getNoVidas()>0): revisa que el jugador siga con vida
            //(jugadores[contJug].getNumDesechosClasif()<10): revisa que todavía no haya clasificado todos sus desechos
            //(jugadores[contJug].getPasaNiv()): revisa que el jugador tenga la facultad de seguir jugando en niveles posteriores
            if ((jugadores[contJug].getNoVidas()>0) && (jugadores[contJug].getNumDesechosClasif()<10) && (jugadores[contJug].getPasaNiv())){
                iGraficaJuego.cambiaPanelTirar(); //Se cambia al panel de tirar
                //Para el temporizador
                iGraficaJuego.tempNivelTirar(jugadores[contJug].getCronometro().getTiempoRestante());; 
                //Se presenta al Jugador en turno
                iGraficaJuego.getLTurno(0).setText(">> Turno de "+jugadores[contJug].getNombreJug());
                historial.escribirTxt("\n>> Turno de "+jugadores[contJug].getNombreJug());
                iGraficaJuego.getLStats(0).setText(jugadores[contJug].mostrarStats());
                historial.escribirTxt("\t\t"+jugadores[contJug].mostrarStats());

                //Se muestran los contenedores disponibles
                iGraficaJuego.getLDesecho(0).setText(niveles[nivelActual].getDesecho(jugadores[contJug].getNumDesechosClasif(),contJug).getNombreDesecho());
                System.out.println("\nDesechar en Contenedor:");
                for (int i=0;i<contenedores.length;i++)
                    System.out.println((i+1)+": "+contenedores[i].getTipoDesecho());
                    

                respInvalida=false;
                resp=-1;

                int tiempoRest=jugadores[contJug].getCronometro().getTiempoRestante();

                System.out.println("\nTienes "+jugadores[contJug].getCronometro().getTiempoRestante()+" segundos para contestar.");
                System.out.println("Ingresa el número de Contenedor:");
                jugadores[contJug].getCronometro().iniciarCronometro();

                lock.lock();  
                try {
                    Timer verificaPresionaContenedor=new Timer(1000, new ActionListener() {
                        int tiempoRestDPC=tiempoRest;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            tiempoRestDPC--;

                            lock.lock();
                            try {
                                //Si algun contenedor se presionó
                                if (iGraficaJuego.getPresionaContenedor()){
                                    ((Timer) e.getSource()).stop();

                                    System.out.println(iGraficaJuego.getBotonPulsadoContenedor());
                                    resp=iGraficaJuego.getBotonPulsadoContenedor();
                                    iGraficaJuego.setPresionaContenedor(false);

                                    jugadores[contJug].getCronometro().detenerCronometro();
                                    System.out.println("\nTe quedaron "+jugadores[contJug].getCronometro().getTiempoRestante()+" segundos.");
                                    mandarAHistorial("\nTe quedaron "+jugadores[contJug].getCronometro().getTiempoRestante()+" segundos.\n");
                                    responde[0]=true; //Ya se respondio
                                    condicionLock.signal(); //Despierta el hilo
                                }
                                if (tiempoRestDPC<=0){
                                    ((Timer) e.getSource()).stop();

                                    jugadores[contJug].getCronometro().detenerCronometro();
                                    System.out.println("\nTe quedaron "+jugadores[contJug].getCronometro().getTiempoRestante()+" segundos.");
                                    mandarAHistorial("\nTe quedaron "+jugadores[contJug].getCronometro().getTiempoRestante()+" segundos.\n");
                                    respInvalida=true;  resp=1;
                                    contJug=pasarTurno(contJug);
                                    responde[0]=true; 
                                    condicionLock.signal(); //Despierta el hilo
                                }   
                            }finally{
                                lock.unlock();
                            }  
                        } 
                    });  
                    verificaPresionaContenedor.start();  
                    

                    // Esperar hasta que el jugador haya respondido o se acabe el tiempo
                    while (!responde[0]) 
                        condicionLock.await(); // Bloquea el hilo actual

                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    System.out.println("El hilo principal fue interrumpido.");
                } finally {
                    lock.unlock();
                }
                
                if (respInvalida==false){ //Si es que ya había respondido...
                    mandarAHistorial("El jugador seleccionó el contenedor "+contenedores[resp-1].getTipoDesecho()+"... ");
                    jugadores[contJug].clasificarDesecho(niveles[nivelActual].getDesecho(jugadores[contJug].getNumDesechosClasif(),contJug), contenedores[resp-1], niveles[nivelActual],historial);
                    jugadores[contJug].setNumDesechosClasif(jugadores[contJug].getNumDesechosClasif()+1);

                    //Si la respuesta enviada fue incorrecta se pasa el turno
                    if (contenedores[resp-1].verificarDesecho(niveles[nivelActual].getDesecho(jugadores[contJug].getNumDesechosClasif()-1,contJug))==false){
                        contJug=pasarTurno(contJug);
                    }
                    else{ //Sino solo se agrega a la planta Tratadora
                        mandarAHistorial("Pasando a la planta...\n");
                        jugadores[contJug].getPlanta().addDesecho(contJug, contenedores[resp-1].getUltimoDesecho());
                    }
                }          
            }
            iGraficaJuego.getLTiempoRest(0).setText(null);;

            //Flujo para tratar desechos
            //(contJug<jugadores.length): verifica que el método pasarTurnos no haya sobrepasado al número de jugadores
            //(jugadores[contJug].getNumDesechosTrat()<jugadores[contJug].getPlanta().getSizeArr_ArrayListDesecho(contJug): verifica que el jugador haya tratado todos los desechos 
            if ((contJug<jugadores.length)&&((jugadores[contJug].getNoVidas()>0) && (jugadores[contJug].getNumDesechosTrat()<jugadores[contJug].getPlanta().getSizeArr_ArrayListDesecho(contJug)) && jugadores[contJug].getPasaNiv())){
                iGraficaJuego.cambiaPanelPlanta(); //Se pasa al panel de Planta

                iGraficaJuego.getLTurno(1).setText(">> Turno de "+jugadores[contJug].getNombreJug());
                iGraficaJuego.getLStats(1).setText(jugadores[contJug].mostrarStats());
                historial.escribirTxt("\n>> Turno de "+jugadores[contJug].getNombreJug());
                historial.escribirTxt("\t\t"+jugadores[contJug].mostrarStats());
                System.out.println();
                //llama al método de planta
                if (jugadores[contJug].getPlanta().identificarDesecho(niveles[nivelActual], jugadores[contJug],contJug,historial)==0)
                    contJug=pasarTurno(contJug);
            }   
            iGraficaJuego.getLTiempoRest(1).setText(null);
            if ((contJug<jugadores.length && jugadores[contJug].getNumDesechosClasif()==10 && jugadores[contJug].getNumDesechosTrat()==jugadores[contJug].getPlanta().getSizeArr_ArrayListDesecho(contJug))||(contJug<jugadores.length && jugadores[contJug].getNoVidas()<=0)){
                System.out.println("+ Para el jugador "+contJug);
                contJug=pasarTurno(contJug);
            }            

            iniciaTrasVerifQuieren(contenedores); 
        }
        else
            iniciaTrasStart(contenedores);
    }

    public void iniciaImprimeMyr(Contenedor[] contenedores){
        Contenedor.mostrarMyrInsertados(contenedores,nivelActual, jugadores,historial);
    }

    //Verifica que haya al menos un jugador con vida al que le falte clasificar sus desechos
    public boolean quedanJugadoresQuierenDesechar(){
        for(Jugador jugador:jugadores)
            if ((jugador.getNoVidas()>0) && (jugador.getNumDesechosClasif()<10) && puedePasarNivelNormal())
                return true;
        return false;
    }

    //Verifica que haya al menos un jugador al que le haga falta tratar desechos en la planta 
    public boolean quedanJugadoresQuierenTratar(){
        for (int i=0;i<jugadores.length;i++)
            if ((jugadores[i].getNoVidas()>0) && (jugadores[i].getNumDesechosTrat()<jugadores[i].getPlanta().getSizeArr_ArrayListDesecho(i)) && puedePasarNivelNormal())
                return true;
        return(false);
    }

    public boolean puedePasarNivelNormal(){
        if (nivelActual>=3) return false;

        for (Jugador jugador:jugadores)
            if (jugador.getPasaNiv()) return true;
         
        return false;
    }

    public boolean puedePasarNivel(){ 
        if (nivelActual>=3) return false;

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

    public String asigBufferedReader(BufferedReader br){
        String resp;
        try{
            resp=br.readLine();
            return resp;
        }
        catch(IOException e){
            System.out.println("Error al leer la entrada\n Por favor vuelva a abrir el JUEGO");
            System.exit(0); //Se sale del programa en caso de que haya allgún problema con el flujo de entrada
        }
        return null;
    }

    public void mostrarFrame(){
        iGraficaJuego.setVisible(true);
    }


    //Setters 
    //Para agregar a los jugadores especificados al principio
    public void setNumeroJugadores(){
        String numJugS;
        while (true){
            try {
                System.out.print("\n\nIngrese el número de JUGADORES (Máximo 5): ");
                numJugS=asigBufferedReader(brJuego);
                numJugadores=Integer.parseInt(numJugS); 

                if (numJugadores>=1 && numJugadores<=5)
                    break;  //Para salir del ciclo
                else
                    System.out.println("Por favor, ingrese un número entre 1 y 5");
            }
            catch(NumberFormatException ex){
                System.out.println("El dato ingresado es inválido.\n");
            }   
        } 
        historial.escribirTxt("\n\n\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n\n\n");
        historial.escribirTxt("Numero de jugadores: "+numJugadores+"\n");
    }

    public void setJugadores(Jugador[]jugadores){
        this.jugadores=jugadores;
    }

    //Función recursiva
    public void pedirNombreJugadores(int i,Jugador[]jugadoresIngresados){
        if (i>1) {
            pedirNombreJugadores(i-1, jugadoresIngresados);
        }
        System.out.println();
        System.out.print("Ingrese su nombre (Jugador "+(i)+"): ");
        String nombre=asigBufferedReader(brJuego);
        jugadoresIngresados[i-1]=new Jugador(nombre);
        System.out.print("Presione ENTER...");
        entrada.nextLine();
        historial.escribirTxt("Jugador "+i+": "+nombre+"\n");
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

    public InterfazGraficaJuego getIGraficaJuego(){
        return iGraficaJuego;
    }


    public void muestraGanadores(){
        int myr=jugadores[0].getNoPuntos();
        System.out.println("---------------------------------------------------------------------------------------------------");
        mandarAHistorial("\n---------------------------------------------------------------------------------------------------");
        for (Jugador jugador:jugadores)
            if (jugador.getNoPuntos()>myr)
                myr=jugador.getNoPuntos();

        if (myr==0){
            iGraficaJuego.getPResultFinal().setBackground(Color.RED);
            iGraficaJuego.getLResultadosFinales(0).setText(null);
            iGraficaJuego.getLResultadosFinales(1).setForeground(Color.white);
            iGraficaJuego.getLResultadosFinales(1).setVerticalAlignment(JLabel.CENTER);
            iGraficaJuego.getLResultadosFinales(1).setText("<html><H2>Perdieron</H2><html>");
            mandarAHistorial("\nPerdieron\n");
            iGraficaJuego.getLMuestraTiempoResResultados(1).setForeground(Color.white);
        }    
        else{
            StringBuilder fraseGanadores=new StringBuilder("<html>"), fraseWinner = new StringBuilder("<html><H2>GANARON: </H2><html>");
            mandarAHistorial("\nGanaron\n");
            iGraficaJuego.getPResultFinal().setBackground(Color.CYAN);
            int[] marcadorcito = new int[5];
            Marcador marcadorClase = new Marcador();
            marcadorcito = leerArchivoBinario(marcadorcito, marcadorClase);
            for (Jugador jugador:jugadores)
                if (jugador.getNoPuntos()==myr){
                    fraseGanadores.append("<H4>¡¡ "+jugador.getNombreJug()+" !!</H4><br>");
                    mandarAHistorial("¡¡ "+jugador.getNombreJug()+" !!\n");
                    marcadorcito = marcadorClase.compararYAgregar(marcadorcito, jugador.getNoPuntos());
                }
            iGraficaJuego.getLResultadosFinales(0).setText(fraseWinner.toString());
            fraseGanadores.append("<H3>Lista de marcadores mas altos: </H3><br>");
            for(int i=4, j=1;i>=0;i--,j++){
                fraseGanadores.append("<H4>Jugador No. "+j+" con "+marcadorcito[i]+" puntos</H4><br>");
            }
            fraseGanadores.append("</html>");
            iGraficaJuego.getLResultadosFinales(1).setText(fraseGanadores.toString());
            escribirArchivoBinario(marcadorcito, marcadorClase);
        }   
        iGraficaJuego.muestraPanelResultados();
        System.exit(0);
    }

    public int[] leerArchivoBinario(int[] marcadorcito, Marcador marcadorClase){
        try {
            marcadorcito = marcadorClase.leerNumerosBinarios();
            return marcadorcito;
        } catch (IOException e) {
            System.out.println("Algo paso con la lectura. Estableciendo arreglo marcador en ceros");
            for(int i = 0;i<5;i++){
                marcadorcito[i]=0;
            }
            return marcadorcito;
        }
    }

    public void escribirArchivoBinario(int[] marcadorcito, Marcador marcadorClase){
        try {
            marcadorClase.escribirNumerosBinarios(marcadorcito);
        } catch (IOException e) {
            System.out.println("Algo paso con la escritura");
        }
    }

    public void setDatos(Contenedor[] contenedores){
        Lock lock= new ReentrantLock();
        Condition lockCondition= lock.newCondition();
        niveles[nivelActual].setDesechos(getNumJugadoresRest());
        //Se instancian los desechos
        niveles[nivelActual].generarDesechos(getNumJugadoresRest()); 

        //Se muestran especificaciones de Nivel
        //Al JTextField se le asigna cada String dado por los metodos presentacionNivel(Método abstracto de Niveles y Polimorfismo)
        iGraficaJuego.getLTitulo().setText(niveles[nivelActual].tituloNivel());
        iGraficaJuego.getLDificultad().setText(niveles[nivelActual].dificultadNivel());
        iGraficaJuego.getLPresentacion().setText(niveles[nivelActual].presentacionNivel());
        lock.lock();
        try{
            iGraficaJuego.cambiaPanelPresentacion(lock, lockCondition);

            while (!iGraficaJuego.getContStart())
                lockCondition.await();
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
            System.out.println("El hilo principal fue interrumpido.");
               
        }finally {
            lock.unlock();
        }
        iGraficaJuego.setContStart(false);

        //A cada etiqueta de cada Contenedor/Bote se le pasa el tipo para que lo muestre
        for (int i=0;i<contenedores.length;i++){
            iGraficaJuego.getLBote(i).setText(contenedores[i].getTipoDesecho());
        }

        if (nivelActual>0) //Si el nivelActual es mayor a 0, se "retiran" los desechos del contenedor
            for (Contenedor contenedor:contenedores)
                contenedor.getDesechosGuardados().clear();

        for (Jugador jugador:jugadores){
            //Se reinician los valores para los segundos de turno y el num de desechos clasificados
            jugador.getCronometro().setTiempoRest(niveles[nivelActual].getSegundosTurno());
            jugador.setNumDesechosClasif(0);
        } 
    }

    public void mandarAHistorial(String a){
        historial.escribirTxt(a);
    }
    
    public static void main(String[] args) {
        Juego juego=new Juego(); //Se crea un objeto de tipo Juego para "activar" el constructor
        Contenedor[]contenedores=new Contenedor[13]; //Se crean los contenedores

        //Se inicializan los contenedores
        for (int i=0;i<contenedores.length;i++)
            contenedores[i]=new Contenedor(i);
            
        juego.setNumeroJugadores();                              
        Jugador[]jugadoresIngresados=new Jugador[juego.getNumJugadores()];

        System.out.println();
        juego.pedirNombreJugadores(juego.getNumJugadores(), jugadoresIngresados);

        //Agregación de Jugador con Juego
        juego.setJugadores(jugadoresIngresados);
        
        //Relación Jugador con Planta
        PlantaTratadora planta=new PlantaTratadora();   //Se crea la planta
        for (int i=0;i<juego.jugadores.length;i++)
            juego.jugadores[i].setPlanta(planta);       //Todos los jugadores comparten planta
        planta.setArregloArrListDesechos(juego.getNumJugadores());

        //Asociacion de PlantaTratadora con InterfazGrafica y de Contenedor con Interfaz Gráfica
        planta.setInterfazGrafica(juego.getIGraficaJuego());
        Contenedor.setInterfazGrafica(juego.getIGraficaJuego());


        //Relación Jugador con Cronometro
        Cronometro[] cronometros=new Cronometro[juego.getNumJugadores()];
        for (int i=0;i<juego.jugadores.length;i++)
            cronometros[i]=new Cronometro();

        for (int i=0;i<juego.jugadores.length;i++)
            juego.jugadores[i].setCronometro(cronometros[i]);

        //Se hace visible el Frame una vez capturados los datos iniciales
        juego.mostrarFrame();


        //No sigue hasta que se haya  Start
        Timer verificaSigueStart = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (juego.getIGraficaJuego().getPresionaStart()) {
                    ((Timer) e.getSource()).stop(); // Detener el temporizador
                    new Thread(()->ejecutaCicloJuego()).start();                         
                }
            }

            private void ejecutaCicloJuego(){
                do{ 
                    juego.setDatos(contenedores);
                    //Inicio del Juego
                    juego.iniciarJuego(contenedores,juego.getNivelActual());
                }while(juego.puedePasarNivel() && juego.getNivelActual()<3);

                juego.muestraGanadores();
            }
        });
        verificaSigueStart.start();            
    }
}
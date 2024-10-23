package Proyecto;

import java.util.ArrayList;
import java.util.Scanner;

public class PlantaTratadora {
    private Scanner entrada = new Scanner(System.in);
    private ArrayList<Desechos>[] desechos;
    private  static ArrayList<String> respuestaTratadora= new ArrayList<>();


    public int identificarDesecho(Nivel niv,Jugador jugador, int numJug){
        System.out.println("Nombre del desecho: " +desechos[numJug].get(jugador.getNumDesechosTrat()).getNombreDesecho());
        System.out.println("\n**Ingresa los comandos en el orden correcto para tratar los desechos**\n");

        switch (desechos[numJug].get(jugador.getNumDesechosTrat()).getNumTipoDesecho()){
            case 0: return(TratarOrganico(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador)); 
            case 1: return(TratarPapelesCarton(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            case 2: return(TratarVidrios(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            case 3: return(TratarPlásticos(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            case 4: return(TratarChatarraMetal(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            case 5: return(TratarAceites(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            case 6: return(TratarPinturas(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            case 7: return(TratarBaterías(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            case 8: return(TratarPilas(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            case 9: return(TratarElectronicos(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            case 10: return(TratarMedicamentos(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            case 11: return(TratarQuimicosLaboratorio(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            case 12: return(TratarResiduosBiologicos(desechos[numJug].get(jugador.getNumDesechosTrat()),niv,jugador));
            
            default: return(0);
        }
    }
    
    //Compara lo que ingreso el usuario con la respuesta
    public static boolean compararRespuesta(ArrayList<String> list, String[] array) {
        if (list.size() != array.length)  {
            return false; // Diferente tamaño, no puede coincidir
        }
        for (int i = 0; i < array.length; i++) {
            if (!list.get(i).equalsIgnoreCase(array[i])) {
                return false; // Si algún elemento es diferente, no son iguales
            }
        }
        // Si todos los elementos son iguales, entonces son iguales
        return true; 
    }

    //Compara lo que ingreso el usuario con la respuesta (de un elemento especifico)
    public static boolean compararRespuesta(ArrayList<String> list, String[] array,int i){
        if (list.get(i).equalsIgnoreCase(array[i]))
            return true;
        return false;
    }

    public String respondeTratamiento(Jugador jugador){
        jugador.getCronometro().iniciarCronometro();
        System.out.print("\t\t\t\t\t(Tienes "+jugador.getCronometro().getTiempoRestante()+" segundos para contestar)\n");

        String respString=entrada.nextLine();

        jugador.getCronometro().detenerCronometro();
        return respString;
    } 

    //Maneras de tratar los desechos
    private int TratarOrganico(Desechos desecho, Nivel niv,Jugador jugador){ 
        System.out.println("Tipo de desecho: Orgánico\n");
        System.out.println("F: Uso final\nM: Mantenimento\nC: Compostaje");
        //respuesta
        String[] respuestaOrganico = {"M", "C", "F"};

        System.out.println();
        for(int i = 0; i < respuestaOrganico.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaOrganico, i))
                    switch(i){
                        case 0: mantenimiento();    break;
                        case 1: compostaje();       break;
                        case 2: usoFinal();         break;
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaOrganico)) {
            return(respCorrecta(jugador,niv));
        } else{
            return(respIncorrecto(jugador, niv));
        }
    }

    private int TratarPapelesCarton(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Papeles/Cartón\n");
        System.out.println("D: Desintegración\nC: Clasificación\nN: Nuevo producto\nR: Refinamiento de la pulpa");
        //respuesta
        String[] respuestaPapelesCartones = {"C","D", "R", "N"};

        System.out.println();
        for(int i = 0; i < respuestaPapelesCartones.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaPapelesCartones, i))
                    switch(i){
                        case 0: clasificacion();        break;
                        case 1: desintegracion();       break;
                        case 2: refinamientoPulpa();    break;
                        case 3: nuevoProducto();        break;
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }

        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPapelesCartones)) {
            return(respCorrecta(jugador,niv));
        } else{
            return(respIncorrecto(jugador, niv));
        }
    }  
    
    private int TratarVidrios(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Vidrios\n");
        System.out.println("C: Clasificación\nT: Trituración\nM: Modelado\nF: Fusión");
        //respuesta
        String[] respuestaVidrios = {"C", "T", "F", "M"};

        System.out.println();
        for(int i = 0; i < respuestaVidrios.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaVidrios, i))
                    switch(i){
                        case 0: clasificacion();    break;
                        case 1: triturar();         break;
                        case 2: fusion();           break;
                        case 3: modelado();         break;
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaVidrios)) {
            return(respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    private int TratarPlásticos(Desechos desecho, Nivel niv,Jugador jugador){ 
        System.out.println("Tipo de desecho: Plásticos\n");
        System.out.println("T: Trituración\nC: Clasificación \nK: Corte\nE: Extrusión\nF: Fusión");
        //respuesta
        String[] respuestaPlasticos = {"C", "T","F", "E", "K"};

        System.out.println();
        for(int i = 0; i < respuestaPlasticos.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaPlasticos, i))
                    switch(i){
                        case 0: clasificacion();    break;
                        case 1: triturar();         break;
                        case 2: fusion();           break;
                        case 3: extrusion();        break;
                        case 4: corte();            break;
                    }
            }     
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPlasticos)) {
            return (respCorrecta(jugador,niv));
        } else{
            return(respIncorrecto(jugador, niv));
        }
    }

    private int TratarChatarraMetal(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Chatarra/Metal\n");
        System.out.println("C: Clasificación\nF: Fusión\nT: Trituración\nR: Reducción de tamaño\nM: Modelado");
        //respuesta
        String[] respuestaChatarraMetal = {"C", "T", "F","R", "M"};

        System.out.println();
        for(int i = 0; i < respuestaChatarraMetal.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaChatarraMetal, i))
                    switch(i){
                        case 0: clasificacion();    break;
                        case 1: fusion();           break;
                        case 2: triturar();         break;
                        case 3: reducir();          break;
                        case 4: modelado();         break;                 
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaChatarraMetal)) {
            return(respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    private int TratarAceites(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Aceites\n");
        System.out.println("F: Filtración\nC: Clasificación\nD: Destilación\nN: Producto Nuevo\nR: Recuperación");
        //respuesta
        String[] respuestaAceites = {"C", "F", "D","R", "N"};

        System.out.println();
        for(int i = 0; i < respuestaAceites.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaAceites, i))
                    switch(i){
                        case 0: clasificacion();    break;
                        case 1: filtracion();       break;
                        case 2: destilacion();      break;
                        case 3: recuperarAceite();  break;
                        case 4: nuevoProducto();    break;
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaAceites)) {
            return(respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    private int TratarPinturas(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Pinturas\n");
        System.out.println("C: Clasificación\nN: Nuevo producto\nF: Filtración\nS: Separar componentes");
        //respuesta
        String[] respuestaPinturas = {"C", "F", "S","N"};

        System.out.println();
        for(int i = 0; i < respuestaPinturas.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaPinturas, i))
                    switch(i){
                        case 0: clasificacion();    break;
                        case 1: filtracion();       break;
                        case 2: separarComp();      break;
                        case 3: nuevoProducto();    break;
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPinturas)) {
            return (respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    private int TratarBaterías(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Baterías\n");
        System.out.println("S: Separar componentes\nC: Clasificación\nD: Desmontaje\nE: Eliminar residuos");
        //respuesta
        String[] respuestaBaterias = {"C","D","S","E"};

        System.out.println();
        for(int i = 0; i < respuestaBaterias.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaBaterias, i))
                    switch(i){
                        case 0: clasificacion();    break;
                        case 1: desmontaje();       break;
                        case 2: separarComp();      break;
                        case 3: eliminacion();      break;
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaBaterias)) {
            return(respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    private int TratarPilas(Desechos desecho,Nivel niv,Jugador jugador){ 
        System.out.println("Tipo de desecho: Pilas\n");
        System.out.println("N: Neutralización de electrocitos \nS: Separación \nE: Eliminar residuos \nC: Clasificación \nD: Desmontaje");
        //respuesta
        String[] respuestaPilas = {"C" , "D" , "S", "N", "E"};

        System.out.println();
        for(int i = 0; i < respuestaPilas.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaPilas, i))
                    switch(i){
                        case 0: clasificacion();    break;
                        case 1: desmontaje();       break;
                        case 2: separarComp();      break;
                        case 3: neutralizar();      break;
                        case 4: eliminacion();      break;
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPilas)) {
            return (respCorrecta(jugador,niv));
        } else{     
            return(respIncorrecto(jugador, niv));
        }
    }

    
    private int TratarElectronicos(Desechos desecho,Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Electrónicos\n");
        System.out.println("T: Trituración \nR: Recuperación de metales y plásticos \nD: Desmontaje \nS: Separación de materiales\nC: Clasificación\nB: Reciclaje de componentes");
        //respuesta
        String[] respuestaElectronicos = {"C" , "D" , "T" , "S" , "R", "B"} ;

        for(int i = 0; i < respuestaElectronicos.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaElectronicos, i))
                    switch(i){
                        case 0: clasificacion();    break;
                        case 1: desmontaje();       break;
                        case 2: triturar();         break;
                        case 3: separarComp();      break;
                        case 4: tomandoPlastMet();  break;
                        case 5: reciclandoComp();   break;
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaElectronicos)) {
            return (respCorrecta(jugador,niv));
        } else{     
            return(respIncorrecto(jugador, niv));
        }
    }

    private int TratarMedicamentos(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Medicamentos\n");
        System.out.println("S: Separación de envases \nC: Clasificación \nN: Neutralización \nT: Tratamiento de residuos líquidos \nF:Disposición final");
        //respuesta
        String[] respuestaMedicamentos = {"C", "S", "N","T", "F"};

        System.out.println();
        for(int i = 0; i < respuestaMedicamentos.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaMedicamentos, i))
                    switch(i){
                        case 0: clasificacion();            break;
                        case 1: separarEnvases();           break;
                        case 2: neutralizar();              break;
                        case 3: tratarResiduosLiquidos();   break;
                        case 4: enviar();                   break;
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaMedicamentos)) {
            return(respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }
    
    private int TratarQuimicosLaboratorio(Desechos desecho,Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Químicos de Laboratorio\n");
        System.out.println("S: Separación \nI: Incineración controlada \nF: Disposición final en instalaciones seguras \nA: Almacenamiento \nG: Recolección segura");
        //respuesta
        String[] respuestaTratarQuimicosPeligrosos = {"G" ,"A", "S" , "I" , "F" } ;

        for(int i = 0; i < respuestaTratarQuimicosPeligrosos.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaTratarQuimicosPeligrosos, i))
                    switch(i){
                        case 0: recolectarSeg();    break;
                        case 1: almacenar();        break;
                        case 2: separarComp();      break;
                        case 3: incinerarResiduos();break;
                        case 4: enviar();           break;
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaTratarQuimicosPeligrosos)) {
            return (respCorrecta(jugador,niv));
        } else{     
            return(respIncorrecto(jugador, niv));
        }
    }
    
    private int TratarResiduosBiologicos(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Residuo Biológico\n");
        System.out.println("D: Desinfección inicial \nE: Métodos de eliminación \nG: Recolección segura \nF: Disposición final en vertedero controlado");
        //respuesta
        String[] respuestaTejidoBiológico = {"G", "D", "E","F"};

        System.out.println();
        for(int i = 0; i < respuestaTejidoBiológico.length ; i++ ){
            System.out.print("\nIngresa la respuesta No "+(i+1)+": ");
            String respString=(respondeTratamiento(jugador));

            if(jugador.getCronometro().getTiempoRestante()>0){
                respuestaTratadora.add(respString);
                if (compararRespuesta(respuestaTratadora, respuestaTejidoBiológico, i))
                    switch(i){
                        case 0: recolectarSeg();    break;
                        case 1: desinfectar();      break;
                        case 2: eliminacion();      break;
                        case 3: enviar();           break;
                    }
            }
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaTejidoBiológico)) {
            return(respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    //Métodos de cada proceso
    //Orgánico
    private void mantenimiento(){
        System.out.println("Gestionando residuos...");
    }

    private void compostaje(){
        System.out.println("Comenzando proceso de compostaje...");
    }

    //Papeles/Carton
    private void desintegracion(){
        System.out.println("Desintegrando...");
    }

    private void refinamientoPulpa(){
        System.out.println("Refinando la pulpa...");
    }

    //Vidrios
    private void fusion(){
        System.out.println("Derritiendo...");
    }

    //Plásticos
    private void extrusion(){
        System.out.println("Pasando plástico fundido por molde...");
        System.out.println("Plástico enfriado...");
    }

    private void corte(){
        System.out.println("Cortando plástico...");
    }

    //Chatarra/Metal
    private void reducir(){
        System.out.println("Reduciendo tamaño...");
    }

    //Aceites
    private void destilacion(){
        System.out.println("Destilando...");
    }

    private void recuperarAceite(){
        System.out.println("Recuperando aceites...");
    }

    //Electrónicos
    private void desmontaje(){
        System.out.println("Separando con cuidado...");
    }

    private void tomandoPlastMet(){
        System.out.println("Tomando metales y plásticos...");
    }

    private void reciclandoComp(){
        System.out.println("Iniciando reciclaje de componenetes...");
    }

    //Medicinas
    private void separarEnvases(){
        System.out.println("Separando envases...");
    }

    private void tratarResiduosLiquidos(){
        System.out.println("Tratando con cuidado los residuos líquidos");
    }

    //Residuos Lab
    private void almacenar(){
        System.out.println("Resguardando...");
    }

    //Residuo Biológico
    private void desinfectar(){
        System.out.println("Desinfectando...");
    }

    private void eliminacion(){
        System.out.println("Aplicando métodos de eliminación de Patógenos/Contaminantes");
    }

    //Comunes
    private void clasificacion(){
        System.out.println("Clasificando tipos...");
    }

    private void usoFinal(){
        System.out.println("Listo para usarse...");
    }

    private void nuevoProducto(){
        System.out.println("Produciendo nuevo producto...");
    }

    private void triturar(){
        System.out.println("Triturando...");
    }

    private void modelado(){
        System.out.println("Modelando para nuevo uso...");
    }

    private void filtracion(){
        System.out.println("Filtrando...");
    }

    private void separarComp(){
        System.out.println("Separando componentes");
    }

    private void neutralizar(){
        System.out.println("Neutralizando químicamente...");
    }

    private void incinerarResiduos(){
        System.out.println("Enviando desechos a incineración controlada...");
    }

    private void recolectarSeg(){
        System.out.println("Recolectando con CUIDADO...");
    }

    private void enviar(){
        System.out.println("Enviando a instalaciones/vertederos especializados...");
    }

    //Muestra mensaje si no se clasifica correctamente
    public int respIncorrecto(Jugador jugador,Nivel niv){
        int bifurca=(int)(Math.random()*2);
        if (bifurca==0)
            System.out.println("Orden incorrecto, suerte a la próxima: -"+niv.getVidasRespIncorrecta()+" vidas.");
        else
            System.out.println("Ups..., ¡Que MAL! -"+niv.getVidasRespIncorrecta()+" vidas.");
        jugador.setNumDesechosTrat(jugador.getNumDesechosTrat()+1);
        jugador.perderVidas(niv);
        respuestaTratadora.clear();
        return (0);
    }

    //Muestra diferentes mensajes si se clasifica correctamente
    private int respCorrecta(Jugador jugador,Nivel niv){
        int bifurca=(int)(Math.random()*7);
        switch (bifurca) {
            case 0: System.out.println("La secuencia coincide. +"+niv.getPuntosRespCorrecta()+" pts.");         break;
            case 1: System.out.println("¡¡Que genial eres!! +"+niv.getPuntosRespCorrecta()+" pts.");            break;
            case 2: System.out.println("Es correcto, eres asombroso. +"+niv.getPuntosRespCorrecta()+" pts.");   break;
            case 3: System.out.println("Se te da muy bien. +"+niv.getPuntosRespCorrecta()+" pts.");             break;
            case 4: System.out.println("Es correcto, ganas "+niv.getPuntosRespCorrecta()+" puntos.");           break;
            case 5: System.out.println("Parece fácil, ¿No es así? +"+niv.getPuntosRespCorrecta()+" pts."); break;
            case 6: System.out.println("Well done +"+niv.getPuntosRespCorrecta()+" pts.");                      break;
        }

        jugador.ganarPuntos(niv);
        jugador.setNumDesechosTrat(jugador.getNumDesechosTrat()+1);
        respuestaTratadora.clear();
        return 1;
    }

    //Setters
    public void addDesecho(int numJug,Desechos desecho){
        desechos[numJug].add(desecho);
    }

    public void setArregloArrListDesechos(int numJug){
        desechos=new ArrayList[numJug];

        for (int i=0;i<numJug;i++)
            desechos[i]=new ArrayList<>();
    }

    //Getter
    public int getSizeArr_ArrayListDesecho(int numJug){
        return desechos[numJug].size();
    }    
}

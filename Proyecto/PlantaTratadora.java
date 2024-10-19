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

    //Maneras de tratar los desechos
    private int TratarOrganico(Desechos desecho, Nivel niv,Jugador jugador){ 
        System.out.println("Tipo de desecho: Orgánico\n");
        System.out.println("F: Uso final\nM: Mantenimento\nC: Compostaje");
        //respuesta
        String[] respuestaOrganico = {"M", "C", "F"};

        System.out.println();
        for(int i = 0; i < respuestaOrganico.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
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
        System.out.println("D: Desintegración\nC: Clasificación y limpieza\nP: Producción de nuevo producto\nR: Refinamiento de la pulpa");
        //respuesta
        String[] respuestaPapelesCartones = {"C", "D", "P", "R"};

        System.out.println();
        for(int i = 0; i < respuestaPapelesCartones.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
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
        System.out.println("C: Clasificación\nT: Trituración\nR: Recolección\nM: Modelado\nF: Fusión");
        //respuesta
        String[] respuestaVidrios = {"R", "C", "T", "F", "M"};

        System.out.println();
        for(int i = 0; i < respuestaVidrios.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
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
        System.out.println("T: Trituración\nC: Clasificación\nG: Recolección\nM: Modelado\nE: Extrusión");
        //respuesta
        String[] respuestaPlásticos = {"G", "C", "T", "E", "M"};

        System.out.println();
        for(int i = 0; i < respuestaPlásticos.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPlásticos)) {
            return (respCorrecta(jugador,niv));
        } else{
            return(respIncorrecto(jugador, niv));
        }
    }

    private int TratarChatarraMetal(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Chatarra/Metal\n");
        System.out.println("C: Clasificación\nR: Recolección\nF: Fusión\nT: Trituración y reducción de tamaño\nM: Modelado");
        //respuesta
        String[] respuestaChatarraMetal = {"R", "C", "F", "T", "M"};

        System.out.println();
        for(int i = 0; i < respuestaChatarraMetal.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
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
        System.out.println("F: Filtración y limpieza\nC: Clasificación\nD: Destilación\nN: Producto Nuevo\nG: Recolección\nR: Recuperación");
        //respuesta
        String[] respuestaAceites = {"G", "C", "F", "D","R", "N"};

        System.out.println();
        for(int i = 0; i < respuestaAceites.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
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
        System.out.println("L: Pretratamiento y limpieza\nC: Clasificación\nN: Fabricación de nuevo producto\nR: Reciclaje de pinturas base de agua/solventes");
        //respuesta
        String[] respuestaPinturas = {"C", "L", "R","N"};

        System.out.println();
        for(int i = 0; i < respuestaPinturas.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
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
        System.out.println("T: Tratamiento y procesamiento\nC: Clasificación\nD: Desmontaje\nG: Recolección\nR: Refinación");
        //respuesta
        String[] respuestaBaterías = {"G","C","D","T","R"};

        System.out.println();
        for(int i = 0; i < respuestaBaterías.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
            else{
                respuestaTratadora.clear();
                return (0);
            }
        }
        System.out.println();
        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaBaterías)) {
            return(respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    private int TratarPilas(Desechos desecho,Nivel niv,Jugador jugador){ 
        System.out.println("Tipo de desecho: Pilas\n");
        System.out.println("N: Neutralizacion de electrocitos \nS: Separación \nT: Disposición de residuo no deseables \nC: Clasificación \nG: Recolección \nD: Desmontaje");
        //respuesta
        String[] respuestaPilas = {"G" , "C" , "D" , "S", "N", "T"};

        System.out.println();
        for(int i = 0; i < respuestaPilas.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
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
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
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
        System.out.println("S: Separación de envases \nC: Clasificación \nN: Neutralización \nG: Recolección de medicamentos \nT: Tratamiento de residuos líquidos \nF:Disposición final");
        //respuesta
        String[] respuestaMedicamentos = {"G", "C", "S", "N","T", "F"};

        System.out.println();
        for(int i = 0; i < respuestaMedicamentos.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
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
        System.out.println("S: Separación \nI: Incineración controlada \nF: Disposición final de instalaciones seguras \nA: Almacenamiento \nG: Recolección segura");
        //respuesta
        String[] respuestaTratarQuimicosPeligrosos = {"G" ,"A", "S" , "I" , "F" } ;

        for(int i = 0; i < respuestaTratarQuimicosPeligrosos.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
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
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(respString);
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

    public void addDesecho(int numJug,Desechos desecho){
        desechos[numJug].add(desecho);
    }

    public int getSizeArr_ArrayListDesecho(int numJug){
        return desechos[numJug].size();
    }

    public void setArregloArrListDesechos(int numJug){
        desechos=new ArrayList[numJug];

        for (int i=0;i<numJug;i++)
            desechos[i]=new ArrayList<>();
    }
}

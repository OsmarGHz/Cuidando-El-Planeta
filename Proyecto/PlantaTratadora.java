package Proyecto;

import java.util.ArrayList;
import java.util.Scanner;

public class PlantaTratadora {
    private Scanner entrada = new Scanner(System.in);
    private static ArrayList<Desechos>[] desechos;
    private  static ArrayList<Integer> respuestaTratadora= new ArrayList<>();

    public PlantaTratadora(int numJug){
        desechos=(ArrayList<Desechos>[])new ArrayList[numJug];
        for (int i=0;i<numJug;i++)
            desechos[i]=new ArrayList<>();
    }

    public int identificarDesecho(Nivel niv,Jugador jugador,int numJug){
        System.out.println("Nombre del desecho: " +desechos[numJug].get(jugador.getNumDesechosTrat()).getNombreDesecho());
        System.out.println("\n**Ingresa los números en el orden correcto para tratar los desechos**\n");

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
            case 9: return(TratarElectronicos(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            case 10: return(TratarMedicamentos(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            case 11: return(TratarQuimicosLaboratorio(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            case 12: return(TratarTejidoBiológico(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            
            default: return(0);
        }
    }
    
    //Compara lo que ingreso el usuario con la respuesta
    public static boolean compararRespuesta(ArrayList<Integer> list, int[] array) {
        if (list.size() != array.length)  {
            return false; // Diferente tamaño, no puede coincidir
        }
        for (int i = 0; i < array.length; i++) {
            if (!list.get(i).equals(array[i])) {
                return false; // Si algún elemento es diferente, no son iguales
            }
        }
        // Si todos los elementos son iguales, entonces son iguales
        return true; 
    }

    //Maneras de tratar los desechos
    private int TratarOrganico(Desechos desecho, Nivel niv,Jugador jugador){ 
        System.out.println("Tipo de desecho: Orgánico\n");
        System.out.println("1: Uso final\n2: Mantenimento\n3: Compostaje");
        //respuesta
        int[] respuestaOrganico = {2, 3, 1};

        System.out.println();
        for(int i = 0; i < respuestaOrganico.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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
        System.out.println("1: Desintegración\n2: Clasificación y limpieza\n3: Producción de nuevo producto\n4: Refinamiento de la pulpa");
        //respuesta
        int[] respuestaPapelesCartones = {2, 1, 4, 3};

        System.out.println();
        for(int i = 0; i < respuestaPapelesCartones.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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
        System.out.println("1: Clasificación\n2: Trituración\n3: Recolección\n4: Modelado\n5: Fusión");
        //respuesta
        int[] respuestaVidrios = {3, 1, 2, 5, 4};

        System.out.println();
        for(int i = 0; i < respuestaVidrios.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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
        System.out.println("1: Trituración\n2: Clasificación\n3: Recolección\n4: Modelado\n5: Extrusión");
        //respuesta
        int[] respuestaPlásticos = {3, 2, 1, 5, 4};

        System.out.println();
        for(int i = 0; i < respuestaPlásticos.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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
        System.out.println("1: Clasificación\n2: Fusión\n3: Recolección\n4: Modelado\n5: Trituración y reducción de tamaño");
        //respuesta
        int[] respuestaChatarraMetal = {3, 1, 2, 5, 4};

        System.out.println();
        for(int i = 0; i < respuestaChatarraMetal.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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
        System.out.println("1: Filtración y limpieza\n2: Clasificación\n3: Destilación\n4: Producto Nuevo\n5: Recolección\n6: Recuperación");
        //respuesta
        int[] respuestaAceites = {5, 2, 1, 3,6, 4};

        System.out.println();
        for(int i = 0; i < respuestaAceites.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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
        System.out.println("1: Clasificación\n2: Pretratamiento y limpieza\n3: Reciclaje de pinturas base de agua/solventes\n4: Fabricación nuevo producto");
        //respuesta
        int[] respuestaPinturas = {1, 2, 3,4};

        System.out.println();
        for(int i = 0; i < respuestaPinturas.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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
        System.out.println("1: Recolección\n2: Clasificación\n3: Desmontaje\n4: Tratamiento y procesamiento\n5: Refinación");
        //respuesta
        int[] respuestaBaterías = {1,2,3,4,5};

        System.out.println();
        for(int i = 0; i < respuestaBaterías.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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

    private int TratarPilas(Desechos desecho,Nivel niv,Jugador jugador){ //Pilas checar
        System.out.println("Tipo de desecho: Pilas\n");
        System.out.println("1: Uso final\n2: Mantenimento\n3: Compostaje");
        //respuesta
        int[] respuestaPilas = {2, 3, 1};

        System.out.println();
        for(int i = 0; i < respuestaPilas.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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
        System.out.println("1: Recoleccion y clasificación.\n2: Desmontaje\n3: Trituración.\n4: Separacioón de materiales.\n5: Recuperación de metales y plásticos.\n6: Reciclaje de componentes.");
        //respuesta
        int[] respuestaElectronicos = {1 , 2 , 3 , 4 , 5 , 6} ;

        for(int i = 0; i < respuestaElectronicos.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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

    private int TratarMedicamentos(Desechos desecho, Nivel niv,Jugador jugador){ //Checar procesos
        System.out.println("Tipo de desecho: Medicamentos\n");
        System.out.println("1: Separación de envases.\n2: Clasificación.\n3: Neutralización.\n4: Gestión de residuos tóxicos \n5: Recolección de medicamentos.\n6: Tratamiento de residuos liquidos.\n7:Disposición final");
        //respuesta
        int[] respuestaMedicamentos = {5, 2, 1, 3,6, 4, 7};

        System.out.println();
        for(int i = 0; i < respuestaMedicamentos.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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
        System.out.println("1: Recolección segura y almacenamiento.\n2: Separación\n3: Procesos de incineración controlada.\n4: Disposición final de instalaciones seguras.");
        //respuesta
        int[] respuestaTratarQuimicosPeligrosos = {1 , 2 , 3 , 4 } ;

        for(int i = 0; i < respuestaTratarQuimicosPeligrosos.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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
    
    private int TratarTejidoBiológico(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Residuo Biológico\n");
        System.out.println("1: Desinfección inicial.\n2: Clasificación de residuos biológicos.\n3: Metodos de eliminación.\n4: Disposición final \n5: Recolección segura.\n6: Tratamiento de residuos líquidos.");
        //respuesta
        int[] respuestaTejidoBiológico = {5, 2, 1, 3,6, 4};

        System.out.println();
        for(int i = 0; i < respuestaTejidoBiológico.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            String respString=CronometroConJOptionPane.mostrarDialogoConCronometro(jugador.getSegundosRest());
            jugador.setSegundosRest(CronometroConJOptionPane.getTiempoRest());

            if(respString!=null)
                respuestaTratadora.add(Integer.parseInt(respString));
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
        System.out.println("Orden incorrecto, suerte a la próxima: -"+niv.getVidasRespIncorrecta()+" vidas.");
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
}

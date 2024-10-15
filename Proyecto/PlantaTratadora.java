package Proyecto;

import java.util.ArrayList;
import java.util.Scanner;

import Actividad9.ArrayString;

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
        System.out.println("Nombre del desecho: " +desechos[numJug].get(desechos[numJug].size()-1).getNombreDesecho());
        System.out.println("\nIngresa los numeros en el orden correcto para tratar los desechos\n");

        switch (desechos[numJug].get(jugador.getNumDesechosTrat()).getNumTipoDesecho()){
            case 0: return(TratarOrganico(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador)); 
            case 1: return(TratarPapelesCarton(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            case 2: return(TratarVidrios(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            case 3: return(TratarPlásticos(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            case 4: return(TratarChatarraMetal(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            case 5: return(TratarAceites(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            case 6: return(TratarPinturas(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            case 7: return(TratarBaterías(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            case 8: return(TratarPilas(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
                //coloque los dos restantes
            case 9: return(TratarElectronicos(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            case 10: return(TratarQuimicosPeligrosos(desechos[numJug].get(desechos[numJug].size()-1),niv,jugador));
            
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

    private int TratarOrganico(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Orgánico\n");
       System.out.println("1.Mantenimento\n2.Compostaje\n3.Uso final");
        //respuesta en orden
        int[] respuestaOrganico = {1 , 2 , 3};

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

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaOrganico)) {
            System.out.println("Es correcto, well done +"+niv.getPuntosRespCorrecta()+" pts.");
            return(respCorrecta(jugador,niv));
        } else{
            return(respIncorrecto(jugador, niv));
        }
    }

    private int TratarPapelesCarton(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Papeles/Cartón\n");
        System.out.println("1.Clasificacion y limpieza.\n2.Desintegración.\n3.Refinamiento de la pulpa.\n4.Produccion de nuevo producto.");
        //respuesta en orden
        int[] respuestaPapelesCartones = {1 , 2 , 3 , 4 };

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

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPapelesCartones)) {
            System.out.println("Es correcto, parece facil, ¿No es así?");
            jugador.ganarPuntos(niv);
            return(respCorrecta(jugador,niv));
        } else{
            return(respIncorrecto(jugador, niv));
        }
    }  
    
    private int TratarVidrios(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Vidrios\n");
        System.out.println("1.Recolección.\n2.Clasificación.\n3.Trituracion.\n4.Fusión.\n5.Modelado.");
        //respuesta oredenada
        int[] respuestaVidrios = {1 , 2 , 3 , 4 , 5};

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

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaVidrios)) {
            System.out.println("Es correcto, ganas "+niv.getPuntosRespCorrecta()+" puntos.");
            jugador.ganarPuntos(niv);
            return(respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    private int TratarPlásticos(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Plásticos\n");
        System.out.println("1.Recolección.\n2.Clasificación.\n3.Trituracion.\n4.Extrusión.\n5.Modelado.");
        //respuesta ordenada
        int[] respuestaPlásticos = {1 , 2 , 3 , 4 , 5};

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

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPlásticos)) {
            System.out.println("Es correcto, se te da muy bien. +"+niv.getPuntosRespCorrecta()+" pts.");
            jugador.ganarPuntos(niv);
            return (respCorrecta(jugador,niv));
        } else{
            return(respIncorrecto(jugador, niv));
        }
    }

    private int TratarChatarraMetal(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Chatarra/Metal\n");
         System.out.println("1.Recolección.\n2.Clasificación.\n3.Fusión\n4.Trituracion y reduccion de tamaño.\n5.Modelado.");
        //respuesta ordenada
        int[] respuestaChatarraMetal = {1 , 2 , 3 , 4 , 5 };

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

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaChatarraMetal)) {
            System.out.println("Es correcto, eres asombroso. +"+niv.getPuntosRespCorrecta()+" pts.");
            jugador.ganarPuntos(niv);
            return(respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    private int TratarAceites(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Aceites\n");
        System.out.println("1.Recolección.\n2.Clasificación\n3.Filtracion y limpieza.\n4.Destilación.\n5.Recuperación\n6.Producto Nuevo.");
        //respuesta ordenada
        int[] respuestaAceites = {1 , 2 , 3 , 4 , 5 , 6};

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

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaAceites)) {
            System.out.println("Es correcto, que genial eres. +"+niv.getPuntosRespCorrecta()+" pts.");
            jugador.ganarPuntos(niv);
            return(respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    private int TratarPinturas(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Pinturas\n");
        System.out.println("1.Recolección\n2.Clasificacion\n3.Desmontaje\n4.Tratamiento y procesamiento.\n5.Refinacion.");
        //respuesta ordenada
        int[] respuestaBaterías = {1, 2 , 3 , 4 , 5};
        
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

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPinturas)) {
            System.out.println("La primera secuencia coincide. +"+niv.getPuntosRespCorrecta()+" pts.");
            jugador.ganarPuntos(niv);
            return (respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    private int TratarBaterías(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Baterías\n");
        System.out.println("1.Recolección\n2.Clasificacion\n3.Desmontaje\n4.Tratamiento y procesamiento.\n5.Refinacion.");
        //respuesta
        int[] respuestaBaterías = {1,2,3,4,5};

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

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaBaterías)) {
            System.out.println("La primera secuencia coincide. +"+niv.getPuntosRespCorrecta()+" pts.");
            jugador.ganarPuntos(niv);
            return(respCorrecta(jugador,niv));
        } else{
            return (respIncorrecto(jugador, niv));
        }
    }

    private int TratarPilas(Desechos desecho,Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Pilas\n");
        System.out.println("1.Recoleccion y clasificación.\n2.Desmontaje y separación.\n3.Neutralizacion de electrocitos.\n4 Disposición de residuo no deseables");
        //respuesta
        int[] respuestaPilas = {1 , 2 , 3 , 4};
        
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

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPilas)) {
            System.out.println("La primera secuencia coincide. +"+niv.getPuntosRespCorrecta()+" pts.");
            jugador.ganarPuntos(niv);
            return (respCorrecta(jugador,niv));
        } else{     
            return(respIncorrecto(jugador, niv));
        }
    }

    //Clases restantes
     private int TratarElectronicos(Desechos desecho,Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Pilas\n");
        System.out.println("1.Recoleccion y clasificación.\n2.Desmontaje\n3.Trituración.\n4.Separacioón de materiales.\n5.Recuperacion de metales y plasticos.\n6.Reciclaje de componentes.");
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

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaElectronicos)) {
            System.out.println("La primera secuencia coincide. +"+niv.getPuntosRespCorrecta()+" pts.");
            jugador.ganarPuntos(niv);
            return (respCorrecta(jugador,niv));
        } else{     
            return(respIncorrecto(jugador, niv));
        }
    }

    private int TratarQuimicosPeligrosos(Desechos desecho,Nivel niv,Jugador jugador){
        System.out.println("Tipo de desecho: Pilas\n");
        System.out.println("1.Recoleccion segura y almacenamiento.\n2.Desmontaje especializado\n3.Extraccion de gases refrigerantes.\n4.Tratamiento de mercurio.\n5.Procesos de incineración controlada.\n6.Disposición final de instalaciones seguras.");
        //respuesta
        int[] respuestaTratarQuimicosPeligrosos = {1 , 2 , 3 , 4 , 5 , 6} ;

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


    //Muestra mensaje si no se clasifica correctamente
    public int respIncorrecto(Jugador jugador,Nivel niv){
        System.out.println("Orden incorrecto, suerte a la próxima: -"+niv.getVidasRespIncorrecta()+" vidas.");
        jugador.setNumDesechosTrat(jugador.getNumDesechosTrat()+1);
        jugador.perderVidas(niv);
        respuestaTratadora.clear();
        return (0);
    }

    public void addDesecho(int numJug,Desechos desecho){
        desechos[numJug].add(desecho);
    }

    private int respCorrecta(Jugador jugador,Nivel niv){
        jugador.ganarPuntos(niv);
        jugador.setNumDesechosTrat(jugador.getNumDesechosTrat()+1);
        respuestaTratadora.clear();
        return 1;
    }

    public int getSizeArr_ArrayListDesecho(int numJug){
        return desechos[numJug].size();
    }
}

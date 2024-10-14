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
        System.out.println("1.Uso final\n2.Mantenimento\n3.Compostaje");
        //respuesta
        int[] respuestaOrganico = {2, 3, 1};

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
        System.out.println("1.Desintegración.\n2.Clasificacion y limpieza.\n3.Produccion de nuevo producto.\n4.Refinamiento de la pulpa.");
        //respuesta
        int[] respuestaPapelesCartones = {2, 1, 4, 3};

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
        System.out.println("1.Clasificación\n2.Trituracion\n3.Recolección\n4.Modelado\n5.Fusión");
        //respuesta
        int[] respuestaVidrios = {3, 1, 2, 5, 4};

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
        System.out.println("1.Trituracion\n2.Clasificación\n3.Recolección\n4.Modelado\n5.Extrusión");
        //respuesta
        int[] respuestaPlásticos = {3, 2, 1, 5, 4};

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
        System.out.println("1.Clasificación\n2.Fusión\n3.Recolección\n4.Modelado\n5.Trituracion y reduccion de tamaño");
        //respuesta
        int[] respuestaChatarraMetal = {3, 1, 2, 5, 4};

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
        System.out.println("1.Filtracion y limpieza\n2.Clasificación\n3.Destilación\n4.Producto Nuevo\n5.Recolección\n6.Recuperación");
        //respuesta
        int[] respuestaAceites = {5, 2, 1, 3,6, 4};

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
        System.out.println("1.Clasificación.\n2.Pretratamiento y limpieza.\n3.Reciclaje de pinturas base de agua/solventes.\n4.Fabricación nuevo producto.");
        //respuesta
        int[] respuestaPinturas = {1, 2, 3,4};

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
        System.out.println("1.Uso final\n2.Mantenimento\n3.Compostaje");
        //respuesta
        int[] respuestaPilas = {2, 3, 1};

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

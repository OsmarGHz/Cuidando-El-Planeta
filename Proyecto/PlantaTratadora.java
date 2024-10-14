package Proyecto;

import java.util.ArrayList;
import java.util.Scanner;

public class PlantaTratadora {
    private Scanner entrada = new Scanner(System.in);
    private static ArrayList<Desechos> desechos;
    private  static ArrayList<Integer> respuestaTratadora= new ArrayList<>();

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

    private void TratarOrganico(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("1.Uso final\n2.Mantenimento\n3.Compostaje");
        //respuesta
        int[] respuestaOrganico = {2, 3, 1};

        for(int i = 0; i < respuestaOrganico.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            respuestaTratadora.add(entrada.nextInt());
        }

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaOrganico)) {
            System.out.println("Es correcto, well done");
            jugador.ganarPuntos(niv);
        } else{
            mensajeIncorrecto(jugador, niv);
        }

        respuestaTratadora.clear();
    }

    private void TratarPapelesCarton(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("1.Desintegración.\n2.Clasificacion y limpieza.\n3.Produccion de nuevo producto.\n4.Refinamiento de la pulpa.");
        //respuesta
        int[] respuestaPapelesCartones = {2, 1, 4, 3};

        for(int i = 0; i < respuestaPapelesCartones.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            respuestaTratadora.add(entrada.nextInt());
        }

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPapelesCartones)) {
            System.out.println("Es correcto, parece facil, ¿No es así?");
            jugador.ganarPuntos(niv);
        } else{
            mensajeIncorrecto(jugador, niv);
        }

        respuestaTratadora.clear(); 
    }  
    
    private void TratarVidrios(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("1.Clasificación\n2.Trituracion\n3.Recolección\n4.Modelado\n5.Fusión");
        //respuesta
        int[] respuestaVidrios = {3, 1, 2, 5, 4};

        for(int i = 0; i < respuestaVidrios.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            respuestaTratadora.add(entrada.nextInt());  
        }

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaVidrios)) {
            System.out.println("Es correcto, ganas puntos");
            jugador.ganarPuntos(niv);
        } else{
            mensajeIncorrecto(jugador, niv);
        }

        respuestaTratadora.clear();
    }

    private void TratarPlásticos(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("1.Trituracion\n2.Clasificación\n3.Recolección\n4.Modelado\n5.Extrusión");
        //respuesta
        int[] respuestaPlásticos = {3, 2, 1, 5, 4};

        for(int i = 0; i < respuestaPlásticos.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            respuestaTratadora.add(entrada.nextInt());
        }

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPlásticos)) {
            System.out.println("Es correcto, se te da muy bien.");
            jugador.ganarPuntos(niv);
        } else{
            mensajeIncorrecto(jugador, niv);
        }

        respuestaTratadora.clear();
    }

    private void TratarChatarraMetal(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("1.Clasificación\n2.Fusión\n3.Recolección\n4.Modelado\n5.Trituracion y reduccion de tamaño");
        //respuesta
        int[] respuestaChatarraMetal = {3, 1, 2, 5, 4};

        for(int i = 0; i < respuestaChatarraMetal.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            respuestaTratadora.add(entrada.nextInt());
        }

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaChatarraMetal)) {
            System.out.println("Es correcto, eres asombroso.");
            jugador.ganarPuntos(niv);
        } else{
            mensajeIncorrecto(jugador, niv);
        }

        respuestaTratadora.clear();
    }

    private void TratarAceites(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("1.Filtracion y limpieza\n2.Clasificación\n3.Destilación\n4.Producto Nuevo\n5.Recolección\n6.Recuperación");
        //respuesta
        int[] respuestaAceites = {5, 2, 1, 3,6, 4};

        for(int i = 0; i < respuestaAceites.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            respuestaTratadora.add(entrada.nextInt());
        }

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaAceites)) {
            System.out.println("Es correcto, que genial eres.");
            jugador.ganarPuntos(niv);
        } else{
            mensajeIncorrecto(jugador, niv);
        }

        respuestaTratadora.clear();
    }

    private void TratarPinturas(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("1.Clasificación.\n2.Pretratamiento y limpieza.\n3.Reciclaje de pinturas base de agua/solventes.\n4.Fabricación nuevo producto.");
        //respuesta
        int[] respuestaPinturas = {1, 2, 3,4};

        for(int i = 0; i < respuestaPinturas.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            respuestaTratadora.add(entrada.nextInt());
        }

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPinturas)) {
            System.out.println("La primera secuencia coincide.");
            jugador.ganarPuntos(niv);
        } else{
            mensajeIncorrecto(jugador, niv);
        }

        respuestaTratadora.clear();
    }

    private void TratarBaterías(Desechos desecho, Nivel niv,Jugador jugador){
        System.out.println("1.recolección\n2.Clasificacion\n3.Desmontaje\n4.Tratamiento y procesamiento.\5.Refinacion.");
        //respuesta
        int[] respuestaBaterías = {1,2,3,4,5};

        for(int i = 0; i < respuestaBaterías.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            respuestaTratadora.add(entrada.nextInt());
        }

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaBaterías)) {
            System.out.println("La primera secuencia coincide.");
            jugador.ganarPuntos(niv);
        } else{
            mensajeIncorrecto(jugador, niv);
        }

        respuestaTratadora.clear();
    }

    private void TratarPilas(Desechos desecho,Nivel niv,Jugador jugador){
        System.out.println("1.Uso final\n2.Mantenimento\n3.Compostaje");
        //respuesta
        int[] respuestaPilas = {2, 3, 1};

        for(int i = 0; i < respuestaPilas.length ; i++ ){
            System.out.println("Ingresa la respuesta No "+(i+1)+": ");
            respuestaTratadora.add(entrada.nextInt());
        }

        if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPilas)) {
            System.out.println("La primera secuencia coincide.");
            jugador.ganarPuntos(niv);
        } else{
            
            mensajeIncorrecto(jugador, niv);
        }

        respuestaTratadora.clear();
    }

    public void identificarDesecho(Contenedor contenedor,Nivel niv,Jugador jugador){
        System.out.println("\nTipo del Desecho " + contenedor.getTipoDesecho());
        System.out.println("Nombre del desecho: " +desechos.get(desechos.size()-1).getNombreDesecho());
        System.out.println("\nIngresa los numeros en el orden correcto para tratar los desechos");

        switch (contenedor.getTipoDesecho()){
            case "Organico": TratarOrganico(desechos.get(desechos.size()-1),niv,jugador); break;
            case "Papeles/Cartones": TratarPapelesCarton(desechos.get(desechos.size()-1),niv,jugador); break;
            case "Vidrios": TratarVidrios(desechos.get(desechos.size()-1),niv,jugador); break;
            case "Plásticos": TratarPlásticos(desechos.get(desechos.size()-1),niv,jugador); break;
            case "Chatarra/Metal": TratarChatarraMetal(desechos.get(desechos.size()-1),niv,jugador); break;
            case "Aceites": TratarAceites(desechos.get(desechos.size()-1),niv,jugador); break;
            case "Pinturas": TratarPinturas(desechos.get(desechos.size()-1),niv,jugador); break;
            case "Baterías": TratarBaterías(desechos.get(desechos.size()-1),niv,jugador); break;
            case "Pilas": TratarPilas(desechos.get(desechos.size()-1),niv,jugador); break;
        }
    }

    public void mensajeIncorrecto(Jugador jugador,Nivel niv){
        System.out.println("Orden incorrecto, suerte a la próxima");
        jugador.perderVidas(niv);
    }

    public static void setDesechosArrayList(){
        desechos=Contenedor.getDesechosGuardados();
    }
}

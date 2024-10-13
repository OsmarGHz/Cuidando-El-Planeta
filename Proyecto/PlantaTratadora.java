package Proyecto;

import java.util.ArrayList;
import java.util.Scanner;



public class PlantaTratadora {
    private  static ArrayList<Integer> respuestaTratadora= new ArrayList<>();
    Scanner entrada = new Scanner(System.in);
    ArrayList<Desechos> desechos = Contenedor.getdesechosGuardados();

    //Compara lo que ingreso el usuario con la respuesta
    public static boolean compararRespuesta(ArrayList<Integer> list, int[] array) {
        if (respuestaTratadora.size() != respuestaTratadora.size()) {
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



    public void TratarOrganico(){
        if (Contenedor.getTipoDesecho() == "Organicos") {
            System.out.println("Tipo del Desecho" + Contenedor.getTipoDesecho());
            System.out.println("Nombre del desecho:" );

            int contador = 1;
            for(Desechos desechos: desechos){
                System.out.println(contador + ". " +Desechos.getNombreDesecho());
                contador ++;
            }
            
            System.out.println("Ingresa los numeros en el orden correcto para tratar los desechos");
            System.out.println("1.Uso final\n2.Mantenimento\n3.Compostaje");
            //respuesta
            int[] respuestaOrganico = {2, 3, 1};

            for(int i = 0; i < respuestaOrganico.length ; i++ ){
                respuestaTratadora.add(entrada.nextInt());
                
            }

            if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaOrganico)) {
                System.out.println("Es correcto, well done");
                Jugador.ganarPuntos(niv);
            } else{
                Jugador.perderVidas(niv);
            }

            respuestaTratadora.clear();
            
        }
    }

    public void TratarPapelesCarton(){
        if (Contenedor.getTipoDesecho() == "Papeles/Cartones") {
            System.out.println("Tipo del Desecho" + Contenedor.getTipoDesecho());
            System.out.println("Nombre del desecho:" );

            int contador = 1;
            for(Desechos desechos: desechos){
                System.out.println(contador + ". " +Desechos.getNombreDesecho());
                contador ++;
            }
            
            System.out.println("Ingresa los numeros en el orden correcto para tratar los desechos");
            System.out.println("1.Desintegración.\n2.Clasificacion y limpieza.\n3.Produccion de nuevo producto.\n4.Refinamiento de la pulpa.");
            //respuesta
            int[] respuestaPapelesCartones = {2, 1, 4, 3};

            for(int i = 0; i < respuestaPapelesCartones.length ; i++ ){
                respuestaTratadora.add(entrada.nextInt());
                
            }

            if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPapelesCartones)) {
                System.out.println("Es correcto, parece facil, ¿No es así?");
                Jugador.ganarPuntos(niv);
            } else{
                Jugador.perderVidas(niv);
            }

            respuestaTratadora.clear();
            
        }  
    }  
    
    public void TratarVidrios(){
        if (Contenedor.getTipoDesecho() == "Vidrios") {
            System.out.println("Tipo del Desecho" + Contenedor.getTipoDesecho());
            System.out.println("Nombre del desecho:" );

            int contador = 1;
            for(Desechos desechos: desechos){
                System.out.println(contador + ". " +Desechos.getNombreDesecho());
                contador ++;
            }
            
            System.out.println("Ingresa los numeros en el orden correcto para tratar los desechos");
            System.out.println("1.Clasificación\n2.Trituracion\n3.Recolección\n4.Modelado\n5.Fusión");
            //respuesta
            int[] respuestaVidrios = {3, 1, 2, 5, 4};

            for(int i = 0; i < respuestaVidrios.length ; i++ ){
                respuestaTratadora.add(entrada.nextInt());
                
            }

            if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaVidrios)) {
                System.out.println("Es correcto, ganas puntos");
                Jugador.ganarPuntos(niv);
            } else{
                Jugador.perderVidas(niv);
            }

            respuestaTratadora.clear();
            
        }
    }

    public void TratarPlásticos(){
        if (Contenedor.getTipoDesecho() == "Plásticos") {
            System.out.println("Tipo del Desecho" + Contenedor.getTipoDesecho());
            System.out.println("Nombre del desecho:" );

            int contador = 1;
            for(Desechos desechos: desechos){
                System.out.println(contador + ". " +Desechos.getNombreDesecho());
                contador ++;
            }
            
            System.out.println("Ingresa los numeros en el orden correcto para tratar los desechos");
            System.out.println("1.Trituracion\n2.Clasificación\n3.Recolección\n4.Modelado\n5.Extrusión");
            //respuesta
            int[] respuestaPlásticos = {3, 2, 1, 5, 4};

            for(int i = 0; i < respuestaPlásticos.length ; i++ ){
                respuestaTratadora.add(entrada.nextInt());
                
            }

            if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPlásticos)) {
                System.out.println("Es correcto, se te da muy bien.");
                Jugador.ganarPuntos(niv);
            } else{
                Jugador.perderVidas(niv);
            }

            respuestaTratadora.clear();
            
        }
    }

    public void TratarChatarraMetal(){
        if (Contenedor.getTipoDesecho() == "Chatarra/Metal") {
            System.out.println("Tipo del Desecho" + Contenedor.getTipoDesecho());
            System.out.println("Nombre del desecho:" );

            int contador = 1;
            for(Desechos desechos: desechos){
                System.out.println(contador + ". " +Desechos.getNombreDesecho());
                contador ++;
            }
            
            System.out.println("Ingresa los numeros en el orden correcto para tratar los desechos");
            System.out.println("1.Clasificación\n2.Fusión\n3.Recolección\n4.Modelado\n5.Trituracion y reduccion de tamaño");
            //respuesta
            int[] respuestaChatarraMetal = {3, 1, 2, 5, 4};

            for(int i = 0; i < respuestaChatarraMetal.length ; i++ ){
                respuestaTratadora.add(entrada.nextInt());
                
            }

            if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaChatarraMetal)) {
                System.out.println("Es correcto, eres asombroso.");
                Jugador.ganarPuntos(niv);
            } else{
                Jugador.perderVidas(niv);
            }

            respuestaTratadora.clear();
            
        }
    }

    public void TratarAceites(){
        if (Contenedor.getTipoDesecho() == "Aceites") {
            System.out.println("Tipo del Desecho" + Contenedor.getTipoDesecho());
            System.out.println("Nombre del desecho:" );

            int contador = 1;
            for(Desechos desechos: desechos){
                System.out.println(contador + ". " +Desechos.getNombreDesecho());
                contador ++;
            }
            
            System.out.println("Ingresa los numeros en el orden correcto para tratar los desechos");
            System.out.println("1.Filtracion y limpieza\n2.Clasificación\n3.Destilación\n4.Producto Nuevo\n5.Recolección\n6.Recuperación");
            //respuesta
            int[] respuestaAceites = {5, 2, 1, 3,6, 4};

            for(int i = 0; i < respuestaAceites.length ; i++ ){
                respuestaTratadora.add(entrada.nextInt());
                
            }

            if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaAceites)) {
                System.out.println("Es correcto, que genial eres.");
                Jugador.ganarPuntos(niv);
            } else{
                Jugador.perderVidas(niv);
            }

            respuestaTratadora.clear();
            
        }
    }

    public void TratarPinturas(){
        if (Contenedor.getTipoDesecho() == "Pinturas") {
            System.out.println("Tipo del Desecho" + Contenedor.getTipoDesecho());
            System.out.println("Nombre del desecho:" );

            int contador = 1;
            for(Desechos desechos: desechos){
                System.out.println(contador + ". " +Desechos.getNombreDesecho());
                contador ++;
            }
            
            System.out.println("Ingresa los numeros en el orden correcto para tratar los desechos");
            System.out.println("1.Clasificación.\n2.Pretratamiento y limpieza.\n3.Reciclaje de pinturas base de agua/solventes.\n4.Fabricación nuevo producto.");
            //respuesta
            int[] respuestaPinturas = {1, 2, 3,4};

            for(int i = 0; i < respuestaPinturas.length ; i++ ){
                respuestaTratadora.add(entrada.nextInt());
                
            }

            if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPinturas)) {
                System.out.println("La primera secuencia coincide.");
                Jugador.ganarPuntos(niv);
            } else{
                Jugador.perderVidas(niv);
            }

            respuestaTratadora.clear();
            
        }
    }

    public void TratarBaterías(){
        if (Contenedor.getTipoDesecho() == "Baterías") {
            System.out.println("Tipo del Desecho" + Contenedor.getTipoDesecho());
            System.out.println("Nombre del desecho:" );

            int contador = 1;
            for(Desechos desechos: desechos){
                System.out.println(contador + ". " +Desechos.getNombreDesecho());
                contador ++;
            }
            
            System.out.println("Ingresa los numeros en el orden correcto para tratar los desechos");
            System.out.println("1.recolección\n2.Clasificacion\n3.Desmontaje\n4.Tratamiento y procesamiento.\5.Refinacion.");
            //respuesta
            int[] respuestaBaterías = {1,2,3,4,5};

            for(int i = 0; i < respuestaBaterías.length ; i++ ){
                respuestaTratadora.add(entrada.nextInt());
                
            }

            if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaBaterías)) {
                System.out.println("La primera secuencia coincide.");
                Jugador.ganarPuntos(niv);
            } else{
                Jugador.perderVidas(niv);
            }

            respuestaTratadora.clear();
            
        }
    }

    public void TratarPilas(){
        if (Contenedor.getTipoDesecho() == "Pilas") {
            System.out.println("Tipo del Desecho" + Contenedor.getTipoDesecho());
            System.out.println("Nombre del desecho:" );

            int contador = 1;
            for(Desechos desechos: desechos){
                System.out.println(contador + ". " +Desechos.getNombreDesecho());
                contador ++;
            }
            
            System.out.println("Ingresa los numeros en el orden correcto para tratar los desechos");
            System.out.println("1.Uso final\n2.Mantenimento\n3.Compostaje");
            //respuesta
            int[] respuestaPilas = {2, 3, 1};

            for(int i = 0; i < respuestaPilas.length ; i++ ){
                respuestaTratadora.add(entrada.nextInt());
                
            }

            if (PlantaTratadora.compararRespuesta(respuestaTratadora, respuestaPilas)) {
                System.out.println("La primera secuencia coincide.");
                Jugador.ganarPuntos(niv);
            } else{
                Jugador.perderVidas(niv);
            }

            respuestaTratadora.clear();
            
        }
    }
    
}

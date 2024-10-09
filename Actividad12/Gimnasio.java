package Actividad12;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Date;

public class Gimnasio {
    private String nombre;
    private ArrayList<Socio>socios;


    Gimnasio(String nombre){
        this.nombre = nombre;
        this.socios = new ArrayList<Socio>();
    }

    public String getGimnasioNombre(){
        return this.nombre;
    }

    public void agregarSocio(Socio socio) {
        this.socios.add(socio);
        socio.darAlta();
    }

    public ArrayList<Socio> getSocios() {
        return this.socios;
    }

    public void estaRemodelado(){
        System.out.println("El gimnasio esta remodelado"); 
    }

    public static void mostrarSociosAlta(Gimnasio gym){
        for (Socio socio : gym.getSocios()){
            if (socio.getDadoDe()){
                System.out.println(socio.getSocioNombre()+" es socio del gimnasio "+gym.getGimnasioNombre());
            }
        }
    }

    public static void mostrarSociosAltaEspecifica(int anio,int mes,int dia, Gimnasio gym){
        GregorianCalendar calendarioIng=new GregorianCalendar(anio, mes-1, dia);
        Date fechaIng=calendarioIng.getTime();
        int numDadosAlta=0;

        System.out.println("\n");
        for (Socio socio : gym.getSocios()){
            if (fechaIng.equals(socio.getFechaAlta())){
                numDadosAlta++;
                System.out.println(socio.getSocioNombre()+" se dio de alta en la fecha "+fechaIng);
            }
        }
        System.out.println("El numero total de los dados de alta en la fecha "+fechaIng+"\nSon "+
        numDadosAlta);
    }

    public static void porcentajeDadosBaja(Gimnasio gym,int tam){
        System.out.println("El porcentaje de dados de baja es: ");
        System.out.println(((((double)Socio.getDadosBaja(gym) / tam))*100)+" %\n"); 
    }

    public static int totalDeRegistrados(Gimnasio gym){
        return (gym.getSocios().size());
    } 

    public static void noPagado(Gimnasio gym){
        for (Socio socio : gym.getSocios()){
            if (socio.estadoDePago() == false){
                System.out.println(socio.getSocioNombre()+" no ha pagado");
            }
        }
    }
}

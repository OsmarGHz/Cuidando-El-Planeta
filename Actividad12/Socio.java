package Actividad12;

import java.util.GregorianCalendar;
import java.util.Date;

public class Socio {
    private String nombre;
    private boolean dadoDe;
    private boolean pagado;
    private Date fechaAlta;

    Socio(String nombre, int anio,int mes,int dia){
        this.nombre = nombre;
        GregorianCalendar calendario=new GregorianCalendar(anio, mes-1, dia);
        fechaAlta=calendario.getTime();
        pagado = false;
    }

    public String getSocioNombre(){
        return this.nombre;
    }

    public void darAlta(){
        dadoDe = true;
    }

    public static int getDadosAlta(Gimnasio gym){
        int dadosDeAlta=0;
        for (Socio socio:gym.getSocios())
            if (socio.getDadoDe()){
                dadosDeAlta++;
            }

        return dadosDeAlta;
    }

    public void darBaja(){
        dadoDe = false;
        //dadosDeAlta--;
    }

    public static int getDadosBaja(Gimnasio gym){
        int dadosDeBaja=0;
        for (Socio socio:gym.getSocios())
            if (socio.getDadoDe()==false){
                dadosDeBaja++;
            }

        return dadosDeBaja;
    }

    public void pagoAnual(){
        if (dadoDe){
            pagado = true;
        }
    } 

    public boolean estadoDePago(){
        return pagado;
    }

    public boolean getDadoDe(){
        return dadoDe;
    }

    public Date getFechaAlta(){
        return fechaAlta;
    }
}

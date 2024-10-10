package Proyecto;

public class Nivel {
    private int puntosRespCorrecta;
    private int vidasRespIncorrecta;
    private int noDesechosGenerar;
    private int minDesechosNiv;
    private String dificultadNiv;
    private int noDesechosClasificados;

    public int getPuntosRespCorrecta(){
        return puntosRespCorrecta;
    }  
    
    public int getVidasRespIncorrecta(){
        return vidasRespIncorrecta;
    }

    public void generarDesechos(){

    }

    public boolean verificarMinDesechosNiv(Contenedor cont){
        //Modificar para que se acepte la suma de todos desechos admitidos en todos los contenedores
        if (minDesechosNiv==cont.getcantDesechosAdmitidos())
            return true;
        return false;
    }

}

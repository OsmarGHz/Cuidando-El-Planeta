package Proyecto;

public class Nivel {
    private int puntosRespCorrecta;
    private int vidasRespIncorrecta;
    private int noDesechosGenerar;
    private int minDesechosNiv; //Minimo por nivel
    private String dificultadNiv;
    private int noDesechosClasificados; //Numero de clasificados

    Nivel(int puntosRespCorrecta,int vidasRespIncorrecta,int noDesechosGenerar, int minDesechosNiv, 
    String dificultadNiv){
        this.puntosRespCorrecta=puntosRespCorrecta;
        this.vidasRespIncorrecta=vidasRespIncorrecta;
        this.noDesechosGenerar=noDesechosGenerar;
        this.minDesechosNiv=minDesechosNiv;
        this.dificultadNiv=dificultadNiv;
    }

    public void generarDesechos(){

    }

    public boolean verificarMinDesechosNiv(){
        //Modificar para que se acepte la suma de todos desechos admitidos en todos los contenedores
        if (minDesechosNiv==Contenedor.getCantTotalDesAdm())
            return true;
        return false;
    }

    //Getters
    public int getPuntosRespCorrecta(){
        return puntosRespCorrecta;
    }  
    
    public int getVidasRespIncorrecta(){
        return vidasRespIncorrecta;
    }

    public String getDificultad(){
        return dificultadNiv;
    }
}

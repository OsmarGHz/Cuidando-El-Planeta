package Proyecto;

public class Jugador {
    private String nombre;
    private int noVidasActuales=5;
    private int noPuntos=0;

    Jugador(String nombre){
        this.nombre=nombre;
    }

    public void clasificarDesecho(Desechos desecho, Contenedor contenedor, Nivel niv){
        if (contenedor.verificarDesecho(desecho)){
            System.out.println("\nDesecho clasificado correctamente: +"+niv.getPuntosRespCorrecta()+" pts.");
            contenedor.agregarDesecho(desecho);
            ganarPuntos(niv);
        }     
        else{
            System.out.println("\nDesecho clasificado incorrectamente: -"+niv.getVidasRespIncorrecta()+
            " vidas.");
            perderVidas(niv);
        }        
    }

    //Setters (solo se puede ingresar a ellos a través de clasificar desecho)
    private void ganarPuntos(Nivel niv){
        noPuntos+=niv.getPuntosRespCorrecta();
    }

    private void perderVidas(Nivel niv){
        noVidasActuales-=niv.getVidasRespIncorrecta();
    }

    //Getters
    public String getNombreJug(){
        return nombre;
    }

    public int getNoPuntos(){
        return noPuntos;
    }

    public int getNoVidas(){
        return noVidasActuales;
    }
}

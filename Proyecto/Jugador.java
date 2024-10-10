package Proyecto;

public class Jugador {
    private int noVidasActuales=5;
    private int noPuntos=0;

    public void clasificarDesecho(Desechos desecho, Contenedor contenedor, Nivel niv){
        if (contenedor.verificarDesecho(desecho)){
            System.out.println("Desecho clasificado correctamente: +"+niv.getPuntosRespCorrecta()+" pts.");
            ganarPuntos(niv);
        }     
        else{
            System.out.println("Desecho clasificado incorrectamente: -"+niv.getVidasRespIncorrecta()+
            " vidas.");
            perderVidas(niv);
        }        
    }

    public void ganarPuntos(Nivel niv){
        noPuntos+=niv.getPuntosRespCorrecta();
    }

    public void perderVidas(Nivel niv){
        noVidasActuales-=niv.getVidasRespIncorrecta();
    }
}

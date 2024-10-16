package Proyecto;

public abstract class Nivel {
    private int puntosRespCorrecta;
    private int vidasRespIncorrecta;
    private int minDesechosNiv; //Minimo por nivel
    private int segundosTurno;
    protected Desechos[][]desechos=new Desechos[10][];   //El primer espacio es para No de Desechos y el segundo para No Jugaodores 

    public Nivel(int puntosRespCorrecta,int vidasRespIncorrecta, int minDesechosNiv,
    int segundosTurno)
    {
        this.puntosRespCorrecta=puntosRespCorrecta;
        this.vidasRespIncorrecta=vidasRespIncorrecta;
        this.minDesechosNiv=minDesechosNiv;
        this.segundosTurno=segundosTurno;
    }

    public abstract void presentacionNivel();

    //Composición con desechos
    public abstract void generarDesechos(int numJug);

    public void setDesechos(int numJug){
        this.desechos=new Desechos[10][numJug];
    }

    public boolean verificarMinDesechosNiv(Jugador jugador){
        if (jugador.getNoPuntos()>=puntosRespCorrecta*minDesechosNiv)
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

    public Desechos getDesecho(int indice1,int indice2){
        return (desechos[indice1][indice2]);
    }

    public int getLeghtDesechosArr(){
        return (desechos.length);
    }

    public int getSegundosTurno(){
        return segundosTurno;
    }
}

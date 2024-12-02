//clase abstracta
public abstract class Nivel implements NivInt {
    private int puntosRespCorrecta;
    private int vidasRespIncorrecta;
    private int minDesechosNiv; //Mínimo por nivel
    private int segundosTurno;
    protected Desechos[][]desechos=new Desechos[DESECHOS_A_OCUPAR][];   //El primer espacio es para No de Desechos y el segundo para No Jugaodores 

    public Nivel(int puntosRespCorrecta,int vidasRespIncorrecta, int minDesechosNiv,
    int segundosTurno)
    {
        this.puntosRespCorrecta=puntosRespCorrecta;
        this.vidasRespIncorrecta=vidasRespIncorrecta;
        this.minDesechosNiv=minDesechosNiv;
        this.segundosTurno=segundosTurno;
    }

    public abstract String tituloNivel();
    public abstract String dificultadNivel();
    public abstract String presentacionNivel();

    //Composición con desechos (cada clase Nivel crea los Desechos que se ingresan en desechos[][])
    public abstract void generarDesechos(int numJug);

    public boolean verificarMinDesechosNiv(Jugador jugador){
        if (jugador.getNumDesechosClasif()>=minDesechosNiv)
            return true;
        return false;
    }

    //Setter
    public void setDesechos(int numJug){
        this.desechos=new Desechos[DESECHOS_A_OCUPAR][numJug];
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

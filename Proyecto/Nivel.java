package Proyecto;

public class Nivel {
    private int puntosRespCorrecta;
    private int vidasRespIncorrecta;
    private int minDesechosNiv; //Minimo por nivel
    private int segundosTurno;
    private String dificultadNiv;
    private Desechos[][]desechos;   //El primer espacio es para No de Desechos y el segundo para No Jugaodores 

    public Nivel(int puntosRespCorrecta,int vidasRespIncorrecta, int minDesechosNiv, String dificultadNiv,
    int segundosTurno)
    {
        this.puntosRespCorrecta=puntosRespCorrecta;
        this.vidasRespIncorrecta=vidasRespIncorrecta;
        this.minDesechosNiv=minDesechosNiv;
        this.dificultadNiv=dificultadNiv;
        this.segundosTurno=segundosTurno;
    }


    //Composición con desechos
    public void generarDesechos(int numJug,int niv){
        desechos=new Desechos[10][numJug];

        for (int i=0;i<desechos.length;i++)
            for (int j=0;j<desechos[i].length;j++)
                desechos[i][j]=new Desechos((int)(Math.random()*8));
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

    public String getDificultad(){
        return dificultadNiv;
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

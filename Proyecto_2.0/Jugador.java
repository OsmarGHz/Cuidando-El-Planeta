public class Jugador {
    private String nombre;
    private int noVidasActuales=5;
    private int noPuntos=0;
    private int numDesechosClasif=0;
    private int numTratados=0;
    private boolean pasaNiv=true;
    //Relacion Jugador con Cronometro
    private Cronometro cronometro;
    //Relacion Jugador con Planta Tratadora
    private PlantaTratadora planta;

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

    public String mostrarStats(){
        return ("Puntos: "+noPuntos+"                  Vidas: "+noVidasActuales);
    }

    public String mostrarStatsHTML(){
        return ("&emsp; Puntos: "+noPuntos+" &emsp; Vidas: "+noVidasActuales);
    }

    //Setters
    public void ganarPuntos(Nivel niv){
        noPuntos+=niv.getPuntosRespCorrecta();
    }

    public void perderVidas(Nivel niv){
        noVidasActuales-=niv.getVidasRespIncorrecta();
    }
    
    public void setNumDesechosClasif(int numDesechosClasif){
        this.numDesechosClasif=numDesechosClasif;
    }
    
    public void setPasaNiv(boolean pasaNiv){
        this.pasaNiv=pasaNiv;
    }

    public void setNumDesechosTrat(int numDesechosTrat){
        numTratados=numDesechosTrat;
    }

    public void setPlanta(PlantaTratadora planta){
        this.planta=planta;
    }

    public void setCronometro(Cronometro cronometro){
        this.cronometro=cronometro;
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

    public int getNumDesechosClasif(){
        return numDesechosClasif;
    }

    public boolean getPasaNiv(){
        return pasaNiv;
    }

    public int getNumDesechosTrat(){
        return numTratados;
    }

    public PlantaTratadora getPlanta(){
        return planta;
    }

    public Cronometro getCronometro(){
        return cronometro;
    }
}

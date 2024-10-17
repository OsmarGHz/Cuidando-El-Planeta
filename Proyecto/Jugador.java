package Proyecto;

public class Jugador {
    private String nombre;
    private int noVidasActuales=5;
    private int noPuntos=0;
    private int segundosRest;
    private int numDesechosClasif=0;
    private int numTratados=0;
    private boolean pasaNiv=true;
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

    public void mostrarStats(){
        System.out.print(nombre+"\n");
        System.out.println("Puntos: "+noPuntos+"\t\t\t\t\t\t\tVidas: "+noVidasActuales);
    }

    //Setters
    public void ganarPuntos(Nivel niv){
        noPuntos+=niv.getPuntosRespCorrecta();
    }

    public void perderVidas(Nivel niv){
        noVidasActuales-=niv.getVidasRespIncorrecta();
    }

    public void setSegundosRest(int segundosRest){
        this.segundosRest=segundosRest;
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

    public int getSegundosRest(){
        return segundosRest;
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
}

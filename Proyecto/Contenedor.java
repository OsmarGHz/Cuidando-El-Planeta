package Proyecto;

public class Contenedor {
    private int noDesechoAdmitido; //Lo permite relacionarlo con su respectivo desecho
    private int cantDesechosAdmitidos=0;
    private String tipoDesecho;

    public boolean verificarDesecho(Desechos desechoIng){
        if (desechoIng.getNoDesecho()==noDesechoAdmitido){
            cantDesechosAdmitidos++;
            return true;
        }
        return false;
    }

    public String getTipoDesecho(){
        return tipoDesecho;
    }

    public int getcantDesechosAdmitidos(){
        return cantDesechosAdmitidos;
    }

    public String mostrarInsertadosFinal(){
        return ("El contenedor de "+tipoDesecho+" acumuló: "+cantDesechosAdmitidos+" desechos.");
    }
}

package Proyecto;

import java.util.ArrayList;

public class Contenedor {
    private int noDesechoAdmitido; //Lo permite relacionarlo con su respectivo desecho
    private int cantDesechosAdmitidos=0;
    private static int cantDesAdmTotal=0;
    private String tipoDesecho;
    private  static ArrayList<Desechos> desechosGuardados= new ArrayList<>();//Aqui.inicializamos el array

    Contenedor(int noDesechoAdmitido){
        this.noDesechoAdmitido = noDesechoAdmitido; //Aqui.Guaramos el tipo de desecho admitido
          
        switch (noDesechoAdmitido) {
            case 0: tipoDesecho="Orgánico";         break;
            case 1: tipoDesecho="Papeles/Cartones"; break;
            case 2: tipoDesecho="Vidrios";          break;
            case 3: tipoDesecho="Plásticos";        break;
            case 4: tipoDesecho="Chatarra/Metal";   break;
            case 5: tipoDesecho="Aceites";          break;
            case 6: tipoDesecho="Pinturas";         break;
            case 7: tipoDesecho="Baterías";         break;
            case 8: tipoDesecho="Pilas";            break;
        }
    }

    public boolean verificarDesecho(Desechos desechoIng){
        if (desechoIng.getNoDesecho()==noDesechoAdmitido){
            cantDesechosAdmitidos++;
            cantDesAdmTotal++;
            return true;
        }
        return false;
    }

    //mostramos los desechos guardados
    //public void mostrarDesechosGuardados(){
     //   for (Desechos desecho: desechosGuardados){
       //     System.out.println(desecho.getNombreDesecho());
        //}
    //} 

    //Aqui
    public void agregarDesecho(Desechos desecho) {
        desechosGuardados.add(desecho); // Metodo para agregar desechos al ArrayList
    }
    //Aqui
    public static ArrayList<Desechos> getdesechosGuardados(){ 
        return desechosGuardados;

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

    public static int getCantTotalDesAdm(){
        return cantDesAdmTotal;
    }
}

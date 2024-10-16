package Proyecto;

import java.util.ArrayList;

public class Contenedor {
    private int noDesechoAdmitido; //Lo permite relacionarlo con su respectivo desecho
    private static int cantDesAdmidTotal=0;
    private String tipoDesecho;
    private ArrayList<Desechos>desechosGuardados=new ArrayList<>();

    Contenedor(int noDesechoAdmitido){
        this.noDesechoAdmitido=noDesechoAdmitido;

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
            case 9: tipoDesecho="Electrónicos";     break;
            case 10:tipoDesecho="Medicamento";      break;
            case 11:tipoDesecho="Residuos Químicos de Laboratorio"; break;
            case 12:tipoDesecho="Residuos Biológicos";              break;
        }
    }

    public boolean verificarDesecho(Desechos desechoIng){
        if (desechoIng.getNumTipoDesecho()==noDesechoAdmitido){
            cantDesAdmidTotal++;
            return true;
        }
        return false;
    }

    public static void mostrarMyrInsertados(Contenedor[] contenedores,int niv){
        System.out.println("\n\nPara el nivel: "+(niv+1)+"\n\nLos contenedores con más Desechos ingresados fueron: ");
        int myr=contenedores[0].desechosGuardados.size();

        for (int i=1;i<contenedores.length;i++)
            if (contenedores[i].desechosGuardados.size()>myr)
                myr=contenedores[i].desechosGuardados.size();

        for (int i=0;i<contenedores.length;i++)
            if (contenedores[i].desechosGuardados.size()==myr){
                System.out.println(contenedores[i].getTipoDesecho()+" con: "+myr+" desecho(s)");
            }
    }

    // Metodo para agregar desechos al ArrayList
    public void agregarDesecho(Desechos desecho) {
        desechosGuardados.add(desecho); 
    }

    //Getters
    public String getTipoDesecho(){
        return tipoDesecho;
    }

    public static int getCantTotalDesAdm(){
        return cantDesAdmidTotal;
    }

    public ArrayList<Desechos> getDesechosGuardados(){
        return desechosGuardados;
    }

    public Desechos getUltimoDesecho(){
        return desechosGuardados.get(desechosGuardados.size()-1);
    }
}

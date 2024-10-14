package Proyecto;

import java.util.ArrayList;

public class Contenedor {
    private int noDesechoAdmitido; //Lo permite relacionarlo con su respectivo desecho
    private static int cantDesAdmidTotal=0;
    private String tipoDesecho;
    private static ArrayList<Desechos>desechosGuardados=new ArrayList<>();

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
        int myr=contenedores[0].getDesechosGuardados().size();
        Contenedor contenedorMyr=contenedores[0];

        for (int i=1;i<contenedores.length;i++)
            if (contenedores[i].getDesechosGuardados().size()>myr){
                myr=contenedores[i].getDesechosGuardados().size();
                contenedorMyr=contenedores[i];
            }

        System.out.println("\n\nPara el nivel: "+(niv+1)+"\nEl contenedor con más Desechos ingresados es el de: "+
        contenedorMyr.getTipoDesecho()+"\nCon: "+contenedorMyr.getDesechosGuardados().size()+
        " desecho(s)");
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

    public static ArrayList<Desechos> getDesechosGuardados(){
        return desechosGuardados;
    }
}

package Proyecto;

import java.util.ArrayList;

public class Contenedor {
    private int numDesechoAdmitido; //Lo permite relacionarlo con los desechos que admite
    private String tipoDesecho; 
    //Agregación con Desechos
    private ArrayList<Desechos>desechosGuardados=new ArrayList<>(); 

    Contenedor(int numDesechoAdmitido){
        //Relaciona la posición en el arreglo de Contenedores con el Tipo de Descho que admite
        this.numDesechoAdmitido=numDesechoAdmitido;

        //Se le asigna cierta "etiqueta" a cada contenedor
        switch (numDesechoAdmitido) {
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
            case 10:tipoDesecho="Medicamentos";      break;
            case 11:tipoDesecho="Residuos Químicos de Laboratorio"; break;
            case 12:tipoDesecho="Residuos Biológicos";              break;
        }
    }

    public boolean verificarDesecho(Desechos desechoIng){
        /*Si el identificador del tipo de desecho ingresado corresponde al tipo de desecho que acepta el 
        contenedor devuelve TRUE*/
        if (desechoIng.getNumTipoDesecho()==numDesechoAdmitido){
            return true;
        }
        return false;
    }

    public static void mostrarMyrInsertados(Contenedor[] contenedores,int niv){
        System.out.println("\n\nPara el nivel: "+(niv+1)+"\n\nLos contenedores con más Desechos ingresados fueron: ");
        int myr=contenedores[0].desechosGuardados.size();

        /*Busca en el array de contenedores cual es el que tiene más desechos ingresados y guarda el 
        mayor valor en la varible "myr"*/
        for (int i=1;i<contenedores.length;i++)
            if (contenedores[i].desechosGuardados.size()>myr)
                myr=contenedores[i].desechosGuardados.size();

        /*Vuelve a recorrer el arreglo e imprime cual es el que tuvo más desechos ingresados (se vuelve
        a recorrer por si es que hay más de un contenedor con el número mayor de desechos ingresados)*/
        if (myr==0)
            System.out.println("Todos los contenedores están vacíos.");
        else 
            for (int i=0;i<contenedores.length;i++)
                if (contenedores[i].desechosGuardados.size()==myr){
                    System.out.println(contenedores[i].getTipoDesecho()+" con: "+myr+" desecho(s)");
                }
    }

    // Método para agregar desechos al ArrayList
    public void agregarDesecho(Desechos desecho) {
        desechosGuardados.add(desecho); 
    }

    //Getters
    public String getTipoDesecho(){
        return tipoDesecho;
    }

    public ArrayList<Desechos> getDesechosGuardados(){
        return desechosGuardados;
    }

    public Desechos getUltimoDesecho(){
        return desechosGuardados.get(desechosGuardados.size()-1);
    }
}

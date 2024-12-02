import java.util.ArrayList;

public class Contenedor {
    private int numDesechoAdmitido; //Lo permite relacionarlo con los desechos que admite
    private String tipoDesecho;
    private static InterfazGraficaJuego iGraficaContenedor; 
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

    public static void mostrarMyrInsertados(Contenedor[] contenedores,int niv, Jugador[]jugadores, Historial hist){
        iGraficaContenedor.getLPresentResultados(0).setText("<html><H2>Para el nivel: "+(niv+1)+"</H2><br><H4>Los contenedores con más Desechos ingresados fueron: </H4></html>");
        hist.escribirTxt("Para el nivel: "+(niv+1)+", Los contenedores con más Desechos ingresados fueron: \n");
        System.out.println();
        int myr=contenedores[0].desechosGuardados.size();
        /*Busca en el array de contenedores cual es el que tiene más desechos ingresados y guarda el 
        mayor valor en la varible "myr"*/
        for (int i=1;i<contenedores.length;i++)
            if (contenedores[i].desechosGuardados.size()>myr)
                myr=contenedores[i].desechosGuardados.size();

        /*Vuelve a recorrer el arreglo e imprime cual es el que tuvo más desechos ingresados (se vuelve
        a recorrer por si es que hay más de un contenedor con el número mayor de desechos ingresados)*/
        if (myr==0){
            iGraficaContenedor.getLPresentResultados(1).setText("<html><strong>Todos los contenedores están vacíos.</strong></html>");
            hist.escribirTxt("Todos los contenedores están vacíos.");
        }else {
            StringBuilder fraseMyrConte=new StringBuilder("<html><strong>Con "+myr+" desecho(s) </strong><br>");
            hist.escribirTxt("Con "+myr+" desecho(s) \n");
            for (int i=0;i<contenedores.length;i++){
                if (contenedores[i].desechosGuardados.size()==myr){
                    fraseMyrConte.append(" + "+contenedores[i].getTipoDesecho()+"<br>");
                    hist.escribirTxt(" + "+contenedores[i].getTipoDesecho()+"\n");
                }
            }
            iGraficaContenedor.getLPresentResultados(1).setText(fraseMyrConte.toString());
        }    
        
        //Se imprimen stats finales por nivel
        StringBuilder fraseJugadoresStats=new StringBuilder("<html><H3>Stats de nivel: </H3><br>");
        hist.escribirTxt("--Stats de nivel: \n");
        for (Jugador jugador:jugadores){
            fraseJugadoresStats.append("<strong>- "+" ( "+jugador.getNombreJug()+" )  "+jugador.mostrarStatsHTML()+"<br>");
            hist.escribirTxt("- "+" ( "+jugador.getNombreJug()+" )  "+jugador.mostrarStats()+"\n");
        }
        fraseJugadoresStats.append("</strong></html>");
        iGraficaContenedor.getLPresentResultados(2).setText(fraseJugadoresStats.toString());

        iGraficaContenedor.muestraPanelMyrCont();
    }

    // Método para agregar desechos al ArrayList
    public void agregarDesecho(Desechos desecho) {
        desechosGuardados.add(desecho); 
    }

    public static void setInterfazGrafica(InterfazGraficaJuego iGraficaJuego){
        iGraficaContenedor=iGraficaJuego;
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

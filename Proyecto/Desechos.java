package Proyecto;

public class Desechos {
    //Clase para desechos genéricos
    private String nombre;
    private int numTipoDesecho;
    private int numIdDesecho;

    Desechos(int numTipoDesecho){ 
        this.numTipoDesecho=numTipoDesecho;
        numIdDesecho=(int)(Math.random()*4);
         
        switch (numTipoDesecho) {
            case 4: //Nombres "Chatarra/Metal"
                switch (numIdDesecho){
                    case 0: nombre="Latas de aluminio";     break;
                    case 1: nombre="Fragmento de tubería";  break;
                    case 2: nombre="Tostadora";             break;
                    case 3: nombre="Cables eléctricos";     break;
                }
                break;
            case 5: //Nombres "Aceites"
                switch (numIdDesecho){
                    case 0: nombre="Aceite de cocina";                 break;
                    case 1: nombre="Aceite de motor";                   break;
                    case 2: nombre="Aceite hidráulico de maquinaria";   break;
                    case 3: nombre="Aceite lubricante industrial";      break;
                }
                break;
            case 6: //Nombres "Pinturas"
                numIdDesecho=(int)(Math.random()*2);
                switch (numIdDesecho){
                    case 0: nombre="Acrílico";  break;
                    case 1: nombre="Esmalte";   break;
                }
                break;
            case 8: //Nombres "Pilas"
                switch (numIdDesecho){
                    case 0: nombre="Pilas alcalinas";   break;
                    case 1: nombre="Pilas de botón";    break;
                    case 2: nombre="Pilas recargables"; break;
                    case 3: nombre="Pilas de litio";    break;
                }
                break;
            default:break;
        }
    }
    //Setter
    protected void setNombreDesecho(String nombre){
        this.nombre=nombre;
    }

    //Getters
    public int getNumTipoDesecho(){
        return numTipoDesecho;
    }

    public String getNombreDesecho(){
        return nombre;
    }

    public String getNombreDesecho(int noContenedor){
        return nombre;
    }
}

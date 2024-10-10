package Proyecto;

import java.util.Random;

public class Desechos {
    private String nombre;
    private int numTipoDesecho;
    private int numIdDesecho;

    Desechos(){
        numTipoDesecho=(int)(Math.random()*8);//Agregar al main de Juego
        numIdDesecho=(int)(Math.random()*4);  

        switch (numTipoDesecho) {
            case 0: //Nombres "Organicos"
                switch (numIdDesecho){
                    case 0: nombre="";  break;
                    case 1: nombre="";  break;
                    case 2: nombre="";  break;
                    case 3: nombre="";  break;
                }
                break;
            case 1: //Nombres "Papeles/Cartones"
                switch (numIdDesecho){
                    case 0: nombre="";  break;
                    case 1: nombre="";  break;
                    case 2: nombre="";  break;
                    case 3: nombre="";  break;
                }
                break;
            case 2: //Nombres "Vidrios"
                switch (numIdDesecho){
                    case 0: nombre="";  break;
                    case 1: nombre="";  break;
                    case 2: nombre="";  break;
                    case 3: nombre="";  break;
                }
                break;
            case 3: //Nombres "Plasticos"
                switch (numIdDesecho){
                    case 0: nombre="";  break;
                    case 1: nombre="";  break;
                    case 2: nombre="";  break;
                    case 3: nombre="";  break;
                }
                break;
            case 4: //Nombres "Chatarra/Metal"
                switch (numIdDesecho){
                    case 0: nombre="";  break;
                    case 1: nombre="";  break;
                    case 2: nombre="";  break;
                    case 3: nombre="";  break;
                }
                break;
            case 5: //Nombres "Aceites"
                switch (numIdDesecho){
                    case 0: nombre="";  break;
                    case 1: nombre="";  break;
                    case 2: nombre="";  break;
                    case 3: nombre="";  break;
                }
                break;
            case 6: //Nombres "Pinturas"
                switch (numIdDesecho){
                    case 0: nombre="";  break;
                    case 1: nombre="";  break;
                    case 2: nombre="";  break;
                    case 3: nombre="";  break;
                }
                break;
            case 7: //Nombres "Baterías"
                switch (numIdDesecho){
                    case 0: nombre="";  break;
                    case 1: nombre="";  break;
                    case 2: nombre="";  break;
                    case 3: nombre="";  break;
                }
                break;
            case 8: //Nombres "Pilas"
                switch (numIdDesecho){
                    case 0: nombre="";  break;
                    case 1: nombre="";  break;
                    case 2: nombre="";  break;
                    case 3: nombre="";  break;
                }
                break;
        }
    }

    public int getNoDesecho(){
        return numTipoDesecho;
    }

    public String getNombreDesecho(){
        return nombre;
    }

    public String getNombreDesecho(int noContenedor,int noDesecho){
        return nombre;
    }
}

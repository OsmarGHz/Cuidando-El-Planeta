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
                    case 0: nombre="Cascaras de frutas";  break;
                    case 1: nombre="Cáscaras de Verduras";  break;
                    case 2: nombre="Sobras de comida";  break;
                    case 3: nombre="Hojas secas";  break;
                }
                break;
            case 1: //Nombres "Papeles/Cartones"
                switch (numIdDesecho){
                    case 0: nombre="Periodico";  break;
                    case 1: nombre="Cajas de carton";  break;
                    case 2: nombre="Revistas";  break;
                    case 3: nombre="Hojas de papel usadas";  break;
                }
                break;
            case 2: //Nombres "Vidrios"
                switch (numIdDesecho){
                    case 0: nombre="Botellas de vidrio";  break;
                    case 1: nombre="Frascos de vidrio";  break;
                    case 2: nombre="Ventana de vidrio";  break;
                    case 3: nombre="Vasos de vidrio";  break;
                }
                break;
            case 3: //Nombres "Plasticos"
                switch (numIdDesecho){
                    case 0: nombre="Botellas de plastico";  break;
                    case 1: nombre="Bolsas de plastico";  break;
                    case 2: nombre="Juguetes de plasticos";  break;
                    case 3: nombre="Envases de plastico";  break;
                }
                break;
            case 4: //Nombres "Chatarra/Metal"
                switch (numIdDesecho){
                    case 0: nombre="Latas de aluminio";  break;
                    case 1: nombre="Tuberia de metal";  break;
                    case 2: nombre="Tostadora";  break;
                    case 3: nombre="Cables de metal";  break;
                }
                break;
            case 5: //Nombres "Aceites"
                switch (numIdDesecho){
                    case 0: nombre="Aceite de cociona";  break;
                    case 1: nombre="Aceite de motor";  break;
                    case 2: nombre="Aceite hidraulico de maquinaria";  break;
                    case 3: nombre="Aceite lubricante industrial";  break;
                }
                break;
            case 6: //Nombres "Pinturas"
                switch (numIdDesecho){
                    case 0: nombre="Acrilico";  break;
                    case 1: nombre="Esmalte";  break;
                    case 2: nombre="";  break;
                    case 3: nombre="";  break;
                }
                break;
            case 7: //Nombres "Baterías"
                switch (numIdDesecho){
                    case 0: nombre="Baterias de automovil";  break;
                    case 1: nombre="Baterias de bicicleta";  break;
                    case 2: nombre="Baterias de respaldo de sistema UPS";  break;
                    case 3: nombre="Baterías de equipos industriales";  break;
                }
                break;
            case 8: //Nombres "Pilas"
                switch (numIdDesecho){
                    case 0: nombre="Pilas alcalinas";  break;
                    case 1: nombre="Pilas de botón";  break;
                    case 2: nombre="Pilas recargables";  break;
                    case 3: nombre="Pilas de litio";  break;
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

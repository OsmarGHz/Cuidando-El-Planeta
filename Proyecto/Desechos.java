package Proyecto;

public class Desechos {
    private String nombre;
    private int numTipoDesecho;
    private int numIdDesecho;

    Desechos(int numTipoDesecho){ 
        this.numTipoDesecho=numTipoDesecho;
        numIdDesecho=(int)(Math.random()*4);
         
        switch (numTipoDesecho) {
            case 0: //Nombres "Organicos"
                switch (numIdDesecho){
                    case 0: nombre="Cascaras de frutas";    break;
                    case 1: nombre="Cáscaras de Verduras";  break;
                    case 2: nombre="Sobras de comida";      break;
                    case 3: nombre="Hojas secas";           break;
                }
                break;
            case 1: //Nombres "Papeles/Cartones"
                switch (numIdDesecho){
                    case 0: nombre="Periodico";             break;
                    case 2: nombre="Revistas";              break;
                    case 1: nombre="Caja de carton";        break;
                    default: nombre="Hojas de papel usadas";break;
                }
                break;
            case 2: //Nombres "Vidrios"
                numIdDesecho=(int)(Math.random()*5);
                switch (numIdDesecho){
                    case 0: nombre="Botellas de vidrio";    break;
                    case 1: nombre="Frascos de vidrio";     break;
                    case 2: nombre="Ventana de vidrio";     break;
                    case 3: nombre="Vasos de vidrio";       break;
                    case 4: nombre="Botellines";            break;
                }
                break;
            case 3: //Nombres "Plasticos"
                switch (numIdDesecho){
                    case 0: nombre="Botellas de plastico";  break;
                    case 1: nombre="Bolsas";                break;
                    case 2: nombre="Juguetes";              break;
                    case 3: nombre="Envases de plastico";   break;
                }
                break;
            case 4: //Nombres "Chatarra/Metal"
                switch (numIdDesecho){
                    case 0: nombre="Latas de aluminio";     break;
                    case 1: nombre="Tuberia";               break;
                    case 2: nombre="Tostadora";             break;
                    case 3: nombre="Cables eléctricos";     break;
                }
                break;
            case 5: //Nombres "Aceites"
                switch (numIdDesecho){
                    case 0: nombre="Aceite de cociona";                 break;
                    case 1: nombre="Aceite de motor";                   break;
                    case 2: nombre="Aceite hidraulico de maquinaria";   break;
                    case 3: nombre="Aceite lubricante industrial";      break;
                }
                break;
            case 6: //Nombres "Pinturas"
                numIdDesecho=(int)(Math.random()*2);
                switch (numIdDesecho){
                    case 0: nombre="Acrilico";  break;
                    case 1: nombre="Esmalte";   break;
                }
                break;
            case 7: //Nombres "Baterías"
                switch (numIdDesecho){
                    case 0: nombre="Baterias de automovil";                 break;
                    case 1: nombre="Baterias de bicicleta";                 break;
                    case 2: nombre="Baterias de respaldo de sistema UPS";   break;
                    case 3: nombre="Baterías de equipos industriales";      break;
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
        }
    }

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

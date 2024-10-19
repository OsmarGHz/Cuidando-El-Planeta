package Proyecto;

public class DesechosNiv2 extends Desechos{
    //Clase para desechos especiales de Nivel 2
    public DesechosNiv2(int numTipoDesecho){
        super(numTipoDesecho);

        switch (numTipoDesecho) {
            case 1: //Nombres "Cartones"
                int numIdDesecho=(int)(Math.random()*3);

                switch (numIdDesecho){
                    case 0: super.setNombreDesecho("Caja de cartón");     break;
                    default:super.setNombreDesecho("Pedazos de cartón");  break;
                }
                break;
            case 2: //Nombres "Vidrios"
                numIdDesecho=(int)(Math.random()*5);

                switch (numIdDesecho){
                    case 0: super.setNombreDesecho("Botellas de vidrio"); break;
                    case 1: super.setNombreDesecho("Frascos de vidrio");  break;
                    case 2: super.setNombreDesecho("Ventana");            break;
                    case 3: super.setNombreDesecho("Vasos de vidrio");    break;
                    case 4: super.setNombreDesecho("Botellines");         break;
                }
                break;
            case 7: //Nombres "Baterías"
                numIdDesecho=(int)(Math.random()*4);

                switch (numIdDesecho){
                    case 0: super.setNombreDesecho("Baterías de automovil");                break;
                    case 1: super.setNombreDesecho("Baterías de bicicleta");                break;
                    case 2: super.setNombreDesecho("Baterías de respaldo de sistema UPS");  break;
                    case 3: super.setNombreDesecho("Baterías de equipos industriales");     break;
                }
                break;
            case 9: //Nombres "Electronicos"
                numIdDesecho=(int)(Math.random()*3);
                switch (numIdDesecho) {
                    case 0: super.setNombreDesecho("TV dañada");         break;
                    case 1: super.setNombreDesecho("Celular dañado");    break;
                    case 2: super.setNombreDesecho("Monitor dañado");    break;
                }
                break;
        }
        
    }
}

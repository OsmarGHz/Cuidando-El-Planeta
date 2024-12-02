public class DesechosNiv1 extends Desechos{
    //Clase para desechos especiales de Nivel 1 y solo los cartones de Niv 2
    public DesechosNiv1(int numTipoDesecho){
        super(numTipoDesecho); //Se llama al contructor de la clse padre

        int numIdDesecho=(int)(Math.random()*4);
        switch (numTipoDesecho) {
            case 0: //Nombres "Orgánicos"
                switch (numIdDesecho){
                    case 0: super.setNombreDesecho("Cáscaras de frutas");     break;
                    case 1: super.setNombreDesecho("Cáscaras de Verduras");   break;
                    case 2: super.setNombreDesecho("Sobras de comida");       break;
                    case 3: super.setNombreDesecho("Hojas secas");            break;
                }
                break;
            case 1: //Nombres "Papeles"
                switch (numIdDesecho){
                    case 0: super.setNombreDesecho("Periódico");              break;
                    case 1: super.setNombreDesecho("Revistas");               break;
                    default:super.setNombreDesecho("Hojas de papel usadas");  break;
                }
                break;
            case 3: //Nombres "Plásticos"
                switch (numIdDesecho){
                    case 0: super.setNombreDesecho("Bolsas");                 break;
                    case 1: super.setNombreDesecho("Juguetes");               break;
                    case 2: super.setNombreDesecho("Envases de plástico");    break;
                    case 3: super.setNombreDesecho("Botellas de plástico");   break;
                }
                break;
        }
    }

}

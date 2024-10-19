package Proyecto;

public class DesechosNiv3 extends Desechos{
    //Clase para desechos especiales de Nivel 3
    public DesechosNiv3(int numTipoDesecho){
        super(numTipoDesecho);

        switch (numTipoDesecho) {
            case 10: setNombreDesecho("Medicamentos caducados");   break; //Nombre "Medicamentos"
            case 11: setNombreDesecho("Disolvente");               break; //Nombre "Residuo Quimico de Laboratorio"
            case 12: setNombreDesecho("Tejido animal");            break; //Nombre "Residuos Biológicos"
        }
    }
}

package Proyecto;

public class Nivel3 extends Nivel{
    public Nivel3(int ptsRespCorrecta, int vidasRespIncorrecta, int minDesechosNivel,int segundosTurno){
        super(ptsRespCorrecta, vidasRespIncorrecta, minDesechosNivel, segundosTurno);
    }

    @Override
    public void presentacionNivel() {
        System.out.println("NIVEL 3: \n\nDificultad: Alta");
        System.out.println("\nTipos de Desechos Especiales: \n*Medicamentos Caducados");
        System.out.println("*Residuos Químicos de Laboratorio \n*Residuos Biológicos");
    }

    @Override
    public void generarDesechos(int numJug) {
        for (int i=0;i<desechos.length;i++)
            for (int j=0;j<numJug;j++){
                int bifurca=(int)(Math.random()*4);
                
                switch (bifurca) {
                    //Te devuelven Desechos Genéricos
                    case 0: desechos[i][j]=new Desechos((int)(((Math.random())*3)+4));      break;
                    case 1: desechos[i][j]=new Desechos(8);                  break;
                    //Te devuelve Medicamentos, Residuos Quimicos de Laboratorio y Residuos Biológicos
                    default:desechos[i][j]=new DesechosNiv3((int)((Math.random()*3)+10));   break;
                }
            }
    }
}

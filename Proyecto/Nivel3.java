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
            for (int j=0;j<desechos[i].length;j++){
                int bifurca=(int)(Math.random()*3);
                
                if (bifurca==1)
                    desechos[i][j]=new Desechos((int)((Math.random()*3)+10));
                else{
                    int bifurca2=(int)((Math.random()*13));

                    if(bifurca2==0||bifurca2==1||bifurca2==3)
                        //Residuos Niv 1
                        desechos[i][j]=new Desechos(bifurca2,(int)(Math.random()*5));
                    else
                        desechos[i][j]=new Desechos(bifurca2);
                }    
            }
    }
}

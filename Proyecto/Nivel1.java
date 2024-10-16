package Proyecto;

public class Nivel1 extends Nivel{
    public Nivel1(int ptsRespCorrecta, int vidasRespIncorrecta, int minDesechosNivel,int segundosTurno){
        super(ptsRespCorrecta, vidasRespIncorrecta, minDesechosNivel, segundosTurno);
    }

    @Override
    public void presentacionNivel() {
        System.out.println("NIVEL 1: \n\nDificultad: Baja");
        System.out.println("\nTipos de Desechos Especiales: \n*Plásticos \n*Papeles \n*Orgánicos");
    }

    @Override
    public void generarDesechos(int numJug) {
        for (int i=0;i<desechos.length;i++)
            for (int j=0;j<desechos[i].length;j++){
                int bifurca=(int)(Math.random()*3);

                if (bifurca==0)
                    //Te da un "Plástico" aleatorio
                    desechos[i][j]=new Desechos(3,(int)(Math.random()*5));
                else  {
                    int bifurca2=(int)(Math.random()*2);
                    if (bifurca2==0)
                        //Te da un "Orgánico" aleatorio
                        desechos[i][j]=new Desechos(bifurca2,(int)(Math.random()*5)); 
                    else 
                        //Te da un "Papel" aleatorio
                        desechos[i][j]=new Desechos(bifurca2,(int)(Math.random()*3));
                }
                      
            }    
    }
}
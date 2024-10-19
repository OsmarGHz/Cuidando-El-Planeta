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
            for (int j=0;j<numJug;j++){
                int bifurca=(int)(Math.random()*4);

                switch (bifurca){
                    //Te devuelve Orgánicos y Papeles
                    case 0: desechos[i][j]=new DesechosNiv1((int)(Math.random()*2));    break; 
                    //Te devuelve Plasticos
                    case 1: desechos[i][j]=new DesechosNiv1(3);          break;  
                    //Te devuelven los Desechos Genéricos     
                    case 2: desechos[i][j]=new Desechos((int)(((Math.random())*3)+4));  break;
                    case 3: desechos[i][j]=new Desechos(8);              break;
                }            
            }    
    }
}
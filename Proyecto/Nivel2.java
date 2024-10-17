package Proyecto;

public class Nivel2 extends Nivel{
    public Nivel2(int ptsRespCorrecta, int vidasRespIncorrecta, int minDesechosNivel,int segundosTurno){
        super(ptsRespCorrecta, vidasRespIncorrecta, minDesechosNivel, segundosTurno);
    }

    @Override
    public void presentacionNivel() {
        System.out.println("NIVEL 2: \n\nDificultad: Media");
        System.out.println("\nTipos de Desechos Especiales: \n*Vidrios \n*Electrónicos \n*Baterías \n*Cartones");
    }

    @Override
    public void generarDesechos(int numJug) {
        for (int i=0;i<desechos.length;i++)
            for (int j=0;j<desechos[i].length;j++){
                int bifurca=(int)(Math.random()*5);

                if (bifurca==0){
                    int bifurca2=(int)((Math.random()*2)+1); //Solo devuelve entre 1 y 2

                    if (bifurca2==1)
                        //Te da un "Cartón" aleatorio
                        desechos[i][j]=new Desechos(bifurca2,(int)((Math.random()*2)+3));
                    else
                        //Te da un "Vidrio" aleatorio
                        desechos[i][j]=new Desechos(bifurca2);
                }
                else if (bifurca==1)
                    //Te da "Baterías" aleatorias
                    desechos[i][j]=new Desechos(7);
                else if (bifurca==2)
                    //Te da "Electronicos" aleatorios
                    desechos[i][j]=new Desechos(9);
                else {
                    
                    int bifurca2=(int)((Math.random()*7));
                    //Te da algun otro residuo de los anteriores (incluso algunos de Niv 1)
                    if(bifurca2==0||bifurca2==1||bifurca2==3)
                        //Residuos Niv 1
                        desechos[i][j]=new Desechos(bifurca2,(int)(Math.random()*5));
                    else
                        //Residuos restantes
                        desechos[i][j]=new Desechos(bifurca2);
                }     
            }
                
    }
}

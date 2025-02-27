public class Nivel2 extends Nivel{
    public Nivel2(int ptsRespCorrecta, int vidasRespIncorrecta, int minDesechosNivel,int segundosTurno){
        super(ptsRespCorrecta, vidasRespIncorrecta, minDesechosNivel, segundosTurno);
    }

    @Override
    public String tituloNivel() {
        return ("NIVEL 2");
    }

    @Override
    public String dificultadNivel() {
        return ("Dificultad: Media");
    }

    @Override
    public String presentacionNivel() {
        return ("<html>- Vidrios<p>- Electrónicos<p>- Baterías<p>- Cartones</html>");
    }

    @Override
    public void generarDesechos(int numJug) {
        for (int i=0;i<desechos.length;i++)
            for (int j=0;j<numJug;j++){
                int bifurca=(int)(Math.random()*5);

                switch (bifurca) {
                    //Te devuelve Cartones y Vidrios
                    case 0: desechos[i][j]=new DesechosNiv2((int)((Math.random()*2)+1));    break;
                    //Te devuelve Baterías
                    case 1: desechos[i][j]=new DesechosNiv2(7);              break;
                    //Te devuelve Electrónicos
                    case 2: desechos[i][j]=new DesechosNiv2(9);              break;
                    //Te devuelven los Desechos Genéricos
                    case 3: desechos[i][j]=new Desechos((int)(((Math.random())*3)+4));      break;
                    case 4: desechos[i][j]=new Desechos(8);                  break;
                }
            }
                
    }
}

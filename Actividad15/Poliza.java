public abstract class Poliza {

    private boolean dadoDe=false;
    private boolean adeudo;

    public abstract void darAlta();
    public abstract void darBaja();
    public abstract void imprimirPoliza(Cliente cliente);

    public boolean getDadoDe(){
        return dadoDe;
    }

    public boolean getAdeudo(){
        return adeudo;
    }

    public void setEstado(boolean estado){
        dadoDe=estado;
    }

    public void setAdeudo(boolean adeudo){
        this.adeudo=adeudo;
    }

   
}

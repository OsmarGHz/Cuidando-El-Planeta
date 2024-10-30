import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Auto extends Poliza{
    private Scanner entradaAuto=new Scanner(System.in);
    private String modeloAuto;
    private String marcaAuto;


    public Auto(){
        System.out.println("Ingrese la marca de Auto a registrar: ");
        marcaAuto=entradaAuto.nextLine();
        System.out.println("Ingrese el modelo de Auto a registrar: ");
        modeloAuto=entradaAuto.nextLine();

        int valor;
        do{
            try {
                System.out.println("¿Tiene adeudo?\n1: Si\n0: No");
                valor=entradaAuto.nextInt();
                if (valor==1)
                    this.setAdeudo(true);
                else if (valor==0)
                    this.setAdeudo(false);
            }
            catch(InputMismatchException ex){
                System.out.println("Opción inválida vuelva a ingresar un valor: ");
                entradaAuto.nextLine();
                valor=2;
            }
        }while (valor<0 || valor >1); 
        darAlta();
    }

    @Override
    public void darAlta(){
        System.out.println("Se dio de alta el seguro de Auto");
        this.setEstado(true);
    } 

    @Override
    public void darBaja(){
        System.out.println("Eliminando Contrato de Seguro de Auto...");

        if (this.getAdeudo()){
            System.out.println("¡¡ERRROR!! \n No es posible, cuenta con adeudo");
        }else{
            System.out.println("Eliminando Contrato...");
            for(int i = 0 ; i < 3 ; i++)
                System.out.println();

            this.setEstado(false);
            System.out.println("Contrato eliminado con éxito");
        }
    }

   

    @Override
    public void imprimirPoliza(Cliente cliente){
        if (this.getDadoDe()){
            Date Hora = new Date();
            SimpleDateFormat FormaHora = new SimpleDateFormat("HH:mm:ss");
            System.out.println("\t***POLIZA***");
            cliente.imprimirDatosSep();
            System.out.println("* Tipo de Seguro: Auto");
            System.out.println("\t* Modelo Del Auto:" +modeloAuto + "\n" + "\t* Marca del Auto:" + marcaAuto );
            System.out.println("\t*\tFecha :" + FormaHora.format(Hora) );
            System.out.println("\t***POLIZA***");
        }
        else 
            System.out.println("No se ha adquirido el Seguro de Auto");
    } 
}

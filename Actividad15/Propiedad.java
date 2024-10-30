import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Propiedad extends Poliza{
    private Scanner entradaPropiedad=new Scanner(System.in);
    private String tipoPropiedad;
    private int montoPropiedad;
    private int extension;
    


    public Propiedad(){
        do{
            try{
                System.out.println("Ingrese el monto de seguro: ");
                montoPropiedad=entradaPropiedad.nextInt();
            }
            catch(InputMismatchException ex){
                System.out.println("Opción inválida vuelva a ingresar un valor: ");
                entradaPropiedad.nextLine(); //Para limpiar el buffer y sea capaz de pedir nuevamente el dato
                montoPropiedad=0;
            }
        }while(montoPropiedad<100);
        
        do{
            try{
                System.out.println("Ingrese el tamaño de su terreno (mt^2): ");
                extension=entradaPropiedad.nextInt();
            }
            catch(InputMismatchException ex){
                System.out.println("Opción inválida vuelva a ingresar un valor: ");
                entradaPropiedad.nextLine(); //Para limpiar el buffer y sea capaz de pedir nuevamente el dato
                extension=0;
            }
        }while(extension<=0);

        int estadoAdeudo;
        do{
            try {
                System.out.println("¿Tiene adeudo?\n1: Si\n0: No");
                estadoAdeudo=entradaPropiedad.nextInt();
                if (estadoAdeudo==1)
                    this.setAdeudo(true);
                else if (estadoAdeudo==0)
                    this.setAdeudo(false);     
            }
            catch(InputMismatchException ex){
                System.out.println("Opción inválida vuelva a ingresar un valor: ");
                entradaPropiedad.nextLine();
                estadoAdeudo=2;
            }
        }while (estadoAdeudo<0 || estadoAdeudo >1); 
    }

    @Override
    public void darAlta(){
        System.out.println("Se dio de alta el seguro de Propiedad");
        this.setEstado(true);
    } 

    @Override
    public void darBaja(){
        System.out.println("Eliminando Contrato de Seguro de Propiedad...");

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

            System.out.println("* Tipo de Seguro: Propiedad");
            System.out.println("*Tipo de propiedad: "+tipoPropiedad);
            System.out.println("\t*\tTamaño Terreno : " +extension+" mt^2");
            System.out.println("\t*\tMonto Propiedad: " + montoPropiedad);

            System.out.println("\t*\tFecha : " + FormaHora.format(Hora) );
            System.out.println("\t***POLIZA***");
        }
        else 
            System.out.println("No se ha adquirido el Seguro de Propiedad");
    } 

    //Getters
    public int getExtension(){
        return extension;
    }

    public int getMontoPropiedad(){
      return montoPropiedad;
    }

    //Setters
    protected void setTipoPropiedad(String tipoPropiedad){
        this.tipoPropiedad=tipoPropiedad;
    }

    protected void setExtension(int extension){
        this.extension=extension;
    }

    protected void setMontoPropiedad(int montoPropiedad){
        this.montoPropiedad=montoPropiedad;
    }
}



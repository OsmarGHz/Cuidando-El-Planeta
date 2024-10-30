import java.util.InputMismatchException;
import java.util.Scanner;

public class Terreno extends Propiedad {
    private Scanner entradaTerr=new Scanner(System.in);
    private final int valorMaxArea = 300000, areaMinSeguro = 400;

    public Terreno(){
        this.setTipoPropiedad("Terreno");
        while(this.getMontoPropiedad()>valorMaxArea && this.getExtension()<areaMinSeguro){
            corrigeDatosTerreno();
        } 
    }

    public void corrigeDatosTerreno(){
        System.out.println("No es posible crear cuenta\n Posibles razones:");
        System.out.println("--El área del terreno es menor al límite (400 mt^2)\n--El monto por el que se asegura es mayor al límite ($300,000)");
        System.out.println("\n\nIngrese otros valores:\n"); 

        do {
            try{
                System.out.println("Ingrese el monto de seguro: ");
                this.setMontoPropiedad(entradaTerr.nextInt());
            }
            catch(InputMismatchException ex){
                System.out.println("Opción inválida vuelva a ingresar un valor: ");
                entradaTerr.nextLine(); //Para limpiar el buffer y sea capaz de pedir nuevamente el dato
                this.setMontoPropiedad(0);
            }
        }while(this.getMontoPropiedad()<100);


        do{
            try{
                System.out.println("Ingrese el tamaño de su terreno (mt^2): ");
                this.setExtension(entradaTerr.nextInt());
            }
            catch(InputMismatchException ex){
                System.out.println("Opción inválida vuelva a ingresar un valor: ");
                entradaTerr.nextLine(); //Para limpiar el buffer y sea capaz de pedir nuevamente el dato
                this.setExtension(0);
            }
        }while(this.getExtension()<=0);
    }
}
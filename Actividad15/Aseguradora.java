import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Aseguradora {
    private static ArrayList<Cliente> carteraClientes=new ArrayList<>();
    private static Scanner entradaAseg=new Scanner(System.in);

    public static int ingresaIndiceSeguro(){
        int indiceSeguro;
        do{
            try{
                indiceSeguro=entradaAseg.nextInt()-1;
            }
            catch(InputMismatchException ex){
                System.out.println("Opción inválida vuelva a ingresar un valor: ");
                entradaAseg.nextLine(); //Para limpiar el buffer y sea capaz de pedir nuevamente el dato
                indiceSeguro=-1;
            }
        } while (indiceSeguro<0 || indiceSeguro>2);
        return (indiceSeguro);
    }

    public static int ingresaIndiceCliente(){
        int indiceCliente;
        do {
            try{
                indiceCliente=entradaAseg.nextInt()-1;
            }  
            catch(InputMismatchException ex){
                System.out.println("Opción inválida vuelva a ingresar un valor: ");
                entradaAseg.nextLine(); //Para limpiar el buffer y sea capaz de pedir nuevamente el dato
                indiceCliente=-1;
            }
        }while(indiceCliente<0 || indiceCliente>=carteraClientes.size());
        return (indiceCliente);
    }

    public static int ingresaIndicePropiedad(){
        System.out.println("1.Residencia\n2.Negocio\n3.Terreno");
        int numTipo;
        do {
            try {
                numTipo=entradaAseg.nextInt();
            }
            catch(InputMismatchException ex){
                System.out.println("Opción inválida vuelva a ingresar un valor: ");
                numTipo=0;
            } 
        }while (numTipo<1 || numTipo>3);
        return (numTipo);
    }

    public static void reporteSegurosEdadEspecific(){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Los clientes entre 40-60 años que han contratado una póliza son: ");
        int numero=0;
        for (Cliente cliente:carteraClientes)
            if (cliente.getEdad()>=40 && cliente.getEdad()<=60){
                cliente.imprimirDatosJuntos();
                numero++;
            }
        System.out.println("El total de clientes entre 40-60 años son: "+numero);    
    }

    public static void darAltaCliente(){
        Cliente nuevoCliente=new Cliente();
        nuevoCliente.darAlta(carteraClientes);
    }

    public static void darBajaCliente(){ 
        int indice;
        mostrarClientesDisp();
        System.out.println("\nIngrese la ubicación del cliente a dar de Baja: "); 
        //Manejar excepcion para otro numero
        indice=ingresaIndiceCliente();
        carteraClientes.get(indice).darBaja(carteraClientes);
    }
    
    public static void contrataSeguro(){ 
        int indiceCliente,indiceSeguro;
        System.out.println("--------------------------------------------------------------------------------------------------------");
        mostrarClientesDisp();
        System.out.println("Ingrese la ubicación del cliente a contratar el servicio: ");
        indiceCliente=ingresaIndiceCliente();
        System.out.println("\nIngrese el Seguro que se contratará:\n1: Auto\n2: Vida\n3: Propiedad");
        indiceSeguro=ingresaIndiceSeguro();

        switch(indiceSeguro){
            case 0: Auto seguroAuto=new Auto();
                    carteraClientes.get(indiceCliente).setSeguro(indiceSeguro, seguroAuto);
                    break;
            case 1: Vida seguroVida=new Vida(); 
                    carteraClientes.get(indiceCliente).setSeguro(indiceSeguro, seguroVida);
                    break;
            case 2: //Para el seguro de Propiedad
                    System.out.println("Ingrese el tipo de seguro de Terreno a registrar: ");
                    int numTipo=ingresaIndicePropiedad();
                    switch(numTipo){
                        case 1: Residencia seguroResid=new Residencia();
                                carteraClientes.get(indiceCliente).setSeguro(indiceSeguro, seguroResid); break;
                        case 2: Negocio seguroNeg=new Negocio();
                                carteraClientes.get(indiceCliente).setSeguro(3, seguroNeg);      break;
                        case 3: Terreno seguroTerr=new Terreno();
                                carteraClientes.get(indiceCliente).setSeguro(4, seguroTerr);     break;       
                    }
        }
    }

    public static void darBajaSeguro(){ 
        int indiceCliente,indiceSeguro;
        System.out.println("--------------------------------------------------------------------------------------------------------");
        mostrarClientesDisp();
        System.out.println("Ingrese la ubicación del cliente a dar de Baja el Seguro: ");
        indiceCliente=ingresaIndiceCliente();
        System.out.println("\nIngrese el Seguro que se dará de baja:\n1: Auto\n2: Vida\n3: Propiedad");
        indiceSeguro=ingresaIndiceSeguro();

        //Por si se llega a ingresar propiedad
        if (indiceSeguro==2){
            System.out.println("Ingrese el tipo de seguro de Terreno a dar de baja: ");
            int numTipo=ingresaIndicePropiedad();
            switch (numTipo){
                case 2: indiceSeguro=3;
                case 3: indiceSeguro=4;
            }
        }

        if (carteraClientes.get(indiceCliente).identificaSiContrato(indiceSeguro)){
            if (carteraClientes.get(indiceCliente).getSeguro(indiceSeguro).getDadoDe())
                carteraClientes.get(indiceCliente).getSeguro(indiceSeguro).darBaja();
            else
                System.out.println("El servicio ya está dado de baja...");
        }
        else
            System.out.println("No ha contratado el Seguro.");
    }
    
    public static void mostrarDatosPoliza(){ 
        int indiceCliente,indiceSeguro;
        System.out.println("--------------------------------------------------------------------------------------------------------");
        mostrarClientesDisp();
        System.out.println("Ingrese la ubicación del cliente a mostrar datos de la Póliza: ");
        indiceCliente=ingresaIndiceCliente();
        System.out.println("\nIngrese el Seguro que se contratará:\n1: Auto\n2: Vida\n3: Propiedad");
        indiceSeguro=ingresaIndiceSeguro();

        //Por si se llega a ingresar propiedad
        if (indiceSeguro==2){
            System.out.println("Ingrese el tipo de seguro de Terreno a mostrar: ");
            int numTipo=ingresaIndicePropiedad();
            switch (numTipo){
                case 2: indiceSeguro=3;
                case 3: indiceSeguro=4;
            }
        }

        if (carteraClientes.get(indiceCliente).identificaSiContrato(indiceSeguro)){
            if (carteraClientes.get(indiceCliente).getSeguro(indiceSeguro).getDadoDe())
                carteraClientes.get(indiceCliente).getSeguro(indiceSeguro).imprimirPoliza(carteraClientes.get(indiceCliente));
            else
                System.out.println("El servicio está dado de baja...");
        }
        else
            System.out.println("No ha contratado el Seguro.");
    }


    public static void mostrarClientesDisp(){
        System.out.println();
        System.out.println();
        for (int i=0;i<carteraClientes.size();i++)
            System.out.println((i+1)+": "+carteraClientes.get(i).imprimirDatosJuntos());
    }

    public static void main(String[] args) {
        Scanner entrada=new Scanner(System.in);
        int respInicial;

        do {
            System.out.println("--------------------------------------------------------------------------------------------------------");
            System.out.println("\nAcciones de la Aseguradora:");
            System.out.println("\n1: Dar de Alta (Cliente)");
            System.out.println("2: Dar de Baja (Cliente)");
            System.out.println("3: Dar Alta (Seguro)");
            System.out.println("4: Dar Baja (Seguro)");
            System.out.println("5: Mostrar datos de la Póliza");
            System.out.println("6: Imprimir Reporte (Seguros contratados de personas entre 40-60 años)");
            System.out.println("7: Salir");
            System.out.println("\nIngrese la opción que desee: ");

            do {
                try {
                    respInicial=entrada.nextInt();
                }
                catch(InputMismatchException ex){
                    System.out.println("Opción inválida vuelva a ingresar un valor: ");
                    entrada.nextLine(); //Para limpiar el buffer y sea capaz de pedir nuevamente el dato
                    respInicial=0;
                }
            }while(respInicial<1 || respInicial>7); 

            switch (respInicial) {
                case 1: Aseguradora.darAltaCliente();   break;
                case 2: Aseguradora.darBajaCliente();   break;
                case 3: Aseguradora.contrataSeguro();   break;
                case 4: Aseguradora.darBajaSeguro();    break;
                case 5: Aseguradora.mostrarDatosPoliza();   break;
                case 6: Aseguradora.reporteSegurosEdadEspecific();  break;
                default: break;
            }
            if (respInicial!=7){
                System.out.println("Presione ENTER");
                entrada.nextLine();
                entrada.nextLine();
            }
        }while(respInicial!=7);
    }
}

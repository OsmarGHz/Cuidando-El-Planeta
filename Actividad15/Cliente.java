import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Cliente {
    private String nombre;
    private int edad;
    private String tipo; // "Fisica" para persona física, "Moral" para persona moral
    private Poliza[] polizasDisp=new Poliza[5]; //Se manejan 0:Auto 1:Vida 2-4:Propiedad

    private Scanner entradaCliente=new Scanner(System.in);

    public Cliente(){
        System.out.println("\nIngrese el nombre del cliente: ");
        nombre=entradaCliente.nextLine();
        do {
            try {
                System.out.println("\nIngrese la edad del cliente: ");
                edad=entradaCliente.nextInt();
            } 
            catch(InputMismatchException ex){
                System.out.println("Opción inválida vuelva a ingresar un valor: ");
                entradaCliente.nextLine(); //Para limpiar el buffer y sea capaz de pedir nuevamente el dato
                edad=0;
            }
        }while(edad<=0);

        do{
            System.out.println("\nIngrese el tipo de Persona (Moral o Física): ");
            entradaCliente.nextLine(); //Se limpia el buffer
            tipo=entradaCliente.nextLine();
        }while(!tipo.equals("Física") && !tipo.equals("Moral"));
    }

    public void imprimirDatosSep() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Tipo de persona: " + tipo);
    }

    public String imprimirDatosJuntos(){
        return(nombre+"\t"+edad+" años. \t(Persona: "+tipo+")");
    }

    // Método para dar de alta a cliente en un ArrayList de clientes
    public void darAlta(ArrayList<Cliente> carteraClientes) {
        if (edad >= 18) {   //Lo agrega solo si es mayor de edad
            carteraClientes.add(this);
            System.out.println(">> El cliente: " + nombre + " ha sido dado de alta.");
        } 
        else 
            System.out.println(">> El cliente: " + nombre + " no cumple con la mayoría de edad.");
    }

    // Método para dar de baja al cliente del ArrayList
    public boolean darBaja(ArrayList<Cliente> carteraClientes) {
        if (carteraClientes.contains(this)) {   //Si el ArrayList contiene al objeto Cliente que invoque el método
            carteraClientes.remove(this);   //Lo quita de ArrayList
            System.out.println("Cliente: " + nombre + " dado de baja");
            return true;
        }
        return false;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getTipo() {
        return tipo;
    }

    public Poliza getSeguro(int indice){
        return (polizasDisp[indice]);
    }

    public boolean identificaSiContrato(int indice){
        if (polizasDisp[indice]==null)
            return false;
        return true;
    }


    //Setter
    public void setSeguro(int indice, Poliza seguro){
        polizasDisp[indice]=seguro;
    }


}
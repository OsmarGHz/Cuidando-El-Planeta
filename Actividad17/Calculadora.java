package Actividad17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculadora {
    private static String valor1,valor2;
    private static double num1,num2,resultado;

    public static void setValoresNull(){
        valor1=null;    valor2=null;
    }

    public static String verificaBR(BufferedReader br){
        String resp=null;
        try {
            resp=br.readLine();
            return resp;
        }
        catch(IOException e){
            System.out.println("Error al leer la entrada: "+e.getMessage());
        }
        return null;
    }

    public static void pideOperandos(BufferedReader br){
        System.out.println("\nIngrese el primer valor a operar: ");
        valor1=verificaBR(br);
        if (valor1==null)
            return;
        System.out.println("Ingrese el segundo valor a operar: ");
        valor2=verificaBR(br);
        if (valor2==null)
            return;
        System.out.println();
    }

    public static void convierteOperandos(){
        if (valor1!=null && valor2!=null){
            try{
                num1=Double.parseDouble(valor1);
                num2=Double.parseDouble(valor2);
            }
            catch(NumberFormatException e){
                System.out.println("** Alguno de los datos capturados fue INCORRECTO **");
                setValoresNull();
            }
        }
    }

    public static void imprimeMenu(){
        System.out.println("CALCULADORA\n");
        System.out.println("1: SUMA");
        System.out.println("2: RESTA");
        System.out.println("3: PRODUCTO");
        System.out.println("4: DIVISIÓN\n");
        System.out.println("5: Salir\n");
        System.out.println("Seleccione la opción que desee: ");
    }

    public static void suma(BufferedReader br){
        System.out.println("\n> SUMA \t\t(Número 1 + Número 2)");
        setValoresNull();
        pideOperandos(br);
        convierteOperandos();

        if (valor1!=null && valor2!=null){
            resultado=num1+num2;
            System.out.println(">> La suma de los números ingresados es: ");
            System.out.printf("%.2f",resultado);
        }  
    }

    public static void resta(BufferedReader br){
        System.out.println("\n> RESTA \t(Número 1 - Número 2)");
        setValoresNull();
        pideOperandos(br);
        convierteOperandos();

        if (valor1!=null && valor2!=null){
            resultado=num1-num2;
            System.out.println(">> La resta de los números ingresados es: ");
            System.out.printf("%.2f",resultado);
        }
    }

    public static void producto(BufferedReader br){
        System.out.println("\n> PRODUCTO \t(Número 1 * Número 2)");
        setValoresNull();
        pideOperandos(br);
        convierteOperandos();

        if (valor1!=null && valor2!=null){
            resultado=num1*num2;
            System.out.println(">> El producto de los números ingresados es: ");
            System.out.printf("%.2f",resultado);
        }
    }

    public static void division(BufferedReader br){
        System.out.println("\n> DIVISIÓN \t(Número 1 / Número 2)");
        setValoresNull();
        pideOperandos(br);
        convierteOperandos();

        if (valor1!=null && valor2!=null){
            if (num2==0)
                System.out.println(">> (El segundo operando no puede ser 0)");
            else{
                resultado=num1/num2;
                System.out.println(">> La división de los números ingresados es: ");
                System.out.printf("%.2f",resultado);
            }
        }
    }

    public static void main(String[] args) {
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);

        String respInicial=null;

        do{
            Calculadora.imprimeMenu();

            respInicial=Calculadora.verificaBR(br);

            if (respInicial!=null){
                switch(respInicial){
                    case "1": Calculadora.suma(br);       break;
                    case "2": Calculadora.resta(br);      break;
                    case "3": Calculadora.producto(br);   break;
                    case "4": Calculadora.division(br);   break;
                    case "5": break;
                    default:  System.out.println("Dato incorrecto, por favor ingrese otro");    break;
                }
            }

            System.out.println("\n\n");
        }while(!respInicial.equals("5") && respInicial!=null);

        //Cerrando el Buffered Reader al final del programa
        try{
            br.close();
        }
        catch(IOException e){
            System.out.println("Error al cerrar el flujo: "+e.getMessage());
        }
    }
}

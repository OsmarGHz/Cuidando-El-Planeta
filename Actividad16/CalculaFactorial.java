package Actividad16;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class CalculaFactorial extends JFrame{
    private JLabel lNumeroIn,lResp,lError;
    private JTextField tfNumeroIn, tfResp;
    private JButton bResp,bSalir,bCerrarError;
    private JPanel panelMuestra;
    private JDialog dError;

    //Para crear los bordes del campo de texto
    private Border bordeTF=BorderFactory.createBevelBorder(BevelBorder.LOWERED,new Color(0,255,51),new Color(0,255,51),new Color(0,153,0),new Color(0,153,0));
    
    //Constantes para las dimensiones del Frame
    private final static int HEIGTH=230;
    private final static int WIDTH=250;

    //Se crean los Action Listener para los botones
    CalculateButtonHandler calculateButtonHandler;
    SalirButtonHandler salirButtonHandler;

    public CalculaFactorial(){
        //Etiquetas
        lNumeroIn=new JLabel("Ingrese un número: ");
        lNumeroIn.setAlignmentX(CENTER_ALIGNMENT);
        lNumeroIn.setForeground(Color.white);

        lResp=new JLabel("El factorial del número Ingresado es: ");
        lResp.setAlignmentX(CENTER_ALIGNMENT);
        lResp.setForeground(Color.WHITE);

        //Campos de Texto 
        //Registra el dato dado por el usuario
        tfNumeroIn=new JTextField();
        tfNumeroIn.setAlignmentX(CENTER_ALIGNMENT);
        tfNumeroIn.setHorizontalAlignment(JTextField.CENTER);
        tfNumeroIn.setBorder(bordeTF);

        //El que mostrará el resultado
        tfResp=new JTextField();
        tfResp.setAlignmentX(CENTER_ALIGNMENT);
        tfResp.setHorizontalAlignment(JTextField.CENTER);
        tfResp.setBorder(bordeTF);
        //Para que el usuario no pueda modificar lo que hay en el campo de respuesta
        tfResp.setEditable(false); 

        //Botones de accion
        bResp=new JButton("Calcular Factorial !");
        calculateButtonHandler=new CalculateButtonHandler();
        bResp.setBackground(Color.CYAN);
        bResp.setForeground(Color.DARK_GRAY);
        bResp.setFocusPainted(false); //Para que no se vea el recuadro de adentro cuando se presione

        bResp.addActionListener(calculateButtonHandler); 
        bResp.setAlignmentX(CENTER_ALIGNMENT);
        
        
        bSalir=new JButton("Close");
        salirButtonHandler=new SalirButtonHandler();
        bSalir.addActionListener(salirButtonHandler);
        bSalir.setAlignmentX(CENTER_ALIGNMENT);

        bSalir.setBackground(Color.red);
        bSalir.setForeground(Color.yellow);
        bSalir.setFocusPainted(false);

        //Para el panel
        panelMuestra=new JPanel();
        panelMuestra.setLayout(new BoxLayout(panelMuestra, BoxLayout.Y_AXIS));
        panelMuestra.setBackground(Color.DARK_GRAY);

        //Se agregan los Objetos al Panel
        panelMuestra.add(lNumeroIn);
        panelMuestra.add(tfNumeroIn);
        
        //Crea los espacios entre los componentes (JTextField y JButton)
        panelMuestra.add(new JLabel(" "));

        panelMuestra.add(bResp);

        panelMuestra.add(new JLabel(" "));

        panelMuestra.add(lResp);
        panelMuestra.add(tfResp);

        panelMuestra.add(new JLabel(" "));

        panelMuestra.add(bSalir);

        //Se modifican los valores del Frame
        initPantalla();

        //En caso de Error
        dError=new JDialog(this,"Error",true);
        dError.setLayout(new FlowLayout(FlowLayout.CENTER));
        dError.setSize(280,100);
        //Para que al cerrarse no acabe con todo el programa y solo libere los recursos del JDialog
        dError.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 

        lError=new JLabel();

        //Boton de Cierre en caso de Error
        bCerrarError = new JButton("Close");
        /*  (e -> dError.dispose()) es otra forma de llamar al actionPerformed() 
        llama al evento y cierra el JDialog liberando sus recursos*/
        bCerrarError.addActionListener(e -> dError.dispose());
        bCerrarError.setBackground(Color.RED);
        bCerrarError.setForeground(Color.yellow);
        bCerrarError.setFocusPainted(false);

        //Se agregan los objetos al FrameError
        dError.add(lError);
        dError.add(bCerrarError);
    }

    public void initPantalla(){
        setTitle("Factorial");
        setLayout(new BorderLayout());
        add(panelMuestra,BorderLayout.CENTER);
        setSize(WIDTH,HEIGTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //ActionListener del botón para calcular el factorial
    private class CalculateButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                int valorIn;
                valorIn=Integer.parseInt(tfNumeroIn.getText());
                if (valorIn < 0 || valorIn>20){
                    /*Por si el usuario ingresa un valor entero negativo o mayor que 20 debido a que los
                    factoriales después de este valor rebasan los valores que puede admitir un "long" */
                    lError.setText("Número inválido. Ingrese uno entre 0 y 20");
                    muestraError();
                }
                else{
                    //Se calcula el factorial del número proporcionado
                    long resultado = calcularFactorial(valorIn);
                    //Se muestra el resultado
                    tfResp.setText(String.valueOf(resultado));
                }
            }
            catch(NumberFormatException ex){
                //Si ocurre que el usuario ingresa un dato incorrecto
                lError.setText("Dato inválido. Intente nuevamente");
                muestraError();
            }
        }

        private void muestraError(){
            //Se hace visible el FrameError
            dError.setVisible(true);
            tfNumeroIn.setText(""); //Pone el campo de texto para ingresar datos en blanco
        }

        //Función que calcula el factorial
        private long calcularFactorial(int num) {
            if (num==0) 
                return 1;
            return (num*calcularFactorial(num-1));
        }
    }

    //ActionListener para el Botón de salir
    private class SalirButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        CalculaFactorial cal=new CalculaFactorial();
    }
}

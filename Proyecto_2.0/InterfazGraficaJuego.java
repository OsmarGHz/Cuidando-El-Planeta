import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InterfazGraficaJuego extends JFrame{
    // 0:Panel Menu, 1:Panel FondoBasura, 2:Panel FondoPlanta, 3:Panel Presentación, 4:Panel Resultados, 5: Pierde/Gana
    private JPanel[] panelesFondo=new JPanel[6];

    private GridBagConstraints c = new GridBagConstraints();

    private ImageIcon cuidandoElPlanetaLogo = new ImageIcon("Imagenes/CuidandoElPlaneta.png");
    private ImageIcon closeIcon = new ImageIcon("Imagenes/minClose.png");
    private ImageIcon[] boteIcon = new ImageIcon[13];

    private JPanel[] pBotes = new JPanel[13];
    private JPanel[] toolBar = new JPanel[4];
    private JPanel[] bottomBar = new JPanel[4];
    private JPanel panelPresentacion = new JPanel(), panelGlobalBotes = new JPanel(), pBotesContenedor = new JPanel();
    private JPanel panelGlobalOPlanta = new JPanel(), pOPlantaContenedor = new JPanel();
    private JButton bStart;
    private JButton[] bCerrar=new JButton[2];
    private JButton[] bBotes=new JButton[13];
    private JButton[] bProcesos=new JButton[29];

    private JLabel lMenu, lTituloNivel, lDificultadNivel, lDesechosEspeciales, lPresentacion, lCarga, lInstrucciones; 
    private JLabel[] lTurnos=new JLabel[2], lStats=new JLabel[2], lDesechos=new JLabel[2], lPresentRes=new JLabel[3];
    private JLabel[] lTiempoRest=new JLabel[2], lPresentResFinal=new JLabel[2], lMuestraTiempoMyrCont=new JLabel[2];
    private JLabel[] lBotes=new JLabel[13];

    private ButtonHandlerStart startAction;
    private ButtonHandlerSalir cerrarAction;

    private ActionListener actionListener;

    private static final int WIDTH=1200, HEIGHT=700; //Y_BOTES=250;

    private volatile boolean contStart=false, presionaStart=false, presionaContenedor=false, presionaProceso=false;
    private volatile boolean dejaMostrarMyrCont=false;
    private int botonPulsadoContenedor=-1,botonPulsadoProceso=-1;

    //Se inicializan e instancian los parametros del Frame
    public InterfazGraficaJuego(){ 
        initImagenes();

        initPantalla();

        initPaneles();

        initBotones();

        initEtiquetas();
    }

    //Inicializar logos de botes
    public void initImagenes(){
        boteIcon[0] = new ImageIcon("Imagenes/ContenedorOrganicos.png");
        boteIcon[1] = new ImageIcon("Imagenes/ContenedorPapeles.png");
        boteIcon[2] = new ImageIcon("Imagenes/ContenedorVidrios.png");
        boteIcon[3] = new ImageIcon("Imagenes/ContenedorPlasticos.png");
        boteIcon[4] = new ImageIcon("Imagenes/ContenedorMetales.png");
        boteIcon[5] = new ImageIcon("Imagenes/ContenedorAceites.png");
        boteIcon[6] = new ImageIcon("Imagenes/ContenedorPinturas.png");
        boteIcon[7] = new ImageIcon("Imagenes/ContenedorBaterias.png");
        boteIcon[8] = new ImageIcon("Imagenes/ContenedorPilas.png");
        boteIcon[9] = new ImageIcon("Imagenes/ContenedorElectronicos.png");
        boteIcon[10] = new ImageIcon("Imagenes/ContenedorMedicamentos.png");
        boteIcon[11] = new ImageIcon("Imagenes/ContenedorQuimicos.png");
        boteIcon[12] = new ImageIcon("Imagenes/Contenedor Biologicos.png");
    }

    //Ajustes del Frame
    public void initPantalla(){
        setTitle("Cuidando el Planeta: El Videojuego");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    //Paneles
    public void initPaneles(){
        for (int i=0; i<panelesFondo.length;i++){
            panelesFondo[i]=new JPanel();
            panelesFondo[i].setLayout(new BorderLayout());
            //panelesFondo[i].setLayout(null);
            //panelesFondo[i].setBounds(0,0,WIDTH,HEIGHT);
            panelesFondo[i].setPreferredSize(new Dimension(WIDTH,HEIGHT));
        }

        for (int i=0;i<toolBar.length;i++){
            toolBar[i] = new JPanel();
            bottomBar[i] = new JPanel();
        }

        //Para la barra de herramientas y el start
        toolBar[0].setLayout(new BorderLayout());
        bottomBar[0].setLayout(new BorderLayout());
        toolBar[0].setBorder(new EmptyBorder(10, 10, 10, 20));
        toolBar[0].setBackground(new Color(0x75ceba));
        toolBar[3].setLayout(new BorderLayout());
        toolBar[3].setBorder(new EmptyBorder(10, 10, 10, 20));
        toolBar[3].setBackground(new Color(0x101110));

        //Para el panel de presentacion de niveles
        panelPresentacion.setBackground(Color.DARK_GRAY);
        panelPresentacion.setLayout(new GridBagLayout());

        //Para el Menu 
        panelesFondo[0].setBackground(new Color(0x7ddfc9));

        //Para el nivel
        panelesFondo[1].setBackground(Color.GRAY);
        panelesFondo[1].setVisible(false);
        toolBar[1].setLayout(new BorderLayout());
        toolBar[1].setBorder(new EmptyBorder(20,20,20,20));
        bottomBar[1].setLayout(new BorderLayout());
        bottomBar[1].setBorder(new EmptyBorder(20,20,20,20));
        panelGlobalBotes.setLayout(new GridBagLayout());
        pBotesContenedor.setLayout(new GridLayout(3, 6, 10, 10));
        c.gridx = 0; c.gridy = 1;
        panelGlobalBotes.add(pBotesContenedor,c);
        panelesFondo[1].add(toolBar[1],BorderLayout.NORTH);
        panelesFondo[1].add(bottomBar[1],BorderLayout.SOUTH);
        panelesFondo[1].add(panelGlobalBotes,BorderLayout.CENTER);

        //Para la planta Tratadora
        panelesFondo[2].setBackground(Color.YELLOW);
        panelesFondo[2].setVisible(false);
        toolBar[2].setLayout(new BorderLayout());
        toolBar[2].setBorder(new EmptyBorder(20,20,20,20));
        bottomBar[2].setLayout(new BorderLayout());
        bottomBar[2].setBorder(new EmptyBorder(20,20,20,20));
        panelGlobalOPlanta.setLayout(new GridBagLayout());
        pOPlantaContenedor.setLayout(new GridLayout(6, 1, 10, 10));
        c.gridx = 0; c.gridy = 1;
        panelGlobalOPlanta.add(pOPlantaContenedor,c);
        panelesFondo[2].add(toolBar[2],BorderLayout.NORTH);
        panelesFondo[2].add(bottomBar[2],BorderLayout.SOUTH);
        panelesFondo[2].add(panelGlobalOPlanta,BorderLayout.CENTER);

        //Para presentacion
        panelesFondo[3].setBackground(Color.DARK_GRAY);
        panelesFondo[3].setVisible(false);

        //Para los resultados
        panelesFondo[4].setBackground(Color.LIGHT_GRAY);
        panelesFondo[4].setVisible(false);
        
        /*
        for (int i=0;i<panelesFondo.length;i++)
            add(panelesFondo[i],BorderLayout.CENTER);
        */
        //Solo agregaremos el 1er panel panelesFondo[0] al frame, por BorderLayout
        add(panelesFondo[0],BorderLayout.CENTER);

        //Paneles para los botes de basura
        //int X_BOTES=20;
        for (int i=0;i<pBotes.length;i++){
            //Se instancian los botes
            pBotes[i]=new JPanel();
            pBotes[i].setLayout(new BorderLayout(0,0));
            //pBotes[i].setPreferredSize(new Dimension(129,180));
            //pBotes[i].setLocation(X_BOTES, Y_BOTES);
            pBotes[i].setBackground(Color.WHITE);
            pBotesContenedor.add(pBotes[i]);

            //X_BOTES+=90;
        }
    }

    //Botones
    public void initBotones(){
        //Boton para comenzar el juego
        bStart=new JButton("START");
        bStart.setPreferredSize(new Dimension(100,50));
        //bStart.setLocation((WIDTH-bStart.getWidth())/2, (3*HEIGHT)/4);
        bStart.setFocusable(false);
        bStart.setBackground(new Color(0x66df84));
        bStart.setForeground(Color.WHITE);
        bStart.setOpaque(true);
        bStart.setFont(new Font("Bahnschrift",Font.PLAIN,20));
        bStart.setBorder(new EmptyBorder(10, 10, 10, 10));

        bottomBar[0].add(bStart,BorderLayout.CENTER);
        bottomBar[0].setBorder(new EmptyBorder(20, 200, 40, 200));
        bottomBar[0].setBackground(new Color(0x7ddfc9));

        startAction=new ButtonHandlerStart();
        bStart.addActionListener(startAction);
        panelesFondo[0].add(bottomBar[0],BorderLayout.SOUTH);

        //botón para Salir del Juego
        initBotonesSalir();

        //Botones para Tirar
        initBotonesContenedores();

        //Botones para procesos en planta Tratadora
        initBotonesProcesos();
    }

    public void initBotonesSalir(){
        cerrarAction=new ButtonHandlerSalir();
        for (int i=0; i<bCerrar.length; i++){
            bCerrar[i]=new JButton();
            bCerrar[i].setIcon(closeIcon);
            bCerrar[i].setSize(50,50);
            //bCerrar[i].setLocation((WIDTH-bCerrar[i].getWidth())-30, bCerrar[i].getHeight()-20);
            bCerrar[i].setFocusable(false);
            bCerrar[i].setBorder(null);
            //bCerrar[i].setForeground(new Color(0, 255, 51));
            
            bCerrar[i].addActionListener(cerrarAction);
        }
        bCerrar[0].setBackground(new Color(0x75ceba));
        toolBar[0].add(bCerrar[0],BorderLayout.EAST);
        panelesFondo[0].add(toolBar[0],BorderLayout.NORTH);

        bCerrar[1].setBackground(new Color(0x101110));
        toolBar[3].add(bCerrar[1],BorderLayout.EAST);
        panelesFondo[3].add(toolBar[3],BorderLayout.NORTH);
    }

    public void initBotonesContenedores(){
        for (int i=0;i<bBotes.length;i++){
            bBotes[i]=new JButton();
            bBotes[i].addActionListener(new ButtonHandlerTirar(i));
            bBotes[i].setIcon(boteIcon[i]);
            bBotes[i].setFocusable(false);
            pBotes[i].add(bBotes[i],BorderLayout.CENTER);
        }
    }

    public void initBotonesProcesos(){
        bProcesos[0]=new JButton("Fusión");
        bProcesos[1]=new JButton("Corte");
        bProcesos[2]=new JButton("Reducir");
        bProcesos[3]=new JButton("Destilar");
        bProcesos[4]=new JButton("Triturar");
        bProcesos[5]=new JButton("Modelar");
        bProcesos[6]=new JButton("Filtrar"); 
        bProcesos[7]=new JButton("Uso final");
        bProcesos[8]=new JButton("Almacenar");


        bProcesos[9]=new JButton("Desinfectar");
        bProcesos[10]=new JButton("Clasificar");
        bProcesos[11]=new JButton("Desmontar");
        bProcesos[12]=new JButton("Extrusión");
        bProcesos[13]=new JButton("Neutralizar");
        bProcesos[14]=new JButton("Compostaje");
        
        
        bProcesos[15]=new JButton("Refinar Pulpa");
        bProcesos[16]=new JButton("Desintegración");
        bProcesos[17]=new JButton("Mantenimiento");
        bProcesos[18]=new JButton("Recuperar Aceite");
        bProcesos[19]=new JButton("Separar envases");
        bProcesos[20]=new JButton("Incinerar residuos");
        bProcesos[21]=new JButton("Crear nuevo producto");
        
        
        bProcesos[22]=new JButton("Reciclar componentes");
        bProcesos[23]=new JButton("Tratar residuos líquidos");
        bProcesos[24]=new JButton("Eliminar Contaminantes");
        bProcesos[25]=new JButton("Separar componentes");

        bProcesos[26]=new JButton("Tomar metales/plásticos");
        bProcesos[27]=new JButton("Recolectar con PRECAUCIÓN");
        bProcesos[28]=new JButton("Enviar a instalación especial");

        //int X_PROCESOS=150;
        for (int i=0;i<bProcesos.length;i++){
            bProcesos[i].addActionListener(new ButtonHandlerProcesos(i));
            bProcesos[i].setFocusable(false);
            if (i<=6){
                bProcesos[i].setSize(80,30);
                //bProcesos[i].setLocation(X_PROCESOS, 150);
                //pOPlantaContenedor.add(bProcesos[i]);
            }  
            if (i==7 || i==8){
                if (i==7){ bProcesos[i].setSize(90,30); //bProcesos[i].setLocation(X_PROCESOS, 150);
                }
                else{ bProcesos[i].setSize(100,30); //bProcesos[i].setLocation(X_PROCESOS, 150); 
                }
                //X_PROCESOS+=10;
                //pOPlantaContenedor.add(bProcesos[i]);
            }

            //X_PROCESOS+=90;   
        }
        
        //X_PROCESOS=110;
        for (int i=9; i<17;i++){
            bProcesos[i].setSize(100,30);
            //bProcesos[i].setLocation(X_PROCESOS, 210);

            if (i>=14){
                bProcesos[i].setSize(110,30);
                //bProcesos[i].setLocation(X_PROCESOS, 210);
                if (i==16){
                    bProcesos[i].setSize(120,30);
                    //bProcesos[i].setLocation(X_PROCESOS, 210);
                }
                //X_PROCESOS+=10;
            }
            //X_PROCESOS+=110;
            //pOPlantaContenedor.add(bProcesos[i]);
        }

        //X_PROCESOS=107;
        for (int i=17; i<23;i++){
            bProcesos[i].setSize(120,30);
            //bProcesos[i].setLocation(X_PROCESOS, 270);
            
            if (i>=18){
                bProcesos[i].setSize(140,30);
                //bProcesos[i].setLocation(X_PROCESOS, 270);
                
                if (i>=21){
                    bProcesos[i].setSize(160,30);
                    //bProcesos[i].setLocation(X_PROCESOS, 270);
                    //X_PROCESOS+=10;

                    if (i>=22){
                        bProcesos[i].setSize(170,30);
                    //    bProcesos[i].setLocation(X_PROCESOS, 270);
                    //    X_PROCESOS+=10;
                    }
                }

                //X_PROCESOS+=20;
            }

            //X_PROCESOS+=130;
            //pOPlantaContenedor.add(bProcesos[i]);
        }

        //X_PROCESOS=210;
        for (int i=23; i<27;i++){
            bProcesos[i].setSize(170,30);
            //bProcesos[i].setLocation(X_PROCESOS, 330);
            if (i==26){
                bProcesos[i].setSize(180,30);
                //bProcesos[i].setLocation(X_PROCESOS, 330);
                //X_PROCESOS+=10;
            }

            //X_PROCESOS+=180;
            //pOPlantaContenedor.add(bProcesos[i]);
        }
        
        //X_PROCESOS=(WIDTH/2)-215;
        for (int i=27; i<29;i++){
            bProcesos[i].setSize(210,30);
            //bProcesos[i].setLocation(X_PROCESOS, 390);

            //X_PROCESOS+=220;
            //pOPlantaContenedor.add(bProcesos[i]);
        }
    }

    //Etiquetas
    public void initEtiquetas(){
        lMenu=new JLabel();
        lMenu.setIcon(cuidandoElPlanetaLogo);
        lMenu.setHorizontalAlignment(JLabel.CENTER);
        //lMenu.setForeground(Color.BLACK);
        //lMenu.setSize(400,400);
        //lMenu.setLocation(((WIDTH-lMenu.getWidth())/2), HEIGHT*1/4);
        panelesFondo[0].add(lMenu,BorderLayout.CENTER);

        lTituloNivel = new JLabel();
        lTituloNivel.setHorizontalAlignment(JLabel.CENTER);
        lTituloNivel.setForeground(Color.WHITE);
        lTituloNivel.setFont(new Font("Bahnschrift",Font.BOLD,40));
        lTituloNivel.setBorder(new EmptyBorder(10, 10, 10, 10));

        lDificultadNivel = new JLabel();
        lDificultadNivel.setHorizontalAlignment(JLabel.CENTER);
        lDificultadNivel.setForeground(Color.WHITE);
        lDificultadNivel.setFont(new Font("Bahnschrift",Font.PLAIN,28));
        lDificultadNivel.setBorder(new EmptyBorder(10, 10, 10, 10));

        lDesechosEspeciales = new JLabel();
        lDesechosEspeciales.setHorizontalAlignment(JLabel.CENTER);
        lDesechosEspeciales.setText("Tipos de Desechos Especiales:");
        lDesechosEspeciales.setForeground(Color.WHITE);
        lDesechosEspeciales.setFont(new Font("Bahnschrift",Font.PLAIN,18));
        lDesechosEspeciales.setBorder(new EmptyBorder(10, 10, 10, 10));

        lPresentacion=new JLabel();
        lPresentacion.setHorizontalAlignment(JLabel.CENTER);
        lPresentacion.setForeground(Color.WHITE);
        lPresentacion.setFont(new Font("Bahnschrift",Font.PLAIN,15));
        lPresentacion.setBorder(new EmptyBorder(10, 10, 10, 10));
        lPresentacion.setPreferredSize(new Dimension(100, 150));

        //lPresentacion.setSize(190,200);
        //lPresentacion.setLocation(((WIDTH-lPresentacion.getWidth())/2), (HEIGHT-lPresentacion.getHeight())/2);
        c.gridx = 0; c.gridy = 0;
        panelPresentacion.add(lTituloNivel,c);
        c.gridx = 0; c.gridy = 1;
        panelPresentacion.add(lDificultadNivel,c);
        c.gridx = 0; c.gridy = 2;
        panelPresentacion.add(lDesechosEspeciales,c);
        c.gridx = 0; c.gridy = 3;
        panelPresentacion.add(lPresentacion,c);

        panelesFondo[3].add(panelPresentacion,BorderLayout.CENTER);

        //Se te indica cuando comenzará el juego
        lCarga=new JLabel();
        lCarga.setHorizontalAlignment(JLabel.RIGHT);
        lCarga.setForeground(Color.WHITE);
        lCarga.setSize(220,80);
        lCarga.setBorder(new EmptyBorder(0, 10, 40, 70));
        lCarga.setFont(new Font("Bahnschrift",Font.PLAIN,18));
        //lCarga.setLocation((WIDTH-lCarga.getWidth())-80,(HEIGHT-lCarga.getHeight())-30);
        panelesFondo[3].add(lCarga,BorderLayout.SOUTH);

        initEtiquetasTurno();

        initEtiquetasStats();

        //Hacer un metodo para tamaño personalizado
        //Etiquetas para los Botes de Basura
        initEtiquetasContenedores();

        //Etiquetas de Tiempo Restante para el jugador
        initEtiquetasTiempRest();

        //Etiquetas para el desecho
        initEtiquetasDesecho();

        lInstrucciones=new JLabel();
        lInstrucciones.setHorizontalAlignment(JLabel.CENTER);
        lInstrucciones.setSize(500,80);
        lInstrucciones.setBorder(new EmptyBorder(20,20,20,20));
        //lInstrucciones.setLocation((WIDTH-lInstrucciones.getWidth())/2,70);
        c.gridx = 0; c.gridy = 0;
        panelGlobalOPlanta.add(lInstrucciones,c);

        initEtiquetasMuestraResultados();
        initEtiquetasResultFinales();
    }

    public void initEtiquetasContenedores(){
        //int X_BOTES=20;
        for(int i=0; i<lBotes.length; i++){
            //Se instancian las etiquetas
            lBotes[i]=new JLabel();
            lBotes[i].setSize(60,30);
            //lBotes[i].setLocation(X_BOTES, Y_BOTES-50);
            lBotes[i].setBackground(new Color(255, 204, 51));
            lBotes[i].setHorizontalAlignment(JLabel.CENTER);
            lBotes[i].setOpaque(true);
            pBotes[i].add(lBotes[i],BorderLayout.SOUTH);

            //X_BOTES+=90;
        }
    }

    public void initEtiquetasTurno(){
        //Se indica el jugador al que le toca el turno
        for (int i=0;i<lTurnos.length;i++){
            lTurnos[i]=new JLabel();
            lTurnos[i].setHorizontalAlignment(JLabel.LEFT);
            lTurnos[i].setSize(220,80);
            //lTurnos[i].setLocation(30,10);
        }
        //Se agrega una etiqueta de Turno al Panel Tirar a Contenedor
        toolBar[1].add(lTurnos[0],BorderLayout.WEST);
        //Se agrega una etiqueta de Turno al Panel Planta Tratadora
        toolBar[2].add(lTurnos[1],BorderLayout.WEST);
    }

    public void initEtiquetasStats(){
        for (int i=0;i<lStats.length;i++){
            lStats[i]=new JLabel();
            lStats[i].setHorizontalAlignment(JLabel.RIGHT);
            lStats[i].setSize(220,80);
            //lStats[i].setLocation((WIDTH-lStats[i].getWidth())-50,10);
        }
        
        toolBar[1].add(lStats[0],BorderLayout.EAST);
        toolBar[2].add(lStats[1],BorderLayout.EAST);
    }
    
    public void initEtiquetasTiempRest(){
        for (int i=0;i<lTiempoRest.length;i++){
            lTiempoRest[i]=new JLabel();
            lTiempoRest[i].setHorizontalAlignment(JLabel.RIGHT);
            lTiempoRest[i].setSize(100,80);
            lTiempoRest[i].setLocation((WIDTH-lTiempoRest[i].getWidth())-50, (HEIGHT-lTiempoRest[i].getHeight())-50);
            lTiempoRest[i].setBorder(new EmptyBorder(0,20,20,20));
        }
        //Se agrega la primera etiqueta de tiempo en el panel de tirar a Contenedor
        bottomBar[1].add(lTiempoRest[0],BorderLayout.EAST);
        //Se agrega la segunda etiqueta de tiempo en el panel de Planta Tratadora
        bottomBar[2].add(lTiempoRest[1],BorderLayout.EAST);
    }

    public void initEtiquetasDesecho(){
        for (int i=0;i<lDesechos.length;i++){
            lDesechos[i]=new JLabel();
            lDesechos[i].setHorizontalAlignment(JLabel.CENTER);
            lDesechos[i].setSize(230,20);
            lDesechos[i].setLocation((WIDTH-lDesechos[i].getWidth())/2, 600);
            lDesechos[i].setBackground(new Color(153, 103, 0));
            lDesechos[i].setForeground(Color.WHITE);
            lDesechos[i].setOpaque(true);
        }
        
        bottomBar[1].add(lDesechos[0],BorderLayout.CENTER);
        bottomBar[2].add(lDesechos[1],BorderLayout.CENTER);
    }

    public void initEtiquetasMuestraResultados(){
        int Y_LOC=100;

        for (int i=0;i<lPresentRes.length;i++)
            lPresentRes[i]=new JLabel();
        
        lPresentRes[0].setSize(250,80);
        lPresentRes[1].setSize(250,200);
        lPresentRes[2].setSize(250,80);

        for (int i=0;i<lPresentRes.length;i++){
            lPresentRes[i].setVerticalAlignment(JLabel.NORTH);
            lPresentRes[i].setHorizontalAlignment(JLabel.LEFT);
            lPresentRes[i].setLocation((3*(WIDTH-lPresentRes[i].getWidth()))/4,Y_LOC);
            Y_LOC=Y_LOC+lPresentRes[i].getHeight()+10;
            panelesFondo[4].add(lPresentRes[i]);
        }

        for (int i=0;i<lMuestraTiempoMyrCont.length;i++){
            lMuestraTiempoMyrCont[i]=new JLabel();
            lMuestraTiempoMyrCont[i].setHorizontalAlignment(JLabel.RIGHT);
            lMuestraTiempoMyrCont[i].setForeground(Color.BLACK);
            lMuestraTiempoMyrCont[i].setSize(40,80);
            lMuestraTiempoMyrCont[i].setLocation((WIDTH-lMuestraTiempoMyrCont[i].getWidth())-80,(HEIGHT-lMuestraTiempoMyrCont[i].getHeight())-30);
            panelesFondo[4+i].add(lMuestraTiempoMyrCont[i]);
        }
        
    }

    public void initEtiquetasResultFinales(){
        int Y_LOC=100;

        for (int i=0;i<lPresentResFinal.length;i++)
            lPresentResFinal[i]=new JLabel();
        
        lPresentResFinal[0].setSize(250,80);
        lPresentResFinal[1].setSize(250,200);

        for (int i=0;i<lPresentResFinal.length;i++){
            lPresentResFinal[i].setVerticalAlignment(JLabel.NORTH);
            lPresentResFinal[i].setHorizontalAlignment(JLabel.LEFT);
            lPresentResFinal[i].setLocation((3*(WIDTH-lPresentResFinal[i].getWidth()))/4,Y_LOC);
            Y_LOC=Y_LOC+lPresentResFinal[i].getHeight()+10;
            panelesFondo[5].add(lPresentResFinal[i]);
        }

    }

    //Temporizador para el nivel en sí
    public void tempNivelTirar(int segs) {
        final int[] segRest={segs};
        Timer timerResp = new Timer(1000, new ActionListener() {
            int tiempoRestante = segRest[0]; // Inicializamos con el valor actual de segRest
    
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                segRest[0] = tiempoRestante; // Actualizamos segRest con el tiempo restante
                lTiempoRest[0].setText("<html>Tiempo restante: <br>" + tiempoRestante + "</html>");
                
                if (tiempoRestante <= 0) {
                    ((Timer) e.getSource()).stop(); // Detener el temporizador
                }
            }
        });
    
        // Agregar un ActionListener al campo de texto para cerrar cuando el usuario presione Enter
        for (int i=0;i<bBotes.length;i++){
            bBotes[i].addActionListener(e -> {
                timerResp.stop(); // Detener el temporizador si el usuario ingresa un valor
            });
        }

        timerResp.start();
    }
    
    public Timer tempNivelPlanta(int segs) {
        final int[] segRest={segs};
        Timer timerResp = new Timer(1000, new ActionListener() {
            int tiempoRestante = segRest[0]; // Inicializamos con el valor actual de segRest
    
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                segRest[0] = tiempoRestante; // Actualizamos segRest con el tiempo restante
                lTiempoRest[1].setText("<html>Tiempo restante: <br>" + tiempoRestante + "</html>");
                
                if (tiempoRestante <= 0) {
                    ((Timer) e.getSource()).stop(); // Detener el temporizador
                }
            }
        });
        
        
        return timerResp;
    }

    public void detenerTimerProcesos(Timer timerResp, int numResp, int[]resp){
        if (numResp==(resp.length-1))
            // Agregar un ActionListener al campo de texto para cerrar cuando el usuario presione Enter
            actionListener= e->timerResp.stop();
        else if (actionListener!=null)
            for (JButton proceso: bProcesos)
                proceso.removeActionListener(actionListener);
    }


    public void cambiaPanelTirar(){
        for (int i=0;i<panelesFondo.length;i++){
            panelesFondo[i].setVisible(false);
            if (isAncestorOf(panelesFondo[i])) remove(panelesFondo[i]);
        }
        add(panelesFondo[1],BorderLayout.CENTER);
        panelesFondo[1].setVisible(true);
    }

    public void cambiaPanelPlanta(){
        for (int i=0;i<panelesFondo.length;i++){
            panelesFondo[i].setVisible(false);
            if (isAncestorOf(panelesFondo[i])) remove(panelesFondo[i]);
        }
        add(panelesFondo[2],BorderLayout.CENTER);
        panelesFondo[2].setVisible(true);
    }

    public void cambiaPanelPresentacion(Lock lock, Condition conditionLock){
        for (int i=0;i<panelesFondo.length;i++){
            panelesFondo[i].setVisible(false);
            if (isAncestorOf(panelesFondo[i])) remove(panelesFondo[i]);
        }
        add(panelesFondo[3],BorderLayout.CENTER);
        panelesFondo[3].setVisible(true);

        // Temporizador para el conteo regresivo
        Timer timerReg = new Timer(1000, new ActionListener() {
            int tiempoRestante = 8; // 8 segundos

            @Override
            public void actionPerformed(ActionEvent event) {
                tiempoRestante--;
                
                lCarga.setText("El nivel comenzará en...  " + tiempoRestante + " segundos");

                if (tiempoRestante <= 0) {
                    ((Timer) event.getSource()).stop(); // Detener el temporizador
                    lock.lock(); // Asegura el Lock antes de llamar a signal
                    try {
                        contStart = true;
                        conditionLock.signal();
                    } finally {
                        lock.unlock(); // Libera el Lock
                    }
                }
            }
        });

        // Inicia el temporizador
        timerReg.start();
    }

    public void muestraPanelMyrCont(){
        for (int i=0;i<panelesFondo.length;i++){
            panelesFondo[i].setVisible(false);
            if (isAncestorOf(panelesFondo[i])) remove(panelesFondo[i]);
        }
        add(panelesFondo[4],BorderLayout.CENTER);
        panelesFondo[4].setVisible(true);

        final Object monitor = new Object(); // Objeto de sincronización

        Timer timerMuestraMyrCont = new Timer(1000, new ActionListener() {
            int tiempoRest = 10;

            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRest--;
                lMuestraTiempoMyrCont[0].setText(tiempoRest + " s");
                if (tiempoRest <= 0) {
                    ((Timer) e.getSource()).stop();
                    synchronized (monitor) {
                        dejaMostrarMyrCont = true; // Actualizar la condición
                        monitor.notify(); // Despertar al hilo principal
                    }
                }
            }
        });

        timerMuestraMyrCont.start();

        synchronized (monitor) {
            while (!dejaMostrarMyrCont) {
                try {
                    monitor.wait(); // Esperar la señal del temporizador
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    System.out.println("El hilo principal fue interrumpido.");
                }
            }
        }   
        dejaMostrarMyrCont=false;
    }

    public void muestraPanelResultados(){
        for (int i=0;i<panelesFondo.length;i++){
            panelesFondo[i].setVisible(false);
            if (isAncestorOf(panelesFondo[i])) remove(panelesFondo[i]);
        }
        add(panelesFondo[5],BorderLayout.CENTER);
        panelesFondo[5].setVisible(true);

        final Object monitor = new Object(); // Objeto de sincronización

        Timer timerMuestraMyrCont = new Timer(1000, new ActionListener() {
            int tiempoRest = 10;

            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRest--;
                lMuestraTiempoMyrCont[1].setText(tiempoRest + " s");
                if (tiempoRest <= 0) {
                    ((Timer) e.getSource()).stop();
                    synchronized (monitor) {
                        dejaMostrarMyrCont = true; // Actualizar la condición
                        monitor.notify(); // Despertar al hilo principal
                    }
                }
            }
        });

        timerMuestraMyrCont.start();

        synchronized (monitor) {
            while (!dejaMostrarMyrCont) {
                try {
                    monitor.wait(); // Esperar la señal del temporizador
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    System.out.println("El hilo principal fue interrumpido.");
                }
            }
        }   
        dejaMostrarMyrCont=false;
    }



    public void mostrarProcesosEsp(int[] array){
        for (int i=0;i<bProcesos.length;i++){
            if (pOPlantaContenedor.isAncestorOf(bProcesos[i])) {
                bProcesos[i].setVisible(false);
                pOPlantaContenedor.remove(bProcesos[i]);
            }
        }
        int[] elementosAleatorizados = new int[array.length];
        for (int i=0;i<array.length;i++) elementosAleatorizados[i] = array[i];
        elementosAleatorizados = Aleatorizar(elementosAleatorizados);
        for (int i=0;i<elementosAleatorizados.length;i++){
            bProcesos[elementosAleatorizados[i]].setVisible(true);
            pOPlantaContenedor.add(bProcesos[elementosAleatorizados[i]]);
        }
    }

    //Aleatorizar un arreglo
    private int[] Aleatorizar(int[] a) {
        // recorremos todo el arreglo
        for(int i = a.length - 1; i > 0; i--) {
            int randomIndex = (int)Math.floor(Math.random() * (i + 1)); // Calculamos un i aleatorio en el rando del arreglo
            int temp = a[i]; //guardamos el elemento que estamos iterando
            a[i] = a[randomIndex];// Asignamos el elemento aleatorio al índice iterado
            a[randomIndex] = temp;// Asignamos el elemento iterado al índice aleatorio
        }
        return a;
    }

    //ActionListeners
    private class ButtonHandlerStart implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            panelesFondo[0].setVisible(false);
            remove(panelesFondo[0]);
            panelesFondo[3].setVisible(true);
            add(panelesFondo[3]);
            presionaStart=true;
        } 
    }
    
    private class ButtonHandlerSalir implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        } 
    }

    private class ButtonHandlerTirar implements ActionListener{
        private int buttonIndex;

        public ButtonHandlerTirar (int indice) {
            this.buttonIndex = indice+1;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            presionaContenedor=true;
            System.out.println("Se oprimió el botón: "+buttonIndex);
            botonPulsadoContenedor=buttonIndex;
        }
    }

    private class ButtonHandlerProcesos implements ActionListener{
        private int buttonIndex;

        public ButtonHandlerProcesos (int indice) {
            this.buttonIndex = indice;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            presionaProceso=true;
            System.out.println("Se oprimió el botón: "+buttonIndex);
            botonPulsadoProceso=buttonIndex;
        }
    }
    
    //Setters
    public void setPresionaContenedor(boolean estado){
        presionaContenedor=estado;
    }

    public void setPresionaProceso(boolean estado){
        presionaProceso=estado;
    }

    public synchronized void setContStart(boolean estado){
        contStart=estado;
    }

    //Getters
    public JLabel getLTitulo(){
        return (lTituloNivel);
    }

    public JLabel getLDificultad(){
        return (lDificultadNivel);
    }

    public JLabel getLPresentacion(){
        return (lPresentacion);
    }

    public JLabel getLTurno(int i){
        return lTurnos[i];
    }

    public JLabel getLStats(int i){
        return lStats[i];
    }

    public JLabel getLBote(int i){
        return lBotes[i];
    }

    public synchronized boolean getContStart(){
        return contStart;
    }

    public boolean getPresionaStart(){
        return presionaStart;
    }

    public JLabel getLDesecho(int i){
        return lDesechos[i];
    }

    public int getBotonPulsadoContenedor(){
        return botonPulsadoContenedor;
    }

    public int getBotonPulsadoProceso(){
        return botonPulsadoProceso;
    }

    public boolean getPresionaContenedor(){
        return presionaContenedor;
    }

    public JLabel getLInstrucciones(){
        return lInstrucciones;
    }

    public boolean getPresionaProceso(){
        return presionaProceso;
    }

    public JLabel getLPresentResultados(int indice){
        return lPresentRes[indice];
    }

    public JLabel getLResultadosFinales(int indice){
        return lPresentResFinal[indice];
    }

    public JLabel getLTiempoRest(int indice){
        return lTiempoRest[indice];
    }

    public JPanel getPResultFinal(){
        return panelesFondo[5];
    }

    public JLabel getLMuestraTiempoResResultados(int indice){
        return lMuestraTiempoMyrCont[indice];
    }
}
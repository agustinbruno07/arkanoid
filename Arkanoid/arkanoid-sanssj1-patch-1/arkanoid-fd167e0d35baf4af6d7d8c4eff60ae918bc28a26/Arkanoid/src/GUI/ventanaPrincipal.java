package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ventanaPrincipal extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private static Timer TIMERjuego = null;
	private boolean LeftPressed, RightPressed;
	private JPanel Paleta;
	private JPanel pelota;
	private JPanel contentPane;
	private player Jugador;
	private int velPelotaX = 5; 
	private int velPelotaY = 5;
	private int newX, newY;
	private List<JButton> bloques = new ArrayList<>();
	private List<Integer> vidas = new ArrayList<>();
	private Image imagenFondo;  
	
	public ventanaPrincipal() {
	    
		Color[] colores = { Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW }; //hago un array con los colores posibles
		Random random = new Random();
	    getContentPane().setLayout(null);
	    setTitle("Arkanoid");
	    setSize(700,700);
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    imagenFondo = new ImageIcon("src/resources/fondo.png").getImage();
	    
	    contentPane = new JPanel() {
			@Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            if (imagenFondo != null) {
	                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
	            }
	        }
	    };
	    contentPane.setLayout(null); 
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);

	   
	    Paleta = new JPanel();
	    Paleta.setBackground(Color.BLACK); 
	    Paleta.setBounds(291, 621, 84, 16);
	    contentPane.add(Paleta);
	    Paleta.setFocusable(false);
	  
	    pelota = new JPanel();
	    pelota.setBackground(Color.WHITE); 
	    pelota.setBounds(326, 600, 10, 10);
	    contentPane.add(pelota);

	 // Bloque 1
	    JButton pos1 = new JButton("");
	    pos1.setBounds(64, 79, 111, 39);
	    Color color1 = colores[random.nextInt(colores.length)];
	    pos1.setBackground(color1);
	    contentPane.add(pos1);
	    bloques.add(pos1);
	    int vidas1 = (color1.equals(Color.RED)) ? 3 : (color1.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas1);

	    // Bloque 2
	    JButton pos2 = new JButton("");
	    pos2.setBounds(174, 79, 111, 39);
	    Color color2 = colores[random.nextInt(colores.length)];
	    pos2.setBackground(color2);
	    contentPane.add(pos2);
	    bloques.add(pos2);
	    int vidas2 = (color2.equals(Color.RED)) ? 3 : (color2.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas2);

	    // Bloque 3
	    JButton pos3 = new JButton("");
	    pos3.setBounds(283, 79, 111, 39);
	    Color color3 = colores[random.nextInt(colores.length)];
	    pos3.setBackground(color3);
	    contentPane.add(pos3);
	    bloques.add(pos3);
	    int vidas3 = (color3.equals(Color.RED)) ? 3 : (color3.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas3);

	    // Bloque 4
	    JButton pos4 = new JButton("");
	    pos4.setBounds(64, 41, 111, 39);
	    Color color4 = colores[random.nextInt(colores.length)];
	    pos4.setBackground(color4);
	    contentPane.add(pos4);
	    bloques.add(pos4);
	    int vidas4 = (color4.equals(Color.RED)) ? 3 : (color4.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas4);

	    // Bloque 5
	    JButton pos5 = new JButton("");
	    pos5.setBounds(174, 41, 111, 39);
	    Color color5 = colores[random.nextInt(colores.length)];
	    pos5.setBackground(color5);
	    contentPane.add(pos5);
	    bloques.add(pos5);
	    int vidas5 = (color5.equals(Color.RED)) ? 3 : (color5.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas5);

	    // Bloque 6
	    JButton pos6 = new JButton("");
	    pos6.setBounds(283, 41, 111, 39);
	    Color color6 = colores[random.nextInt(colores.length)];
	    pos6.setBackground(color6);
	    contentPane.add(pos6);
	    bloques.add(pos6);
	    int vidas6 = (color6.equals(Color.RED)) ? 3 : (color6.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas6);

	    // Bloque 7
	    JButton pos7 = new JButton("");
	    pos7.setBounds(394, 41, 111, 39);
	    Color color7 = colores[random.nextInt(colores.length)];
	    pos7.setBackground(color7);
	    contentPane.add(pos7);
	    bloques.add(pos7);
	    int vidas7 = (color7.equals(Color.RED)) ? 3 : (color7.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas7);

	    // Bloque 8
	    JButton pos8 = new JButton("");
	    pos8.setBounds(394, 79, 111, 39);
	    Color color8 = colores[random.nextInt(colores.length)];
	    pos8.setBackground(color8);
	    contentPane.add(pos8);
	    bloques.add(pos8);
	    int vidas8 = (color8.equals(Color.RED)) ? 3 : (color8.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas8);

	    // Bloque 9
	    JButton pos9 = new JButton("");
	    pos9.setBounds(505, 41, 111, 39);
	    Color color9 = colores[random.nextInt(colores.length)];
	    pos9.setBackground(color9);
	    contentPane.add(pos9);
	    bloques.add(pos9);
	    int vidas9 = (color9.equals(Color.RED)) ? 3 : (color9.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas9);

	    // Bloque 10
	    JButton pos10 = new JButton("");
	    pos10.setBounds(505, 79, 111, 39);
	    Color color10 = colores[random.nextInt(colores.length)];
	    pos10.setBackground(color10);
	    contentPane.add(pos10);
	    bloques.add(pos10);
	    int vidas10 = (color10.equals(Color.RED)) ? 3 : (color10.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas10);

	    // Bloque 11
	    JButton pos11 = new JButton("");
	    pos11.setBounds(64, 117, 111, 39);
	    Color color11 = colores[random.nextInt(colores.length)];
	    pos11.setBackground(color11);
	    contentPane.add(pos11);
	    bloques.add(pos11);
	    int vidas11 = (color11.equals(Color.RED)) ? 3 : (color11.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas11);

	    // Bloque 12
	    JButton pos12 = new JButton("");
	    pos12.setBounds(174, 117, 111, 39);
	    Color color12 = colores[random.nextInt(colores.length)];
	    pos12.setBackground(color12);
	    contentPane.add(pos12);
	    bloques.add(pos12);
	    int vidas12 = (color12.equals(Color.RED)) ? 3 : (color12.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas12);

	    // Bloque 13
	    JButton pos13 = new JButton("");
	    pos13.setBounds(283, 117, 111, 39);
	    Color color13 = colores[random.nextInt(colores.length)];
	    pos13.setBackground(color13);
	    contentPane.add(pos13);
	    bloques.add(pos13);
	    int vidas13 = (color13.equals(Color.RED)) ? 3 : (color13.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas13);

	    // Bloque 14
	    JButton pos14 = new JButton("");
	    pos14.setBounds(394, 117, 111, 39);
	    Color color14 = colores[random.nextInt(colores.length)];
	    pos14.setBackground(color14);
	    contentPane.add(pos14);
	    bloques.add(pos14);
	    int vidas14 = (color14.equals(Color.RED)) ? 3 : (color14.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas14);

	    // Bloque 15
	    JButton pos15 = new JButton("");
	    pos15.setBounds(505, 117, 111, 39);
	    Color color15 = colores[random.nextInt(colores.length)];
	    pos15.setBackground(color15);
	    contentPane.add(pos15);
	    bloques.add(pos15);
	    int vidas15 = (color15.equals(Color.RED)) ? 3 : (color15.equals(Color.YELLOW)) ? 2 : 1;
	    vidas.add(vidas15);
	    
	    
	    addKeyListener(this);
	    setFocusable(true);
	    Jugador = new player(291, 621);

	    TIMERjuego = new Timer(10, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            update();
	            moverPelota();
	        }
	    });
	    TIMERjuego.start();
	}
	
	private int vidasPorColor(Color color) {
	    if(color.equals(Color.RED)) return 3;
	    else if(color.equals(Color.YELLOW)) return 2;
	    else return 1; // verde y azul
	}
	
	 public void update() {
	        if (LeftPressed) {
	            Jugador.moveLeft();
	        }
	        if (RightPressed) {
	            Jugador.moveRight();
	        }
		    if(Jugador.getX() < 0 ) { 
		    	Jugador.setX(0);
		    }else if(Jugador.getX() > contentPane.getWidth() - Jugador.getWidth()) {
		        Jugador.setX(contentPane.getWidth() - Jugador.getWidth());
		    }
	        
	        Paleta.setLocation(Jugador.getX(), Jugador.getY());
	 }
	 public void moverPelota() {
		 newX = pelota.getX() + velPelotaX;//nueva posicion para X
		 newY = pelota.getY() + velPelotaY;// nueva posicion para Y 
		 //colisiones 
		if (newX<0 + 20 || newX > contentPane.getWidth() - pelota.getWidth() - 20 ) { // 20 para que se vea que choque con el borde de la imagen y no lo pase
			 velPelotaX = -velPelotaX; // cambia la direccion de la pelota al chocar con los bordes
		}
		if (newY < 0 + 15) {
				velPelotaY = -velPelotaY; // cambia la direccion de la pelota al chocar con el techo
		}
		if (pelota.getBounds().intersects(Paleta.getBounds())) {// si choca con la paleta se cambia de direccion
				velPelotaY = -velPelotaY; 
				 newY = Paleta.getY() - pelota.getHeight(); 
		}
		
		for(int i = 0; i < bloques.size(); i++) {
		    JButton bloque = bloques.get(i);
		    if(bloque.isVisible() && pelota.getBounds().intersects(bloque.getBounds())) {
		        velPelotaY = -velPelotaY; // rebote

		        // Restar vida
		        int vidaActual = vidas.get(i) - 1;
		        vidas.set(i, vidaActual);

		        // Solo ocultar si vida llega a 0
		        if(vidaActual <= 0) {
		            bloque.setVisible(false);
		        }

		        // Salir del bucle para que no reste varias veces en un solo frame
		        break;
		    }
		}
	    
		 // SI LA PELOTA SE CAE OSEA QUE PERDISTE
			if (newY > contentPane.getHeight()) {
				JOptionPane.showMessageDialog(this, "PERDISTE", "Game Over", JOptionPane.INFORMATION_MESSAGE);
				resetPaleta();
				resetPelota(); 
			}
		
		 
		 pelota.setLocation(newX, newY);
		 
	 }
	 public void resetPaleta() {
		 Jugador.setX(291); // se pone la paleta devuelta en el medio(SI PERDISTE)
	     Jugador.setY(621);
	 }
	 public void resetPelota() {
		newX = 326; // se pone la pelota devuelta en el centro(SI PERDISTE)
		newY = 600;
		velPelotaX = 3; // se reinicia la velocidad de la pelota
		velPelotaY = 3; 
	 }
	
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) LeftPressed = true;
        if (key == KeyEvent.VK_RIGHT) RightPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) LeftPressed = false;
        if (key == KeyEvent.VK_RIGHT) RightPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}


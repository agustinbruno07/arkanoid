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

		//indico una posicion fija para cada bloque y genero sus colores aleatoriamente
	    JButton pos1 = new JButton("");
	    pos1.setBounds(64, 79, 111, 39);
	    pos1.setBackground(colores[random.nextInt(colores.length)]); //toma una posicion del array hecho para setear el color
	    contentPane.add(pos1);
	    
	    JButton pos2 = new JButton("");
	    pos2.setBounds(174, 79, 111, 39);
	    pos2.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos2);
	    
	    JButton pos3 = new JButton("");
	    pos3.setBounds(283, 79, 111, 39);
	    pos3.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos3);
	    
	    JButton pos4 = new JButton("");
	    pos4.setBounds(64, 41, 111, 39);
	    pos4.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos4);
	    
	    JButton pos5 = new JButton("");
	    pos5.setBounds(174, 41, 111, 39);
	    pos5.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos5);
	    
	    JButton pos6 = new JButton("");
	    pos6.setBounds(283, 41, 111, 39);
	    pos6.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos6);
	    
	    JButton pos7 = new JButton("");
	    pos7.setBounds(394, 41, 111, 39);
	    pos7.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos7);
	    
	    JButton pos8 = new JButton("");
	    pos8.setBounds(394, 79, 111, 39);
	    pos8.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos8);
	    
	    JButton pos9 = new JButton("");
	    pos9.setBounds(505, 41, 111, 39);
	    pos9.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos9);
	    
	    JButton pos10 = new JButton("");
	    pos10.setBounds(505, 79, 111, 39);
	    pos10.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos10);
	    
	    JButton pos11 = new JButton("");
	    pos11.setBounds(64, 117, 111, 39);
	    pos11.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos11);
	    
	    JButton pos12 = new JButton("");
	    pos12.setBounds(174, 117, 111, 39);
	    pos12.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos12);
	    
	    JButton pos13 = new JButton("");
	    pos13.setBounds(283, 117, 111, 39);
	    pos13.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos13);
	    
	    JButton pos14 = new JButton("");
	    pos14.setBounds(394, 117, 111, 39);
	    pos14.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos14);
	    
	    JButton pos15 = new JButton("");
	    pos15.setBounds(505, 117, 111, 39);
	    pos15.setBackground(colores[random.nextInt(colores.length)]);
	    contentPane.add(pos15);

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


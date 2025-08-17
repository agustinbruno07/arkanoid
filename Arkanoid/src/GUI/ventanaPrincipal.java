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
	    
	    JButton B_VERDE = new JButton("");
	    B_VERDE.setBackground(Color.GREEN);
	    B_VERDE.setForeground(Color.GREEN);
	    B_VERDE.setBounds(64, 79, 111, 39);
	    contentPane.add(B_VERDE);
	    
	    JButton B_AMARILLO_1 = new JButton("");
	    B_AMARILLO_1.setBackground(Color.YELLOW);
	    B_AMARILLO_1.setForeground(Color.YELLOW);
	    B_AMARILLO_1.setBounds(174, 79, 111, 39);
	    contentPane.add(B_AMARILLO_1);
	    
	    JButton B_AZUL_2 = new JButton("");
	    B_AZUL_2.setBackground(Color.BLUE);
	    B_AZUL_2.setForeground(Color.BLUE);
	    B_AZUL_2.setBounds(283, 79, 111, 39);
	    contentPane.add(B_AZUL_2);
	    
	    JButton B_ROJO_3 = new JButton("");
	    B_ROJO_3.setBackground(Color.RED);
	    B_ROJO_3.setForeground(Color.RED);
	    B_ROJO_3.setBounds(64, 41, 111, 39);
	    contentPane.add(B_ROJO_3);
	    
	    JButton B_VERDE_4 = new JButton("");
	    B_VERDE_4.setBackground(Color.GREEN);
	    B_VERDE_4.setForeground(Color.GREEN);
	    B_VERDE_4.setBounds(174, 41, 111, 39);
	    contentPane.add(B_VERDE_4);
	    
	    JButton B_VERDE_5 = new JButton("");
	    B_VERDE_5.setBackground(Color.GREEN);
	    B_VERDE_5.setForeground(Color.GREEN);
	    B_VERDE_5.setBounds(283, 41, 111, 39);
	    contentPane.add(B_VERDE_5);
	    
	    JButton B_AMARILLO_6 = new JButton("");
	    B_AMARILLO_6.setBackground(Color.YELLOW);
	    B_AMARILLO_6.setForeground(Color.YELLOW);
	    B_AMARILLO_6.setBounds(394, 41, 111, 39);
	    contentPane.add(B_AMARILLO_6);
	    
	    JButton B_AMARILLO_7 = new JButton("");
	    B_AMARILLO_7.setBackground(Color.YELLOW);
	    B_AMARILLO_7.setForeground(Color.YELLOW);
	    B_AMARILLO_7.setBounds(394, 79, 111, 39);
	    contentPane.add(B_AMARILLO_7);
	    
	    JButton B_ROJO_8 = new JButton("");
	    B_ROJO_8.setBackground(Color.RED);
	    B_ROJO_8.setForeground(Color.RED);
	    B_ROJO_8.setBounds(505, 41, 111, 39);
	    contentPane.add(B_ROJO_8);
	    
	    JButton B_ROJO_9 = new JButton("");
	    B_ROJO_9.setBackground(Color.RED);
	    B_ROJO_9.setForeground(Color.RED);
	    B_ROJO_9.setBounds(505, 79, 111, 39);
	    contentPane.add(B_ROJO_9);
	    
	    JButton B_VERDE_10 = new JButton("");
	    B_VERDE_10.setBackground(Color.GREEN);
	    B_VERDE_10.setForeground(Color.GREEN);
	    B_VERDE_10.setBounds(64, 117, 111, 39);
	    contentPane.add(B_VERDE_10);
	    
	    JButton B_AMARILLO_11 = new JButton("");
	    B_AMARILLO_11.setBackground(Color.YELLOW);
	    B_AMARILLO_11.setForeground(Color.YELLOW);
	    B_AMARILLO_11.setBounds(174, 117, 111, 39);
	    contentPane.add(B_AMARILLO_11);
	    
	    JButton B_VERDE_12 = new JButton("");
	    B_VERDE_12.setBackground(Color.GREEN);
	    B_VERDE_12.setForeground(Color.GREEN);
	    B_VERDE_12.setBounds(283, 117, 111, 39);
	    contentPane.add(B_VERDE_12);
	    
	    JButton B_AZUL_13 = new JButton("");
	    B_AZUL_13.setBackground(Color.BLUE);
	    B_AZUL_13.setForeground(Color.BLUE);
	    B_AZUL_13.setBounds(394, 117, 111, 39);
	    contentPane.add(B_AZUL_13);
	    
	    JButton B_ROJO_14 = new JButton("");
	    B_ROJO_14.setBackground(Color.RED);
	    B_ROJO_14.setForeground(Color.RED);
	    B_ROJO_14.setBounds(505, 117, 111, 39);
	    contentPane.add(B_ROJO_14);

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

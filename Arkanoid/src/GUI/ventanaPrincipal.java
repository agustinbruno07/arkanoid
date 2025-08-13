package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
public class ventanaPrincipal extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private boolean LeftPressed, RightPressed;
	private JPanel Paleta;
	private JPanel contentPane;
	private player Jugador;
	public ventanaPrincipal() {
		getContentPane().setLayout(null);
		setSize(700, 450);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel Paleta = new JPanel();
		Paleta.setBounds(178, 234, 80, 16);
		contentPane.add(Paleta);
		addKeyListener(this);
        setFocusable(true);
        Jugador = new player(178, 234); 
	}
	
	 public void update() {
	        if (LeftPressed) {
	            Jugador.moveLeft();
	        }
	        if (RightPressed) {
	            Jugador.moveRight();
	        }
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

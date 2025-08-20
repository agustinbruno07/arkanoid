package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ventanaPrincipal extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;
    private static Timer TIMERjuego = null;
    private boolean LeftPressed, RightPressed;
    private JPanel Paleta;
    private JPanel contentPane;
    private JLabel nivelLabel; // Etiqueta para mostrar el nivel actual
    private player Jugador;
    private ArrayList<JPanel> pelotas; // Lista de pelotas
    private ArrayList<int[]> velocidades; // Lista de velocidades para cada pelota
    private Map<JButton, Integer> colisionesBotones; // Mapa para rastrear colisiones
    private Image imagenFondo;
    private int nivelActual; // Variable para rastrear el nivel actual
    private int puntaje; // Variable para el puntaje
    private JLabel puntajeLabel; // Etiqueta para mostrar el puntaje

    public ventanaPrincipal() {
        pelotas = new ArrayList<>();
        velocidades = new ArrayList<>();
        colisionesBotones = new HashMap<>(); //mapa para rastrear colisiones
        nivelActual = 1; // Iniciar en el nivel 1
        puntaje = 0; // Puntaje inicial

        Color[] colores = { Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW };
        Random random = new Random();

        getContentPane().setLayout(null);
        setTitle("Arkanoid");
        setSize(700, 700);
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

        // Etiqueta para mostrar el nivel actual
        nivelLabel = new JLabel("Nivel: 1");
        nivelLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nivelLabel.setForeground(Color.WHITE);
        nivelLabel.setBounds(49, 580, 200, 30);
        contentPane.add(nivelLabel);

        // Etiqueta para mostrar el puntaje
        puntajeLabel = new JLabel("Puntaje: 0");
        puntajeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        puntajeLabel.setForeground(Color.WHITE);
        puntajeLabel.setBounds(49, 557, 200, 30);
        contentPane.add(puntajeLabel);

        // Crear la primera fila de bloques
        crearBloques();

        addKeyListener(this);
        setFocusable(true);
        Jugador = new player(291, 621);

        // Crear la primera pelota justo encima de la paleta
        agregarPelota(Paleta.getX() + Paleta.getWidth() / 2 - 5, Paleta.getY() - 10, 5, 5);

        TIMERjuego = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                moverPelotas();
                verificarColisiones();
            }
        });
        TIMERjuego.start();
    }

    private void agregarPelota(int x, int y, int velX, int velY) {
        JPanel nuevaPelota = new JPanel();
        nuevaPelota.setBackground(Color.WHITE);
        nuevaPelota.setBounds(x, y, 10, 10);
        contentPane.add(nuevaPelota);
        pelotas.add(nuevaPelota);
        velocidades.add(new int[] { velX, velY });
    }

    public void update() {
        if (LeftPressed) {
            Jugador.moveLeft();
        }
        if (RightPressed) {
            Jugador.moveRight();
        }
        if (Jugador.getX() < 0) {
            Jugador.setX(0);
        } else if (Jugador.getX() > contentPane.getWidth() - Jugador.getWidth()) {
            Jugador.setX(contentPane.getWidth() - Jugador.getWidth());
        }
        Paleta.setLocation(Jugador.getX(), Jugador.getY());
    }

    public void moverPelotas() {
        for (int i = 0; i < pelotas.size(); i++) {
            JPanel pelota = pelotas.get(i);
            int[] velocidad = velocidades.get(i);

            int newX = pelota.getX() + velocidad[0];
            int newY = pelota.getY() + velocidad[1];

            // Colisiones con bordes
            if (newX < 0 + 20 || newX > contentPane.getWidth() - pelota.getWidth() - 20) {
                velocidad[0] = -velocidad[0];
            }
            if (newY < 0 + 15) {
                velocidad[1] = -velocidad[1];
            }
            if (pelota.getBounds().intersects(Paleta.getBounds())) {
                velocidad[1] = -velocidad[1];
                newY = Paleta.getY() - pelota.getHeight();
            }

            // Si la pelota se sale del área de juego
            if (newY > contentPane.getHeight()) {
                contentPane.remove(pelota); // Eliminar del panel
                pelotas.remove(i); // Eliminar de la lista
                velocidades.remove(i); // Eliminar su velocidad
                i--; // Ajustar índice tras eliminación

                // Verificar si quedan pelotas
                if (pelotas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "PERDISTE", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    reiniciarJuego(); // Reiniciar el juego
                    return;
                }
            } else {
                pelota.setLocation(newX, newY);
            }
        }
    }

    public void verificarColisiones() {
        Set<JPanel> pelotasProcesadas = new HashSet<>(); //evita que una misma pelota destruya varios bloques

        for (int i = 0; i < pelotas.size(); i++) {
            JPanel pelota = pelotas.get(i);

            if (pelotasProcesadas.contains(pelota)) {
                continue; // Saltar si ya se procesó esta pelota
            }

            for (JButton boton : new ArrayList<>(colisionesBotones.keySet())) { // Iterar sobre una copia
                if (boton.isVisible() && pelota.getBounds().intersects(boton.getBounds())) {
                    int colisionesRestantes = colisionesBotones.get(boton) - 1;

                    if (colisionesRestantes <= 0) {
                        if (boton.getBackground().equals(Color.BLUE)) {
                            // Agregar una nueva pelota al colisionar con un botón azul
                            agregarPelota(pelota.getX(), pelota.getY(), 5, 5);
                        }
                        boton.setVisible(false); // Eliminar botón
                        colisionesBotones.remove(boton); // Eliminar del mapa
                        contentPane.remove(boton); // Eliminar del panel
                        puntaje += 10; // Sumar 10 puntos por bloque destruido
                        puntajeLabel.setText("Puntaje: " + puntaje); // Actualizar etiqueta
                    } else {
                        colisionesBotones.put(boton, colisionesRestantes); // Actualizar colisiones restantes
                    }

                    velocidades.get(i)[1] = -velocidades.get(i)[1]; // Cambiar dirección de la pelota
                    pelotasProcesadas.add(pelota); // Marcar la pelota como procesada
                    break; // Salir del bucle interno
                }
            }
        }

        contentPane.repaint(); // Actualizar el panel
        verificarGanador(); // Verificar si el jugador ganó
    }

    public void resetPaleta() {
        Jugador.setX(291);
        Jugador.setY(621);
    }

    public void resetPelotas() {
        for (JPanel pelota : pelotas) {
            contentPane.remove(pelota);
        }
        pelotas.clear();
        velocidades.clear();
        // Agregar la pelota justo encima de la paleta
        agregarPelota(Paleta.getX() + Paleta.getWidth() / 2 - 5, Paleta.getY() - 10, 5, 5);
    }
	// Método para reiniciar el juego
    public void reiniciarJuego() {
        nivelActual = 1;
        puntaje = 0;
        puntajeLabel.setText("Puntaje: 0");
        nivelLabel.setText("Nivel: 1");
        colisionesBotones.clear();
        contentPane.removeAll();
        contentPane.add(Paleta);
        contentPane.add(nivelLabel);
        contentPane.add(puntajeLabel);
        crearBloques();
        resetPaleta();
        pelotas.clear();
        velocidades.clear();
        agregarPelota(Paleta.getX() + Paleta.getWidth() / 2 - 5, Paleta.getY() - 10, 5, 5);
        contentPane.repaint();
        TIMERjuego.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT)
            LeftPressed = true;
        if (key == KeyEvent.VK_RIGHT)
            RightPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT)
            LeftPressed = false;
        if (key == KeyEvent.VK_RIGHT)
            RightPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void eliminarPelotas() {
        for (JPanel pelota : pelotas) {
            contentPane.remove(pelota);
        }
        pelotas.clear();
        velocidades.clear();
    }

    public void verificarGanador() {
        boolean quedanBotones = colisionesBotones.keySet().stream().anyMatch(JButton::isVisible);

        if (!quedanBotones) {
            TIMERjuego.stop(); // Detener el juego temporalmente
            eliminarPelotas(); // Eliminar todas las pelotas
            puntaje += 100; // Sumar 100 puntos al pasar de nivel
            puntajeLabel.setText("Puntaje: " + puntaje); // Actualizar etiqueta
            int respuesta = JOptionPane.showConfirmDialog(this, "¡Ganaste el Nivel " + nivelActual + "! ¿Quieres pasar al siguiente nivel?", "Nivel completado", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                nivelActual++; // Incrementar el nivel
                siguienteNivel();
            } else {
                System.exit(0); // Salir del juego
            }
        }
    }

    public void siguienteNivel() {
        colisionesBotones.clear();
        contentPane.removeAll();
        contentPane.add(Paleta);
        contentPane.add(nivelLabel);
        contentPane.add(puntajeLabel); // Volver a agregar la etiqueta de puntaje

        crearBloques();

        // Limpiar pelotas y velocidades
        pelotas.clear();
        velocidades.clear();
        // Calcular velocidad según el nivel, con límite máximo
        int velocidadBase = 5 + nivelActual - 1;
        int velocidadMaxima = 12;
        if (velocidadBase > velocidadMaxima) {
            velocidadBase = velocidadMaxima;
        }
        // Agregar una nueva pelota justo encima de la paleta con velocidad aumentada
        agregarPelota(Paleta.getX() + Paleta.getWidth() / 2 - 5, Paleta.getY() - 10, velocidadBase, velocidadBase);

        nivelLabel.setText("Nivel: " + nivelActual);
        contentPane.repaint(); // Actualizar el panel
        TIMERjuego.start(); // Reiniciar el temporizador
    }


    private void crearBloques() {
    int filas = 1;
    int columnas = 5;

    if (nivelActual == 2) {
        filas = 2;
    } else if (nivelActual == 3) {
        filas = 3;
    } else if (nivelActual == 4) {
        filas = 4;
    } else if (nivelActual >= 5) {
        filas = 5;
    }

    Random random = new Random();

    for (int fila = 0; fila < filas; fila++) {
        for (int col = 0; col < columnas; col++) {
            JButton boton = new JButton("");
            boton.setBounds(64 + (col * 111), 41 + (fila * 39), 111, 39);

            // Ajustar probabilidades según el nivel
            Color color;
            int probabilidad = random.nextInt(100); // Generar un número entre 0 y 99
            if (nivelActual <= 2) {
                // Más verdes y azules en niveles bajos
                if (probabilidad < 50) {
                    color = Color.GREEN; // 50% probabilidad
                } else if (probabilidad < 80) {
                    color = Color.BLUE; // 30% probabilidad
                } else if (probabilidad < 90) {
                    color = Color.YELLOW; // 10% probabilidad
                } else {
                    color = Color.RED; // 10% probabilidad
                }
            } else {
                // Más rojos y amarillos en niveles altos
                if (probabilidad < 30) {
                    color = Color.GREEN; // 30% probabilidad
                } else if (probabilidad < 50) {
                    color = Color.BLUE; // 20% probabilidad
                } else if (probabilidad < 80) {
                    color = Color.YELLOW; // 30% probabilidad
                } else {
                    color = Color.RED; // 20% probabilidad
                }
            }

            boton.setBackground(color);
            contentPane.add(boton);

            int colisionesNecesarias = switch (color.toString()) {
                case "java.awt.Color[r=255,g=0,b=0]" -> 3; // Rojo
                case "java.awt.Color[r=255,g=255,b=0]" -> 2; // Amarillo
                case "java.awt.Color[r=0,g=255,b=0]" -> 1; // Verde
                default -> 1; // Azul
            };
            colisionesBotones.put(boton, colisionesNecesarias);
        }
    }
    }

}

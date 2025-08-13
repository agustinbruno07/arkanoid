package GUI;

public class player {
    public static final int WIDTH = 10;   // ancho de la paleta
    public static final int HEIGHT = 79;  // alto de la paleta
    private int x;  
    private int y;  
    private final int speed = 7; // Velocidad de movimiento

    public player(int x, int y) { 
        this.x = x;
        this.y = y;
    }
    public void moveLeft() { 
        x -= speed;
    }

    public void moveRight() { 
        x += speed;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return WIDTH; }
    public int getHeight() { return HEIGHT; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}   
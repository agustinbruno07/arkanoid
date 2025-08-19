import GUI.ventanaPrincipal;
import java.awt.EventQueue;
public class mainArkanoid {
	public static void main(String[] args) {
	       verArkanoid();
	       
		}
	 private static void verArkanoid() {
	        EventQueue.invokeLater(new Runnable() { 
	            public void run() {
	                try {
	                     ventanaPrincipal ventana = new ventanaPrincipal(); 
	                    ventana.setVisible(true); 
	                } catch (Exception e) {
	                    e.printStackTrace(); 
	                }
	            }
	        });
	    }
}   
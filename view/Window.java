package view;
 
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
 
public class Window extends JFrame implements MouseListener {

  Mouse mouse = new Mouse();
 
  public Window(){                
    this.setTitle("Hellfest 2014");
    this.setSize(800, 600);
    this.setLocationRelativeTo(null);               
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(new Sprite());

    this.setVisible(true);
    addMouseListener(this);
  }
 
  public void mousePressed(MouseEvent e) {
       
    }

    public void mouseReleased(MouseEvent e) {
       
    }

    public void mouseEntered(MouseEvent e) {
       
    }

    public void mouseExited(MouseEvent e) {
       
    }
 
  public void mouseClicked(MouseEvent e) {
          System.out.println("coordinates");
       mouse.setClick(e.getX(),e.getY());
       System.out.println("x: "+ e.getX() + "y: " + e.getY());
  }
}

	
	


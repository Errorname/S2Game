package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Sprite extends JPanel {
  public void paintComponent(Graphics g){
    try {
      Image img = ImageIO.read(new File("soleil1.jpg"));
      Image imgn = ImageIO.read(new File("liliana.jpg"));
      //Pour une image de fond
      g.drawImage(imgn, 0, 0, this.getWidth(), this.getHeight(), this);
      g.drawImage(img, 300, 400, 200, 200, this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }               
}




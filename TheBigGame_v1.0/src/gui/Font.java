package gui;

import core.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
*This class define font
*
*@author B Team
*@version 1.0
*/
public class Font {
	public static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ   " + "0123456789-.!?/%$\\=*+,;:()&#\"'";
	
    /**Getter to get string width
    *@return length of letters
    */ 
	public static int getStringWidth(String s) {
        return s.length() * 8;
    }
    

    public static BufferedImage img = GameView.loadImage("images/gamfont.png");

    /**Constructor of class Font 
    */ 
    private Font() {
    
    }

    /**Method which draw the letters
    *@param g
    *@param msg
    *@param x
    *@param y
    */ 
    public static void draw(Graphics2D g, String msg, int x, int y) {
        msg = msg.toUpperCase();
        int length = msg.length();
        for (int i = 0; i < length; i++) {
            int c = letters.indexOf(msg.charAt(i));
            if (c < 0) continue;
            //screen.blit(Art.font[c % 29][c / 29], x, y);
            BufferedImage ret = img.getSubimage((c%29)*16,(c/29)*16,16,16);
            g.drawImage(ret, x, y, null);
            x += 16;
        }
    }
}

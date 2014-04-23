package core;
import javax.swing.JFrame;

public class GameApp extends JFrame {
	private static final long serialVersionUID = 1L;

	public GameApp() {
		int width = 800;
		int height = 600;
		setSize(width, height);
		setResizable(false);
		setTitle("TheBigGame");
		GameControleur controleur = new GameControleur(width, height);
		add(controleur);
		revalidate(); // strange
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main (String[] args) {
		new GameApp();
	}
}

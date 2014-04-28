package beditor;

import beditor.view.*;
import beditor.controller.*;
import java.util.Locale;
import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;
import javax.swing.UIManager;
public class BEditor
{
	public static void main(String args[])
	{
		Locale.setDefault(Locale.ENGLISH);
		MainWindow window = new MainWindow();
		ButtonEventsManager buttons = new ButtonEventsManager(window);
	}
}

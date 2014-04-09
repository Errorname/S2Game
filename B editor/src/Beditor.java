import view.*;
import controller.*;

public class Beditor
{
	public static void main(String args[])
	{
		MainWindow window = new MainWindow();
		ButtonEventsManager buttons = new ButtonEventsManager(window);
	}
}

package beditor;

import javax.swing.*;
import java.awt.*;
import beditor.view.*;
import beditor.controller.*;
import java.util.Locale;

public class BEditor
{
	public static void main(String args[])
	{
		Locale.setDefault(Locale.ENGLISH);
		MainWindow window = new MainWindow();
	}
}

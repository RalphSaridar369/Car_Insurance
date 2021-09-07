import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


// search/save options will give an error if the path isn't edited to the local drive 

public class Main {
	public static void main (String[] args){
		
		MainWindow run = new MainWindow();
		run.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		run.getContentPane();
		run.pack();
		run.setVisible(true);
		ImageIcon img = new ImageIcon("C:\\Users\\Ralph\\eclipse-workspace\\Project2\\src\\logoco.png");
		run.setIconImage(img.getImage());
		
	}
}
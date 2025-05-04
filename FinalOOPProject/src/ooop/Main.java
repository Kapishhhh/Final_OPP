package ooop;




import java.awt.FlowLayout;

import javax.swing.JFrame;


import uk.ac.leedsbeckett.oop.LBUGraphics;


public class Main extends LBUGraphics {
	

//create and setup main class
	public Main()
	{
		JFrame MainFrame = new JFrame();        // create main window    
		MainFrame.setLayout(new FlowLayout());     
		MainFrame.add(this);                  // add turtle graphics to panel    
		MainFrame.setSize(850,450);                //sets window size
		MainFrame.setVisible(true);   // shows the windows
		about();         // displays graphics

	}
	public static void main(String[] args) {
		new Main();
	}
	@Override
	
    //   handles turtle commands 

	public void processCommand(String arg0) {
		// TODO Auto-generated method stub
		
	}




}



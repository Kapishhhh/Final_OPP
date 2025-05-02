package ooop;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JOptionPane;   

import uk.ac.leedsbeckett.oop.LBUGraphics;

public class GraphicsSystem  extends LBUGraphics{
	public ArrayList<String> list;
	public int size;
	public ArrayList<String> commandList=new ArrayList<String>();
	public ArrayList<String> arr = new ArrayList<String>(
		    Arrays.asList("clock",  "circle", "equilateral", "penwidth", "name", "image", "save", "load", "screenshot", "triangle", "square", "pencolor", "about", "pendown", "penup", "left", "right", "move", "reverse", "green", "red", "white", "blue", "reset", "clear", "help","nepal"));

	

	public GraphicsSystem()
	{
		JFrame MainFrame = new JFrame();            // create a window to show the turtle panel
		MainFrame.setLayout(new FlowLayout());     // not completely required

		MainFrame.add(this);                       // "this" means the  object, so we add the turtle panel to it

		MainFrame.setSize(850,450);                // set the window size so we can see it properly

		MainFrame.setVisible(true);   

	
		
		JMenuBar menuBar = new JMenuBar(); // makess  menu bar
		JMenu fileMenu = new JMenu("File"); // makes   ffile menu

		// Saving cmd 
		JMenuItem saveItem = new JMenuItem("Save Commands");
		saveItem.addActionListener(e -> saveCommands(commandList));

		// Loading cmd 
		JMenuItem loadItem = new JMenuItem("Load Commands");
		loadItem.addActionListener(e -> loadCommands());

		// Adding the items to the file menu using command save and load
		fileMenu.add(saveItem); 
		fileMenu.add(loadItem);

		// Adding the file menu to the menu bar
		menuBar.add(fileMenu);

		// set the menu bar at the top of the window
		MainFrame.setJMenuBar(menuBar);
		MainFrame.setJMenuBar(menuBar);
		MainFrame.setLayout(new FlowLayout()); // arrange components in a row, one after another
		MainFrame.add(this);
		MainFrame.setSize(850,450);
		MainFrame.setVisible(true);
	
	}
	
	


	public void saveCommands(ArrayList<String>   commandList) { // method save
		JFileChooser fileChooser = new JFileChooser(); // create file save
		fileChooser.setDialogTitle("Save Command File");
		int userSelection = fileChooser.showSaveDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			try {
				Files.write(fileToSave.toPath(), commandList);
			} catch (IOException e) {                            // handle errors if there's an issue saving the file

				JOptionPane.showMessageDialog(null, "Sorry, File could not be saved", "Error", JOptionPane.ERROR_MESSAGE);
			}}
	}

	public void loadCommands() {		// command loads the command 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Load Command File");
		int userSelection = fileChooser.showOpenDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {
				Scanner scan = new Scanner(selectedFile);
				while(scan.hasNextLine()) {
					String line = scan.nextLine();
					processCommand(line);
				}
				scan.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, " Sorry, File could not be loaded", "File load error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	public void saveImage() {		//this method saves the image with an picture.png file name in the given location of user pc
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Image");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int userSelection = fileChooser.showSaveDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getAbsolutePath() + "/picture.png";
			try {
				Thread.sleep(120);
				Robot r = new Robot(); // create a Robot object which takes actions and take screenshots
				Rectangle capture = new Rectangle(0, 0, 900, 900);
				BufferedImage Image = r.createScreenCapture(capture); // captures ss
				ImageIO.write(Image, "png", new File(path));
				System.out.println("Screenshot saved");
			} catch (AWTException e) {
				System.err.println(" Sorry, Could not create Robot instance: " + e.getMessage());
			} catch (IOException e) {
				System.err.println(" Sorry, Could not write image to file: " + e.getMessage());
			} catch (InterruptedException e) {
				System.err.println("Thread interrupted while sleeping: " + e.getMessage());
			}
		}
	}

	public void loadImage() throws IOException {  //load image from files
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Open Image");
	    int userSelection = fileChooser.showOpenDialog(null);
	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        BufferedImage myPicture = ImageIO.read(selectedFile);

	        // Set the image to turtle 
	        this.setBufferedImage(myPicture);

	     // optionall: directly draw the image on the canvas
	        Graphics g = this.getGraphics(); // get turtle  graphics
	        g.drawImage(myPicture, 0, 0, this); // draw the image 
	    }
	}
	//@override
	public void about() {		//this method is a overriden about method
		super.about();
		getGraphicsContext().drawString("Kapish Sah",200,100);


	}
	
	// Equilateral triangle


	public void EquilateralTriangle(int side) {		// displays the equiletral triangle
		this.drawOn();
		this.forward(side);
		this.left(120);
		this.forward(side);
		this.left(120);
		this.forward(side);
		this.left(120);
	

	}
	public void Triangle(int a,int b ,int c) {
		 double angleA = Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2.0 * b * c)));
	     double angleB = Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2.0 * a * c)));
	    double angleC = Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2.0 * a * b)));
	   
	this.drawOn();
	this.forward(a);
	this.left((int)(180.0- angleC));
	this.forward(b);
	this.left((int)(180.0-angleA));
	this.forward(c);
	this.left((int)(180.0-angleB));
	
	
	
	}
	public void myName() {
		Graphics g = getGraphics();
        g.setColor(Color.RED);
        Font font = new Font("Arial", Font.BOLD,50);
        g.setFont(font);
        g.drawString("KAPISH", 320, 390);
        
        
}
	
	// Square
	public void square(int side) {
		this.drawOn();
		this.forward(side);
		this.left(90);
		this.forward(side);
		this.left(90);
		this.forward(side);
		this.left(90);
		
		this.forward(side);
		this.left(90);



	}
	
	
  
     // Clock shapee
 
 
     public void clock() {
	 this.drawOn();
	 this.circle(150);
	 this.penwidth(5);
	 this.circle(5);
	 this.left(180);
	 this.drawOff();
	 this.forward(160);
	 this.left(180);
	 this.drawOn();
	 this.penwidth(3);
	 this.forward(20);
	 this.drawOff();
	 this.forward(120);
	 this.left(90);
	 this.forward(160);
	 this.left(180);
	 this.drawOn();
	 this.forward(20);
	 this.drawOff();
	 this.forward(140);
	 this.left(90);
	 this.forward(170);
	 this.left(180);
	 this.drawOn();
	 this.forward(20);
	 this.drawOff();
	 this.forward(140);
	 this.left(90);
	 this.forward(150);
	 this.left(180);
	 this.drawOn();
	 this.forward(20);
	 this.drawOff();
	 this.forward(140);
	 this.left(60);
	 this.forward(150);
	 this.left(180);
	 this.drawOn();
	 this.forward(20);
	 this.drawOff();
	 this.forward(150);	
	 this.left(160);
	 this.forward(165);
	 this.left(180);
	 this.drawOn();
	 this.forward(20);
	 this.drawOff();
	 this.forward(140);
	 this.left(120);
	 this.forward(165);
	 this.left(180);
	 this.drawOn();
	 this.forward(20);
	 this.drawOff();
	 this.forward(140);
	 this.left(140);
	 this.forward(160);
	 this.left(180);
	 this.drawOn();
	 this.forward(20);
	 this.drawOff();
	 this.forward(140);
	 this.left(110);
	 this.forward(160);
	 this.left(180);
	 this.drawOn();
	 this.forward(20);
	 this.drawOff();
	 this.forward(140);
	 this.left(150);
	 this.forward(160);
	 this.left(180);
	 this.drawOn();
	 this.forward(20);
	 this.drawOff();
	 this.forward(140);
     this.left(120);
     this.drawOff();
     this.forward(150);
     this.left(180);
     this.drawOn();
     this.forward(20);
     this.drawOff();
     this.forward(140);
     this.left(160);
     this.drawOff();
     this.forward(160);
     this.left(180);
     this.drawOn();
     this.forward(20);
     this.drawOff();
     this.forward(140);
     this.left(120);
     this.penwidth(9);
     this.drawOn();
     this.forward(70);
     this.drawOff();
     this.right(50);
     this.forward(100);
     this.right(160);
     this.forward(160);
     this.left(60);
     this.drawOn();
     this.forward(120);
     this.drawOff();
    this.left(100);
    this.forward(200);

    }
     
    
 
 //thickness

	public void	penwidth(int width) {
     this.setStroke(width); // set the thickness of the drawing pen

	}

	
	public void pencolour(int red,int green,int blue) {		//this method gives the rgb color to the pen
		this.setPenColour(new Color(red,green,blue));
	}
	

	public void processCommand(String command)   
	//this method must be provided because LBUGraphics will call it when it's JTextField is used
	{


		list=new ArrayList<String>();  // create a new list to hold parts of the command
		String parts[]=command.split(" ");  // split the command by spaces

		for (int i=0;i<parts.length;i++) 
		{
			list.add(parts[i]);    // add each part to the list
		}
		size=list.size();  // store the size of the command parts
		evaluate(command); // process the command
	}

	public void evaluate(String val) {		// checks method and run 

		try {

			if(arr.contains(list.get(0)))  // // check if the first word in the command is a valid keyword
	        
			{

				switch(list.get(0)) // decide what action to take based on the command
				{
				case "move":
				    if (size == 1) {
				        JOptionPane.showMessageDialog(null, "Please Enter Some value", "Invalid parameter", JOptionPane.ERROR_MESSAGE);
				    } else {
				    	// check if the value given after the command is a positive number

				        if (Integer.parseInt(list.get(1)) > 0) {
				            forward(Integer.parseInt(list.get(1)));
				            System.out.println("Turtle moved " + list.get(1) + " forward (move) !....");
				            commandList.add(val);
				        } else {
				            JOptionPane.showMessageDialog(null, "You entered negative parameter", "Invalid parameter", JOptionPane.ERROR_MESSAGE);
				        }
				    }

						break;

				case "right":
					if(size==1) {
						right(90);
						commandList.add(val);
					}



					else
					{
						// check if the value given after the command is a positive number

						if(Integer.parseInt(list.get(1))>0) {
							right(Integer.parseInt(list.get(1)));
							System.out.println("Turtle turned "+list.get(1)+" right !....");
							commandList.add(val);
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "You entered negative parameter","Invalid parameter",JOptionPane.ERROR_MESSAGE);
						}
					}


					break;

				case "reverse":
				    if (size == 1) {
				        JOptionPane.showMessageDialog(null, "Please Enter Some value", "Invalid parameter", JOptionPane.ERROR_MESSAGE);
				    } else {
				        if (Integer.parseInt(list.get(1)) > 0) {
				            forward(-Integer.parseInt(list.get(1)));
				            System.out.println("Turtle moved " + list.get(1) + " backward (reverse) !....");
				            commandList.add(val);
				        } else {
				            JOptionPane.showMessageDialog(null, "You entered negative parameter", "Invalid parameter", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				    break;

				case "left":
				    if(size == 1) {
				        left(90);
				        commandList.add(val);
				    } else {
				        if(Integer.parseInt(list.get(1)) > 0) {
				            left(Integer.parseInt(list.get(1)));
				            System.out.println("Turtle turned " + list.get(1) + " left !....");
				            commandList.add(val);
				        } else {
				            JOptionPane.showMessageDialog(null, "You entered negative parameter", "Invalid parameter", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				    break;

				    
				case "circle":
					if(size==1) 
					{

						JOptionPane.showMessageDialog(null, "Please Enter Some value","Invalid parameter",JOptionPane.ERROR_MESSAGE);

					}
					else 
					{
						if(Integer.parseInt(list.get(1))>0) {
							this.drawOn();
							circle(Integer.parseInt(list.get(1)));
							System.out.println("Turtle make circle  "+list.get(1));
							commandList.add(val); // save this command

						}
						else 
						{
							JOptionPane.showMessageDialog(null, "You entered negative parameter","Invalid parameter",JOptionPane.ERROR_MESSAGE);

						}

					}
					break;

				case "penup":
					drawOff(); 
					commandList.add(val);
					System.out.println("Pen is up now!!....");
					break;

				case "pendown":
					drawOn();
					commandList.add(val);
					System.out.println("Pen is down now!!....");
					break;

				case "blue":
					setPenColour(Color.blue);
					commandList.add(val);
					System.out.println("Turtle trail is now set to blue!....");
					break;

				case "green":
					setPenColour(Color.green);
					commandList.add(val); // save this command

					System.out.println("Turtle trail is now set to green!....");
					break;

				case "red":
					setPenColour(Color.red);
					commandList.add(val); // save this command

					System.out.println("Turtle trail is now set to red!....");
					break;

				case "white":
					setPenColour(Color.white);
					commandList.add(val); // save this command

					System.out.println("Turtle trail is now set to white!....");
					break;
				case "clear":
				    if (!commandList.isEmpty()) {
				        System.out.println("Warning: You have unsaved changes."); // Console warning (Prompt 1)

				        int choice = JOptionPane.showConfirmDialog(null, "Do you want to save changes before clearing?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);

				        if (choice == JOptionPane.YES_OPTION) {
				            saveCommands(commandList);
				            JOptionPane.showMessageDialog(null, "Changes saved.");
				            clear();
				            System.out.println("Trails have been cleared.");
				            commandList.add(val); // still record the clear command
				        } else if (choice == JOptionPane.NO_OPTION) {
				            JOptionPane.showMessageDialog(null, "Changes not saved.");
				            clear();
				            System.out.println("Trails have been cleared.");
				            commandList.add(val); // still record the clear command
				        } else {
				            JOptionPane.showMessageDialog(null, "Operation canceled.");
				        }
				    } else {
				        clear();
				        System.out.println("Trails have been cleared.");
				        commandList.add(val); // record clear even if nothing drawn
				    }
				    break;

				case "reset":
					reset();
					System.out.println("Turtle in reset to the orginal position");
					break;
				case "about":
					about();
					commandList.add(val); // save this command

					break;
				case "equilateral":
					if(size==1) {
						JOptionPane.showMessageDialog(null, "Please entered  parameter","No parameter",JOptionPane.ERROR_MESSAGE);
					}
					else if (Integer.parseInt(list.get(1))>0) {
						EquilateralTriangle(Integer.parseInt(list.get(1)));
						commandList.add(val);}
					else{
						JOptionPane.showMessageDialog(null, "You entered negative parameter","Invalid parameter",JOptionPane.ERROR_MESSAGE);}	
					break;

				case "pencolor":
					
					// check if RGB values are missing (need 3 values: red, green, blue)

					if(size==1 || list.size()==2 || list.size()==3) {
						JOptionPane.showMessageDialog(null, "Please entered  parameter","No parameter",JOptionPane.ERROR_MESSAGE);
					}
					else if(Integer.parseInt(list.get(1))>0) {
						pencolour(Integer.parseInt(list.get(1)),Integer.parseInt(list.get(2)),Integer.parseInt(list.get(3)));
						commandList.add(val);} // save this command

					else{
						JOptionPane.showMessageDialog(null, "You entered negative parameter","Invalid parameter",JOptionPane.ERROR_MESSAGE);}	
					break;

				case "square":
					if(size==1) {
						JOptionPane.showMessageDialog(null, "Please enter  parameter","No parameter",JOptionPane.ERROR_MESSAGE);
					}
					else if (Integer.parseInt(list.get(1))>0) {
						square(Integer.parseInt(list.get(1)));
						commandList.add(val);} // save this command

					else{
						JOptionPane.showMessageDialog(null, "You entered negative parameter","Invalid parameter",JOptionPane.ERROR_MESSAGE);}	

					break;
				case "penwidth":
					if(size==1) {
						JOptionPane.showMessageDialog(null, "Please enter  parameter","No parameter",JOptionPane.ERROR_MESSAGE);
					}
					else if (Integer.parseInt(list.get(1))>0) {
						setStroke(Integer.parseInt(list.get(1)));
						System.out.println("Turtle penwidth incresed by "+list.get(1));
						commandList.add(val);} // save this command

					else{
						JOptionPane.showMessageDialog(null, "You entered negative parameter","Invalid parameter",JOptionPane.ERROR_MESSAGE);}	

					break;
					
				case "triangle":
					if(size==1 || list.size()==3||list.size()==2) {
						JOptionPane.showMessageDialog(null, "Please entered  parameter","No parameter",JOptionPane.ERROR_MESSAGE);
					}
					else if (Integer.parseInt(list.get(1))>0){
						int SideA=Integer.parseInt(list.get(1));
						int SideB=Integer.parseInt(list.get(2));
						int SideC=Integer.parseInt(list.get(3));
						Triangle(SideA,SideB,SideC);
					}
					
					else{
						JOptionPane.showMessageDialog(null, "You entered negative parameter","Invalid parameter",JOptionPane.ERROR_MESSAGE);}	
					break;

				case "save":
					saveCommands(commandList);
					
					JOptionPane.showMessageDialog(null, "Command saved successfully","Image saved",JOptionPane.PLAIN_MESSAGE);
					break;

				case "load":
					
					
					loadCommands();
					JOptionPane.showMessageDialog(null, "Command saved successfully","Image saved",JOptionPane.PLAIN_MESSAGE);
					commandList.add(val);
					

					break;
				case "screenshot":
				
					saveImage();
					commandList.add(val); // save this command

					

					break;
				case "image":
					try
					{
						loadImage();
						commandList.add(val); // save this command

						
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				

					break;
				case "name":
					
					myName();
					commandList.add(val); // save this command

				break;
				
				
				case "nepal":
				    drawNepalFlag();         // Call the method
				    commandList.add(val);    // Log command
				    break;

				
				case "help":
				    Help();         // Call your Help() method
				    commandList.add(val);    // Save this command
				    break;

				
								
				
				

				
				case "clock":
					clock(); // call clock() method
				commandList.add(val); // save this command
				break;
				}}
			



			else{
				JOptionPane.showMessageDialog(null, "You entered invalid command","Invalid Command try again",JOptionPane.ERROR_MESSAGE);
			}
		}

		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "You entered string value in parameter","parameter error",JOptionPane.ERROR_MESSAGE);

		}
	}
	
     // Help section
	public void Help() {
		javax.swing.JTextArea textArea = new javax.swing.JTextArea(
	        "ABOUT\n"+
	        "------\n"+
	        "about: Display the turtle dance moving round oop and the name of the user\n\n"+

	        "PEN COMMANDS\n"+
	        "---------\n"+
	        "penwidth: sets the texture of pen color to more thickness\n"+
	        "penup: lifts the pen so movement doesn’t draw\n" +
	        "pendown: puts the pen down so movement draws a line\n" +
	        "blue: Make the pen color blue\n"+
	        "green: Makes the pen color green\n"+
	        "red: Makes the pen color red\n"+
	        "white: Makes the pen color white\n"+

	        "SCREEN COMMANDS\n"+
	        "---------------\n"+
	        "clear: Clears the whole screen\n"+
	        "reset: Moves the turtle back to the starting position, facing downward, without clearing the drawing\n"+
	        "save: Provides options to save commands or save image\n"+
	        "load: Provides options to load commands or load image\n"+

	        "DRAWINGS\n"+
	        "--------\n"+
	        "circle angle: Draws a circle with the radius entered by the user\n"+
	        "rectangle BREADTH HEIGHT: Draws a rectangle\n"+
	        "square side: Draws a square with equal sides\n"+
	        "equilateral 1POINT: Draws an equilateral triangle\n"+
	        "nepal : Draws Nepal\n"+
	        "triangle 3POINTS: Draws a triangle with three given points\n\n"+

	        "LINE COMMANDS\n"+
	        "-------------\n"+
	        "move UNITS: Moves the turtle forward by given units\n"+
	        "reverse UNITS: Moves the turtle backward by given units\n"+
	        "left DEGREES: Turns the turtle to the left by given degrees\n"+
	        "right DEGREES: Turns the turtle to the right by given degrees\n"+

	        "HELP\n"+
	        "----\n"+
	        "help: Displays this help menu!"
	    );

	    // Set TextArea properties
	    textArea.setEditable(false);
	    textArea.setBackground(java.awt.Color.blue);   // blue background
	    textArea.setForeground(java.awt.Color.WHITE);   // White text
	    textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));  // Nice font

	    // Put TextArea inside a ScrollPane
	    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);

	    // Show the scrollPane in a message dialog
	    javax.swing.JOptionPane.showMessageDialog(null, scrollPane, "Help Menu", javax.swing.JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void drawNepalFlag() {
	    drawOn();

	    int canvasHeight = 300;  // Assume canvas height

	    // Flip Y-coordinates for the flag outline
	    int[] x = {50, 50, 150, 90, 150};
	    int[] yOriginal = {50, 250, 175, 140, 50};
	    int[] y = new int[yOriginal.length];
	    for (int i = 0; i < yOriginal.length; i++) {
	        y[i] = canvasHeight - yOriginal[i];
	    }

	    setPenColour(Color.BLUE);
	    drawPolygon(x, y, x.length);

	    setPenColour(new Color(220, 20, 60)); // crimson
	    fillPolygon(x, y, x.length);

	    // Draw moon (now at the bottom)
	    setPenColour(Color.WHITE);
	    fillOval(75, canvasHeight - 90 - 30, 30, 30);  // flipped Y for moon

	    // Draw sun (now at the top)
	    fillOval(75, canvasHeight - 170 - 30, 30, 30);  // flipped Y for sun
	}

	private void fillOval(int x, int y, int width, int height) {
	    Graphics g = getGraphics();
	    g.setColor(Color.WHITE);
	    g.fillOval(x, y, width, height);
	}

	private void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
	    Graphics g = getGraphics();
	    g.setColor(new Color(220, 20, 60));
	    g.fillPolygon(xPoints, yPoints, nPoints);
	}

	private void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
	    Graphics g = getGraphics();
	    g.setColor(Color.BLUE);
	    g.drawPolygon(xPoints, yPoints, nPoints);
	}
	



public static void main(String[] args) {

	new GraphicsSystem();
}}



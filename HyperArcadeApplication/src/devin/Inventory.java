package devin;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import guiTeacher.components.Action;
import guiTeacher.components.Button;
import guiTeacher.components.Graphic;
import guiTeacher.components.TextArea;
import guiTeacher.interfaces.Visible;
import guiTeacher.userInterfaces.FullFunctionScreen;
import hyperArcade.ArcadeGUI;

public class Inventory extends FullFunctionScreen {
	private Button back;
	private ArrayList<DevinItem>itemlist; 
	private TextArea ticketCount;
	//private ArrayList<String>itemlist1;
	public Inventory(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		itemlist = new ArrayList<DevinItem>();
		//ArrayList<String> itemlist1 = new ArrayList<String>();
		
		ticketCount = ArcadeGUI.homeScreen.getTicketCount();
		viewObjects.add(ticketCount);
			
		viewObjects.add(new Graphic(0, 0, getWidth(),getHeight(),"resources/inventory.png"));
		back = new Button (0,50,200,100,"GO Back",Color.white,new Action() {
			public void act() {
				ArcadeGUI.hyperArcade.setScreen(ArcadeGUI.homeScreen);
			}
		});
		
		viewObjects.add(back);
	}
	//buttons to go back, maybe implement custom image button to make it better.
	//arrays on inventory board, brown broads will serve as a marker for the array. could use an image for brown board.
	//needs to implement ticket interface, which is what I will be making.


}

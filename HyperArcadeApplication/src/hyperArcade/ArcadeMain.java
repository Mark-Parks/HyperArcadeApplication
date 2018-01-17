package hyperArcade;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import guiTeacher.components.Action;
import guiTeacher.components.Button;
import guiTeacher.components.CustomImageButton;
import guiTeacher.components.Graphic;
import guiTeacher.components.TextArea;
import guiTeacher.interfaces.DrawInstructions;
import guiTeacher.interfaces.Visible;
import guiTeacher.userInterfaces.FullFunctionScreen;

public class ArcadeMain extends FullFunctionScreen {

	public ArcadeMain(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		viewObjects.add(new Graphic(0, 0, getWidth(), getHeight(), "resources/homescreen.png"));
		// Button tetris = new Button(110,400,100,100,"TETRIS",new Action() {
		// @Override
		// public void act() {
		//
		// }
		// });
		TextArea tetrisScreen = new TextArea(110, 400, 100, 70, "TETRIS");
		CustomImageButton tetris = new CustomImageButton(110, 400, 100, 70, new DrawInstructions() {

			@Override
			public void draw(Graphics2D g, boolean highlight) {
				if (!highlight) {
					g.setColor(Color.black);
					g.fillRect(0, 0, 100, 65);
					viewObjects.remove(tetrisScreen);
				} else {
					g.setColor(Color.white);
					g.fillRect(0, 0, 100, 65);
					g.setFont(ArcadeGUI.themeFont);
					viewObjects.add(tetrisScreen);
				}
			}
		}, new Action() {

			@Override
			public void act() {
				// TODO Auto-generated method stub

			}
		});
		tetris.setBackground(Color.black);
		tetris.setForeground(Color.white);
		viewObjects.add(tetris);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

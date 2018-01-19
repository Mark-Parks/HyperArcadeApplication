package willTetris;

import guiTeacher.GUIApplication;

public class TetrisGUI extends GUIApplication {

	public TetrisGUI(int width, int height) {
		super(width, height);
		setVisible(true);
	}

	public static void main(String[] args) {
		TetrisGUI hyperArcade = new TetrisGUI(1024, 764);
		Thread runner = new Thread(hyperArcade);
		runner.start();
	}

	@Override
	public void initScreen() {
		TetrisMain Screen1 = new TetrisMain(getWidth(), getHeight());
		setScreen(Screen1);
	}

}
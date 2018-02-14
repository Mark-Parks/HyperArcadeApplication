package AliceDanPacman;

import java.awt.image.BufferedImage;

import guiTeacher.components.AnimatedComponent;

public class AliceRedLeft extends AnimatedComponent{

	public AliceRedLeft(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.addSequence("resources/Pacman_spriteSheet.png", 200, 32, 65, 14, 13, 2);
		Thread t = new Thread(this);
		update();
		t.start();
	}

	public BufferedImage getImage() {
		return this.getFrame().get(this.getCurrentFrame());
	}

}
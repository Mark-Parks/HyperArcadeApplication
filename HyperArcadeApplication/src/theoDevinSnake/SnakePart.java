package theoDevinSnake;

import java.awt.Color;
import java.awt.Graphics2D;

import guiTeacher.components.Component;

public class SnakePart extends Component {
	private int x; 
	private int y;
	private int w;
	private int h;
	private int direction;

	public SnakePart(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}

	@Override
	public void update(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.drawRect(x,y,w,h);
		g.fillRect(x, y,w,h);
	}

}

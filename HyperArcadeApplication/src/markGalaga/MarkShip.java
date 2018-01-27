package markGalaga;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import guiTeacher.components.AnimatedComponent;
import guiTeacher.components.Graphic;
import willTetris.Collidable;

public class MarkShip extends MarkPlayerMovement implements Collidable{
	
	ArrayList<MarkProjectile> playerShots;
	
	public MarkShip(int x, int y, int w, int h, ArrayList<MarkProjectile> shots) {
		super(x, y, w, h);
		setX(x);
		setY(y);
		setShots(shots);
		this.addSequence("resources/Galaga_spriteSheet.png", 1000, 184, 55, 15, 16, 1);
		update();
		Thread t = new Thread(this);
		t.start();
	}
	
	private void setShots(ArrayList<MarkProjectile> shots) {
		this.playerShots = shots;
	}

	

	public void moveStop() {
		setVx(0);
	}
	
	public void moveRight() {
		setVx(5);
	}
	
	public void moveLeft() {
		setVx(-5);
	}

	public void fireShot(ArrayList<MarkProjectile> arl, int x, int y) {
		for(int i = 0; i<arl.size();i++) {
			if(arl.get(i).getVy() == 0) {
				arl.get(i).setX(x);
				arl.get(i).setY(y);
				arl.get(i).setVy(-10);			
				break;
			}
		}	
	}
	
	public void checkBehaviors() {
		if(getVx() > 0 && getX() > 712) {
			moveStop();
		}
		if(getVx() < 0 && getX() < 330) {
			moveStop();
		}
	}
	
	public boolean detectCollision(MarkProjectile shot) {
		if((shot.getX() < getX() + getWidth() && shot.getX() + shot.getWidth() > getX() &&
			shot.getY() < getY() + getHeight() && shot.getHeight() + shot.getY() > getY())){
			return true; 
		}
		else{
			return false; 
		}
	}
	
	public boolean detectCollision(MarkMob mob) {
		if((mob.getX() < getX() + getWidth() && mob.getX() + mob.getWidth() > getX() &&
			mob.getY() < getY() + getHeight() && mob.getHeight() + mob.getY() > getY())){
			return true; 
		}
		else{
			return false; 
		}
	}

	public ArrayList<MarkProjectile> getShots() {
		return playerShots;
	}
		
}

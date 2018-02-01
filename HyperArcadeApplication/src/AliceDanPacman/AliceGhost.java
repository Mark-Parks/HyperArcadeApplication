package AliceDanPacman;

import java.util.ArrayList;
import java.util.List;

import guiTeacher.components.AnimatedComponent;
import guiTeacher.interfaces.Visible;
import willTetris.Collidable;

public class AliceGhost extends AnimatedComponent implements Collidable{
	static Thread counter = new Thread();
	public boolean canBeEaten = true;

	
	//there are 4 main ghosts named blinky (red), pinky, clyde (orange), inky (blue)
	//scatter mode-each ghost has a predefined corner of the grid that they follow
	//The red ghost starts outside of the ghost house, and is usually the first one to be seen as a threat
	//http://gameinternals.com/post/2072558330/understanding-pac-man-ghost-behavior	
	//The pink ghost starts inside the ghost house, but always exits immediately, even in the first level. 
	//The blue ghost is nicknamed Inky, and remains inside the ghost house for a short time on the first level, 
	//not joining the chase until Pac-Man has managed to consume at least 30 of the dots. 
	//The orange ghost, â€œClydeâ€�, is the last to leave the ghost house, and does not exit 
	//at all in the first level until over a third of the dots have been eaten. 
	//The ghosts are always in one of three possible modes: Chase, Scatter, or Frightened. 
	//chase-normal mode with ghosts chasing pacman
	// While in Chase mode, all of the ghosts use Pac-Manâ€™s position as a factor in selecting their target tile, 
	//though it is more significant to some ghosts than others.
	//Frightened mode is unique because the ghosts do not have a specific target tile while in this mode. 
	//A ghost in Frightened mode also turns dark blue, moves much more slowly and can be eaten by Pac-Man.
	
	//if ghost goes over dot, setvisible of dots to false
	
	//arraylist of active and inactive ghost
	

	public AliceGhost(int x, int y, int w, int h, String ghostType, PacmanScreen screen3) {
		super(x, y, w, h);
		
		ArrayList<String> active = new ArrayList<String>();
		ArrayList<String> inactive = new ArrayList<String>();


		//first is speed, then is location
	
		
	}

	

	public void initAllObjects(List<Visible> viewObjects) {
		PacmanBackground ghost = new PacmanBackground(0,0,getWidth(),getHeight());
		viewObjects.add(ghost);
	}
	
	

	public void checkIfBlue() {
		
		while(isBlue()) {
			canBeEaten=false;
			
		}
			
			//make a blue ghost
		}
		
		public final void whenBlue() {
			
			Thread timer = new Thread(new Runnable() {
				
				public void run() {
					
					while(isBlue()) {
						
						try {
							
							Thread.sleep(1000);
						}
						
						catch(Throwable e) {
							e.printStackTrace();
							
						}
					}
					
				}

				
				
			});
			
		}
			
	


	private boolean isBlue() {
		
		if(DanielPacman.wentOverPowerUp()) {
			
			return true;
		}
		return false;
		//return DanielPacman.ateBlue();

	}
}

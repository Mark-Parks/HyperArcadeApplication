package markGalaga;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import guiTeacher.components.Action;
import guiTeacher.components.AnimatedComponent;
import guiTeacher.components.Button;
import guiTeacher.components.TextArea;
import guiTeacher.components.TextBox;
import guiTeacher.components.TextField;
import guiTeacher.interfaces.Visible;
import guiTeacher.userInterfaces.FullFunctionScreen;
import markGalaga.GalagaBackground;

public class MarkGalaga extends FullFunctionScreen{
	
	private String galagaSpriteSheet;
	private GalagaBackground background;
	private GameManager manager;
	private TextArea labelBox;
	private TextArea scoreBox;
	private TextArea highscoreBox;	
	private TextArea lifeBox;
	private TextArea messageBox;
	private TextArea stageBox;
	private TextArea resultsBox;
	private MarkGalaga game;
	private MarkShip playerShip;
	private ArrayList<MarkProjectile> playerShots;
	private ArrayList<MarkProjectile> mobShots;
	private MarkAlphaGreen alphaGreen;
	private MarkAlphaPurple alphaPurple;
	private MarkAlphaRed alphaRed;
	private MarkAlphaBlue alphaBlue;
	private ArrayList<MarkMob> mobs;
	private int[][] idleCood;
	private int shotsFired;
	private int hits;
	private int score;
	private int highscore;
	private int lives;
	private int stage;
	private boolean spawning;
	private boolean running;
	
	
	public MarkGalaga(int width, int height) {
		super(width, height);
	}

	
	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		
		setBackground(Color.DARK_GRAY);
		
		score = 0;
		highscore = 10000;
		lives = 2;
		spawning = false;
		running = false;
		
		background = new GalagaBackground(325, -764, 425,getHeight()*2, true);
		viewObjects.add(background);
		
		galagaSpriteSheet = "resources/Galaga_spriteSheet.png";
		
		playerShots = new ArrayList<MarkProjectile>();
		mobShots = new ArrayList<MarkProjectile>();

		alphaGreen = new MarkAlphaGreen(1030,600,15,16,this);
		alphaPurple = new MarkAlphaPurple(1030,620,15,16,this);
		alphaRed = new MarkAlphaRed(1030,640,13,10,this);
		alphaBlue = new MarkAlphaBlue(1030,660,13,10,this);
		
		mobs = new ArrayList<MarkMob>();
		idleCood = new int[40][2];
		
		playerShip = new MarkShip(getWidth()/2 + 8, 600, 32, 32, this);
		playerShip.setVisible(false);
		viewObjects.add(playerShip);
		
		for(int i = 0; i < 2; i++) {
			playerShots.add(i, new MarkProjectile(1030,400,6,16,"player",this));
			playerShots.get(i).addSequence(galagaSpriteSheet, 1000, 374, 51, 3, 8, 1);
			viewObjects.add(playerShots.get(i));
		}
		
		for(int i = 0; i < 1; i++) {
			mobShots.add(i, new MarkProjectile(1030,300/2,6,16,"mob",this));
			mobShots.get(i).addSequence(galagaSpriteSheet, 1000, 366, 195, 3, 8, 1);
			viewObjects.add(mobShots.get(i));
		}
		
		for(int i = 0; i < 40; i++) {
			if(i < 4) {
				idleCood[i][0] = (getWidth()/2)+(2*32)-((i%4)*32);
				idleCood[i][1] = 100;
			}else if(i < 12) {
				idleCood[i][0] = (getWidth()/2)+(4*32)-((i%8)*32);
				idleCood[i][1] = 134;
			}else if(i < 20) {
				idleCood[i][0] = (getWidth()/2)+(4*32)-((i%8)*32);
				idleCood[i][1] = 168;
			}else if(i < 30) {
				idleCood[i][0] = (getWidth()/2)+(5*32)-((i%10)*32);
				idleCood[i][1] = 202;
			}else if(i < 40) {
				idleCood[i][0] = (getWidth()/2)+(5*32)-((i%10)*32);
				idleCood[i][1] = 236;
			}
		}
		
		
		labelBox = new TextArea(325, 10, 425, 40,"    1UP                                    HIGH SCORE");
		labelBox.setCustomTextColor(Color.RED);
		labelBox.setSize(25);
		viewObjects.add(labelBox);
		
		lifeBox = new TextArea(325,710,100,40,"LIVES "+lives);
		lifeBox.setCustomTextColor(Color.WHITE);
		lifeBox.setSize(20);
		viewObjects.add(lifeBox);
		
		scoreBox = new TextArea(350, 30, 425, 40,"");
		scoreBox.setCustomTextColor(Color.WHITE);
		scoreBox.setSize(25);
		viewObjects.add(scoreBox);
		
		messageBox = new TextArea(getWidth()/2-100, getHeight()/2-60, 300, 40, "PRESS  ENTER  TO  START  GAME");
		messageBox.setCustomTextColor(Color.CYAN);
		messageBox.setSize(22);
		addObject(messageBox);
		
		stageBox = new TextArea(getWidth()/2-20, getHeight()/2, 200, 40, "STAGE "+stage);
		stageBox.setCustomTextColor(Color.CYAN);
		stageBox.setSize(32);
		stageBox.setVisible(false);
		addObject(stageBox);
		
		highscoreBox = new TextArea(510, 30, 425, 40,highscore+"");
		highscoreBox.setCustomTextColor(Color.WHITE);
		highscoreBox.setSize(25);
		viewObjects.add(highscoreBox);
		
		resultsBox = new TextArea(400,250,400,400,"                     RESULTS\n"
												+ " SHOTS FIRED               "+shotsFired+"\n"
												+ " SHOTS HIT                 "+hits+"\n"
												+ " HIT RATIO                 "+(double)hits/shotsFired+"%");
		resultsBox.setCustomTextColor(Color.RED);
		resultsBox.setSize(26);
		resultsBox.setVisible(false);
		viewObjects.add(resultsBox);
		
		manager = new GameManager(1,1,1,1,this);
		
		updateScore(null);
	}
	
	private void startGame() {
		running = true;
		spawning = true;
		Thread intro = new Thread(new Runnable() {
			
			@Override
			public void run() {
				background.setEnabled(false);
				messageBox.setX(getWidth()/2-20);
				messageBox.setSize(32);
				messageBox.setText("PLAYER 1");
				for(int i = 0; i < 7; i++) {
					messageBox.setVisible(!messageBox.isVisible());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				stage = 1;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				background.setEnabled(true);
				stageBox.setText("STAGE "+stage);
				stageBox.setVisible(true);
				messageBox.setVisible(true);
				playerShip.setVisible(true);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				messageBox.setVisible(false);
				stageBox.setVisible(false);
				playerShip.moveStop();
				playerShip.setX(getWidth()/2+8);
				playerShip.setEnabled(true);
				generateStage(stage);
			}
		});
		intro.start();
	}
	
	public void generateStage(int stgNum) {
		//Stage type order
		//3 2 * 1 2 3 * 1 2 3
		//1 2 3 4 5 6 7 8 9 10
		// * = challenge stage
		spawning = true;
		if(stgNum%4 != 3) {
			for(int i = 0; i < 40; i++) {
				if(i < 4) {
					mobs.add(i, new MarkMob(1030,200,30,32,"green",i,this));
					addObject(mobs.get(i));
				}else if(i < 12) {
					mobs.add(i, new MarkMob(1030,200,30,20,"red",i,this));
					addObject(mobs.get(i));
				}else if(i < 20) {
					mobs.add(i, new MarkMob(1030,200,30,20,"red",i,this));
					addObject(mobs.get(i));
				}else if(i < 30) {
					mobs.add(i, new MarkMob(1030,200,26,20,"blue",i,this));
					addObject(mobs.get(i));
				}else if(i < 40) {
					mobs.add(i, new MarkMob(1030,200,26,20,"blue",i,this));
					addObject(mobs.get(i));
				}
			}
		}
		
		if(stgNum == 1 || stgNum%4 == 0) {
			
		}else if(stgNum == 2 || stgNum%4 == 1) {
			
		}else if(stgNum%4 == 2) {
			
		}else {
			/**
			 * This is where the specific challenge stage will make mobs
			 */
		}
		Thread h = new Thread(new Runnable() {
					
			@Override
			public void run() {
				for(MarkMob m : mobs) {
					m.spawn(stgNum);
					try {
						Thread.sleep(125);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				spawning = false;
			}
		});
		h.start();
	}

	public void nextRound() {
		stage++;
		spawning = true;
		mobShots.clear();
		for(int i = 0; i < stage; i++) {
			mobShots.add(i, new MarkProjectile(1030,300/2,6,16,"mob",this));
			mobShots.get(i).addSequence(galagaSpriteSheet, 1000, 366, 195, 3, 8, 1);
			addObject(mobShots.get(i));
		}
		Thread j = new Thread(new Runnable() {
			
			@Override
			public void run() {
				stageBox.setText("STAGE "+stage);
				stageBox.setCustomTextColor(Color.CYAN);
				stageBox.setVisible(true);
				update();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stageBox.setVisible(false);
				generateStage(stage);
			}
		});
		j.start();
	}
	
	public void shipRespawn() {
		if(lives > 0) {
			Thread k = new Thread(new Runnable() {
				
				@Override
				public void run() {
					spawning = true;
					lives--;
					lifeBox.setText("LIVES "+lives);
					for(MarkMob m: mobs) {
						m.goToPos(idleCood[m.getPos()][0],idleCood[m.getPos()][1],5);
						m.setEnabled(false);
					}
					messageBox.setText("READY");
					messageBox.setCustomTextColor(Color.CYAN);
					messageBox.setVisible(true);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					messageBox.setVisible(false);
					playerShip.moveStop();
					playerShip.setX(getWidth()/2+8);
					playerShip.setVisible(true);
					playerShip.setEnabled(true);
					for(MarkMob m: mobs) {
						m.setEnabled(true);
					}
					spawning = false;
				}
			});
			k.start();
		}else {
			gameOver();
		}
	}
	
	public void gameOver() {
		for(MarkMob m: mobs) {
			m.setRunning(false);
			remove(m);
		}
		mobs.clear();
		update();
		messageBox.setText("GAME  OVER");
		messageBox.setVisible(true);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageBox.setVisible(false);
		resultsBox.setText("                  RESULTS\n"
						 + " SHOTS FIRED                    "+shotsFired+"\n"
						 + " SHOTS HIT                      "+hits+"\n"
						 + " HIT RATIO      "+(double)(hits/shotsFired));
		resultsBox.setVisible(true);
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultsBox.setVisible(false);
		playerShip.setEnabled(false);
		lives = 2;
		lifeBox.setText("LIVES "+lives);
		score = 0;
		updateScore(null);
		running = false;
		messageBox.setX(getWidth()/2-100);
		messageBox.setSize(22);
		messageBox.setText("PRESS  ENTER  TO  PLAY  GAME");
		messageBox.setVisible(true);
		update();
	}

	public void updateScore(MarkMob m) {
		if(m == null) {
			scoreBox.setText(score+"0");
			highscoreBox.setText(highscore+"");
			update();
		}else if(m.getType() == "red") {
			if(m.isAttacking()) {
				score = score + 160;
				if( score >= highscore)
					highscore = score;
				scoreBox.setText(score+"");
				highscoreBox.setText(highscore+"");
				update();
			}else {
				score = score + 80;
				if( score >= highscore)
					highscore = score;
				scoreBox.setText(score+"");
				highscoreBox.setText(highscore+"");
				update();
			}
		}else if ( m.getType() == "blue") {
			if(m.isAttacking()) {
				score = score + 100;
				if( score >= highscore)
					highscore = score;
				scoreBox.setText(score+"");
				highscoreBox.setText(highscore+"");
				update();
			}else {
				score = score + 50;
				if( score >= highscore)
					highscore = score;
				scoreBox.setText(score+"");
				highscoreBox.setText(highscore+"");
				update();
			}
		}else if ( m.getType() == "purple") {
			if(m.isAttacking()) {
				score = score + 400;
				if( score >= highscore)
					highscore = score;
				scoreBox.setText(score+"");
				highscoreBox.setText(highscore+"");
				update();
			}else {
				score = score + 200;
				if( score >= highscore)
					highscore = score;
				scoreBox.setText(score+"");
				highscoreBox.setText(highscore+"");
				update();
			}
		}
	}
	
	
	
	

	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE :
				if(playerShip.isEnabled()) {
					shotsFired++;
					playerShip.fireShot(playerShots,playerShip.getX()+(playerShip.getWidth()/2)-(playerShots.get(0).getWidth()/2),playerShip.getY());
					break;
				}
			case KeyEvent.VK_LEFT :
				if(playerShip.isEnabled())
					playerShip.moveLeft();
				break;
			case KeyEvent.VK_RIGHT :
				if(playerShip.isEnabled())
					playerShip.moveRight();
				break;
			case KeyEvent.VK_ENTER : 
				if(!playerShip.isEnabled() && !running)
					startGame();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT :
				if(playerShip.isEnabled())
				if(playerShip.getVx() < 0)
					playerShip.moveStop();
				else break;
			case KeyEvent.VK_RIGHT : 
				if(playerShip.isEnabled())
				if(playerShip.getVx() > 0)
					playerShip.moveStop();
				else break;
		}
	}

	
	public int getScore() {
		return score;
	}
	
	public MarkShip getShip() {
		return playerShip;
	}
	
	public ArrayList<MarkMob> getMobs(){
		return mobs;
	}
	
	public ArrayList<MarkProjectile> getMobShots() {
		return mobShots;
	}
	
	public ArrayList<MarkProjectile> getPlayerShots() {
		return playerShots;
	}
	
	public MarkAlphaGreen getAlphaGreen() {
		return alphaGreen;
	}
	
	public MarkAlphaPurple getAlphaPurple() {
		return alphaPurple;
	}
	
	public MarkAlphaRed getAlphaRed() {
		return alphaRed;
	}

	public MarkAlphaBlue getAlphaBlue() {
		return alphaBlue;
	}
	
	public boolean isSpawning() {
		return spawning;
	}
	
	public void setHits(int n) {
		this.hits = n;
	}
	
	public int getHits() {
		return hits;
	}

	public int getStage() {
		return stage;
	}

	public boolean getRunning() {
		return running;
	}

	public int getLives() {
		return lives;
	}

	public int[][] getIdleCoods(){
		return idleCood;
	}


	public void setSpawning(boolean b) {
		this.spawning = b;
	}
	
}

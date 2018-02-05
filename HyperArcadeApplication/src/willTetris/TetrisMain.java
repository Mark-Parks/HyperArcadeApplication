package willTetris;

import java.awt.ActiveEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import guiTeacher.components.Action;
import guiTeacher.components.Button;
import guiTeacher.components.Graphic;
import guiTeacher.interfaces.Visible;
import guiTeacher.userInterfaces.FullFunctionScreen;

public class TetrisMain extends FullFunctionScreen {

	private Block[][] board;
	private ArrayList<Block> Tetramino = new ArrayList<Block>(4);
	private ArrayList<Block> active = new ArrayList<Block>(4);
	private ArrayList<ArrayList<Block>> Tetraminos = new ArrayList<ArrayList<Block>>(7);
	private ArrayList<ArrayList<ArrayList<Block>>> TetraminoRotations = new ArrayList<ArrayList<ArrayList<Block>>>(4);
	private int rotation;
	private int tetramino;

	public TetrisMain(int width, int height) {
		super(width, height);
		rotation = 0;
		tetramino = 2;

		// I PIECE
		Tetramino.add(new Block(3, 0, Color.cyan));
		Tetramino.add(new Block(4, 0, Color.cyan));
		Tetramino.add(new Block(5, 0, Color.cyan));
		Tetramino.add(new Block(6, 0, Color.cyan));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// O PIECE
		Tetramino.add(new Block(4, 0, Color.yellow));
		Tetramino.add(new Block(5, 0, Color.yellow));
		Tetramino.add(new Block(4, 1, Color.yellow));
		Tetramino.add(new Block(5, 1, Color.yellow));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// T PIECE
		Tetramino.add(new Block(3, 1, Color.white));
		Tetramino.add(new Block(4, 1, Color.white));
		Tetramino.add(new Block(5, 1, Color.white));
		Tetramino.add(new Block(4, 0, Color.white));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// S PIECE
		Tetramino.add(new Block(3, 1, Color.green));
		Tetramino.add(new Block(4, 1, Color.green));
		Tetramino.add(new Block(4, 0, Color.green));
		Tetramino.add(new Block(5, 0, Color.green));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// T PIECE
		Tetramino.add(new Block(4, 1, Color.red));
		Tetramino.add(new Block(5, 1, Color.red));
		Tetramino.add(new Block(3, 0, Color.red));
		Tetramino.add(new Block(4, 0, Color.red));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// J PIECE
		Tetramino.add(new Block(5, 2, Color.blue));
		Tetramino.add(new Block(5, 3, Color.blue));
		Tetramino.add(new Block(5, 4, Color.blue));
		Tetramino.add(new Block(4, 4, Color.blue));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// L PIECE
		Tetramino.add(new Block(4, 2, Color.orange));
		Tetramino.add(new Block(4, 3, Color.orange));
		Tetramino.add(new Block(4, 4, Color.orange));
		Tetramino.add(new Block(5, 4, Color.orange));
		Tetraminos.add(Tetramino);

		TetraminoRotations.add(Tetraminos);

		/*
		 * 1 ROTATION
		 */

		// I PIECE
		Tetramino.add(new Block(3, 0, Color.cyan));
		Tetramino.add(new Block(4, 0, Color.cyan));
		Tetramino.add(new Block(5, 0, Color.cyan));
		Tetramino.add(new Block(6, 0, Color.cyan));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// O PIECE
		Tetramino.add(new Block(4, 0, Color.yellow));
		Tetramino.add(new Block(5, 0, Color.yellow));
		Tetramino.add(new Block(4, 1, Color.yellow));
		Tetramino.add(new Block(5, 1, Color.yellow));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// T PIECE
		Tetramino.add(new Block(4, 2, Color.white));
		Tetramino.add(new Block(4, 1, Color.white));
		Tetramino.add(new Block(5, 1, Color.white));
		Tetramino.add(new Block(4, 0, Color.white));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// S PIECE
		Tetramino.add(new Block(3, 1, Color.green));
		Tetramino.add(new Block(4, 1, Color.green));
		Tetramino.add(new Block(4, 0, Color.green));
		Tetramino.add(new Block(5, 0, Color.green));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// T PIECE
		Tetramino.add(new Block(4, 1, Color.red));
		Tetramino.add(new Block(5, 1, Color.red));
		Tetramino.add(new Block(3, 0, Color.red));
		Tetramino.add(new Block(4, 0, Color.red));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// J PIECE
		Tetramino.add(new Block(5, 2, Color.blue));
		Tetramino.add(new Block(5, 3, Color.blue));
		Tetramino.add(new Block(5, 4, Color.blue));
		Tetramino.add(new Block(4, 4, Color.blue));
		Tetraminos.add(Tetramino);
		Tetramino = new ArrayList<Block>(4);
		// L PIECE
		Tetramino.add(new Block(4, 2, Color.orange));
		Tetramino.add(new Block(4, 3, Color.orange));
		Tetramino.add(new Block(4, 4, Color.orange));
		Tetramino.add(new Block(5, 4, Color.orange));
		Tetraminos.add(Tetramino);

		TetraminoRotations.add(Tetraminos);

		newPiece();

		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				lower();
			}
		}, 0, 200);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		board = new Block[10][20];
	}

	public void dropdown() {
	}

	public void lower() {
		boolean canMove = true;

		for (Block b : active) {
			if (b.getY() > 18) {
				canMove = false;
			} else if (board[b.getX()][b.getY() + 1] != null) {
				if (!board[b.getX()][b.getY() + 1].getActive()) {
					canMove = false;
				}
			}
		}

		if (canMove) {
			for (int i = 0; i < 4; i++) {
				board[active.get(i).getX()][active.get(i).getY()] = null;
			}
			for (int i = 0; i < 4; i++) {
				active.set(i, new Block(active.get(i).getX(), active.get(i).getY() + 1, active.get(i).getColor()));
				board[active.get(i).getX()][active.get(i).getY()] = active.get(i);
			}
		} else {
			for (Block b : active) {
				b.setActive(false);
			}
			newPiece();
		}

	}

	public void moveLeft() {
		boolean canMove = true;

		for (Block b : active) {
			if (b.getX() < 1) {
				canMove = false;
			} else if (board[b.getX() - 1][b.getY()] != null) {
				if (!board[b.getX() - 1][b.getY()].getActive()) {
					canMove = false;
				}
			}
		}

		if (canMove) {
			for (int i = 0; i < 4; i++) {
				board[active.get(i).getX()][active.get(i).getY()] = null;
			}
			for (int i = 0; i < 4; i++) {
				active.set(i, new Block(active.get(i).getX() - 1, active.get(i).getY(), active.get(i).getColor()));
				board[active.get(i).getX()][active.get(i).getY()] = active.get(i);
			}
		}
	}

	public void moveRight() {
		boolean canMove = true;

		for (Block b : active) {
			if (b.getX() > 8) {
				canMove = false;
			} else if (board[b.getX() + 1][b.getY()] != null) {
				if (!board[b.getX() + 1][b.getY()].getActive()) {
					canMove = false;
				}
			}
		}

		if (canMove) {
			for (int i = 0; i < 4; i++) {
				board[active.get(i).getX()][active.get(i).getY()] = null;
			}
			for (int i = 0; i < 4; i++) {
				active.set(i, new Block(active.get(i).getX() + 1, active.get(i).getY(), active.get(i).getColor()));
				board[active.get(i).getX()][active.get(i).getY()] = active.get(i);
			}
		}
	}

	public void newPiece() {
		int x = (int) (Math.random() * Tetraminos.size());
		active = (ArrayList<Block>) Tetraminos.get(2).clone();
	}

	public void gameOver() {

	}

	public void paint(Graphics g) {
		for (int w = 0; w < board.length; w++) {
			for (int h = 0; h < board[w].length; h++) {
				if (board[w][h] != null)
					g.setColor(board[w][h].getColor());
				else
					g.setColor(Color.black);
				g.fillRect(w * 30, h * 30, 25, 25);
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			if (!active.isEmpty())
				moveRight();
			break;
		case KeyEvent.VK_LEFT:
			if (!active.isEmpty())
				moveLeft();
			break;
		case KeyEvent.VK_UP:
			if (!active.isEmpty())
				clockWise();
			break;
		case KeyEvent.VK_DOWN:
			if (!active.isEmpty())
				countClockWise();
			break;
		}
	}

	private void countClockWise() {
	}

	private void clockWise() {
		for (int i = 0; i < 4; i++) {
			active.get(i).setX(active.get(i).getX() + TetraminoRotations.get(1).get(tetramino).get(i).getX()
					- TetraminoRotations.get(0).get(tetramino).get(i).getX());
			active.get(i).setY(active.get(i).getY() + TetraminoRotations.get(1).get(tetramino).get(i).getY()
					- TetraminoRotations.get(0).get(tetramino).get(i).getY());
		}
		for (int i = 0; i < 4; i++) {
			board[active.get(i).getX()][active.get(i).getY()] = null;
		}
		for (int i = 0; i < 4; i++) {
			active.set(i, new Block(active.get(i).getX(), active.get(i).getY(), active.get(i).getColor()));
			board[active.get(i).getX()][active.get(i).getY()] = active.get(i);
		}
	}
}

package roboyobo.nac;

import org.newdawn.slick.Graphics;

public class Box {
	
	private int x, y;
	
	/**
	 * The current state. X, O or blank
	 * blank = 0
	 * O = 1
	 * X = 2
	 */
	private int value;
	private String letter = "";
	private String[] letters = {"", "O", "X"};
	
	public Box(int par1, int par2) {
		x = par1;
		y = par2;
	}
	
	public void render(Graphics g) {
		g.drawRect(x, y, Reference.boxSize, Reference.boxSize);
		g.drawString(letter, x + 20, y + 20);
	}
	
	public void setValue(int par1) {
		value = par1;
		letter = letters[par1];
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isOccupied() {
		if(value > 0) {
			return true;
		}
		return false;
	}
	
}

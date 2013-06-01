package roboyobo.valleyDefense.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.valleyDefense.util.Reference;

public class Button {
	
	private int startX, startY, length, height;
	private boolean isLocked;
	private int stateID;
	
	/**
	 * 
	 * @param par1 The Starting X Coordinate
	 * @param par2 The Starting Y Coordinate
	 * @param par3 The Length Of The Button
	 * @param par4 The Height Of The Button
	 * @param par5 Is The Button Locked Or Not. i.e. Is It Clickable Or Not
	 * @param par6 The ID Of The State Which Will Be Entered When This Is Clicked (isLocked Dependant)
	 * @param par7 The String To Be Displayed On The Button (If Any)
	 */
	public Button(int par1, int par2, int par3, int par4, boolean par5, int par6, String par7) {
		startX = par1;
		startY = par2;
		length = par3;
		height = par4;
		isLocked = par5;
		stateID = par6;
	}
	

	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setLocked(boolean par1) {
		isLocked = par1;
	}
	
	public boolean getLocked() {
		return isLocked;
	}

	
	public void renderButton(int par1, int par2, int par3, int par4, Graphics g) {
		if(isLocked) {
			int border = par3 / 20;
			g.setColor(Color.black);
			g.fill(new Rectangle(par1, par2, par3, par4));
			g.setColor(Color.red);
			g.fill(new Rectangle(par1 + border, par2 + border, par3 - (border * 2), par4 - (border * 2)));
		}
		else {
			int border = par3 / 20;
			g.setColor(Color.black);
			g.fill(new Rectangle(par1, par2, par3, par4));
			g.setColor(Color.gray);
			g.fill(new Rectangle(par1 + border, par2 + border, par3 - (border * 2), par4 - (border * 2)));
		}
	}


	public void enterState(StateBasedGame sbg) {
		sbg.enterState(stateID);
	}
}

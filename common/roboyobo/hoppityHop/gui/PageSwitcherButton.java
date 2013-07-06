package roboyobo.hoppityHop.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import roboyobo.hoppityHop.loader.Textures;

public class PageSwitcherButton {
	
	private int startX, startY, length, height;
	private boolean isLocked;
	public int id;
	
	/**
	 * 
	 * @param par1 The Starting X Coordinate
	 * @param par2 The Starting Y Coordinate
	 * @param par3 The Length Of The Button
	 * @param par4 The Height Of The Button
	 * @param par5 Is The Button Locked Or Not. i.e. Is It Clickable Or Not
	 * @param par6 The ID Of The State Which Will Be Entered When This Is Clicked (isLocked Dependant)
	 * @param par7 The String To Be Displayed On The Button (If Any)
	 * @param par8 The Font To Use On The Button
	 */
	public PageSwitcherButton(int par1, int par2, int par3, int par4, boolean par5, int par6) {
		startX = par1;
		startY = par2;
		length = par3;
		height = par4;
		isLocked = par5;
		id = par6;
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
		if(id == 0) {
			Textures.psb2.draw(par1, par2);
		}
		if(id == 1) {
			Textures.psb1.draw(par1, par2);
		}
	}


	public void enterState(StateBasedGame sbg, boolean transition) {
		if(transition) {
			sbg.enterState(id, new FadeOutTransition(), new FadeInTransition());
		}
		else {
			sbg.enterState(id);
		}
		
	}
}

package roboyobo.valleyDefense.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import roboyobo.valleyDefense.util.Reference;

public class LevelBox {
	
	private int startX, startY, length, height;
	private boolean isUnlocked, isDone;
	private int stateID;
	private String levelName;
	
	/**
	 * 
	 * @param par1 The Starting X Coordinate
	 * @param par2 The Starting Y Coordinate
	 * @param par3 The Length Of The LevelBox
	 * @param par4 The Height Of The LevelBox
	 * @param par5 Is The Map Unlocked Or Not
	 * @param par6 The Map Which Will Be Loaded When This Is Clicked
	 * @param par7 The String To Be Displayed On The Button (If Any)
	 * @param par8 Has The Level Been Completed
	 */
	public LevelBox(int par1, int par2, int par3, int par4, boolean par5, int par6, String par7, boolean par8) {
		startX = par1;
		startY = par2;
		length = par3;
		height = par4;
		isUnlocked = par5;
		stateID = par6;
		levelName = par7;
		isDone = par8;
		System.out.println("This Level Box: " + startY + ", isUnlocked: " + isUnlocked + ", isDone: " + isDone);
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
		isUnlocked = par1;
	}
	
	public boolean getLocked() {
		return isUnlocked;
	}
	
	
	public void isLevelDone(boolean par1) {
		isDone = par1;
	}
	
	public void renderLevelBox(int par1, int par2, int par3, int par4, Graphics g) {
		
		if(isUnlocked && isDone) {
			int border = par3 / 20;
			g.setColor(Color.black);
			g.fill(new Rectangle(par1, par2, par3, par4));
			g.setColor(Color.green);
			g.drawString("Unlocked", par1 + 220, par2 + 75);
			g.drawString("Done", par1 + 255, par2 + 10);
		}
		else if(isUnlocked) {
			int border = par3 / 20;
			g.setColor(Color.black);
			g.fill(new Rectangle(par1, par2, par3, par4));
			g.setColor(Color.orange);
			g.drawString("Unlocked", par1 + 220, par2 + 75);
		}
		else {
			int border = par3 / 20;
			g.setColor(Color.black);
			g.fill(new Rectangle(par1, par2, par3, par4));
			g.setColor(Color.red);
			g.drawString("Locked", par1 + 240, par2 + 75);
		}
		g.setColor(Color.blue);
		g.drawString(levelName, par1 + 10, par2 + 10);
		
	}


	public void enterState(StateBasedGame sbg, boolean transition) {
		if(transition) {
			sbg.enterState(stateID, new FadeOutTransition(), new FadeInTransition());
		}
		else {
			sbg.enterState(stateID);
		}
		
	}
}

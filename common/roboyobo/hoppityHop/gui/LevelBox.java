package roboyobo.hoppityHop.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import roboyobo.hoppityHop.level.Level;
import roboyobo.hoppityHop.tile.Tile;
import roboyobo.hoppityHop.util.Reference;

public class LevelBox implements java.io.Serializable {
	
	private int startX, startY, length, height;
	private boolean isUnlocked, isDone;
	private int stateID;
	private String levelName;
	private int page;
	
	/**
	 * @param par1 The Starting X Coordinate
	 * @param par2 The Starting Y Coordinate
	 * @param par3 The Length Of The Level Box
	 * @param par4 The Height Of The Level Box
	 * @param par5 The Level To Read Data From
	 */
	public LevelBox(int par1, int par2, int par3, int par4, Level par5) {
		startX = par1;
		startY = par2;
		length = par3;
		height = par4;
		isUnlocked = par5.isUnlocked();
		isDone = par5.isDone();
		levelName = par5.getName();
		page = par5.getPage();
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
	
	public void setUnlocked(boolean par1) {
		isUnlocked = par1;
	}
	
	public boolean getUnlocked() {
		return isUnlocked;
	}
	
	
	public void isLevelDone(boolean par1) {
		isDone = par1;
	}
	
	public int getPage() {
		return page;
	}
	
	public void renderLevelBox(int par1, int par2, int par3, int par4, Graphics g) {
	Rectangle bounds = new Rectangle(par1, par2, par3, par4);
	if(page == Reference.currentPage) {
		if(!bounds.intersects(Reference.gameSelectorLine)) {	
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
	}
	}


	public void enterState(StateBasedGame sbg, boolean transition, Level level) {
		if(transition) {
			sbg.enterState(Reference.gameID, new FadeOutTransition(), new FadeInTransition());
		}
		else {
			sbg.enterState(Reference.gameID);
		}
		Reference.map = level;
	}
}

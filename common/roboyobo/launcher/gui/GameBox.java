package roboyobo.launcher.gui;

import java.awt.Rectangle;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.launcher.Launcher;
import roboyobo.launcher.game.Games;
import roboyobo.launcher.util.ChatHelper;
import roboyobo.launcher.util.Reference;
import roboyobo.weekendGame.WeekendGame;

public class GameBox {
	
	private int id;
	
	public GameBox(int id) {
		this.id = id;
	}
	
	public void changeID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public Rectangle getBounds() {
		switch(this.id) {
		case 1 : return new Rectangle( (int) Reference.boxX, (int) Reference.boxY, (int) Reference.gameBoxSizeX, (int) Reference.gameBoxSizeY);
		case 2 : return new Rectangle( (int) Reference.boxX + 500, (int) Reference.boxY, (int) Reference.gameBoxSizeX, (int) Reference.gameBoxSizeY);
		case 3 : return new Rectangle( (int) Reference.boxX, (int) Reference.boxY + 300, (int) Reference.gameBoxSizeX, (int) Reference.gameBoxSizeY);
		case 4 : return new Rectangle( (int) Reference.boxX + 500, (int) Reference.boxY + 300, (int) Reference.gameBoxSizeX, (int) Reference.gameBoxSizeY);
		default : return new Rectangle( (int) Reference.boxX, (int) Reference.boxY, (int) Reference.gameBoxSizeX, (int) Reference.gameBoxSizeY);
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		switch(this.id) {
		case 1 : g.fillRect(Reference.boxX, Reference.boxY, Reference.gameBoxSizeX, Reference.gameBoxSizeY);
		case 2 : g.fillRect(Reference.boxX + 500, Reference.boxY, Reference.gameBoxSizeX, Reference.gameBoxSizeY);
		case 3 : g.fillRect(Reference.boxX, Reference.boxY + 300, Reference.gameBoxSizeX, Reference.gameBoxSizeY);
		case 4 : g.fillRect(Reference.boxX + 500, Reference.boxY + 300, Reference.gameBoxSizeX, Reference.gameBoxSizeY);
		}
	}
	
	public void update() {
		
	}
	
	public void launchGame(int id) throws SlickException {
		System.out.println("Launching game");
		String[] args = {Reference.username};
		switch(id) {
		case 1 : {
			
		}
		default : {
			
		}
		}
		
	}
}

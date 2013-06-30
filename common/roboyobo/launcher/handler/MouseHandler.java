package roboyobo.launcher.handler;

import java.awt.Rectangle;
import java.util.Iterator;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.launcher.game.Games;
import roboyobo.launcher.gui.GameBox;
import roboyobo.launcher.state.MainMenuState;
import roboyobo.launcher.util.ChatHelper;

public class MouseHandler {

	public static void checkMousePressed(int button, int x, int y) throws SlickException {
		MainMenuState.gbi = MainMenuState.gbs.iterator();
		while(MainMenuState.gbi.hasNext()) {
			GameBox gb = (GameBox) MainMenuState.gbi.next();
			Rectangle bounds = new Rectangle(x, y, 10, 10);
			if(bounds.intersects(gb.getBounds())) {
				System.out.println("Collides With Bounds" + gb.getID());
				gb.launchGame(1);
			}
		}
	}
	
}

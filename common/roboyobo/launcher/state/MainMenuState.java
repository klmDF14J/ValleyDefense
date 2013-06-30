package roboyobo.launcher.state;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.launcher.Launcher;
import roboyobo.launcher.game.Games;
import roboyobo.launcher.gui.GameBox;
import roboyobo.launcher.util.ChatHelper;
import roboyobo.launcher.util.Reference;
import roboyobo.weekendGame.WeekendGame;

public class MainMenuState extends State {
	
	public MainMenuState(int StateID) {
		super(StateID);
	}
	
	public static ArrayList<GameBox> gbs = new ArrayList<GameBox>();
	public static Iterator<GameBox> gbi;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		new Image("/resources/images/launcher/Background.png");
		gbs.add(new GameBox(1));
		gbs.add(new GameBox(2));
		gbs.add(new GameBox(3));
		gbs.add(new GameBox(4));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		iterateGameBoxes(gc, sbg, g);
	}
	
	private void iterateGameBoxes(GameContainer gc, StateBasedGame sbg, Graphics g) {
		gbi = gbs.iterator();
		while(gbi.hasNext()) {
			GameBox gb = (GameBox) gbi.next();
			gb.render(gc, sbg, g);
			//ChatHelper.debugprint("" + gb.getID());
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}
	
	

}

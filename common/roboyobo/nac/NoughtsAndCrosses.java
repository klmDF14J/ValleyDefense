package roboyobo.nac;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.nac.state.InGameState;

public class NoughtsAndCrosses extends StateBasedGame {
	
	static AppGameContainer app;

	public NoughtsAndCrosses() {
		super("Noughts And Crosses");
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new InGameState());
	}
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new NoughtsAndCrosses());
		app.start();
	}
}

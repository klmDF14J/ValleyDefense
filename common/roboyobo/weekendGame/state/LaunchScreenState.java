package roboyobo.weekendGame.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import roboyobo.launcher.Launcher;
import roboyobo.launcher.util.Reference;

public class LaunchScreenState extends BasicGameState {

	private int timePassed;
	private int timeToDisplayFor = 1500;
	
	private Image splashScreen;
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		splashScreen = new Image("/resources/images/weekendGame/framework/Launch.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		splashScreen.draw(0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timePassed += delta;

		if(timePassed >= timeToDisplayFor) {
			sbg.enterState(roboyobo.weekendGame.util.Reference.mainMenuState, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}


}

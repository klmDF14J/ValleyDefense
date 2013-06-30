package roboyobo.launcher.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.launcher.Launcher;
import roboyobo.launcher.util.Reference;

public class SplashScreenState extends State {
	
	public SplashScreenState(int StateID) {
		super(StateID);
	}

	private int timePassed;
	private int timeToDisplayFor = 3000;
	
	private Image splashScreen;
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		splashScreen = new Image("/resources/images/launcher/Splash.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		splashScreen.draw(0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timePassed += delta;

		if(timePassed >= timeToDisplayFor) {
			sbg.enterState(Reference.mainMenuState);
		}
	}


}

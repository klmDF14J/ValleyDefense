package roboyobo.launcher;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.launcher.handler.MouseHandler;
import roboyobo.launcher.state.MainMenuState;
import roboyobo.launcher.state.SplashScreenState;
import roboyobo.launcher.util.Reference;
import roboyobo.weekendGame.WeekendGame;

public class Launcher extends StateBasedGame {

	public static AppGameContainer app;
	
	public Launcher() {
		super("Launcher");
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new SplashScreenState(Reference.splashScreenState));
		addState(new MainMenuState(Reference.mainMenuState));
		enterState(Reference.splashScreenState);
	}
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new Launcher());
		app.setDisplayMode(Reference.screenSizeWidth, Reference.screenSizeHeight, false);
		app.start();
		
	}
	
	public void mousePressed(int button, int x, int y) {
		try {
			MouseHandler.checkMousePressed(button, x, y);
			WeekendGame.main(null);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

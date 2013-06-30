package roboyobo.weekendGame.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import roboyobo.weekendGame.WeekendGame;
import roboyobo.weekendGame.gui.Buttons;
import roboyobo.weekendGame.loader.TextureLoader;
import roboyobo.weekendGame.util.Reference;

public class GameOverState extends BasicGameState {
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		TextureLoader.gameBackground.draw(0, 0);
		TextureLoader.gameOver.draw(WeekendGame.app.getWidth() / 2 - (TextureLoader.gameOver.getWidth() / 2), WeekendGame.app.getHeight() / 2 - (TextureLoader.gameOver.getHeight() / 2));
		g.drawString("You Died On Wave " + Reference.currentWave, 420, 350);
		g.drawString("You Were Killed By " + Reference.lastHitByMob, 382, 370);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	}

	@Override
	public int getID() {
		return Reference.gameOverState;
	}

}

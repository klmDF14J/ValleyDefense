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

import roboyobo.weekendGame.gui.Buttons;
import roboyobo.weekendGame.loader.TextureLoader;
import roboyobo.weekendGame.util.Reference;

public class MenuState extends BasicGameState {
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		TextureLoader.menuBackground.draw(0, 0);
		Buttons.addNewButton(Reference.standardButton, 100, 100);
		Buttons.addNewButton(Reference.standardButton, 200, 200);
		Buttons.drawButton(g, 0);
		Buttons.drawButton(g, 1);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			//sbg.enterState(Reference.wave1State, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return Reference.mainMenuState;
	}

}

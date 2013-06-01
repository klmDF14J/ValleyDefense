package roboyobo.valleyDefense.state;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import roboyobo.valleyDefense.gui.Button;
import roboyobo.valleyDefense.loader.Textures;
import roboyobo.valleyDefense.util.Reference;

public class MenuState extends BasicGameState {
	
	public int stateID;
	
	public MenuState(int StateID) {
		stateID = StateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Reference.buttons = new ArrayList<Button>();
		Reference.buttons.add(new Button(Reference.screenWidth / 2 - (400 / 2), Reference.screenHeight / 2 - 200, 400, 100, false, Reference.gameSelectorID, "Play"));
		Reference.buttons.add(new Button(Reference.screenWidth / 2 - (400 / 2), Reference.screenHeight / 2 - 50, 400, 100, false, Reference.mapEditorID, "Level Editor"));
		Reference.buttons.add(new Button(Reference.screenWidth / 2 - (400 / 2), Reference.screenHeight / 2 + 100, 400, 100, true, Reference.menuID, "Options"));
		for(int var1 = 0; var1 < (Reference.screenWidth / (20 * 2F)); var1++) {
			for(int var2 = 0; var2 < (Reference.screenHeight / (20 * 2F)); var2++) {
				Textures.greyBrick.draw((20 * 2F) * var1, (20 * 2F) * var2, 2F);
			}
		}
		for(int var3 = 0; var3 < Reference.buttons.size(); var3++) {
			Button button = Reference.buttons.get(var3);
			button.renderButton(button.getStartX(), button.getStartY(), button.getLength(), button.getHeight(), g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}

	public static void handleMouseClick(int button, int x, int y, StateBasedGame sbg) {
		Rectangle bounds = new Rectangle(x, y, 10, 10);
		for(int var1 = 0; var1 < Reference.buttons.size(); var1++) {
			Button b = Reference.buttons.get(var1);
			if(bounds.intersects(new Rectangle(b.getStartX(), b.getStartY(), b.getLength(), b.getHeight()))) {
				if(!b.getLocked()) {
					b.enterState(sbg, true);
				}
			}
		}
	}
}

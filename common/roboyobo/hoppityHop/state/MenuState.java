package roboyobo.hoppityHop.state;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.newdawn.slick.Color;
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

import roboyobo.hoppityHop.HoppityHop;
import roboyobo.hoppityHop.gui.Button;
import roboyobo.hoppityHop.level.Level;
import roboyobo.hoppityHop.loader.Textures;
import roboyobo.hoppityHop.util.Reference;

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
		Reference.buttons.add(new Button(Reference.screenWidth / 2 - (400 / 2), Reference.screenHeight / 2 - 200, 400, 100, false, Reference.gameSelectorID, "Play", Reference.fonts.get(1)));
		Reference.buttons.add(new Button(Reference.screenWidth / 2 - (400 / 2), Reference.screenHeight / 2 - 50, 400, 100, false, Reference.mapEditorID, "Editor", Reference.fonts.get(1)));
		Reference.buttons.add(new Button(Reference.screenWidth / 2 - (400 / 2), Reference.screenHeight / 2 + 100, 400, 100, true, Reference.menuID, "Options", Reference.fonts.get(1)));
		for(int var1 = 0; var1 < (Reference.screenWidth / (Reference.tileSize * Reference.tileScaleSize)); var1++) {
			for(int var2 = 0; var2 < (Reference.screenHeight / (Reference.tileSize * Reference.tileScaleSize)); var2++) {
				Reference.tiles.get(4).draw((Reference.tileSize * Reference.tileScaleSize) * var1, (Reference.tileSize * Reference.tileScaleSize) * var2, Reference.tileScaleSize);
			}
		}
		for(int var3 = 0; var3 < Reference.buttons.size(); var3++) {
			Button button = Reference.buttons.get(var3);
			button.renderButton(button.getStartX(), button.getStartY(), button.getLength(), button.getHeight(), g);
		}
		HoppityHop.app.setTargetFrameRate(60);
		for(int var4 = 0; var4 < Reference.levels.size(); var4++) {
			Reference.levels.get(var4).loadLevel(Reference.levels.get(var4).getLevelFile());
		}
		Reference.fonts.get(0).drawString(275, 75, "Hoppity Hop", new Color(0, 0, 0));
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
				if(!b.getLocked() && HoppityHop.stateID == Reference.menuID) {
					b.enterState(sbg, true);
				}
			}
		}
	}
}

package roboyobo.valleyDefense.state;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import roboyobo.valleyDefense.game.MoneyHandler;
import roboyobo.valleyDefense.gui.Button;
import roboyobo.valleyDefense.loader.Textures;
import roboyobo.valleyDefense.tile.Tile;
import roboyobo.valleyDefense.util.Reference;
import roboyobo.valleyDefense.game.Building;

public class GameState extends BasicGameState {
	
	public int stateID;
	
	public GameState(int StateID) {
		stateID = StateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Reference.buttons = new ArrayList<Button>();
		Reference.buildings = new ArrayList<Building>();
		Reference.buttons.add(new Button(1200, Reference.screenHeight / 2 + 300, 200, 50, false, Reference.menuID, "Back To Menu", Reference.fonts.get(0)));
		Reference.buttons.add(new Button(1200, Reference.screenHeight / 2 + 350, 200, 50, false, Reference.gameSelectorID, "Choose A Different Map", Reference.fonts.get(0)));
		for(int var1 = 0; var1 < (Reference.screenWidth / (Reference.tileSize * Reference.tileScaleSize)); var1++) {
			for(int var2 = 0; var2 < (Reference.screenHeight / (Reference.tileSize * Reference.tileScaleSize)); var2++) {
				Reference.tiles.get(4).draw((Reference.tileSize * Reference.tileScaleSize) * var1, (Reference.tileSize * Reference.tileScaleSize) * var2, Reference.tileScaleSize);
			}
		}
		
		renderMap();
		for(int var3 = 0; var3 < Reference.buttons.size(); var3++) {
			Button button = Reference.buttons.get(var3);
			button.renderButton(button.getStartX(), button.getStartY(), button.getLength(), button.getHeight(), g);
		}
		g.setColor(Color.black);
		g.fill(new Rectangle(1080, 40, 320, 640));
		g.setColor(Color.gray);
		g.fill(new Rectangle(1080 + (320 / 20), 40 + (320 / 20), 320 - ((320 / 20) * 2), 640 - ((320 / 20) * 2)));
		
		Reference.items.get(1).draw(1100, 60, Reference.iconScaleSize);
		Reference.fonts.get(0).drawString(1175, 60, "X" + MoneyHandler.getMoney(), new Color(0, 0, 0));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}
	
	private void renderMap() {
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
				if(Reference.map.getTileAt(var1, var2) != null) {
					Tile tile = Reference.map.getTileAt(var1, var2);
					tile.renderTile(Reference.tiles.get(tile.getTileID()), tile.getX(), tile.getY(), Reference.tileScaleSize);
				}
			}
		}
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
	
	public static void handleKeyPress(int key, char c) {
		if(key == Input.KEY_C) {
			MoneyHandler.addMoney(20);
		}
	}
}

package roboyobo.valleyDefense.state;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.valleyDefense.gui.Button;
import roboyobo.valleyDefense.gui.LevelBox;
import roboyobo.valleyDefense.loader.Textures;
import roboyobo.valleyDefense.map.Maps;
import roboyobo.valleyDefense.util.Reference;

public class GameLevelSelectorState extends BasicGameState {
	
	public int stateID;
	
	public GameLevelSelectorState(int StateID) {
		stateID = StateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Reference.levels = new ArrayList<LevelBox>();
		Maps.unlockedMaps = new ArrayList();
		Maps.completedMaps = new ArrayList();
		Maps.levelNames = new ArrayList();
		Maps.unlockedMaps.add(true);
		Maps.unlockedMaps.add(true);
		Maps.unlockedMaps.add(false);
		Maps.completedMaps.add(true);
		Maps.completedMaps.add(false);
		Maps.completedMaps.add(false);
		Maps.levelNames.add("Level 1");
		Maps.levelNames.add("Level 2");
		Maps.levelNames.add("Level 3");
		for(int var1 = 0; var1 < Maps.numOfMaps; var1++) {
			if(Maps.numOfMaps <= Maps.unlockedMaps.size() && Maps.numOfMaps <= Maps.completedMaps.size() && Maps.numOfMaps <= Maps.levelNames.size()) {
				Reference.levels.add(new LevelBox(Reference.screenWidth / 2 - (300 / 2), (Reference.screenHeight / 2 - 250) + (var1 * 150), 300, 100, (boolean) Maps.unlockedMaps.get(var1), stateID, (String) Maps.levelNames.get(var1), (boolean) Maps.completedMaps.get(var1)));
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		for(int var1 = 0; var1 < (Reference.screenWidth / (20 * 2F)); var1++) {
			for(int var2 = 0; var2 < (Reference.screenHeight / (20 * 2F)); var2++) {
				Textures.greyBrick.draw((20 * 2F) * var1, (20 * 2F) * var2, 2F);
			}
		}
		int par1 = Reference.screenWidth / 2 - (400 / 2);
		int par2 = Reference.screenHeight / 2 - 300;
		int par3 = 400;
		int par4 = 600;
		int border = par3 / 20;
		g.setColor(Color.black);
		g.fill(new Rectangle(par1, par2, par3, par4));
		g.setColor(Color.gray);
		g.fill(new Rectangle(par1 + border, par2 + border, par3 - (border * 2), par4 - (border * 2)));
		
		for(int var3 = 0; var3 < Reference.levels.size(); var3++) {
			LevelBox level = Reference.levels.get(var3);
			level.renderLevelBox(level.getStartX(), level.getStartY(), level.getLength(), level.getHeight(), g);
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
		//TODO Turret Placing Logic
	}
}

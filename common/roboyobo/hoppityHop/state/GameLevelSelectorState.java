package roboyobo.hoppityHop.state;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.hoppityHop.HoppityHop;
import roboyobo.hoppityHop.gui.Button;
import roboyobo.hoppityHop.gui.LevelBox;
import roboyobo.hoppityHop.gui.LevelTab;
import roboyobo.hoppityHop.gui.PageSwitcherButton;
import roboyobo.hoppityHop.level.Level;
import roboyobo.hoppityHop.level.LevelTools;
import roboyobo.hoppityHop.loader.Textures;
import roboyobo.hoppityHop.util.Reference;

public class GameLevelSelectorState extends BasicGameState {
	
	public int stateID;
	
	public GameLevelSelectorState(int StateID) {
		stateID = StateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Reference.levels = new ArrayList<Level>();
		Reference.levelTabs = new ArrayList<LevelTab>();
		Reference.levels.add(new Level(Reference.mapWidth, Reference.mapHeight, true, false, "Tutorial Level", 0));
		Reference.levels.add(new Level(Reference.mapWidth, Reference.mapHeight, false, false, "Level 1", 0));
		Reference.levels.add(new Level(Reference.mapWidth, Reference.mapHeight, false, false, "Level 2", 0));
		Reference.levels.add(new Level(Reference.mapWidth, Reference.mapHeight, false, false, "Level 3", 1));
		Reference.levels.add(new Level(Reference.mapWidth, Reference.mapHeight, false, false, "Level 4", 1));
		Reference.levels.add(new Level(Reference.mapWidth, Reference.mapHeight, false, false, "Level 5", 1));
		Reference.levels.add(new Level(Reference.mapWidth, Reference.mapHeight, false, false, "Level 6", 2));
		Reference.levels.add(new Level(Reference.mapWidth, Reference.mapHeight, false, false, "Level 7", 2));
		Reference.levels.add(new Level(Reference.mapWidth, Reference.mapHeight, false, false, "Level 8", 2));
		
		Reference.levelTabs.add(new LevelTab(500, 700, 200, 50, false, 0, "Campaign"));
		Reference.levelTabs.add(new LevelTab(700, 700, 200, 50, false, 1, "Other"));
		
		Reference.pageSwitcherButtons[0] = new PageSwitcherButton(550, 575, 100, 100, false, 0);
		Reference.pageSwitcherButtons[1] = new PageSwitcherButton(750, 575, 100, 100, false, 1);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		for(int var1 = 0; var1 < (Reference.screenWidth / (Reference.tileSize * Reference.tileScaleSize)); var1++) {
			for(int var2 = 0; var2 < (Reference.screenHeight / (Reference.tileSize * Reference.tileScaleSize)); var2++) {
				Reference.tiles.get(4).draw((Reference.tileSize * Reference.tileScaleSize) * var1, (Reference.tileSize * Reference.tileScaleSize) * var2, Reference.tileScaleSize);
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
		int var4 = 0;
		for(int var3 = 0; var3 < Reference.levels.size(); var3++) {
			if(Reference.levelTab == 0) {
				LevelBox level = Reference.levels.get(var3).getLevelBox(var3);
				if(level.getPage() > 0) {
					level = Reference.levels.get(var3).getLevelBox(var4);
					var4++;
					if(var4 > 2) {
						var4 = 0;
					}
				}
				level.renderLevelBox(level.getStartX(), level.getStartY(), level.getLength(), level.getHeight(), g);
			}
		}
		Reference.fonts.get(0).drawString(300, 25, "Select A Level", new Color(0, 0, 0));
		int offset = Reference.offset;
		for(int var5 = 0; var5 < Reference.levelTabs.size(); var5++) {
			LevelTab tab = Reference.levelTabs.get(var5);
			tab.renderButton(tab.getStartX(), tab.getStartY(), tab.getLength(), tab.getHeight(), g);
		}
		
		for(int var6 = 0; var6 < Reference.pageSwitcherButtons.length; var6++) {
			PageSwitcherButton psb = Reference.pageSwitcherButtons[var6];
			psb.renderButton(psb.getStartX(), psb.getStartY(), psb.getLength(), psb.getHeight(), g);
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
		for(int var1 = 0; var1 < Reference.levels.size(); var1++) {
			LevelBox levelBox = Reference.levels.get(var1).getLevelBox(var1);
			if(bounds.intersects(new Rectangle(levelBox.getStartX(), levelBox.getStartY(), levelBox.getLength(), levelBox.getHeight()))) {
				if(levelBox.getUnlocked()) {
					levelBox.enterState(sbg, true, Reference.levels.get(var1));
				}
			}
		}
		for(int var2 = 0; var2 < Reference.levelTabs.size(); var2++) {
			LevelTab b = Reference.levelTabs.get(var2);
			if(bounds.intersects(new Rectangle(b.getStartX(), b.getStartY(), b.getLength(), b.getHeight()))) {
				Reference.levelTab = b.id;
			}
		}
		
		for(int var3 = 0; var3 < Reference.pageSwitcherButtons.length; var3++) {
			PageSwitcherButton psb = Reference.pageSwitcherButtons[var3];
			if(bounds.intersects(new Rectangle(psb.getStartX(), psb.getStartY(), psb.getLength(), psb.getHeight()))) {
				if(psb.id == 0 && Reference.currentPage >= 1) {
					Reference.currentPage--;
				}
				if(psb.id == 1 && Reference.currentPage < 2) {
					Reference.currentPage++;
				}
			}
		}
	}

	public static void handleKeyPress(int key, char c) {
		
	}
}

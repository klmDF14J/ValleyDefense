package roboyobo.hoppityHop.state;

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

import roboyobo.hoppityHop.game.Building;
import roboyobo.hoppityHop.game.MoneyHandler;
import roboyobo.hoppityHop.gui.Button;
import roboyobo.hoppityHop.loader.Textures;
import roboyobo.hoppityHop.tile.Tile;
import roboyobo.hoppityHop.util.Reference;

public class GameState extends BasicGameState {
	
	public int stateID;
	
	public GameState(int StateID) {
		stateID = StateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Reference.pickupableBuildings = new ArrayList<Integer>();
		
		Reference.pickupableBuildings.add(1);
		Reference.pickupableBuildings.add(2);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Reference.buttons = new ArrayList<Button>();
		Reference.buttons.add(new Button(1200, Reference.screenHeight / 2 + 300, 200, 50, false, Reference.menuID, "Back To Menu", Reference.fonts.get(4)));
		Reference.buttons.add(new Button(1200, Reference.screenHeight / 2 + 350, 200, 50, false, Reference.gameSelectorID, "Choose A Different Map", Reference.fonts.get(4)));
		for(int var1 = 0; var1 < (Reference.screenWidth / (Reference.tileSize * Reference.tileScaleSize)); var1++) {
			for(int var2 = 0; var2 < (Reference.screenHeight / (Reference.tileSize * Reference.tileScaleSize)); var2++) {
				Reference.tiles.get(1).draw((Reference.tileSize * Reference.tileScaleSize) * var1, (Reference.tileSize * Reference.tileScaleSize) * var2, Reference.tileScaleSize);
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
		
		for(int var3 = 0; var3 < 7; var3++) {
			int[] x = {100, 180, 260, 340, 420, 500, 580};
			Textures.box.draw(x[var3] - 100, 720, 2F);
			if(var3 == Reference.currentBuilding - 1) {
				Textures.boxH.draw(x[var3] - 100, 720, 2F);
			}
			Reference.buildingImages.get(var3 + 1).draw(x[var3] - 80, 740, 2F);
		}
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
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
				if(Reference.map.getBuildingAt(var1, var2) != null) {
					Building building = Reference.map.getBuildingAt(var1, var2);
					building.renderTile(Reference.buildingImages.get(building.getTileID()), building.getX(), building.getY(), Reference.tileScaleSize);
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
		placeBuilding(button, x, y);
	}
	
	private static void placeBuilding(int button, int x, int y) {
		int mouse_x = x;
		int mouse_y = y;
		int mouseXScaled;
		int mouseYScaled;
		boolean hasExtraData = false;
		boolean isPickupable = false;
		int id = Reference.currentBuilding;
		mouse_x -= 40;
		mouse_y -= 40;
		mouseXScaled = mouse_x / 40;
		mouseYScaled = mouse_y / 40;
		if(mouseXScaled < Reference.mapWidth && mouseYScaled < Reference.mapHeight) {
			boolean isAir = false;
			
			for(int var1 = 0; var1 < Reference.pickupableBuildings.size(); var1++) {
				if(id == Reference.pickupableBuildings.get(var1)) {
					isPickupable = true;
				}
			}
			
			if(button == 0) { 
				Building building = new Building(Reference.xPositions[mouseXScaled], Reference.yPositions[mouseYScaled], id , hasExtraData, isPickupable);
				Building b = Reference.map.getBuildingAt(mouseXScaled, mouseYScaled);
				if(b == null) {
					if(Reference.blocks[id - 1] > 0 && Reference.currentBuilding <= 7) {
						Reference.map.setBuildingAt(mouseXScaled, mouseYScaled, building);
						Reference.blocks[id - 1]--;
						System.out.println("B is null");
					}
				}
				else if(b.getTileID() == 0) {
					if(Reference.blocks[id - 1] > 0 && Reference.currentBuilding <= 7) {
						Reference.map.setBuildingAt(mouseXScaled, mouseYScaled, building);
						Reference.blocks[id - 1]--;
						System.out.println("B.getTileID() == 0");
					}
				}
			}
			else if(button == 1) {
				Building building = new Building(Reference.xPositions[mouseXScaled], Reference.yPositions[mouseYScaled], id, hasExtraData, isPickupable);
				building.setTileID(0);
				Building b1 = Reference.map.getBuildingAt(mouseXScaled, mouseYScaled);
					if(b1 != null) {
						for(int var2 = 0; var2 < Reference.pickupableBuildings.size(); var2++) {
							if(b1.getTileID() == Reference.pickupableBuildings.get(var2)) {
								Reference.map.setBuildingAt(mouseXScaled, mouseYScaled, building);
								Reference.blocks[b1.getTileID() - 1]++;
							}
						}
					}
				}
				
				
			}
			
	}
	
	public static void handleKeyPress(int key, char c) {
		if(key == Input.KEY_R) {
			Reference.blocks = Reference.blockDefaults;
			Reference.map = Reference.levels.get(Reference.currentLevel);
			Reference.map.clear();
		}
		if(key == Input.KEY_K) {
			if(Reference.currentLevel + 1 <= Reference.levels.size()) {
				Reference.currentLevel++;
				Reference.map = Reference.levels.get(Reference.currentLevel);
			}
		}
		if(key == Input.KEY_L) {
			if(Reference.currentLevel - 1 >= 0) {
				Reference.currentLevel--;
				Reference.map = Reference.levels.get(Reference.currentLevel);
			}
		}
		if(key == Input.KEY_1) {
			Reference.currentBuilding = 1;
		}
		if(key == Input.KEY_2) {
			Reference.currentBuilding = 2;
		}
		if(key == Input.KEY_3) {
			Reference.currentBuilding = 3;
		}
		if(key == Input.KEY_4) {
			Reference.currentBuilding = 4;
		}
		if(key == Input.KEY_5) {
			Reference.currentBuilding = 5;
		}
		if(key == Input.KEY_6) {
			Reference.currentBuilding = 6;
		}
		if(key == Input.KEY_7) {
			Reference.currentBuilding = 7;
		}
	}
}

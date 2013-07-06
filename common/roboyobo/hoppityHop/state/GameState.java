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

import roboyobo.hoppityHop.game.MoneyHandler;
import roboyobo.hoppityHop.gui.Button;
import roboyobo.hoppityHop.loader.Textures;
import roboyobo.hoppityHop.player.Player;
import roboyobo.hoppityHop.tile.Tile;
import roboyobo.hoppityHop.util.Chat;
import roboyobo.hoppityHop.util.Reference;

public class GameState extends BasicGameState {
	
	public int stateID;
	
	public GameState(int StateID) {
		stateID = StateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Reference.pickupableBuildings = new ArrayList<Integer>();
		Reference.placeableBlocks = new ArrayList<Integer>();
		
		Reference.pickupableBuildings.add(20);
		
		for(int var1 = 0; var1 < 7; var1++) {
			Reference.placeableBlocks.add(20 + var1);
		}
		
		//Reference.map.setup();
		
		Reference.players[0] = new Player(Reference.username, 0, 10, 10, Color.red, "Test Player");
		Reference.players[1] = new Player("Awesome Man", 1, 0, 5, Color.blue);
		Reference.players[2] = new Player("Cool Dude", 2, 3, 7, Color.yellow);
		Reference.players[3] = new Player("Great Guy", 3, 9, 2, Color.green);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Reference.buttons = new ArrayList<Button>();
		Reference.buttons.add(new Button(1200, Reference.screenHeight / 2 + 300, 200, 50, false, Reference.menuID, "Back To Menu", Reference.fonts.get(4)));
		Reference.buttons.add(new Button(1200, Reference.screenHeight / 2 + 350, 200, 50, false, Reference.gameSelectorID, "Choose A Different Map", Reference.fonts.get(4)));
		
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
		
		renderAllPlayers(g);
		
		int[] blocks = Reference.levels.get(Reference.currentLevel).getBlocks();
		
		for(int var3 = 0; var3 < 7; var3++) {
			int[] x = {100, 180, 260, 340, 420, 500, 580};
			Textures.box.draw(x[var3] - 100, 720, 2F);
			if(var3 == Reference.currentBuilding - 1) {
				Textures.boxH.draw(x[var3] - 100, 720, 2F);
			}
			Reference.tiles.get(var3 + 20).draw(x[var3] - 80, 740, 2F);
			if(var3 == 0) {
				Reference.fonts.get(4).drawString(x[0] - 45, 780, "" + blocks[var3]);
			}
			else if(var3 == 6 && blocks[6] != 0) {
				Reference.fonts.get(4).drawString(x[6] - 35, 780, "" + blocks[var3]);
			}
			else if(var3 == 6 && blocks[6] == 0) {
				Reference.fonts.get(4).drawString(x[6] - 39, 780, "" + blocks[var3]);
			}
			else {
				Reference.fonts.get(4).drawString(x[var3] - 40, 780, "" + blocks[var3]);
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Reference.updateTime++;
		if(Reference.updateTime >= Reference.tickTime) {
			Reference.updateTime = 0;
			for(int var1 = 0; var1 < Reference.players.length; var1++) {
				if(Reference.players[var1] != null) {
					if(!Reference.players[var1].isOnSolidBlock()) {
						Reference.players[var1].moveDown();
					}
				}
			}
		}
		
		Reference.lastJumpTime++;
		
		if(Reference.players[Reference.currentPlayer].isOnSolidBlock()) {
			Reference.lastJumpTime += Reference.minJumpTime;
		}
		
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
					if(b.getStateID() == 0 || b.getStateID() == 1) {
						resetLevel();
					}
				}
			}
		}
		placeBuilding(button, x, y);
	}
	
	private void renderMap() {
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
				Tile tile = Reference.map.getTileAt(var1, var2);
				if(tile != null) {
					tile.renderTile(Reference.tiles.get(tile.getTileID()), tile.getX(), tile.getY(), Reference.tileScaleSize);
				}
			}
		}
	}
	
	private void renderAllPlayers(Graphics g) {
		for(int var1 = 0; var1 < Reference.players.length; var1++) {
			if(Reference.players[var1] != null) {
				Reference.players[var1].renderPlayer();
				if(Reference.players[var1].getID() == Reference.currentPlayer) {
					Player player = Reference.players[var1];
					g.setColor(player.getColour());
					g.drawRect(Reference.xPositions[player.getX()], Reference.yPositions[player.getY()], player.getImage().getWidth() * Reference.tileScaleSize, player.getImage().getHeight() * Reference.tileScaleSize);
				}
			}
		}
	}
	
	private static void placeBuilding(int button, int x, int y) {
		int[] blocks = Reference.levels.get(Reference.currentLevel).getBlocks();
		int mouse_x = x;
		int mouse_y = y;
		int mouseXScaled;
		int mouseYScaled;
		boolean hasExtraData = false;
		boolean isPickupable = false;
		int id = Reference.currentBuilding;
		int texture = id + 19;
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
				Tile tile = Reference.map.getTileAt(mouseXScaled, mouseYScaled);
				if(tile == null) {
					if(blocks[id - 1] > 0 && Reference.currentBuilding <= 7) {
						Reference.map.setTileAt(mouseXScaled, mouseYScaled, new Tile(Reference.xPositions[mouseXScaled], Reference.yPositions[mouseYScaled], texture, hasExtraData));
						blocks[id - 1]--;
					}
				}
				else if(tile.getTileID() == 0) {
					if(blocks[id - 1] > 0 && Reference.currentBuilding <= 7) {
						Reference.map.setTileAt(mouseXScaled, mouseYScaled, new Tile(Reference.xPositions[mouseXScaled], Reference.yPositions[mouseYScaled], texture, hasExtraData));
						blocks[id - 1]--;
					}
				}
			}
			else if(button == 1) {
				Tile tile = Reference.map.getTileAt(mouseXScaled, mouseYScaled);
					if(tile != null ) {
						for(int var2 = 0; var2 < Reference.pickupableBuildings.size(); var2++) {
							if(tile.getTileID() == Reference.pickupableBuildings.get(var2)) {
								Reference.map.setTileAt(mouseXScaled, mouseYScaled, new Tile(Reference.xPositions[mouseXScaled], Reference.yPositions[mouseYScaled], 0, hasExtraData));
								blocks[tile.getTileID() - 20]++;
							
							}
						}
					}
				}
				
				
			}
			
	}
	
	public static void handleKeyPress(int key, char c) {
		if(key == Input.KEY_R) {
			resetLevel();
		}
		
		int[] keys = {Input.KEY_1, Input.KEY_2, Input.KEY_3, Input.KEY_4, Input.KEY_5, Input.KEY_6, Input.KEY_7};
		for(int var1 = 0; var1 < keys.length; var1++) {
			if(key == keys[var1]) {
				Reference.currentBuilding = var1 + 1;
			}
		}
		
		if(key == Input.KEY_C) {
			if(Reference.currentPlayer == 3) {
				Reference.currentPlayer = 0;
			}
			else {
				Reference.currentPlayer++;
			}
		}
		if(Reference.players[Reference.currentPlayer] != null) {
			handlePlayerMovement(key);
		}
		
	}
	
	private static void handlePlayerMovement(int key) {
		if(key == Input.KEY_A) {
			Reference.players[Reference.currentPlayer].moveLeft();
		}
		if(key == Input.KEY_D) {
			Reference.players[Reference.currentPlayer].moveRight();
		}
		if(key == Input.KEY_W) {
			Reference.players[Reference.currentPlayer].moveUp();
		}
		if(key == Input.KEY_S) {
			Reference.players[Reference.currentPlayer].moveDown();
		}
	}
	
	private static void resetLevel() {
		Chat.print("Resseting Level");
		Reference.map.clear();
	}
}

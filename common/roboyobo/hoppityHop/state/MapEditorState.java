package roboyobo.hoppityHop.state;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.hoppityHop.level.Level;
import roboyobo.hoppityHop.level.LevelTools;
import roboyobo.hoppityHop.tile.Tile;
import roboyobo.hoppityHop.util.Reference;

public class MapEditorState extends BasicGameState {
	
	public int stateID;
	public Iterator i;

	public MapEditorState(int StateID) {
		stateID = StateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		addImagesToAndSetupArray();
		LevelTools.setLevelBackground(Reference.map, new Color(255, 0, 0));
		LevelTools.setMapToAir(Reference.map);
	}
	
	private void addImagesToAndSetupArray() {
		Reference.extraDataTiles = new ArrayList();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		LevelTools.renderExtraLevelGui(Reference.map, g);
		renderMap();
		renderCurrentTile();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}
	
	private static void saveMap() {
		String extension = ".VDMap";
		if(Reference.fileChooser.getSelectedFile().getAbsolutePath().endsWith(extension)) {
			extension = "";
		}
			
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(Reference.fileToWriteTo + extension));
			oos.writeObject(Reference.map);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void loadMap() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(Reference.fileToOpen));
			Reference.map = (Level) ois.readObject();
			System.out.println(Reference.fileToOpen.getName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getID() {
		return stateID;
	}
	
	private void renderCurrentTile() {
		Image currentTile = Reference.tiles.get(Reference.currentTile);
		currentTile.draw(1200, 40, 8F);
	}
	
	public static void setupXValues() {
		int var3 = 1;
		for(int var1 = 0; var1 < Reference.xPositions.length; var1++) {
			Reference.xPositions[var1] = (var3 * 40);
			var3++;
		}
	}
	
	public static void setupYValues() {
		int var3 = 1;
		for(int var1 = 0; var1 < Reference.yPositions.length; var1++) {
			Reference.yPositions[var1] = (var3 * 40);
			var3++;
		}
	}
	
	public static void fillMap(int id) {
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
					Reference.map.setTileAt(var1, var2, new Tile(Reference.xPositions[var1], Reference.yPositions[var2], id, false));
			}
		}
	}
	
	private void renderMap() {
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
				if(Reference.map.getTileAt(var1, var2) != null) {
					Tile tile = Reference.map.getTileAt(var1, var2);
					if(!tile.isAir()) {
						tile.renderTile(Reference.tiles.get(tile.getTileID()), tile.getX(), tile.getY(), Reference.tileScaleSize);
					}
				}
			}
		}
	}

	public static void handleMapSaving() throws FileNotFoundException, IOException {
		
	}

	public static void handleMapLoading() {
		int returnVal = Reference.fileChooser.showOpenDialog(new JFrame());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Reference.fileToOpen = Reference.fileChooser.getSelectedFile();
			loadMap();
		}
	}

	public static void handleMouseClick(int button, int x, int y) {
		int mouse_x = x;
		int mouse_y = y;
		int mouseXScaled;
		int mouseYScaled;
		boolean hasExtraData = false;
		int id = Reference.currentTile;
		mouse_x -= 40;
		mouse_y -= 40;
		mouseXScaled = mouse_x / 40;
		mouseYScaled = mouse_y / 40;
		
		for(int var1 = 0; var1 < Reference.extraDataTiles.size(); var1++) {
			int var2 = (int) Reference.extraDataTiles.get(var1);
			if(var2 == id) {
				hasExtraData = true;
			}
		}
		if(mouseXScaled < Reference.mapWidth && mouseYScaled < Reference.mapHeight) {
			boolean isAir = false;
			if(id == 0) {
				isAir = true;
			}
			
			Tile tile = new Tile(Reference.xPositions[mouseXScaled], Reference.yPositions[mouseYScaled], id, hasExtraData);
			tile.setAir(isAir);
			Reference.map.setTileAt(mouseXScaled, mouseYScaled, tile);
			
		}
	}

	public static void handleKeyPress(int key, char c) {

		if(key == Input.KEY_1) {
			if(Reference.currentTile >= 1) {
				Reference.currentTile--;
			}
		}
		
		if(key == Input.KEY_2) {
			if(Reference.tiles.size() - 1 > Reference.currentTile) {
				Reference.currentTile++;
			}
		}
		
		if(key == Input.KEY_S) {
			int returnVal = Reference.fileChooser.showSaveDialog(new JFrame());
			if (returnVal == Reference.fileChooser.APPROVE_OPTION) {
	            Reference.fileToWriteTo = Reference.fileChooser.getSelectedFile();
	            saveMap();
	        }
			
		
		}
		if(key == Input.KEY_O) {
			MapEditorState.handleMapLoading();
		}
		
		if(key == Input.KEY_N) {
			if(JOptionPane.showConfirmDialog(null, "Do you want to save changes?" + "\n Any unsaved changes will be lost","Creating new map",JOptionPane.YES_NO_OPTION) == 0) {
				try {
					MapEditorState.handleMapSaving();
					MapEditorState.fillMap(1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				MapEditorState.fillMap(1);
			}
		}
	}
	
	

}

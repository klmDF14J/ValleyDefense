package roboyobo.valleyDefense.state;

import java.io.DataOutputStream;
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

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.valleyDefense.loader.Textures;
import roboyobo.valleyDefense.map.Maps;
import roboyobo.valleyDefense.tile.Tile;
import roboyobo.valleyDefense.util.Reference;
import roboyobo.valleyDefense.util.Texture;

public class MapEditorState extends BasicGameState {
	
	public int stateID;
	public Iterator i;

	private int iteratorPos = 0;
	private boolean done = false;
	
	
	public MapEditorState(int StateID) {
		stateID = StateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Maps.initMaps();
		setMap(Maps.map1);
		addImagesToAndSetupArray();
	}
	
	private void addImagesToAndSetupArray() {
		Reference.tileImages = new ArrayList<Image>();
		Reference.tileImagesIterator = Reference.tileImages.iterator();
		Reference.tileImages.add(Textures.grass);
		Reference.tileImages.add(Textures.dirt);
		Reference.tileImages.add(Textures.greyBrick);
		Reference.tileImages.add(Textures.brownBrick);
		
		Reference.extraDataTiles = new ArrayList();
		Reference.extraDataTiles.add(1);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		renderMap();
		renderCurrentTile();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}
	
	private static void saveMap() throws FileNotFoundException, IOException {
		String extension = ".VDMap";
		if(Reference.fileChooser.getSelectedFile().getAbsolutePath().endsWith(extension)) {
			extension = "";
		}
			
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Reference.fileToWriteTo + extension));
		oos.writeObject(Reference.map);
		oos.close();
	}
	
	private static void loadMap() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(Reference.fileToOpen));
			Reference.map = (Tile[][]) ois.readObject();
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
	
	private void setMap(Tile[][] map) {
		Reference.map = map;
	}

	@Override
	public int getID() {
		return stateID;
	}
	
	private void renderCurrentTile() {
		Image currentTile = Reference.tileImages.get(Reference.currentTile);
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
					Reference.map[var1][var2] = new Tile(Reference.xPositions[var1], Reference.yPositions[var2], id, false);
			}
		}
	}
	
	private void renderMap() {
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
				if(Reference.map[var1][var2] != null) {
					Tile tile = Reference.map[var1][var2];
					tile.renderTile(Reference.tileImages.get(tile.getTileID()), tile.getX(), tile.getY(), Reference.tileScale);
				}
			}
		}
	}

	public static void handleMapSaving() throws FileNotFoundException, IOException {
		int returnVal = Reference.fileChooser.showSaveDialog(new JFrame());
		if (returnVal == Reference.fileChooser.APPROVE_OPTION) {
            Reference.fileToWriteTo = Reference.fileChooser.getSelectedFile();
            saveMap();
        }
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
			Reference.map[mouseXScaled][mouseYScaled] = new Tile(Reference.xPositions[mouseXScaled], Reference.yPositions[mouseYScaled], id, hasExtraData);
		}
	}

	public static void handleKeyPress(int key, char c) {
		if(key == Input.KEY_1) {
			if(Reference.currentTile >= 1) {
				Reference.currentTile--;
			}
		}
		
		if(key == Input.KEY_2) {
			if(Reference.tileImages.size() - 1 > Reference.currentTile) {
				Reference.currentTile++;
			}
		}
		
		if(key == Input.KEY_S) {
			try {
				MapEditorState.handleMapSaving();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

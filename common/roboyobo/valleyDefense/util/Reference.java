package roboyobo.valleyDefense.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;

import org.newdawn.slick.Image;

import roboyobo.valleyDefense.gui.Button;
import roboyobo.valleyDefense.gui.LevelBox;
import roboyobo.valleyDefense.loader.Textures;
import roboyobo.valleyDefense.tile.Tile;

public class Reference {
	public static int menuID = 0;
	public static int gameSelectorID = 1;
	public static int mapEditorID = 2;
	public static String imageFolder = "/resources/images/valleyDefense/";
	public static int mapWidth = 25;
	public static int mapHeight = 16;
	public static Tile[][] map = new Tile[mapWidth][mapHeight];
	public static int[] xPositions = new int[mapWidth];
	public static int[] yPositions = new int[mapHeight];
	public static float tileScale = 2F;
	public static ArrayList<Image> tileImages;
	public static Iterator tileImagesIterator;
	public static int currentTile = 0;
	public static File fileToWriteTo;
	public static JFileChooser fileChooser = new JFileChooser();
	public static File fileToOpen;
	public static ArrayList extraDataTiles;
	public static int screenWidth = 1400;
	public static int screenHeight = 800;
	public static ArrayList<Button> buttons;
	public static ArrayList<LevelBox> levels;
	
}

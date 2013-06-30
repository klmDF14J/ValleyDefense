package roboyobo.hoppityHop.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;

import org.newdawn.slick.Image;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Line;

import roboyobo.hoppityHop.game.Building;
import roboyobo.hoppityHop.gui.Button;
import roboyobo.hoppityHop.gui.LevelTab;
import roboyobo.hoppityHop.gui.PageSwitcherButton;
import roboyobo.hoppityHop.level.Level;

public class Reference {
	public static int menuID = 0;
	public static int gameSelectorID = 1;
	public static int mapEditorID = 2;
	public static int gameID = 3;
	
	public static String imageFolder = "/resources/images/valleyDefense/";
	
	public static int mapWidth = 25;
	public static int mapHeight = 16;
	
	
	public static Level map = new Level(Reference.mapWidth, Reference.mapHeight, false, false, "", 0);
	
	public static int[] xPositions = new int[mapWidth];
	public static int[] yPositions = new int[mapHeight];
	
	public static ArrayList<Image> tiles;
	public static int currentTile = 0;
	public static File fileToWriteTo;
	public static JFileChooser fileChooser = new JFileChooser();
	public static File fileToOpen;
	public static ArrayList extraDataTiles;
	public static int screenWidth = 1400;
	public static int screenHeight = 800;
	public static ArrayList<Button> buttons;
	public static ArrayList<Level> levels;
	public static Line gameSelectorLine = new Line(500, 700 - 20, 900, 700 - 20);
	public static int currentPage = 0;
	public static int levelTab = 0;
	public static int offset = 200;
	public static ArrayList<LevelTab> levelTabs;
	public static ArrayList<Image> items;
	public static float tileScaleSize = 2F;
	public static float iconScaleSize = 4F;
	public static int tileSize = 20;
	public static int iconSize = 16;
	public static ArrayList<UnicodeFont> fonts;
	public static int currentBuilding = 1;
	public static ArrayList<Image> buildingImages;
	public static PageSwitcherButton[] pageSwitcherButtons = new PageSwitcherButton[2];
	public static int[] blocks = {10, 8, 6, 4, 2, 2, 1};
	public static int[] blockDefaults = {10, 8, 6, 4, 2, 2, 1};
	public static ArrayList<Integer> pickupableBuildings;
	public static int currentLevel = 0;
}

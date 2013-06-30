package roboyobo.nac;

import java.util.ArrayList;

public class Reference {
	
	/**
	 * State ID's
	 */
	public static int splashScreen = 0;
	public static int menu = 1;
	public static int inGame = 2;
	public static int gameOver = 3;
	
	public static int boxSize = 40;
	
	public static int mapWidth = 3;
	public static int mapHeight = 3;
	
	public static Box[][] board = new Box[mapWidth][mapHeight];
	
	public static int[] boxPos = {100, 140, 180};
	public static int[] xPos = {100, 140, 180};
	public static int[] yPos = {100, 140, 180};
	public static int currentPlayer = 0;
	public static int start = 1;
}

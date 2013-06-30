package roboyobo.hoppityHop.level;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import roboyobo.hoppityHop.loader.Textures;
import roboyobo.hoppityHop.tile.Tile;
import roboyobo.hoppityHop.util.Reference;

public class LevelTools {

	public static void fillMap(Level level, int id) {
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
				level.setTileAt(var1, var2, new Tile(Reference.xPositions[var1], Reference.yPositions[var2], id, false));
			}
		}
	}
	
	public static void setMapToAir(Level level) {
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
				level.setTileAt(var1, var2, new Tile(Reference.xPositions[var1], Reference.yPositions[var2], 0, false));
			}
		}
	}
	
	
	/**
	 * @param level The Level To Set The Background Color On
	 * @param color The Color To Set The Background To (Supports Hexadecimal And RGB)
	 */
	public static void setLevelBackground(Level level, Color color) {
		level.setColor(color);
	}
	
	public static void renderExtraLevelGui(Level level, Graphics g) {
		Color color = level.getColor();
		g.setColor(color);
		g.fill(new Rectangle(0, 0, Reference.screenWidth, Reference.screenHeight));
	}
	
}

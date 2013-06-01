package roboyobo.valleyDefense.map;

import org.newdawn.slick.Image;

import roboyobo.valleyDefense.loader.Textures;
import roboyobo.valleyDefense.tile.Tile;
import roboyobo.valleyDefense.util.Reference;

public class Maps {
	public static Tile[][] map1 = new Tile[Reference.mapWidth][Reference.mapHeight];
	
	public static void initMaps() {		
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
				if(var2 == 0) {
				}
				else {
				}
				map1[var1][var2] = new Tile(Reference.xPositions[var1], Reference.yPositions[var2], 0, false);
			}
		}
	}
}

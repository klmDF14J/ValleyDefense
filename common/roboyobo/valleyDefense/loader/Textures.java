package roboyobo.valleyDefense.loader;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import roboyobo.valleyDefense.util.Reference;

public class Textures {
	
	public static SpriteSheet tiles;
	public static Image grass, dirt, greyBrick, brownBrick;
	
	public static void load() throws SlickException {
		tiles = new SpriteSheet(Reference.imageFolder + "/tiles/Tiles.png", 20, 20);
		grass = tiles.getSprite(4, 7);
		dirt = tiles.getSprite(16, 6);
		greyBrick = tiles.getSprite(11, 0);
		brownBrick = tiles.getSprite(14, 5);
	}
}

package roboyobo.valleyDefense.loader;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import roboyobo.valleyDefense.util.Reference;

public class Textures {
	
	public static SpriteSheet Tiles;
	public static SpriteSheet items;
	
	public static void load() throws SlickException {
		Reference.tiles = new ArrayList<Image>();
		Reference.items = new ArrayList<Image>();
		Reference.fonts = new ArrayList<UnicodeFont>();
		Reference.fonts.add(addAndReturnNewFont("buttonText", 72));
		Reference.fonts.add(addAndReturnNewFont("buttonText", 48));
		Reference.fonts.add(addAndReturnNewFont("buttonText", 36));
		Reference.fonts.add(addAndReturnNewFont("buttonText", 24));
		Reference.fonts.add(addAndReturnNewFont("buttonText", 12));
		Tiles = new SpriteSheet(Reference.imageFolder + "/tiles/Tiles.png", 20, 20);
		items = new SpriteSheet(Reference.imageFolder + "/items/Items.png", 16, 16);
		
		for(int var1 = 0; var1 < 20; var1++) {
			for(int var2 = 0; var2 < 20; var2++) {
				Image image = Tiles.getSprite(var2, var1);
				Reference.tiles.add(image);
				
			}
		}
		
		for(int var3 = 0; var3 < 16; var3++) {
			for(int var4 = 0; var4 < 16; var4++) {
				Image image = items.getSprite(var4, var3);
				Reference.items.add(image);
			}
		}
		
	}
	
	private static UnicodeFont addAndReturnNewFont(String par2, int par3) throws SlickException {
		if(par3 > 72) { par3 = 72; }
		UnicodeFont font = new UnicodeFont(Reference.imageFolder + "/framework/" + par2 + ".ttf", par3, false, false);
		font.addAsciiGlyphs();
		font.addGlyphs(400, 600);
		font.getEffects().add(new ColorEffect());
		font.loadGlyphs();
		return font;
	}
}

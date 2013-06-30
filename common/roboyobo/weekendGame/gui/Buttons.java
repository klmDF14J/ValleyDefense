package roboyobo.weekendGame.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import roboyobo.weekendGame.loader.TextureLoader;
import roboyobo.weekendGame.util.Reference;

public class Buttons {
	public static int[] buttonCoordsX = new int[10];
	public static int[] buttonCoordsY = new int[10];
	public static int amountOfButtons = -1;
	public static void addNewButton(int buttonType, int x, int y) {
		buttonCoordsX[amountOfButtons + 1] = x;
		buttonCoordsY[amountOfButtons + 1] = y;
		//amountOfButtons++;
	}
	
	public static void drawButton(Graphics g, int buttonID) throws SlickException {
			TextureLoader.button.draw(buttonCoordsX[buttonID], buttonCoordsY[buttonID], Reference.buttonScaleSize);
	}

}

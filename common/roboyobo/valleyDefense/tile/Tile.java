package roboyobo.valleyDefense.tile;

import org.newdawn.slick.Image;

import roboyobo.valleyDefense.util.Texture;



public class Tile implements java.io.Serializable {
	private int posX, posY;
	private int tileID;
	private boolean hasExtraData;
	private boolean entryPoint = false;
	private String entryDirection = "";
	
	public Tile(int par1, int par2, int par3, boolean par4) {
		posX = par1;
		posY = par2;
		tileID = par3;
		hasExtraData = par4;
	}
	
	public void renderTile(Image image, int xPos, int yPos, float scale) {
		image.draw(xPos, yPos, scale);
	}

	public int getTileID() {
		return tileID;
	}

	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
	
	public void setAsEntryPoint(String direction) {
		if(hasExtraData) {
			entryPoint = true;
			entryDirection = direction;
		}
	}
}

package roboyobo.hoppityHop.game;

import org.newdawn.slick.Image;

import roboyobo.hoppityHop.util.Texture;



public class Building implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int posX, posY;
	private int tileID;
	private boolean hasExtraData;
	private boolean isAir = false;
	private boolean isExit = false;
	private boolean isPickupable = true;
	
	public Building(int par1, int par2, int par3, boolean par4, boolean par5) {
		posX = par1;
		posY = par2;
		tileID = par3;
		hasExtraData = par4;
		isPickupable = par5;
	}
	
	public void renderTile(Image image, int xPos, int yPos, float scale) {
		image.draw(xPos, yPos, scale);
	}
	
	public void setTileID(int par1) {
		tileID = par1;
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
	
	public void setAir(boolean par1) {
		isAir = par1;
	}
	
	public boolean isAir() {
		return isAir;
	}
	
	public boolean hasExtraData() {
		return hasExtraData;
	}
	
	public void setHasExtraData(boolean par1) {
		hasExtraData = par1;
	}
	
	public boolean isExit() {
		return isExit;
	}
	
	public void setExit(boolean par1) {
		isExit = par1;
	}

	public boolean canBePickedUp() {
		return isPickupable;
	}
	
}

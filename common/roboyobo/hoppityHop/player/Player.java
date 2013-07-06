package roboyobo.hoppityHop.player;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import roboyobo.hoppityHop.util.Chat;
import roboyobo.hoppityHop.util.Reference;

public class Player {
	private String name, skin;
	private boolean isAlive;
	private Image image;
	private int x, y;
	private int id;
	private Color colour;
	
	/**
	 * @param par1 The Name Of The Player (Affects The Skin)
	 * @param par2 The ID Of The Player
	 * @param par3 The Starting X Coordinate
	 * @param par4 The Starting Y Coordinate
	 * @param par5 The Colour Of The Players Surrounding Box
	 */
	public Player(String par1, int par2, int par3, int par4, Color par5) {
		name = par1;
		id = par2;
		x = par3;
		y = par4;
		colour = par5;
		skin = par1;
		
		try {
			image = new Image(Reference.imageFolder + "framework/players/" + skin + ".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param par1 The Name Of The Player (Doesn't Affect The Skin)
	 * @param par2 The ID Of The Player
	 * @param par3 The Starting X Coordinate
	 * @param par4 The Starting Y Coordinate
	 * @param par5 The Colour Of The Players Surrounding Box
	 * @param par6 The Skin Of The Player
	 */
	public Player(String par1, int par2, int par3, int par4, Color par5, String par6) {
		name = par1;
		id = par2;
		x = par3;
		y = par4;
		colour = par5;
		skin = par6;
		
		try {
			image = new Image(Reference.imageFolder + "framework/players/" + skin + ".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void setName(String par1) {
		name = par1;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void renderPlayer() {
		image.draw(Reference.xPositions[x], Reference.yPositions[y], Reference.tileScaleSize);
		Reference.fonts.get(4).drawString(Reference.xPositions[x] - ((name.length()) * 3), Reference.yPositions[y] - 10, name);
	}
	
	public boolean isOnSolidBlock() {
		boolean var1 = true;
		if(Reference.map.getTileAt(x, y + 2).getTileID() == 0) {
			var1 = false;
		}
		return var1;
	}
	
	private void moveAndCheckOtherPlayers(int type) {
		if(type == 0) {
			x--;
		}
		else if(type == 1) {
			x++;
		}
		
		for(int var1 = 0; var1 < Reference.players.length; var1++) {
			Player player = Reference.players[var1];
			if(player.id != id) {			
				if(x == player.getX() && y == player.getY()) {
					if(type == 0) {
						x++;
					}
					else if(type == 1) {
						x--;
					}
				}
				
				if(x == player.getX() && y + 1 == player.getY()) {
					if(type == 0) {
						x++;
					}
					else if(type == 1) {
						x--;
					}
				}
			}
		}
	}
	
	private void checkDown() {
		for(int var1 = 0; var1 < Reference.players.length; var1++) {
			Player player = Reference.players[var1];
			if(player.id != id) {			
				if(x == player.getX()) {
					if(y + 1 == player.getY() || y == player.getY()) {
						y--;
					}
				}
			}
		}
	}
	
	private void checkUp() {
		for(int var1 = 0; var1 < Reference.players.length; var1++) {
			Player player = Reference.players[var1];
			if(player.id != id) {			
				if(x == player.getX()) {
					if(y - 1 == player.getY() || y == player.getY()) {
						y++;
					}
				}
			}
		}
	}
	
	
	
	
	public void moveLeft() {
		if(x > 0 && Reference.map.getTileAt(x - 1, y).getTileID() == 0 && Reference.map.getTileAt(x - 1, y + 1).getTileID() == 0) {
			moveAndCheckOtherPlayers(0);
		}
	}
	
	public void moveRight() {
		if(x < Reference.mapWidth - 1 && Reference.map.getTileAt(x + 1, y + 1) == null || Reference.map.getTileAt(x + 1, y + 1).getTileID() == 0) {
			moveAndCheckOtherPlayers(1);
		}
	}
	
	public void moveUp() {
		if(y > 0 && Reference.lastJumpTime >= 60 && Reference.map.getTileAt(x, y - 1).getTileID() == 0) {
			y--;
			checkUp();
		}
		Reference.updateTime = 0;
		Reference.lastJumpTime = 0;
	}
	
	public void moveDown() {
		if(y < Reference.mapHeight && !isOnSolidBlock()) {
			y++;
			checkDown();
		}
	}
	
	public int getID() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public Color getColour() {
		return colour;
	}
}

package roboyobo.hoppityHop.level;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.newdawn.slick.Color;

import roboyobo.hoppityHop.game.Building;
import roboyobo.hoppityHop.gui.LevelBox;
import roboyobo.hoppityHop.tile.Tile;
import roboyobo.hoppityHop.util.Chat;
import roboyobo.hoppityHop.util.Exception;
import roboyobo.hoppityHop.util.Reason;
import roboyobo.hoppityHop.util.Reference;

public class Level implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Tile[][] level;
	private Building[][] buildings;
	
	private boolean isUnlocked, isDone;
	private String name;
	private int page = 0;
	private File levelFile;
	private Level l;
	private Color color;
	private int[] blocks;
	
	public Level(int par1, int par2, boolean par3, boolean par4, String par5, int par6) {
		level = new Tile[par1][par2];
		buildings = new Building[par1][par2];
		isUnlocked = par3;
		isDone = par4;
		name = par5;
		page = par6;
		levelFile = new File("resources/levels/hoppityHop/" + name + ".VDMap");
	}
	
	public Tile[][] loadLevel(File file) {
		Level l;
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(levelFile));
			l = (Level) ois.readObject();
			level = l.getLevel();
			ois.close();
		} catch (FileNotFoundException e) {
			Chat.error(Exception.fileNotFoundException, Reason.loadError.getReason() + levelFile);
		} catch (IOException e) {
			Chat.error(Exception.IOException, Reason.ioError.getReason());
		}
		catch (ClassNotFoundException e) {
			Chat.error(Exception.classNotFoundException, Reason.classError.getReason() + e.getClass());
		}
		return level;
	}
	
	public File getLevelFile() {
		return levelFile;
	}
	
	public Tile[][] getLevel() {
		return level;
	}
	
	public Building[][] getBuildings() {
		return buildings;
	}
	
	public void setTileAt(int par1, int par2, Tile par3) {
		level[par1][par2] = par3;
	}
	
	public void setBuildingAt(int par1, int par2, Building par3) {
		buildings[par1][par2] = par3;
	}
	
	public void setIDAt(int par1, int par2, int par3, int par4, boolean par5) {
		if(par1 == 0) {
			Tile t = new Tile(par2, par3, par4, par5);
			setTileAt(par2, par3, t);
		}
		else if(par1 == 1) {
			Building b = new Building(par2, par3, par4, par5, false); 
			setBuildingAt(par2, par3, b);
		}
	}
	
	public Tile getTileAt(int par1, int par2) {
		return level[par1][par2];
	}
	
	public Building getBuildingAt(int par1, int par2) {
		return buildings[par1][par2];
	}

	public boolean isUnlocked() {
		return isUnlocked;
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	public String getName() {
		return name;
	}
	
	
	public LevelBox getLevelBox(int par1) {
		LevelBox levelBox = new LevelBox(Reference.screenWidth / 2 - (300 / 2), (Reference.screenHeight / 2 - 250) + (par1 * 150), 300, 100, this);
		return levelBox;
	}

	public int getPage() {
		return page;
	}

	public void setColor(Color par1) {
		color = par1;
	}

	public Color getColor() {
		return color;
	}
	
	public void setBlocks(int[] par1) {
		blocks = par1;
	}
	
	public int[] getBlocks() {
		return blocks;
	}

	public void clear() {
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
				setIDAt(1, var1, var2, 0, false);
			}
		}
	}

	
	
}

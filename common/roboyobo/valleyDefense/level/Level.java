package roboyobo.valleyDefense.level;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.newdawn.slick.Color;

import roboyobo.valleyDefense.gui.LevelBox;
import roboyobo.valleyDefense.tile.Tile;
import roboyobo.valleyDefense.util.Reference;

public class Level implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Tile[][] level;
	
	private boolean isUnlocked, isDone;
	private String name;
	private int page = 0;
	private File levelFile;
	private Level l;
	private Color color;
	
	public Level(int par1, int par2, boolean par3, boolean par4, String par5, int par6) {
		level = new Tile[par1][par2];
		isUnlocked = par3;
		isDone = par4;
		name = par5;
		page = par6;
		levelFile = new File("resources/levels/valleyDefense/" + name + ".VDMap");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return level;
	}
	
	public File getLevelFile() {
		return levelFile;
	}
	
	public Tile[][] getLevel() {
		return level;
	}
	
	public void setTileAt(int par1, int par2, Tile par3) {
		level[par1][par2] = par3;
	}
	
	public Tile getTileAt(int par1, int par2) {
		return level[par1][par2];
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
	
}

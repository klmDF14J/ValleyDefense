package roboyobo.nac.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.hoppityHop.tile.Tile;
import roboyobo.nac.Box;
import roboyobo.nac.Reference;

public class InGameState extends BasicGameState {


	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
	
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
				Reference.board[var1][var2] = new Box(Reference.boxPos[var1], Reference.boxPos[var2]);
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		for(int var1 = 0; var1 < Reference.mapWidth; var1++) {
			for(int var2 = 0; var2 < Reference.mapHeight; var2++) {
				Reference.board[var1][var2].render(g);
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		int mouse_x = x;
		int mouse_y = y;
		int mouseXScaled;
		int mouseYScaled;
		int id = Reference.currentPlayer;
		mouse_x -= 40;
		mouse_y -= 40;
		mouseXScaled = mouse_x / 40;
		mouseYScaled = mouse_y / 40;
		
		if(mouseXScaled - 1 >= 0 && mouseYScaled - 1 >= 0 && mouseXScaled <= Reference.mapWidth && mouseYScaled <= Reference.mapHeight) {
			Reference.board[mouseXScaled - 1][mouseYScaled - 1].setValue(id);
			if(Reference.currentPlayer == 0) { Reference.currentPlayer = 1; } 
			else if(Reference.currentPlayer == 1) { Reference.currentPlayer = 2; } 
			else if(Reference.currentPlayer == 2) { Reference.currentPlayer = 1; } 
		}
	}
	
	@Override
	public int getID() {
		return Reference.inGame;
	}

}

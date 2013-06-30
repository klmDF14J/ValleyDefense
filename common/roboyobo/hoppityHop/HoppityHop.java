package roboyobo.hoppityHop;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.hoppityHop.loader.Textures;
import roboyobo.hoppityHop.state.GameLevelSelectorState;
import roboyobo.hoppityHop.state.GameState;
import roboyobo.hoppityHop.state.MapEditorState;
import roboyobo.hoppityHop.state.MenuState;
import roboyobo.hoppityHop.util.Reference;



public class HoppityHop extends StateBasedGame {

	public static AppGameContainer app;
	public static int stateID;
	
	public HoppityHop() {
		super("Hoppity Hop");
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		Textures.load();
		MapEditorState.setupXValues();
		MapEditorState.setupYValues();
		addState(new MenuState(Reference.menuID));
		addState(new GameLevelSelectorState(Reference.gameSelectorID));
		addState(new MapEditorState(Reference.mapEditorID));
		addState(new GameState(Reference.gameID));
		enterState(Reference.menuID);
		stateID = getCurrentStateID();
	}
	
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new HoppityHop());
		app.setDisplayMode(Reference.screenWidth, Reference.screenHeight, false);
		app.start();
		app.setTargetFrameRate(60);
	}

	
	@Override
	public void mousePressed(int button, int x, int y) {
		if(getCurrentStateID() == Reference.menuID) {
			MenuState.handleMouseClick(button, x, y, this);
		}
		if(getCurrentStateID() == Reference.gameSelectorID) {
			GameLevelSelectorState.handleMouseClick(button, x, y, this);
		}
		if(getCurrentStateID() == Reference.mapEditorID) {
			MapEditorState.handleMouseClick(button, x, y);
		}
		if(getCurrentStateID() == Reference.gameID) {
			GameState.handleMouseClick(button, x, y, this);
		}
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(getCurrentStateID() == Reference.mapEditorID) {
			MapEditorState.handleKeyPress(key, c);
		}
		
		if(getCurrentStateID() == Reference.gameSelectorID) {
			GameLevelSelectorState.handleKeyPress(key, c);
		}
		
		if(getCurrentStateID() == Reference.gameID) {
			GameState.handleKeyPress(key, c);
		}
	}
	
}

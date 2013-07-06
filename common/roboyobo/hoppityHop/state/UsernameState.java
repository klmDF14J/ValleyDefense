package roboyobo.hoppityHop.state;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import roboyobo.hoppityHop.HoppityHop;
import roboyobo.hoppityHop.gui.Button;
import roboyobo.hoppityHop.level.Level;
import roboyobo.hoppityHop.loader.Sounds;
import roboyobo.hoppityHop.loader.Textures;
import roboyobo.hoppityHop.util.Chat;
import roboyobo.hoppityHop.util.Exception;
import roboyobo.hoppityHop.util.Reason;
import roboyobo.hoppityHop.util.Reference;

public class UsernameState extends BasicGameState {
	
	public int stateID;
	private TextField tf;
	private MouseOverArea moa;
	
	public UsernameState(int StateID) {
		stateID = StateID;
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException, ArithmeticException {
		tf = new TextField(gc, Reference.fonts.get(4), 300, 100, 200, 50, new ComponentListener() {
			public void componentActivated(AbstractComponent source) {
				tf.setFocus(true);
			}
		});
		moa = new MouseOverArea(gc, Textures.button, 600, 100, 200, 90, new ComponentListener() {
			public void componentActivated(AbstractComponent source) {
				sbg.enterState(Reference.menuID, new FadeOutTransition(), new FadeInTransition());
				
			}
		});
		
		moa.setMouseOverImage(Textures.buttonH);
		moa.setMouseDownSound(Sounds.beep);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Background.tile(Reference.tiles.get(7));
		tf.render(gc, g);
		moa.render(gc, g);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}

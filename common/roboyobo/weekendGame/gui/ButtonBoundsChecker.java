package roboyobo.weekendGame.gui;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import roboyobo.weekendGame.WeekendGame;
import roboyobo.weekendGame.util.Reference;

public class ButtonBoundsChecker { 
	public static void checkBounds(int x, int y, StateBasedGame sbg) {
		if(sbg.getCurrentStateID() == 1) {
		Rectangle bounds = new Rectangle(x,y,10, 10);
		if(bounds.intersects(Reference.startButtonBounds)) {
			sbg.enterState(Reference.wave1State, new FadeOutTransition(), new FadeInTransition());
		}
	}
	}
}

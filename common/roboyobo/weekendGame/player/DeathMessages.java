package roboyobo.weekendGame.player;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import roboyobo.weekendGame.util.Reference;

public class DeathMessages {
	public static void showDeathMessages(StateBasedGame sbg) {
		Reference.gameOverTextShown = true;
		sbg.enterState(Reference.gameOverState, new FadeOutTransition(), new FadeInTransition());
	}
}

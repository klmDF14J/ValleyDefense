package roboyobo.hoppityHop.loader;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import roboyobo.hoppityHop.util.Reference;

public class Sounds {
	
	public static Sound beep;
	
	public static void load() throws SlickException {
		beep = new Sound(Reference.soundsDirectory + "Beep.wav");
	}
}

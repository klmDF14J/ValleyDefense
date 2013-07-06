package roboyobo.hoppityHop.state;

import org.newdawn.slick.Image;

import roboyobo.hoppityHop.util.Reference;

public class Background {

	public static void tile(Image par1) {
		for(int var1 = 0; var1 < (Reference.screenWidth / (Reference.tileSize * Reference.tileScaleSize)); var1++) {
			for(int var2 = 0; var2 < (Reference.screenHeight / (Reference.tileSize * Reference.tileScaleSize)); var2++) {
				par1.draw((Reference.tileSize * Reference.tileScaleSize) * var1, (Reference.tileSize * Reference.tileScaleSize) * var2, Reference.tileScaleSize);
			}
		}
	}

}

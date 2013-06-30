package roboyobo.weekendGame.player;

import org.newdawn.slick.Graphics;

import roboyobo.weekendGame.loader.TextureLoader;
import roboyobo.weekendGame.util.Reference;

public class HealthManager {
	public static float getHealthLossBasedOnMob(String mob) {
		float healthLoss = 0;
		if(mob == "Zombie") {
			healthLoss = 0.001F;
		}
		else if(mob == "Wave2Zombie") {
			healthLoss = 0.0015F;
		}
		else if(mob == "Devil") {
			healthLoss = 0.003F;
		}
		else {
			healthLoss = 0F;
		}
		return healthLoss;
	}
	
	public static void dealDamage(float damage, String mobThatAttacked) {
		if(Reference.playerHealth > 0) {
		Reference.playerHealth -= damage;
		Reference.lastHitByMob = mobThatAttacked;
		}
	}

	public static void renderHealthBar(Graphics g) {
		int i = 0;
		if(Reference.playerHealth < 1) {
			i = 10;
		}
		else if(Reference.playerHealth < 11) {
			i = 9;
		}
		else if(Reference.playerHealth < 21) {
			i = 8;
		}
		else if(Reference.playerHealth < 31) {
			i = 7;
		}
		else if(Reference.playerHealth < 41) {
			i = 6;
		}
		else if(Reference.playerHealth < 51) {
			i = 5;
		}
		else if(Reference.playerHealth < 61) {
			i = 4;
		}
		else if(Reference.playerHealth < 71) {
			i = 3;
		}
		else if(Reference.playerHealth < 81) {
			i = 2;
		}
		else if(Reference.playerHealth < 91) {
			i = 1;
		}
		else if(Reference.playerHealth < 101) {
			i = 0;
		}
		
		if(i != 10) {
		TextureLoader.healthBarImages[i].draw(690, 100, 10F);
		}
		else {
			TextureLoader.healthBarImages[10].draw(690, 100, 10F);
		}
	}
}

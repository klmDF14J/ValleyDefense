package roboyobo.weekendGame.state.wave;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import roboyobo.weekendGame.entity.mob.Zombie;
import roboyobo.weekendGame.loader.TextureLoader;
import roboyobo.weekendGame.player.DeathMessages;
import roboyobo.weekendGame.player.HealthManager;
import roboyobo.weekendGame.util.Reference;

public class Wave1State extends BasicGameState {
	
	
	private Random rand = new Random();
	
	private double randomNum;
	private int numOfTimesRun = 0;
	
	public static Zombie zombie1;
	public static Zombie zombie2;
	public static Zombie zombie3;
	public static Zombie zombie4;
	public static Zombie zombie5;
	
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		zombie1 = new Zombie(100, Reference.wave1StartY, 1);
		zombie2 = new Zombie(201, Reference.wave1StartY, 2);
		zombie3 = new Zombie(302, Reference.wave1StartY, 3);
		zombie4 = new Zombie(403, Reference.wave1StartY, 4);
		zombie5 = new Zombie(504, Reference.wave1StartY, 5);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		TextureLoader.gameBackground.draw(0, 0);
		int[] coords = {0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000};
		
		for(int i = 0; i < 20; i++) {
			TextureLoader.wave1Floor.draw(coords[i], 550, 2.5F);
		}
		
		TextureLoader.playerBoy.draw(Reference.playerX, Reference.playerY, Reference.mobScaleFactor);
		if(Reference.currentWave == 1) {
		zombie1.render(gc, sbg, g);
		zombie2.render(gc, sbg, g);
		zombie3.render(gc, sbg, g);
		zombie4.render(gc, sbg, g);
		zombie5.render(gc, sbg, g);
		}
		g.drawString("Health: " + (int) Reference.playerHealth, 100, 100);
		HealthManager.renderHealthBar(g);
		//g.resetTransform();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if(Reference.isDead && !Reference.gameOverTextShown) {
			DeathMessages.showDeathMessages(sbg);
		}
		
		if(Reference.playerHealth <= 0) {
			Reference.isDead = true;
		}
		Reference.playerBounds = new Rectangle(Reference.playerX, Reference.playerY, 50, 100);
		if(Reference.currentWave == 1 && !Reference.isDead) {
		
		if(zombie1.isDead && zombie2.isDead && zombie3.isDead && zombie4.isDead && zombie5.isDead) {
			System.out.println("Wave 1 Complete!");
			Reference.waveFinished = true;
		}
		
		if(Reference.waveFinished) {
			Reference.waveFinishedTimer += delta;
			System.out.println("Time Passed Since Wave End: " + Reference.waveFinishedTimer);
		}
		
		if(Reference.waveFinishedTimer >= Reference.waveFinishedTimeToWaitFor) {
			Reference.waveFinished = false;
			Reference.waveFinishedTimer = 0;
			Reference.currentWave = 2;
			sbg.enterState(Reference.wave2State, new FadeOutTransition(), new FadeInTransition());
		}
		zombie1.update(gc, sbg, delta);
		zombie2.update(gc, sbg, delta);
		zombie3.update(gc, sbg, delta);
		zombie4.update(gc, sbg, delta);
		zombie5.update(gc, sbg, delta);
		}
		if(gc.getInput().isKeyDown(Input.KEY_D) && Reference.playerX < 900) {
			Reference.playerX += Reference.movementSpeed;
		}
		if(gc.getInput().isKeyDown(Input.KEY_A) && Reference.playerX > 0) {
			Reference.playerX -= Reference.movementSpeed;
		}
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE) && !Reference.isFalling && !Reference.isJumping) {
			Reference.isJumping = true;
		}
		
		if(Reference.isFalling && Reference.playerY < 450) {
			Reference.playerY += Reference.gravity;
			if(Reference.playerY >= 450) {
				Reference.isFalling = false;
			}
		}
		if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		
		if(Reference.isJumping) {
			if(Reference.playerY > Reference.startY - Reference.jumpHeight) {
			Reference.playerY -= Reference.jumpSpeed;
			
			}
			else {
				Reference.isJumping = false;
				Reference.isFalling = true;
			}
		}
		
		if(Reference.isAttacking) {
			Reference.timePassedSinceAttack++;
		}
		
		if(Reference.timePassedSinceAttack >= 300) {
			Reference.isAttacking = false;
			Reference.lineSizeX = 100;
			Reference.timePassedSinceAttack = 0;
			Reference.leftAttackLine = 0;
		}
		
	}

	@Override
	public int getID() {
		return Reference.wave1State;
	}
	
	public void getAndCheckRandomNumber() {
		numOfTimesRun++;
		if(numOfTimesRun == 60) {
		numOfTimesRun = 0;
		randomNum = rand.nextInt(2);
		if(randomNum == 1) {
			Reference.shouldEnemySpawn = true;
		}
		System.out.println(randomNum);
		}
	}

	
	


}

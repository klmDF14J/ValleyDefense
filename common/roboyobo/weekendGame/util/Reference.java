package roboyobo.weekendGame.util;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.opengl.Texture;

import roboyobo.weekendGame.state.wave.Wave1State;

public class Reference {
	public static int launchScreenState = 0;
	public static int mainMenuState = 1;
	public static int wave1State = 2;
	public static int standardButton = 0;
	public static float playerX = 475;
	public static float playerY = 450;
	public static float movementSpeed = 0.3F;
	public static float jumpHeight = 250;
	public static boolean isFalling = false;
	public static boolean isJumping = false;
	public static float jumpSpeed = 0.3F;
	public static float gravity = 0.15F;
	public static float startX = 475;
	public static float startY = 450;
	public static boolean shouldEnemySpawn = false;
	public static int currentWave = 1;
	public static int wave1StartY = 450;
	public static float wave1MovementSpeed = 0.1F;
	public static Rectangle playerBounds = new Rectangle(playerX, playerY, 100, 100);
	public static float playerHealth = 100;
	public static float mobScaleFactor = 3.125F;
	public static java.awt.Rectangle[] mobBoundsArray = new java.awt.Rectangle[10];
	public static String imagesDirectory = "/resources/images/weekendGame";
	public static boolean isAttacking;
	public static int lineSizeX = 100;
	public static int mouseX;
	public static int mouseY;
	public static String attackDirection;
	public static int attackLineSize;
	public static float swordDamageDealt = 0.03F;
	public static int timePassedSinceAttack;
	public static int leftAttackLine;
	public static boolean waveFinished;
	public static int waveFinishedTimer;
	public static int waveFinishedTimeToWaitFor = 3000;
	public static int wave2State = 3;
	public static float wave2MovementSpeed = 0.25F;
	public static float wave1heightToFollowPlayer = 375;
	public static float wave2heightToFollowPlayer = 200;
	public static float buttonScaleSize = 2.5F;
	public static Rectangle startButtonBounds = new Rectangle(200,200,500,125);
	public static boolean isDead;
	public static boolean gameOverTextShown = false;
	public static String lastHitByMob;
	public static int gameOverState = 4;
	public static float screenWidth = 1400;
}

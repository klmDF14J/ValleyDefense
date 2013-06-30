package roboyobo.weekendGame.entity.mob;

import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.weekendGame.entity.Entity;
import roboyobo.weekendGame.loader.TextureLoader;
import roboyobo.weekendGame.player.HealthManager;
import roboyobo.weekendGame.state.wave.Wave1State;
import roboyobo.weekendGame.util.Reference;

public class Zombie extends Entity {
	
	public java.awt.Rectangle bounds;
	public int ID;
	public float health = 50;
	public boolean isDead = false;
   public Zombie(float position, float position2, int ID) {
      super(position, position2);
      this.ID = ID;
      bounds = new java.awt.Rectangle( (int) position, (int) position2, 100, 100);
   }

   @Override
   public void update(GameContainer container, StateBasedGame game, int delta) {
	   if(!this.isDead) {
	   bounds = new java.awt.Rectangle( (int) position, (int) position2, 100, 100);
	   if(this.position < Reference.playerX && !bounds.getBounds().intersectsLine(Reference.playerX, Reference.playerY, Reference.playerX + Reference.lineSizeX, Reference.playerY + 100)) {
			if(Reference.playerY > Reference.wave1heightToFollowPlayer && !bounds.intersects(new java.awt.Rectangle((int)Reference.playerX, (int) Reference.playerY, 100, 100))) {		 
				
				this.position += Reference.wave1MovementSpeed;
			}
	   }
	   
	   else if(this.position > Reference.playerX && !bounds.getBounds().intersectsLine(Reference.playerX, Reference.playerY, Reference.playerX + Reference.lineSizeX, Reference.playerY + 100)) {
		   if(Reference.playerY > Reference.wave1heightToFollowPlayer && !bounds.intersects(new java.awt.Rectangle((int)Reference.playerX, (int) Reference.playerY, 100, 100))) {		    
		   this.position -= Reference.wave1MovementSpeed;
		   }
		   }
	   }
	   
	  
	   if(bounds.getBounds().intersectsLine(Reference.playerX, Reference.playerY, Reference.playerX + Reference.lineSizeX, Reference.playerY + 100) && !this.isDead) {
		   HealthManager.dealDamage(HealthManager.getHealthLossBasedOnMob("Zombie"), "A Zombie");
	   }
	   if(bounds.getBounds().intersectsLine(Reference.playerX, Reference.playerY, Reference.playerX - Reference.leftAttackLine + Reference.lineSizeX, Reference.playerY + 100) && Reference.isAttacking) {
		   if(Reference.attackDirection == "right") {
		   health -= Reference.swordDamageDealt;
		   }
		   
	   }
	   
	   if(bounds.getBounds().intersectsLine(Reference.playerX - Reference.leftAttackLine, Reference.playerY, Reference.playerX - Reference.leftAttackLine + Reference.lineSizeX, Reference.playerY + 100) && Reference.isAttacking) {
		   if(Reference.attackDirection == "left") {
			   health -= Reference.swordDamageDealt;
		   } 
		  
	   }
	   
	   if(health <= 0 && !this.isDead) {
		   this.health = 0;
		   this.isDead = true;
		   try {
			Sound sound = new Sound("/resources/sounds/weekendGame/Mob Hurt.wav");
			sound.play();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   }


   @Override
   public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
	   graphics.drawRect(Reference.playerX - Reference.leftAttackLine, Reference.playerY, Reference.lineSizeX,100);
	   
	   
	   graphics.drawString("ID: " + ID + " Health: " + health, position, position2 - 50);
	   if(!this.isDead) {
     TextureLoader.zombie.draw(position, position2, Reference.mobScaleFactor);
	   }
	   else {
		   
	   }
   }

   @Override
   public void shoot(GameContainer container, float cameraX, float cameraY) {
      // TODO Auto-generated method stub
   }
   
}
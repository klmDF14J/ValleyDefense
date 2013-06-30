package roboyobo.weekendGame.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Entity {

   protected float position;
   protected float position2;


   public Entity(float position, float position2) {
      this.position = position;
      this.position2 = position2;
   }

   public abstract void update(GameContainer container, StateBasedGame game, int delta);
   
   public abstract void render(GameContainer container, StateBasedGame game, Graphics graphics);

   public abstract void shoot(GameContainer container, float cameraX, float cameraY);



   public float getPosition() {
      return position;
   }

   public void setPosition(float position, float position2) {
      this.position = position;
      this.position2 = position2;
   }

}
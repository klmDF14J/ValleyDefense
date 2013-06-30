package roboyobo.weekendGame.player;

import roboyobo.weekendGame.util.Reference;

public class AttackChecker {
	public static void checkMouseCoords() {
		if(Reference.mouseX > Reference.playerX) {
			Reference.attackDirection = "right";
			Reference.isAttacking = true;
		}
		else if(Reference.mouseX < Reference.playerX) {
			Reference.attackDirection = "left";
			Reference.isAttacking = true;
		}
		if(Reference.attackDirection == "right") {
			Reference.lineSizeX = 200;
			Reference.isAttacking = true;
		}
		if(Reference.attackDirection == "left") {
			Reference.leftAttackLine = 100;
			Reference.lineSizeX = 200;
			Reference.isAttacking = true;
		}
	}
}

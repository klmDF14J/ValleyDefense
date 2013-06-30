package roboyobo.hoppityHop.game;

public class MoneyHandler {
	
	private static int money;
	
	public static void addMoney(int par1) {
		money += par1;
	}
	
	public static int getMoney() {
		return money;
	}
}

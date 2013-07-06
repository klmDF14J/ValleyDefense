package roboyobo.hoppityHop.util;

public class Chat {
	
	
	public static void error(Enum type, String string) {
		System.out.println("Error: " + type + ". " + string);
	}

	public static void print(String message) {
		System.out.println(message);
	}
	
	public static void handlePlayerJoin(String name) {
		Chat.print("Welcome " + name);
	}
}

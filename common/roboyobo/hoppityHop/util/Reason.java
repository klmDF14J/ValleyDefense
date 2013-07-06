package roboyobo.hoppityHop.util;

public enum Reason {
	loadError("Failed To Load File: "), ioError("IO Error"), classError("Class Not Found"), slickError("Slick Exception At: "), arithmeticError("An Arithmetic Exception Occured");
	
	
	String reason;
	
	private Reason(String par1) {
		reason = par1;
	}
	
	public String getReason() {
		return reason;
	}
}

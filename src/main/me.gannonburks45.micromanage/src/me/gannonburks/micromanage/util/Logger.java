package src.me.gannonburks.micromanage.util;

public class Logger {

	//Info log thing
	public static void info(String msg) {
		
		System.out.println("[MicroManageJ](INFO): " + msg);
	}
	
	//Warn log thing
	public static void warn(String msg) {
		
		System.out.println("[MicroManageJ](WARN): " + msg);
	}
	
	//ERROR log thing
	public static void error(String msg) {
			
		System.out.println("[MicroManageJ](ERROR): " + msg.toUpperCase());
	}
}

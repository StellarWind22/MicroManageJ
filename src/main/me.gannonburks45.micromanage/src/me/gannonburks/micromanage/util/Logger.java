package src.me.gannonburks.micromanage.util;

public class Logger {

	/**
	 * Info logging method mostly just
	 * here to make switching over to
	 * log4j easier this method and the class
	 * it is in is temporary.
	 * 
	 * @param msg Message to be logged.
	 */
	public static void info(String msg) {
		
		System.out.println("[MicroManageJ](INFO): " + msg);
	}
	
	/**
	 * Warning logging method mostly just
	 * here to make switching over to
	 * log4j easier this method and the class
	 * it is in is temporary.
	 * 
	 * @param msg Message to be logged.
	 */
	public static void warn(String msg) {
		
		System.out.println("[MicroManageJ](WARN): " + msg);
	}
	
	/**
	 * Error logging method mostly just
	 * here to make switching over to
	 * log4j easier this method and the class
	 * it is in is temporary.
	 * 
	 * @param msg Message to be logged.
	 */
	public static void error(String msg) {
			
		System.out.println("[MicroManageJ](ERROR): " + msg.toUpperCase());
	}
}

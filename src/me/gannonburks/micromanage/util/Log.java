package me.gannonburks.micromanage.util;

import java.util.logging.Logger;

public class Log {

	//Create Private Logger
	private static Logger logger;
	
	public Log(String name) {
		
		logger = Logger.getLogger(name);
	}
	
	//Get Logger
	public static Logger get() {
		return logger;
	}
	
	//Info
	public static void Info(String msg) {
		logger.info(msg);
	}
	
	//Warn
	public static void Warn(String msg) {
		logger.warning(msg);
	}
	
	//Severe
	public static void Severe(String msg) {
		logger.severe(msg);
	}
}

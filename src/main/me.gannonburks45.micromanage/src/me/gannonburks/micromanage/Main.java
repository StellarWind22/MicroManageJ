package src.me.gannonburks.micromanage;

import net.dv8tion.jda.api.JDA;
import src.me.gannonburks.micromanage.init.Init;
import src.me.gannonburks.micromanage.util.Logger;

public class Main {

	//Instantiate stuff
	public static JDA bot;
	
	//Global Stuff
	public static final String DEFAULT_PREFIX = "-";
		
	/*
	 * PROGRAM ENTRY POINT
	 */
	public static void main(String[] args)
	{	
		Init.init(args);
	}
	
	public static void shutdown()
	{	
		Logger.info("Bot shutting down!");
		System.exit(0);
	}
}

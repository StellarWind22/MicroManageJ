package src.me.gannonburks.micromanage;

import net.dv8tion.jda.api.JDA;
import src.me.gannonburks.micromanage.util.Init;
import src.me.gannonburks.micromanage.util.Logger;

public class Main {

	//Instantiate stuff
	public static JDA bot;
	
	//Global Stuff
	public static final String PREFIX = "-";
	
	/*
	 * PROGRAM ENTRY POINT
	 */
	public static void main(String[] args)
	{	
		//Register Commands
		Init.regCommands();
		
		//Login to Discord
		Init.login(args);
		
		//Start Up Console
		Init.console();
	}
	
	public static void shutdown()
	{	
		Logger.info("Bot shutting down!");
		System.exit(0);
	}
}
package src.me.gannonburks.micromanage;

import java.util.logging.Logger;

import net.dv8tion.jda.api.JDA;
import src.me.gannonburks.micromanage.util.Init;

public class Main {

	//Instantiate stuff
	public static JDA bot;
	public static final Logger LOG = Logger.getLogger("MicroManage");
	
	//Global Stuff
	public static final String prefix = "-";
	
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
		System.exit(0);
	}
}

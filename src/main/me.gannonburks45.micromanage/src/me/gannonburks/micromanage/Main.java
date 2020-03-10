package src.me.gannonburks.micromanage;

import java.util.Scanner;
import java.util.logging.Logger;

import net.dv8tion.jda.api.JDA;
import src.me.gannonburks.micromanage.util.CommandHandler;
import src.me.gannonburks.micromanage.util.Init;

public class Main {

	//Instantiate stuff
	public static JDA bot;
	public static final Logger LOG = Logger.getLogger("MicroManage");
	
	//Global Stuff
	public static final String prefix = "-";
	
	//Program Entry Point
	public static void main(String[] args) {
		
		//Register Commands
		Init.regCommands();
		
		//Login to Discord
		Init.login(args);
		
		//"Console"
		Scanner keyboard = new Scanner(System.in);
		
		String inp = "";
		
		while(!(inp.startsWith(prefix + "shutdown"))) {
		
			inp = keyboard.nextLine();
			
			if(CommandHandler.isCmd(inp)) {
				
				//Split input into args
				String[] consoleArgs = inp.split(" ");
				String label = consoleArgs[0].replaceFirst(prefix, "");
				
				CommandHandler.executeCommand(label, consoleArgs);
			}
		}
		
		//Exit Program
		keyboard.close();
		shutdown();
	}
	
	public static void shutdown() {
		
		System.exit(0);
	}
}

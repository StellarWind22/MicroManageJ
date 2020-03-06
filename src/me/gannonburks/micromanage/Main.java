package me.gannonburks.micromanage;

import java.util.Scanner;
import java.util.logging.Logger;

import me.gannonburks.micromanage.util.Init;
import me.gannonburks.micromanage.util.MsgHandler;
import net.dv8tion.jda.api.JDA;

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
		
		//Register Events
		Init.regEventListeners();
		
		//Login to Discord
		Init.login(args);
		
		
		//Start up console
		openConsole();
		
	}
	
	private static void openConsole()
	{
		Scanner inp = new Scanner(System.in);
		
		String consoleIn = labeledInput("Command: ", inp);
		
		while(consoleIn != prefix + "shutdown")
		{
			consoleIn = labeledInput("Command: ", inp);
			
			String[] consoleArgs = consoleIn.split(" ");
			String label = consoleArgs[0].replaceFirst(prefix, "");
			
			
			
			//If you type prefix + say broadcast message in chat
			if(label == "say")
			{
				MsgHandler.sendMsgBroadcast("general", String.join(" ", consoleArgs).replaceFirst(Main.prefix + "say", "").trim(), 1);
			}
			else 
			{
				System.out.print("Invalid Command");
			}
		}
		System.exit(0);
	}
	
	private static String labeledInput(String label, Scanner scanner) {
		
		System.out.print(label);
		String inp = scanner.nextLine();
		System.out.print("\n");
		
		return inp;
	}
}

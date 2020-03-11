package src.me.gannonburks.micromanage.init;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.CommandHandler;
import src.me.gannonburks.micromanage.event.events.OnMessageReceivedEvent;
import src.me.gannonburks.micromanage.util.Logger;

public class Init {

	/*
	 * Login method
	 */
	public static void login(String[] args) {
		
		if(args.length > 0) {	//Check for a argument
			
			try
			{	//Try to log in
				Main.bot = new JDABuilder(AccountType.BOT)
						.addEventListeners(new OnMessageReceivedEvent(false))
						.setToken(args[0]).build();
			}
			catch (LoginException e)
			{
				Logger.error("Invalid Auth token!");
				Main.shutdown();
			}
			
		} else {
			
			//Ask for valid token
			Logger.error("Please input a authorization token as the first argument!");
			Main.shutdown();
		}
	}
	
	/*
	 * Console Method
	 */
	public static void console() {
		
		Scanner keyboard = new Scanner(System.in);
		String inp = "";
		
		while(!(inp.startsWith(Main.PREFIX + "shutdown")))
		{
		
			inp = keyboard.nextLine();
			
			//Split input into args
			String[] consoleArgs = inp.split(" ");
			String label = consoleArgs[0].replaceFirst(Main.PREFIX, "");
			
			if(CommandHandler.isCmd(inp))
			{
				CommandHandler.executeCommand(label, consoleArgs);
			}
			else
			{
				System.out.println("\"" + label + "\" is not a valid command!");
			}
		}
		
		//Exit Program
		keyboard.close();
		Main.shutdown();
	}
}

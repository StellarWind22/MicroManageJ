package src.me.gannonburks.micromanage.util;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.CommandRegistry;
import src.me.gannonburks.micromanage.commands.EchoCommand;
import src.me.gannonburks.micromanage.commands.PrivateMessageCommand;
import src.me.gannonburks.micromanage.commands.ServerMessageCommand;
import src.me.gannonburks.micromanage.commands.ShutdownCommand;
import src.me.gannonburks.micromanage.event.OnMessageReceivedEvent;

public class Init {

	/*
	 * Login method
	 */
	public static void login(String[] args) {
		
		if(args.length > 0) {	//Check for a argument
			
			try {				//Try to log in
				
				Main.bot = new JDABuilder(AccountType.BOT)
						.addEventListeners(new OnMessageReceivedEvent())
						.setToken(args[0]).build();
				
			} catch (LoginException e) {
				
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
	 * Register commands
	 */
	public static void regCommands() {
		
		CommandRegistry.register(new EchoCommand("echo").getCommand());
		CommandRegistry.register(new PrivateMessageCommand("pm").getCommand());	
		CommandRegistry.register(new ServerMessageCommand("sm").getCommand());
		CommandRegistry.register(new ShutdownCommand("shutdown").getCommand());
	}
	
	/*
	 * Console Method
	 */
	public static void console() {
		
		Scanner keyboard = new Scanner(System.in);
		String inp = "";
		
		while(!(inp.startsWith(Main.prefix + "shutdown")))
		{
		
			inp = keyboard.nextLine();
			
			//Split input into args
			String[] consoleArgs = inp.split(" ");
			String label = consoleArgs[0].replaceFirst(Main.prefix, "");
			
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

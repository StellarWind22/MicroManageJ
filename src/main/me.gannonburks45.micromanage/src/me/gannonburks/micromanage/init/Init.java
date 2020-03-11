package src.me.gannonburks.micromanage.init;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.CommandRegistry;
import src.me.gannonburks.micromanage.command.commands.DisableCommand;
import src.me.gannonburks.micromanage.command.commands.EchoCommand;
import src.me.gannonburks.micromanage.command.commands.EnableCommand;
import src.me.gannonburks.micromanage.command.commands.HelpCommand;
import src.me.gannonburks.micromanage.command.commands.PrivateMessageCommand;
import src.me.gannonburks.micromanage.command.commands.ServerMessageCommand;
import src.me.gannonburks.micromanage.command.commands.ShutdownCommand;
import src.me.gannonburks.micromanage.event.events.OnMessageReceivedEvent;
import src.me.gannonburks.micromanage.util.CommandHandler;
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
						.addEventListeners(new OnMessageReceivedEvent())
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
	 * Register commands
	 */
	public static void regCommands()
	{
		CommandRegistry.register(new HelpCommand("help", false, null));
		CommandRegistry.register(new EchoCommand("echo", true, "Repeats message."));
		CommandRegistry.register(new PrivateMessageCommand("pm", true,"Sends a private message to someone."));	
		CommandRegistry.register(new ServerMessageCommand("sm", true,"Sends a message to a specific server in a specific channel."));
		CommandRegistry.register(new EnableCommand("enable", false, "Enables a command."));
		CommandRegistry.register(new DisableCommand("disable", false, "Disables a command."));
		CommandRegistry.register(new ShutdownCommand("shutdown", false, "Shuts down the bot."));
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

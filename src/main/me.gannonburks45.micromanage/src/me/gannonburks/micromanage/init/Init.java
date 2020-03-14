package src.me.gannonburks.micromanage.init;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.CommandHandler;
import src.me.gannonburks.micromanage.event.BotEvent;
import src.me.gannonburks.micromanage.event.events.OnGuildReady;
import src.me.gannonburks.micromanage.event.events.OnGuildUpdate;
import src.me.gannonburks.micromanage.event.events.OnMessageReceivedEvent;
import src.me.gannonburks.micromanage.module.Module;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.util.Logger;

public class Init {

	/*
	 * Primary initialization method
	 * 
	 * @param args Arguments for bot startup.
	 */
	public static void init(String[] args)
	{
		initDefModule();
		login(args);
		initListeners();
		console();
	}
	
	/*
	 * Login method
	 */
	public static void login(String[] args) {
		
		if(args.length > 0) {	//Check for a argument
			
			try
			{	//Try to log in
				Main.bot = new JDABuilder(AccountType.BOT).setToken(args[0]).build();
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
		
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		String inp = null;
		
		while(true)
		{
			inp = keyboard.nextLine();
			
			if(CommandHandler.isCmd(inp, null))
			{
				String label = CommandHandler.getLabel(inp, null);
				
				if(ModuleRegistry.containsConsoleCommand(label))
				{
					CommandHandler.execute(label, CommandHandler.getArgs(inp, null));
				}
				else
				{
					System.out.println("\"" + label + "\" is not a valid command, try " + Main.DEFAULT_PREFIX + "help for a list of valid commands!");
				}
			}
			else
			{
				System.out.println("\"" + inp + "\" is not a command, commands start with \"" + Main.DEFAULT_PREFIX + "\"!");
			}
		}
	}
	
	/*
	 * Default module init
	 */
	public static void initDefModule()
	{
		//Create Module
		Module def = new Module("default");
		
		//Populate Commands
		def.registerAllCommands
		(
				/*
				 * REPLACE WITH ACTUAL COMMANDS
				 * 
				new HelpCommand("help", false, true, null),
				new WhereAmICommand("whereami", false, true, "Tells you where you are."),
				new ServersCommand("servers", false, true, "Lists all servers in the server registry."),
				new EchoCommand("echo", true, true, "Repeats message."),
				new PrivateMessageCommand("pm", true, true, "Sends a private message to someone."),
				new ServerMessageCommand("sm", true, true, "Sends a message to a specific server in a specific channel."),
				new EnableCommand("enable", false, false, "Enables a command."),
				new DisableCommand("disable", false, false, "Disables a command."),
				new ShutdownCommand("shutdown", false, true, "Shuts down the bot.")
				*/
		);
		
		def.registerAllEvents
		(
				new OnMessageReceivedEvent(false),
				new OnGuildReady(false),
				new OnGuildUpdate(false)
		);
		
		//Register It
		ModuleRegistry.registerAll(def);
	}
	
	/*
	 * add event listeners to bot instance
	 */
	public static void initListeners()
	{
		for(BotEvent event : ModuleRegistry.getAllEvents())
		{
			Main.bot.addEventListener(event);
		}
	}
}

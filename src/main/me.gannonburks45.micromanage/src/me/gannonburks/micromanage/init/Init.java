package src.me.gannonburks.micromanage.init;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.BotCommandHandler;
import src.me.gannonburks.micromanage.command.commands.EchoCommand;
import src.me.gannonburks.micromanage.command.commands.HelpCommand;
import src.me.gannonburks.micromanage.command.commands.ModulesCommand;
import src.me.gannonburks.micromanage.command.commands.ServersCommand;
import src.me.gannonburks.micromanage.command.commands.SetDisabledCommand;
import src.me.gannonburks.micromanage.command.commands.ShutdownCommand;
import src.me.gannonburks.micromanage.event.BotEvent;
import src.me.gannonburks.micromanage.event.events.OnGuildReady;
import src.me.gannonburks.micromanage.event.events.OnGuildUpdate;
import src.me.gannonburks.micromanage.event.events.OnMessageReceivedEvent;
import src.me.gannonburks.micromanage.module.Module;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.util.Logger;

public class Init
{
	/**
	 * Primary initialization method
	 * 
	 * @param args 	First argument is the token all others are ignored
	 * 				this is meant to be passed in from main(Stringg[] args).
	 */
	public static void init(String[] args)
	{
		initCoreModule();
		login(args);
		initListeners();
		console();
	}
	
	/**
	 * Initialization method for logging the bot user
	 * into the Discord API.
	 * 
	 * @param args 	First argument is the token all others are ignored
	 * 				this is meant to be passed in from main(Stringg[] args).
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
	
	/**
	 * Initialization method for the console.
	 */
	public static void console()
	{
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		String inp = null;
		
		while(true)
		{
			inp = keyboard.nextLine();
			
			if(BotCommandHandler.isCmd(inp, null))
			{
				String label = BotCommandHandler.getLabel(inp, null);
				
				if(ModuleRegistry.containsConsoleCommand(label))
				{
					BotCommandHandler.execute(label, BotCommandHandler.getArgs(inp, null));
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
	
	/**
	 * Initialization method for the core module of the bot.
	 */
	public static void initCoreModule()
	{
		//Create Module
		Module core = new Module("core", "v1.0.0", "Crim", "Core module for bot.", "https://github.com/CrimsonDawn45/MicroManageJ/issues");
		
		//Populate Commands
		core.packageAllCommands
		(
				new HelpCommand("help","List all commands.", true, true, true, false),
				new EchoCommand("echo","Repeats what a user says back to them.", true, true, true, true),
				new ModulesCommand("modules", "Lists all active modules.", true, true, true , false),
				new ServersCommand("servers","Lists all servers that the bot is in.", true, true, true, false),
				new SetDisabledCommand("setdisabled","Changes disabled status of a command.", true, false, false, false),
				new ShutdownCommand("shutdown","Shuts down the bot.", false, true, true, false)
		);
		
		core.packageAllEvents
		(
				new OnMessageReceivedEvent(false),
				new OnGuildReady(false),
				new OnGuildUpdate(false)
		);
		
		//Register It
		ModuleRegistry.registerAll(core);
	}
	
	/**
	 * Initialization method for the event listeners of the bot.
	 */
	public static void initListeners()
	{
		for(BotEvent event : ModuleRegistry.getAllEvents())
		{
			Main.bot.addEventListener(event);
		}
	}
}

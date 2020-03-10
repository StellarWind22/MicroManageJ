package src.me.gannonburks.micromanage.util;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.CommandRegistry;
import src.me.gannonburks.micromanage.commands.DirectMessageCommand;
import src.me.gannonburks.micromanage.commands.EchoCommand;
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
				
				Main.LOG.severe("Auth token invalid!");
				Main.shutdown();
			}
			
		} else {
			
			//Ask for valid token
			Main.LOG.severe("Enter an auth token as the first argument!");;
			Main.shutdown();
		}
	}
	
	/*
	 * Register commands
	 */
	public static void regCommands() {
		
		CommandRegistry.register(new EchoCommand("echo").getCommand());
		CommandRegistry.register(new DirectMessageCommand("dm").getCommand());	
		CommandRegistry.register(new ShutdownCommand("shutdown").getCommand());
	}
}

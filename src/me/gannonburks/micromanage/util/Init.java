package me.gannonburks.micromanage.util;

import javax.security.auth.login.LoginException;

import me.gannonburks.micromanage.Main;
import me.gannonburks.micromanage.command.CommandRegistry;
import me.gannonburks.micromanage.commands.DirectMessageCommand;
import me.gannonburks.micromanage.commands.EchoCommand;
import me.gannonburks.micromanage.commands.ShutdownCommand;
import me.gannonburks.micromanage.event.OnMessageReceivedEvent;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;

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
				System.exit(0);
			}
			
		} else {
			
			//Ask for valid token
			Main.LOG.severe("Enter an auth token as the first argument!");;
			System.exit(0);
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

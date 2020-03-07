package me.gannonburks.micromanage.util;

import me.gannonburks.micromanage.Main;
import me.gannonburks.micromanage.command.CommandRegistry;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandHandler {
	
	/*
	 * COMMAND EXECUTION STUFF
	 */
	public final static void executeCommand(String label, String[] args, User sender, Object channel) {
		
		//Fire Command
		if(channel instanceof TextChannel) {				//Guild Channel Stuff
			
			TextChannel txtChannel = (TextChannel) channel;
			
			
			
			if(!(CommandRegistry.contains(label)))			//If that command doesn't exist send message
			{
				MessageHandler.sendMsgGuild(txtChannel, "\"" + label + "\" is not a valid command!");
				return;
			}
			
			//Fire command with args
			CommandRegistry.get(label).fireInGuild(args, sender, txtChannel);;
			return;
			
		} else if(channel instanceof PrivateChannel) {		//Private Channel Stuff
			
			PrivateChannel prvChannel = (PrivateChannel) channel;
			
			if(!(CommandRegistry.contains(label))) 			//If that command doesn't exist send message
			{
				MessageHandler.sendMsgPrivate(prvChannel, "\"" + label + "\" is not a valid command!");
			}
			
			//Fire command with args
			CommandRegistry.get(label).fireInPrivate(args, sender, prvChannel);
			return;
			
		} else {
			
			Main.LOG.severe("Object fed into command fire event was not a TextChannel or PrivateChannel!");
			Main.shutdown();
		}
		
	}
	
	public final static void executeCommand(String label, String[] args) {
		
		if(!(CommandRegistry.contains(label)))
		{
			System.out.println("\"" + label + "\" is not a valid command!");
			return;
		}
		
		CommandRegistry.get(label).fireInConsole(args);
	}
	
	//Check for prefix
	public static boolean isCmd(String cmd) {
		
		return cmd.toLowerCase().startsWith(Main.prefix);
	}
}

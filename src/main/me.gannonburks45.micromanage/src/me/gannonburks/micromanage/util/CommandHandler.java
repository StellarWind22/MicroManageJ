package src.me.gannonburks.micromanage.util;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.CommandRegistry;

public class CommandHandler {
	
	/*
	 * COMMAND EXECUTION STUFF
	 */
	public final static void executeCommand(String label, String[] args, User sender, Object channel) {
		
		//Fire Command
		if(channel instanceof TextChannel) {				//Guild Channel Stuff
			
			TextChannel txtChannel = (TextChannel) channel;
			
			
			
			if(!(CommandRegistry.contains(label, false)))			//If that command doesn't exist send message
			{
				MessageHandler.sendMsgGuild(txtChannel, "\"" + label + "\" is not a valid command, try " + Main.PREFIX + "help for a list of commands!");
				return;
			}
			
			//Fire command with args
			CommandRegistry.get(label, false).fireInGuild(args, sender, txtChannel);;
			return;
			
		} else if(channel instanceof PrivateChannel) {		//Private Channel Stuff
			
			PrivateChannel prvChannel = (PrivateChannel) channel;
			
			if(!(CommandRegistry.contains(label, false))) 			//If that command doesn't exist send message
			{
				MessageHandler.sendMsgPrivate(prvChannel, "\"" + label + "\" is not a valid command, try " + Main.PREFIX + "help for a list of commands!");
				return;
			}
			
			//Fire command with args
			CommandRegistry.get(label, false).fireInPrivate(args, sender, prvChannel);
			return;
			
		} else {
			
			Logger.error("Object fed into command fire event was not a TextChannel or PrivateChannel!");
			Main.shutdown();
		}
		
	}
	
	public final static void executeCommand(String label, String[] args) {
		
		if(!(CommandRegistry.contains(label, false)))
		{
			System.out.println("\"" + label + "\" is not a valid command, try " + Main.PREFIX + "help for a list of commands!");
			return;
		}
		
		CommandRegistry.get(label, false).fireInConsole(args);
	}
	
	//Check for prefix
	public static boolean isCmd(String cmd) {
		
		return cmd.toLowerCase().startsWith(Main.PREFIX);
	}
}

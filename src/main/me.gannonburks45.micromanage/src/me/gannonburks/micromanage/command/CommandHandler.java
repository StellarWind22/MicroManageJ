package src.me.gannonburks.micromanage.command;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.server.ServerRegistry;
import src.me.gannonburks.micromanage.util.Logger;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class CommandHandler {
	
	/*
	 * COMMAND EXECUTION STUFF
	 */
	public final static void executeCommand(String label, String[] args, User sender, Object channel) {
		
		//Fire Command
		if(channel instanceof TextChannel) {				//Guild Channel Stuff
			
			TextChannel txtChannel = (TextChannel) channel;
			
			if(!(ServerRegistry.get(txtChannel.getGuild().getName()).getCommandRegistry().contains(label, false)))			//If that command doesn't exist send message
			{
				MessageHandler.sendMsgGuild(txtChannel, "\"" + label + "\" is not a valid command, try " + Main.DEFAULT_PREFIX + "help for a list of commands!");
				return;
			}
			
			//Fire command with args
			ServerRegistry.get(txtChannel.getGuild().getName()).getCommandRegistry().get(label, false).fireInGuild(args, sender, txtChannel);;
			return;
			
		} else if(channel instanceof PrivateChannel) {		//Private Channel Stuff
			
			PrivateChannel prvChannel = (PrivateChannel) channel;
			
			CommandRegistry commandRegistry = ModuleRegistry.toCommandRegistry();
			
			if(!(commandRegistry.contains(label, false))) 			//If that command doesn't exist send message
			{
				MessageHandler.sendMsgPrivate(prvChannel, "\"" + label + "\" is not a valid command, try " + Main.DEFAULT_PREFIX + "help for a list of commands!");
				return;
			}
			
			if(!(commandRegistry.get(label, false).canRunInPrivate()))
			{
				MessageHandler.sendMsgPrivate(prvChannel, "\"" + label + "\" does not work in a private message channel!");
			}
			
			//Fire command with args
			commandRegistry.get(label, false).fireInPrivate(args, sender, prvChannel);
			return;
			
		} else {
			
			Logger.error("Object fed into command fire event was not a TextChannel or PrivateChannel!");
			Main.shutdown();
		}
		
	}
	
	
	public static final void executeCommand(String label, String[] args) {
		
		CommandRegistry commandRegistry = ModuleRegistry.toCommandRegistry();
		
		if(!(commandRegistry.contains(label, false)))
		{
			System.out.println("\"" + label + "\" is not a valid command, try " + Main.DEFAULT_PREFIX + "help for a list of commands!");
			return;
		}
		
		commandRegistry.get(label, false).fireInConsole(args);
	}
	
	
	//Check for prefix
	public static boolean isCmd(String cmd) {
		
		return cmd.toLowerCase().startsWith(Main.DEFAULT_PREFIX);
	}
}

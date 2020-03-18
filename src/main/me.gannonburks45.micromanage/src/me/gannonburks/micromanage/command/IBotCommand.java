package src.me.gannonburks.micromanage.command;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public interface IBotCommand {
	
	/**
	 * Method that fires a command event in a TextChannel of a discord server.
	 * 
	 * @param args 		Arguments for the command.
	 * @param sender 	User who sent this command.
	 * @param channel 	Channel of the guild that this will execute in.
	 */
	default void fireInGuild(String[] args, User sender, TextChannel channel) {}
	
	/**
	 * Method that fires a command event in a PrivateChannel for private messaging.
	 * 
	 * @param args 		Arguments for the command.
	 * @param sender 	User who sent this command.
	 * @param channel 	Private channel that the command will execute in.
	 */
	default void fireInPrivate(String[] args, User sender, PrivateChannel channel) {}
	
	/**
	 * Method that fires a command event in a PrivateChannel for private messaging.
	 * 
	 * @param args 	arguments for the command.
	 */
	default void fireInConsole(String[] args) {}
}

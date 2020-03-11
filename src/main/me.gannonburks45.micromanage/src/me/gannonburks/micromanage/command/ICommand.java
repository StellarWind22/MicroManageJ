package src.me.gannonburks.micromanage.command;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public interface ICommand {
	
	/*
	 * Method that fires a command event in a TextChannel of a discord server.
	 * 
	 * @param args Arguments for the command.
	 * @param sender User who sent this command.
	 * @param channel Channel of the guild that this will execute in.
	 * 
	 * @see Command event that is fired in a discord server.
	 */
	default void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		return;	
	}
	
	/*
	 * Method that fires a command event in a PrivateChannel for private messaging.
	 * 
	 * @param args arguments for the command.
	 * @param sender user who sent this command.
	 * @param channel private channel that the command will execute in.
	 * 
	 * @see Command event that is fired from a private message.
	 */
	default void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		return;	
	}
	
	/*
	 * Method that fires a command event in a PrivateChannel for private messaging.
	 * 
	 * @param args arguments for the command.
	 * 
	 * @see Command event that is fired from the console.
	 */
	default void fireInConsole(String[] args)
	{
		return;
	}
	
	/*
	 * Method that returns an instance of a command object.
	 * 
	 * @return Instance of Command.
	 */
	default Command getCommand()
	{	
		return (Command)this;
	}
}

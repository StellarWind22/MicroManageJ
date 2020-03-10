package src.me.gannonburks.micromanage.command;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public interface ICommand {
	
	
	default void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		return;	
	}
	
	default void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		return;	
	}
	
	default void fireInConsole(String[] args)
	{
		return;
	}
	
	
	default Command getCommand()
	{	
		return (Command)this;
	}
}

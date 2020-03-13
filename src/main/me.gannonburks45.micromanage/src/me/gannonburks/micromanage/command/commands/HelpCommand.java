package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.command.Command;

public class HelpCommand extends Command {

	public HelpCommand(String label, String description, boolean canFireInGuild, boolean canFireInPrivate, boolean canFireInConsole, boolean canDisable)
	{
		super(label, description, canFireInGuild, canFireInPrivate, canFireInConsole, canDisable);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		
	}
}

package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.Command;

public class ShutdownCommand extends Command {

	public ShutdownCommand(String label, String description, boolean canFireInGuild, boolean canFireInPrivate, boolean canFireInConsole, boolean canDisable)
	{
		super(label, description, canFireInGuild, canFireInPrivate, canFireInConsole, canDisable);
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		Main.shutdown();
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		Main.shutdown();
	}
}

package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.Command;

public class ShutdownCommand extends Command {

	public ShutdownCommand(String labelIn, boolean canDisableIn, boolean canRunInPrivateIn, String descriptionIn)
	{
		super(labelIn, canDisableIn, canRunInPrivateIn, descriptionIn);
	}

	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		Main.shutdown();
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

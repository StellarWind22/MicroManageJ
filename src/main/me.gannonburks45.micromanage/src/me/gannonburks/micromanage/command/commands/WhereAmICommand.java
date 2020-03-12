package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class WhereAmICommand extends Command {

	public WhereAmICommand(String labelIn, boolean canDisableIn, boolean canRunInPrivateIn, String descriptionIn)
	{
		super(labelIn, canDisableIn, canRunInPrivateIn, descriptionIn);
	}

	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		MessageHandler.sendMsgGuild(channel, channel.getGuild().getName());
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		MessageHandler.sendMsgPrivate(channel, channel.getName());
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		System.out.println("console");
	}
}

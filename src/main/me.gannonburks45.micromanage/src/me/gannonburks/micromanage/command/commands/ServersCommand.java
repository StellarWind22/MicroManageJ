package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.command.BotCommand;
import src.me.gannonburks.micromanage.server.DiscordServer;
import src.me.gannonburks.micromanage.server.ServerRegistry;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class ServersCommand extends BotCommand {

	public ServersCommand(String label, String description, boolean canFireInGuild, boolean canFireInPrivate, boolean canFireInConsole, boolean canDisable)
	{
		super(label, description, canFireInGuild, canFireInPrivate, canFireInConsole, canDisable);
	}

	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		MessageHandler.sendMsgGuild(channel, "List of Servers:");
		
		for(DiscordServer server : ServerRegistry.getAll())
		{
			MessageHandler.sendMsgGuild(channel, "> " + server.getName());
		}
		return;
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		MessageHandler.sendMsgPrivate(channel, "List of Servers:");
		
		for(DiscordServer server : ServerRegistry.getAll())
		{
			MessageHandler.sendMsgPrivate(channel, "> " + server.getName());
		}
		return;
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		System.out.println("List of Servers:");
		
		for(DiscordServer server : ServerRegistry.getAll())
		{
			System.out.println("> " + server.getName());
		}
		return;
	}
}

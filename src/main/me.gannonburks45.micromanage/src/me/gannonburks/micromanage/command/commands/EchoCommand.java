package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.command.BotCommand;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class EchoCommand extends BotCommand {

	public EchoCommand(String label, String description, boolean canFireInGuild, boolean canFireInPrivate, boolean canFireInConsole, boolean canDisable)
	{
		super(label, description, canFireInGuild, canFireInPrivate, canFireInConsole, canDisable);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		String msg = String.join(" ", args).trim();
		
		MessageHandler.sendMsgGuild(channel, sender.getAsMention() + " " + msg);
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		String msg = String.join(" ", args).trim();
		
		MessageHandler.sendMsgPrivate(channel, sender.getAsMention() + " " +  msg);
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		String msg = String.join(" ", args).trim();
		
		System.out.println("@Console " + msg);
	}
}
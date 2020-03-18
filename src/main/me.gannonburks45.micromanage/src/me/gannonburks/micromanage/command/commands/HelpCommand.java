package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.command.BotCommand;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class HelpCommand extends BotCommand {

	public HelpCommand(String label, String description, boolean canFireInGuild, boolean canFireInPrivate, boolean canFireInConsole, boolean canDisable)
	{
		super(label, description, canFireInGuild, canFireInPrivate, canFireInConsole, canDisable);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		MessageHandler.sendMsgGuild(channel, "List Of Commands:");
		
		for(BotCommand command : ModuleRegistry.getAllGuildCommands())
		{
			MessageHandler.sendMsgGuild(channel, "> " + command.getLabel() + " - " + command.getDescription());
		}
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		MessageHandler.sendMsgPrivate(channel, "List Of Commands:");
		
		for(BotCommand command : ModuleRegistry.getAllPrivateCommands())
		{
			MessageHandler.sendMsgPrivate(channel, "> " + command.getLabel() + " - " + command.getDescription());
		}
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		System.out.println("List Of Commands:");
		
		for(BotCommand command : ModuleRegistry.getAllConsoleCommands())
		{
			System.out.println("> " + command.getLabel() + " - " + command.getDescription());
		}
	}
}

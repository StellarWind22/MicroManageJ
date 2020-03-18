package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.command.BotCommand;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.server.DiscordServer;
import src.me.gannonburks.micromanage.server.ServerRegistry;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class SetDisabledCommand extends BotCommand {

	public SetDisabledCommand(String label, String description, boolean canFireInGuild, boolean canFireInPrivate, boolean canFireInConsole, boolean canDisable)
	{
		super(label, description, canFireInGuild, canFireInPrivate, canFireInConsole, canDisable);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		DiscordServer server = ServerRegistry.get(channel.getGuild());
		
		if(args.length > 0)
		{
			String label = args[0];
			
			if(ModuleRegistry.containsGuildCommand(label))
			{
				BotCommand command = ModuleRegistry.getGuildCommand(label);
				String isDisabled = args[1];
				
				if(isDisabled.equalsIgnoreCase("true") || isDisabled.equalsIgnoreCase("false"))
				{
					
					if(command.canDisable())
					{
						if(server.setDisabled(ModuleRegistry.getGuildCommand(label), sender, Boolean.parseBoolean(isDisabled)))
						{
							MessageHandler.sendMsgGuild(channel, sender.getAsMention() + " The command \"" + label + "\" is now disabled!");
							return;
						}
						else
						{
							MessageHandler.sendMsgGuild(channel, sender.getAsMention() + " Failed to disable \"" + label + "\"!");
							return;
						}
					}
					else
					{
						MessageHandler.sendMsgGuild(channel, sender.getAsMention() + " The command \"" + label + "\" cannot be disabled!");
					}
				}
				else
				{
					MessageHandler.sendMsgGuild(channel, sender.getAsMention() + " The argument \"" + isDisabled + "\" is not a valid boolean value!");
					return;
				}
			}
			else
			{
				MessageHandler.sendMsgGuild(channel, sender.getAsMention() + " Invalid command label, try " + server.getPrefix() + "help for a list of commands!");
				return;
			}
		}
		else
		{
			MessageHandler.sendMsgGuild(channel, sender.getAsMention() + "Usage:\"" + server.getPrefix() + this.getLabel() + " <label of command> <true || false>\"!");
			return;
		}
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		
	}
}

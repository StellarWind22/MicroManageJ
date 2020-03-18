package src.me.gannonburks.micromanage.event.events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.BotCommandHandler;
import src.me.gannonburks.micromanage.event.BotEvent;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.server.DiscordServer;
import src.me.gannonburks.micromanage.server.ServerRegistry;
import src.me.gannonburks.micromanage.util.Logger;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class OnMessageReceivedEvent extends BotEvent {

	public OnMessageReceivedEvent(boolean canStopIn)
	{
		super(canStopIn);
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event)
	{
		
		//Ignore own messages
		if(event.getAuthor() == Main.bot.getSelfUser()) return;
		
		//Grab some vars
		String rawMessage = event.getMessage().getContentRaw();
		DiscordServer server = ServerRegistry.get(event.getGuild());
		
		//Got Message
		Logger.info("Got Message: \"" + rawMessage + "\" from server: \"" + event.getGuild().getName() + "\" in channel: \"" + event.getChannel().getName() + "\" from: \"" + event.getAuthor().getName() + "\".");
		
		
		if(BotCommandHandler.isCmd(rawMessage, server))
		{
			String label = BotCommandHandler.getLabel(rawMessage, server);
			String[] args = BotCommandHandler.getArgs(rawMessage, server);
			String prefix = server.getPrefix();
			
			if(ModuleRegistry.containsGuildCommand(label) && !(server.isDisabled(ModuleRegistry.getGuildCommand(label))))
			{
				BotCommandHandler.execute(label, args, event.getAuthor(), event.getChannel());
				return;
			}
			else
			{
				MessageHandler.sendMsgGuild(event.getChannel(), event.getAuthor().getAsMention() + " \"" + label + "\" is not a valid command, try \"" + prefix + "help\" for a list of commands.");
				return;
			}
		}
	}
	
	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event)
	{
		//Ignore own messsages
		if(event.getAuthor() == Main.bot.getSelfUser()) return;
		
		//Grab some vars
		String rawMessage = event.getMessage().getContentRaw();
		
		//Got Message
		Logger.info("Got Message: \"" + rawMessage + "\" from: \"" + event.getAuthor().getName());
		
		
		if(BotCommandHandler.isCmd(rawMessage, null))
		{
			String label = BotCommandHandler.getLabel(rawMessage, null);
			String[] args = BotCommandHandler.getArgs(rawMessage, null);
			
			if(ModuleRegistry.containsPrivateCommand(label))
			{
				BotCommandHandler.execute(label, args, event.getAuthor(), event.getChannel());
				return;
			}
			else
			{
				MessageHandler.sendMsgPrivate(event.getChannel(), event.getAuthor().getAsMention() + " \"" + label + " is not a valid command, try \"" + Main.DEFAULT_PREFIX + "help\" for a list of commands");
				return;
			}
		}
	}
}

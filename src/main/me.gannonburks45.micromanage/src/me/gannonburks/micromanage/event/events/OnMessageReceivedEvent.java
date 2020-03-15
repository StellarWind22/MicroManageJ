package src.me.gannonburks.micromanage.event.events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.CommandHandler;
import src.me.gannonburks.micromanage.event.BotEvent;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.server.Server;
import src.me.gannonburks.micromanage.server.ServerRegistry;
import src.me.gannonburks.micromanage.util.Logger;
import src.me.gannonburks.micromanage.util.MessageHandler;
import src.me.gannonburks.micromanage.util.SettingsReader;

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
		Server server = ServerRegistry.get(event.getGuild());
		
		//Got Message
		Logger.info("Got Message: \"" + rawMessage + "\" from server: \"" + event.getGuild().getName() + "\" in channel: \"" + event.getChannel().getName() + "\" from: \"" + event.getAuthor().getName() + "\".");
		
		
		if(CommandHandler.isCmd(rawMessage, server))
		{
			String label = CommandHandler.getLabel(rawMessage, server);
			String[] args = CommandHandler.getArgs(rawMessage, server);
			String prefix = SettingsReader.getPrefix(server);
			
			if(ModuleRegistry.containsGuildCommand(label) && !(SettingsReader.isDisabedIn(server, ModuleRegistry.getGuildCommand(label))))
			{
				CommandHandler.execute(label, args, event.getAuthor(), event.getChannel());
				return;
			}
			else
			{
				MessageHandler.sendMsgGuild(event.getChannel(), "\"" + label + " is not a valid command, try \"" + prefix + "help\" for a list of commands");
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
		
		
		if(CommandHandler.isCmd(rawMessage, null))
		{
			String label = CommandHandler.getLabel(rawMessage, null);
			String[] args = CommandHandler.getArgs(rawMessage, null);
			
			if(ModuleRegistry.containsGuildCommand(label) && !(SettingsReader.isDisabedIn(null, ModuleRegistry.getGuildCommand(label))))
			{
				CommandHandler.execute(label, args, event.getAuthor(), event.getChannel());
				return;
			}
			else
			{
				MessageHandler.sendMsgPrivate(event.getChannel(), "\"" + label + " is not a valid command, try \"" + Main.DEFAULT_PREFIX + "help\" for a list of commands");
				return;
			}
		}
	}
}

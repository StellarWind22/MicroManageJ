package src.me.gannonburks.micromanage.event.events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.CommandHandler;
import src.me.gannonburks.micromanage.util.Logger;

public class OnMessageReceivedEvent extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		//Ignore own messages
		if(event.getAuthor() == Main.bot.getSelfUser()) return;
		
		//Got Message
		Logger.info("Got Message: \"" + event.getMessage().getContentRaw() + "\" from server: \"" + event.getGuild().getName() + "\" in channel: \"" + event.getChannel().getName() + "\" from: \"" + event.getAuthor().getName() + "\".");
		
		//Format Input
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		//If any message starts with the prefix attempt to execute
		if(CommandHandler.isCmd(args[0]))
		{	
			CommandHandler.executeCommand(args[0].replaceFirst(Main.PREFIX, ""), args, event.getAuthor(), event.getChannel());
		}
	}
	
	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
		
		//Ignore own messages
		if(event.getAuthor() == Main.bot.getSelfUser()) return;
		
		//Got Message
		Logger.info("Got Message: \"" + event.getMessage().getContentRaw() + "\" from: \"" + event.getAuthor().getName() + "\".");
		
		
		//Format Input
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		//If any message starts with the prefix attempt to execute
		if(CommandHandler.isCmd(args[0]))
		{	
			CommandHandler.executeCommand(args[0].replaceFirst(Main.PREFIX, ""), args, event.getAuthor(), event.getChannel());
		}
	}
}

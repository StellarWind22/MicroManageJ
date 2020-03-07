package me.gannonburks.micromanage.event;

import me.gannonburks.micromanage.Main;
import me.gannonburks.micromanage.util.CommandHandler;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnMessageReceivedEvent extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		//Ignore own messages
		if(event.getAuthor() == Main.bot.getSelfUser()) return;
		
		
		//Format Input
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		//If any message starts with the prefix attempt to execute
		if(CommandHandler.isCmd(args[0]))
		{	
			CommandHandler.executeCommand(args[0].replaceFirst(Main.prefix, ""), args, event.getAuthor(), event.getChannel());
		}
	}
	
	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
		
		//Ignore own messages
		if(event.getAuthor() == Main.bot.getSelfUser()) return;
		
		
		//Format Input
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		//If any message starts with the prefix attempt to execute
		if(CommandHandler.isCmd(args[0]))
		{	
			CommandHandler.executeCommand(args[0].replaceFirst(Main.prefix, ""), args, event.getAuthor(), event.getChannel());
		}
	}
}

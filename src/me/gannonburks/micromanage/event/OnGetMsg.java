package me.gannonburks.micromanage.event;

import me.gannonburks.micromanage.Main;
import me.gannonburks.micromanage.util.CmdHandler;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

public class OnGetMsg {

	/*
	 * Here is where the bot takes in messages and processes them
	 */
	
	@SubscribeEvent
	public void onGetMsgGuild(GuildMessageReceivedEvent e) {
		
		String[] args = e.getMessage().getContentRaw().split(" ");
		
		if(CmdHandler.isCmd(args[0]))	//If any message starts with the prefix attempt to execute
		{	
			CmdHandler.executeCommand(args[0].replaceFirst(Main.prefix, ""), args, e.getAuthor(), e.getChannel());
		}
	}
	
	@SubscribeEvent
	public void onGetMsgDirect(PrivateMessageReceivedEvent e) {
		
		String[] args = e.getMessage().getContentRaw().split(" ");
		
		if(CmdHandler.isCmd(args[0]))	//If any message starts with the prefix attempt to execute
		{	
			CmdHandler.executeCommand(args[0].replaceFirst(Main.prefix, ""), args, e.getAuthor(), e.getChannel());
		}
	}
}

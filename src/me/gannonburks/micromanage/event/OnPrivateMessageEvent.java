package me.gannonburks.micromanage.event;

import me.gannonburks.micromanage.Main;
import me.gannonburks.micromanage.util.CmdHandler;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import okhttp3.EventListener;

public class OnPrivateMessageEvent extends EventListener {

	@SubscribeEvent
	public void onGetMsgDirect(PrivateMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		if(CmdHandler.isCmd(args[0]))	//If any message starts with the prefix attempt to execute
		{	
			CmdHandler.executeCommand(args[0].replaceFirst(Main.prefix, ""), args, event.getAuthor(), event.getChannel());
		}
	}
}

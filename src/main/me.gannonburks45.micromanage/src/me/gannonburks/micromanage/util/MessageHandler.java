package src.me.gannonburks.micromanage.util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;

public class MessageHandler {
	
	//Global Stuff
	public static final int CHAR_LIMIT = 2000;
	
	
	/*
	 * BROADCAST TO MULTIPLE SERVERS
	 * 
	 * if limit = 0 then there is no limit!
	 */
	public static void sendMsgBroadcast(String channel, String msg, int channelLimit)
	{
		
		for(Guild guild : Main.bot.getGuilds()) 
		{
			
			int loops = 0;
			
			for(TextChannel txtChannel : guild.getTextChannelsByName(channel, true)) 
			{
				
				//Break when limit reached
				if(loops >= channelLimit) 
				{
					break;
				} 
				else 
				{
					
					sendMsgGuild(txtChannel, msg);		//Send using nice formatted method with pagination
					
					if(channelLimit != 0 ) loops++;		//Iterate on loops if limits enabled
				}
			}
		}
	}
	
	
	
	/*
	 * MESSAGE SPECIFIC USER
	 */
	public static void sendMsgPrivate(PrivateChannel channel, String msg) {
		
		for(String msgChunk : msg.split("(?<=\\G.{" + CHAR_LIMIT + "})"))
		{
			//Send in discord-safe chunks
			channel.sendMessage(msgChunk).queue();;
		}
	}
	
	/*
	 * Send message to users with similar name
	 * 
	 * if limit = 0 there is no limit
	 */
	public static void sendMsgPrivateByName(String name, String msg, int userLimit) {
		
		int loops = 0;
		
		for(User user : Main.bot.getUsersByName(name, true)) {
			
			if(loops >= userLimit)
			{	
				break;
			}
			else
			{
				//Send Private Message
				user.openPrivateChannel().queue((channel) -> {
					
					for(String msgChunk : msg.split("(?<=\\G.{" + CHAR_LIMIT + "})"))
					{
						//Send in discord-safe chunks
						channel.sendMessage(msgChunk).queue();;
					}
				});
				
				if(userLimit != 0) loops++;
			}
		}
	}
	
	
	/*
	 * Send message directly to a defined channel
	 */
	public static void sendMsgGuild(TextChannel channel, String msg) {
		
		for(String msgChunk : msg.split("(?<=\\G.{" + CHAR_LIMIT + "})"))
		{
			//Send in discord-safe chunks
			channel.sendMessage(msgChunk).queue();;
		}
	}
}

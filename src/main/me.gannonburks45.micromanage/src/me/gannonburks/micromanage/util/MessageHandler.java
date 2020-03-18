package src.me.gannonburks.micromanage.util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;

public class MessageHandler
{
	public static final int CHAR_LIMIT = 2000;
	
	/**
	 * Method for broadcasting a message
	 * to all servers in a channel with the
	 * same name.
	 * 
	 * @param channelIn		Name of channel to send message to.
	 * @param msgIn			Message to be sent to the channel.
	 * 
	 * @param channelLimit	Number of channels with matching names
	 * 						to send message to(0 == unlimited).
	 */
	public static void sendMsgBroadcast(String channelIn, String msgIn, int channelLimit)
	{
		
		for(Guild guild : Main.bot.getGuilds()) 
		{
			
			int loops = 0;
			
			for(TextChannel txtChannel : guild.getTextChannelsByName(channelIn, true)) 
			{
				
				//Break when limit reached
				if(loops >= channelLimit) 
				{
					break;
				} 
				else 
				{
					
					sendMsgGuild(txtChannel, msgIn);	//Send using nice formatted method with pagination
					
					if(channelLimit != 0 ) loops++;		//Iterate on loops if limits enabled
				}
			}
		}
	}
	
	/**
	 * Method for sending a private
	 * message directly to private channel
	 * 
	 * @param channelIn	Channel to send message to.
	 * 
	 * @param msgIn		Message to be sent.
	 */
	public static void sendMsgPrivate(PrivateChannel channelIn, String msgIn)
	{
		for(String msgChunk : msgIn.split("(?<=\\G.{" + CHAR_LIMIT + "})"))
		{
			//Send in discord-safe chunks
			channelIn.sendMessage(msgChunk).queue();
			Logger.info("Sent message: \"" + msgIn + "\" to: \"" + channelIn.getUser().getName() + "\".");
		}
	}
	
	/**
	 * Method for sending a private
	 * message to a user via name.
	 * 
	 * @param nameIn	Name of user to send message to.
	 * @param msgIn		Message to be sent to the user.
	 * 
	 * @param userLimit	Number of users with matching names
	 * 					to send message to(0 == unlimited).
	 */
	public static void sendMsgPrivateByName(String nameIn, String msgIn, int userLimit) {
		
		int loops = 0;
		
		for(User user : Main.bot.getUsersByName(nameIn, true))
		{
			
			if(loops >= userLimit)
			{	
				break;
			}
			else
			{
				//Send Private Message
				user.openPrivateChannel().queue((channel) -> {
					
					for(String msgChunk : msgIn.split("(?<=\\G.{" + CHAR_LIMIT + "})"))
					{
						//Send in discord-safe chunks
						channel.sendMessage(msgChunk).queue();
						Logger.info("Sent message: \"" + msgIn + "\" to: \"" + user.getName() + "\".");
					}
				});
				
				if(userLimit != 0) loops++;
			}
		}
	}
	
	
	/**
	 * Method for sending a message
	 * directly to a guild channel
	 * 
	 * @param channelIn	Channel to send the message to.
	 * @param msgIn		Message to be sent.
	 */
	public static void sendMsgGuild(TextChannel channelIn, String msgIn) {
		
		for(String msgChunk : msgIn.split("(?<=\\G.{" + CHAR_LIMIT + "})"))
		{
			//Send in discord-safe chunks
			channelIn.sendMessage(msgChunk).queue();
			Logger.info("Sent message: \"" + msgIn + "\" to server: \"" + channelIn.getGuild().getName() + "\" in channel: \"" + channelIn.getName() + "\".");
		}
	}
	
	/**
	 * Method for sending a message to a
	 * guild via name.
	 * 
	 * @param guildIn		Name of guild to send message to.
	 * @param channelIn		Name of channel to send message in.
	 * @param msgIn			Message to be sent.
	 * 
	 * @param guildLimit	Number of guild(s) with matching names
	 * 						to send it to(0 = unlimited).
	 * 
	 * @param channelLimit	Number of channels(s) with matching names
	 * 						to send it in(0 = unlimited).
	 */
	public static void sendMsgGuildByName(String guildIn, String channelIn, String msgIn, int guildLimit, int channelLimit)
	{
		
		int loopsG = 0;
		
		for(Guild guild : Main.bot.getGuildsByName(guildIn, true))
		{
			
			if(loopsG >= guildLimit)
			{	
				break;
			}
			else
			{
				int loopsC = 0;
				
				for(TextChannel txtChannel : guild.getTextChannelsByName(channelIn, true))
				{
					
					if(loopsC >= channelLimit)
					{
						break;
					}
					else
					{
						
						sendMsgGuild(txtChannel, msgIn);
						
						if(channelLimit != 0) loopsC++;
					}
				}
				
				if(guildLimit != 0) loopsG++;
			}
		}
	}
}

package src.me.gannonburks.micromanage.server;

import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public final class DiscordServer {

	private String name;
	
	private List<TextChannel> txtChannels;
	private List<VoiceChannel> vocChannels;
	
	private Guild guild;
	
	public DiscordServer(Guild guildIn)
	{
		this.guild = guildIn;
		
		this.name = guildIn.getName();
		this.txtChannels = guildIn.getTextChannels();
		this.vocChannels = guildIn.getVoiceChannels();
	}
	
	/**
	 * Method for getting JDA's guild object
	 * form of the server.
	 * 
	 * @return Guild object of the server.
	 */
	public Guild getGuild()
	{
		return this.guild;
	}
	
	/**
	 * Method for getting the server's name.
	 * 
	 * @return The name of the server.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Method for getting all the text channels
	 * in a server so you don't have to use getGuild()
	 * very often.
	 * 
	 * @return	Mutable array of all text channels in
	 * 			the server.
	 */
	public List<TextChannel> getTextChannels()
	{
		return this.txtChannels;
	}
	
	/**
	 * Method for getting all the voice channels
	 * in a server so you don't have to use getGuild()
	 * very often.
	 * 
	 * @return	Mutable array of all voice channels in
	 * 			the server.
	 */
	public List<VoiceChannel> getVoiceChannels()
	{
		return this.vocChannels;
	}
}

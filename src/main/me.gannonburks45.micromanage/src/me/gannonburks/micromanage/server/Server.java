package src.me.gannonburks.micromanage.server;

import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class Server {

	private String name;
	
	private List<TextChannel> txtChannels;
	private List<VoiceChannel> vocChannels;
	
	private Guild guild;
	
	public Server(Guild guildIn)
	{
		this.guild = guildIn;
		
		this.name = guildIn.getName();
		this.txtChannels = guildIn.getTextChannels();
		this.vocChannels = guildIn.getVoiceChannels();
		
		//Create settings
	}
	
	//Returns this server's JDA guild object.
	public Guild getGuild()
	{
		return this.guild;
	}
	
	//Returns this server's name
	public String getName()
	{
		return this.name;
	}
	
	//Getter for text channels
	public List<TextChannel> getTextChannels()
	{
		return this.txtChannels;
	}
	
	//Getter for voice channels
	public List<VoiceChannel> getVoiceChannels()
	{
		return this.vocChannels;
	}
}

package src.me.gannonburks.micromanage.server;

import java.sql.Timestamp;
import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import src.me.gannonburks.micromanage.command.BotCommand;

public final class DiscordServer {

	private String name;
	
	private List<TextChannel> txtChannels;
	private List<VoiceChannel> vocChannels;
	
	private Guild guild;
	
	/**
	 * Constructor for server class, takes
	 * in a JDA Guild object and turns it into
	 * a server instance for the server registry.
	 * 
	 * @param guildIn Guild for the server object.
	 */
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
	
	/**
	 * Method for getting a timestamp of the date
	 * the bot joined the server.
	 * 
	 * @return Timestamp of join date.
	 */
	public Timestamp getJoinDate()
	{
		return SettingsReader.getJoinDate(this);
	}
	
	/**
	 * Method for getting prefix of a server
	 * 
	 * @return Prefix of the server.
	 */
	public String getPrefix()
	{
		return SettingsReader.getPrefix(this);
	}
	
	/**
	 * Method for checking if a command is
	 * disabled in the server.
	 * 
	 * @param command Command to check for.
	 * 
	 * @return If command is disabled.
	 */
	public boolean isDisabled(BotCommand command)
	{
		return SettingsReader.isDisabedIn(this, command);
	}
	
	/**
	 * Method for getting timestamp of date
	 * the command was last changed by anyone.
	 * 
	 * @param command Command to get timestamp for.
	 * 
	 * @return Timestamp of changed date.
	 */
	public Timestamp getLastChanged(BotCommand command)
	{
		return SettingsReader.getLastChanged(this, command);
	}
	
	/**
	 * Method for getting the JDA User object
	 * of the last user who changed the command
	 * settings.
	 * 
	 * @param command Command to check for.
	 * 
	 * @return JDA User object of user who last changed it.
	 */
	public User getLastChangedBy(BotCommand command)
	{
		return SettingsReader.getLastChangedBy(this, command);
	}
	
	/**
	 * Method for setting the servers prefix.
	 * 
	 * @param prefix New prefix to change it to
	 * 
	 * @return If the operation succeeded.
	 */
	public boolean SetPrefix(String prefix)
	{
		return SettingsReader.setPrefix(this, prefix);
	}
	
	/**
	 * Method for setting a command's disabled
	 * status.
	 * 
	 * @param command		Command to change.
	 * @param sender		User that requested it.
	 * @param isDisabled	Value to change it to.
	 * 
	 * @return				If the operation succeeded.
	 */
	public boolean setDisabled(BotCommand command, User sender, boolean isDisabled)
	{
		return SettingsReader.setDisabledIn(this, command, sender, isDisabled);
	}
}

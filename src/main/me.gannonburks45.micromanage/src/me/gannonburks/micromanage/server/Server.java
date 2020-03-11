package src.me.gannonburks.micromanage.server;

import net.dv8tion.jda.api.entities.Guild;
import src.me.gannonburks.micromanage.command.CommandRegistry;

public class Server implements IServer {

	private Guild guild;
	private CommandRegistry registry;
	
	public Server(Guild guildIn, CommandRegistry registryIn)
	{
		this.guild = guildIn;
		this.registry = registryIn;
	}
	
	//Returns this server's JDA guild object.
	public Guild getGuild()
	{
		return this.guild;
	}
	
	//Returns this server's version of the command registry.
	public CommandRegistry getCommandRegistry()
	{
		return this.registry;
	}
}

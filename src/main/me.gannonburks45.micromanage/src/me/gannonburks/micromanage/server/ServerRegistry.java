package src.me.gannonburks.micromanage.server;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.Guild;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.util.Logger;

public final class ServerRegistry
{
	private static ArrayList<DiscordServer> servers = new ArrayList<DiscordServer>();
	
	/**
	 * Registration method for the ServerRegistry.
	 * 
	 * @param server Server object to be added.
	 */
	public static void register(DiscordServer server)
	{
		if(servers.contains(server))
		{
			Logger.error("ServerRegistry already contains an instance of \"" + server.getName() + "\"!");
			return;
		}
		servers.add(server);
	}
	
	/**
	 * De-registration method for the ServerRegistry.
	 * 
	 * @param server Server object to be removed.
	 */
	public static void deRegister(DiscordServer server)
	{
		for(DiscordServer entry : servers)
		{
			if(entry.equals(server))
			{
				servers.remove(entry);
			}
		}
	}
	
	/**
	 * Method for checking whether or not the registry
	 * contains that server.
	 * 
	 * @param server	Server to check the registry for.
	 * 
	 * @return If the registry contains the server.
	 */
	public static boolean contains(DiscordServer server)
	{
		return servers.contains(server);
	}
	
	public static boolean contains(String server)
	{
		for(DiscordServer entry : servers)
		{
			if(entry.getName().equalsIgnoreCase(server))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method for getting a specific server from the
	 * registry via the name.
	 * 
	 * @param server Name of server to retrieve.
	 * 
	 * @return Instance of the server if it can find it.
	 */
	public static DiscordServer get(String server)
	{	
		for(Guild guild : Main.bot.getGuildsByName(server, true))
		{
			return get(guild);
		}
		return null;
	}
	
	/**
	 * Method for getting a specific server from the
	 * registry via a JDA Guild object.
	 * 
	 * @param guild Guild object to retrieve server for.
	 * 
	 * @return Instance of the server if it can find it.
	 */
	public static DiscordServer get(Guild guild)
	{
		for(DiscordServer server : servers)
		{
			if(server.getGuild().equals(guild))
			{
				return server;
			}
		}
		return null;
	}
	
	/**
	 * Method for getting all servers that are
	 * currently registered in the registry.
	 * 
	 * @return Mutable array of all server objects.
	 */
	public static ArrayList<DiscordServer> getAll()
	{
		return servers;
	}
}

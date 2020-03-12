package src.me.gannonburks.micromanage.server;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.Guild;
import src.me.gannonburks.micromanage.Main;

public final class ServerRegistry {

	private static ArrayList<Server> registry = new ArrayList<Server>();
	
	
	//Register
	public static void register(Server serverIn)
	{
		registry.add(serverIn);
	}
	
	//DeRegister
	public static void deRegister(Server serverIn)
	{
		for(Server server : registry)
		{
			if(server.equals(serverIn))
			{
				registry.remove(serverIn);
			}
		}
	}
	
	//Contains
	public static boolean contains(Server serverIn)
	{
		return registry.contains(serverIn);
	}
	
	public static boolean contains(String serverIn)
	{
		for(Server server : registry)
		{
			if(server.getName().equalsIgnoreCase(serverIn))
			{
				return true;
			}
		}
		return false;
	}
	
	//Get
	public static Server get(String serverIn)
	{	
		for(Guild guild : Main.bot.getGuildsByName(serverIn, true))
		{
			return get(guild);
		}
		return null;
	}
	
	//Get
	public static Server get(Guild guildIn)
	{
		for(Server server : registry)
		{
			if(server.getGuild().equals(guildIn))
			{
				return server;
			}
		}
		return null;
	}
	
	//GetAll
	public static ArrayList<Server> getAll()
	{
		return registry;
	}
}

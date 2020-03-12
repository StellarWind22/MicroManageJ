package src.me.gannonburks.micromanage.server;

import java.util.ArrayList;

public class ServerRegistry {

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
		for(Server server : registry)
		{
			if(server.getName().equals(serverIn)) {
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

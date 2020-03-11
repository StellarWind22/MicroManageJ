package src.me.gannonburks.micromanage.server;

import java.util.ArrayList;

public class ServerRegistry {

	private ArrayList<Server> registry = new ArrayList<Server>();
	
	
	//Register
	public void register(Server serverIn)
	{
		registry.add(serverIn);
	}
	
	//DeRegister
	public void deRegister(Server serverIn)
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
	public boolean contains(Server serverIn)
	{
		return registry.contains(serverIn);
	}
	
	public boolean contains(String serverIn)
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
	public Server get(String serverIn)
	{
		for(Server server : registry)
		{
			if(server.getName().equalsIgnoreCase(serverIn)) {
				return server;
			}
		}
		return null;
	}
	
	//GetAll
	public ArrayList<Server> getAll()
	{
		return this.registry;
	}
}

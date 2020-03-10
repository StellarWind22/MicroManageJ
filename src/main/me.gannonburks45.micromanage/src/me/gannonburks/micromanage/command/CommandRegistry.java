package src.me.gannonburks.micromanage.command;

import java.util.ArrayList;

import src.me.gannonburks.micromanage.util.Logger;

public class CommandRegistry {

	private static ArrayList<Command> registry = new ArrayList<Command>();
	
	//Register
	public static void register(Command cmdIn)
	{
		
		if(contains(cmdIn.getLabel()))
		{
			Logger.error("command registry already contains a command with the label \"" + cmdIn.getLabel() + "\".");
			return;
		}
		
		registry.add(cmdIn);
	}
	
	//RegisterAll
	public static void registerAll(Command[] cmdsIn)
	{
		
		for(Command cmdLItem : cmdsIn)
		{
			register(cmdLItem);
		}
	}
	
	//Deregister
	public static void deRegister(Command cmdIn)
	{
		
		if(!(contains(cmdIn.getLabel())))
		{
			Logger.error("command registry does not contain a command with the label \"" + cmdIn.getLabel() + "\".");
			return;
		}
		
		for(Command cmd : registry)
		{
			if(cmd.equals(cmdIn))
			{
				registry.remove(cmdIn);
			}
		}
	}
	
	//contains
	public static boolean contains(Command command)
	{
		return registry.contains(command);
	}
	
	public static boolean contains(String labelIn)
	{
		return registry.contains(get(labelIn));
	}
	
	//getCommand
	public static Command get(String labelIn)
	{
		for(Command cmd : registry)
		{
			
			if(cmd.getLabel().equalsIgnoreCase(labelIn))
			{
				return cmd.getCommand();
			}
		}
		return null;
	}
	
	//getAll
	public static ArrayList<Command> getAll()
	{	
		return registry;
	}
}

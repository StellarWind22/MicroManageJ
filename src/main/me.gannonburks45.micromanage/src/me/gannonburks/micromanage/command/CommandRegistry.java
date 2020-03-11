package src.me.gannonburks.micromanage.command;

import java.util.ArrayList;

import src.me.gannonburks.micromanage.util.Logger;

public class CommandRegistry {

	private static ArrayList<Command> registry = new ArrayList<Command>();
	
	//Register
	public static void register(Command cmdIn)
	{
		
		if(contains(cmdIn.getLabel(), true))
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
		
		if(!(contains(cmdIn.getLabel(), true)))
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
	public static boolean contains(Command commandIn, boolean showDisabled)
	{
		if(showDisabled)
		{
			return registry.contains(commandIn);
		}
		else
		{
			if(registry.contains(commandIn))
			{
				if(commandIn.isDisabled())
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{
				return false;
			}
		}
	}
	
	public static boolean contains(String labelIn, boolean showDisabled)
	{
		return registry.contains(get(labelIn, showDisabled));
	}
	
	//getCommand
	public static Command get(String labelIn, boolean showDisabled)
	{
		for(Command cmd : registry)
		{
			if(cmd.getLabel().equalsIgnoreCase(labelIn))
			{
				if(showDisabled)
				{	
					return cmd.getCommand();
				}
				else
				{
					if(cmd.isDisabled())
					{
						return null;
					}
					else
					{
						return cmd.getCommand();
					}
				}
			}
		}
		return null;
	}
	
	//getAll
	public static ArrayList<Command> getAll(boolean showDisabled)
	{	
		if(showDisabled) {
			
			return registry;
		}
		else
		{
			ArrayList<Command> registryOut = new ArrayList<Command>();
			
			for(Command cmd : registry)
			{
				if(cmd.isDisabled())
				{
					continue;
				}
				else
				{
					registryOut.add(cmd);
				}
			}
			
			return registryOut; 
		}
		
	}
}

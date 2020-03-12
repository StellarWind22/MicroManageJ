package src.me.gannonburks.micromanage.command;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Nonnull;

import net.dv8tion.jda.internal.utils.Checks;
import src.me.gannonburks.micromanage.util.Logger;

public class CommandRegistry {

	private ArrayList<Command> registry = new ArrayList<Command>();
	
	//Empty Constructor
	public CommandRegistry() {}
	
	//Clone
	public CommandRegistry(CommandRegistry registryIn)
	{
		for(Command cmd : registryIn.getAll(true))
		{
			register(cmd);
		}
	}
	
	
	//Register
	public void register(Command cmdIn)
	{
		
		if(contains(cmdIn.getLabel(), true))
		{
			Logger.error("command registry already contains a command with the label \"" + cmdIn.getLabel() + "\".");
			return;
		}
		
		registry.add(cmdIn);
	}
	
	//RegisterAll
	public void registerAll(@Nonnull Command... cmdsIn)
	{
		Checks.noneNull(cmdsIn, "cmdsIn");
		
		for(Command cmdIn : cmdsIn)
		{
			if(contains(cmdIn.getLabel(), true))
			{
				Logger.error("command registry already contains a command with the label \"" + cmdIn.getLabel() + "\".");
				return;
			}
		}
		
		Collections.addAll(this.registry, cmdsIn);
	}
	
	public void registerAll(@Nonnull ArrayList<Command> cmdsIn)
	{
		Checks.noneNull(cmdsIn, "cmdsIn");
		
		for(Command cmdIn : cmdsIn)
		{
			register(cmdIn);
		}
	}
	
	//Deregister
	public void deRegister(Command cmdIn)
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
	public boolean contains(Command commandIn, boolean showDisabled)
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
	
	public boolean contains(String labelIn, boolean showDisabled)
	{
		return registry.contains(get(labelIn, showDisabled));
	}
	
	//getCommand
	public Command get(String labelIn, boolean showDisabled)
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
	public ArrayList<Command> getAll(boolean showDisabled)
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

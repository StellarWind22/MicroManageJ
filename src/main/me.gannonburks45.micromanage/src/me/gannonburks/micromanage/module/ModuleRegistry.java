package src.me.gannonburks.micromanage.module;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Nonnull;

import net.dv8tion.jda.internal.utils.Checks;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.command.CommandRegistry;
import src.me.gannonburks.micromanage.event.BotEvent;
import src.me.gannonburks.micromanage.util.Logger;

public final class ModuleRegistry {

	private static ArrayList<Module> registry = new ArrayList<Module>();
	
	//Register
	public static void register(Module moduleIn)
	{
		registry.add(moduleIn);
	}
	
	//RegisterAll
	public static void registerAll(@Nonnull Module... modulesIn)
	{
		Checks.noneNull(modulesIn, "modulesIn");
		
		for(Module module : modulesIn)
		{
			if(contains(module.getName()))
			{
				Logger.error("module registry already contains a module with the name \"" + module.getName() + "\".");
				return;
			}
		}
		
		Collections.addAll(registry, modulesIn);
	}
	
	
	//Contains
	public static boolean contains(Module moduleIn)
	{
		return registry.contains(moduleIn);
	}
	
	public static boolean contains(String moduleIn)
	{
		return registry.contains(get(moduleIn));
	}
	
	//Get
	public static Module get(String moduleIn)
	{
		for(Module module : registry)
		{
			if(module.getName().equalsIgnoreCase(moduleIn))
			{
				return module;
			}
		}
		return null;
	}
	
	//GetAll
	public static ArrayList<Module> getAll()
	{
		return registry;
	}
	
	//Get all registered commands
	public static ArrayList<Command> getAllCommands()
	{
		ArrayList<Command> commands = new ArrayList<Command>();
		
		for(Module module : registry)
		{
			commands.addAll(module.getCommands());
		}
		
		return commands;
	}
	
	//Get all registered events
	public static ArrayList<BotEvent> getAllEvents()
	{
		ArrayList<BotEvent> events = new ArrayList<BotEvent>();
		
		for(Module module : registry)
		{
			events.addAll(module.getEvents());
		}
		
		return events;
	}
	
	public static CommandRegistry toCommandRegistry()
	{
		CommandRegistry cmdReg = new CommandRegistry();
		
		cmdReg.registerAll(getAllCommands());
		
		return cmdReg;
	}
}

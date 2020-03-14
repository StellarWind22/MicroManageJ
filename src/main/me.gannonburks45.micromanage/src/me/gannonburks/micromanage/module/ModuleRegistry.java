package src.me.gannonburks.micromanage.module;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Nonnull;

import net.dv8tion.jda.internal.utils.Checks;
import src.me.gannonburks.micromanage.command.Command;
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
			if(containsModule(module.getName()))
			{
				Logger.error("module registry already contains a module with the name \"" + module.getName() + "\".");
				return;
			}
		}
		
		Collections.addAll(registry, modulesIn);
	}
	
	
	//Contains
	public static boolean containsModule(Module moduleIn)
	{
		return registry.contains(moduleIn);
	}
	
	public static boolean containsModule(String moduleIn)
	{
		return registry.contains(getModule(moduleIn));
	}
	
	//Get
	public static Module getModule(String moduleIn)
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
	public static ArrayList<Module> getAllModules()
	{
		return registry;
	}
	
	
	
	//Contains specific command
	public static boolean containsCommand(String commandName)
	{
		return (getCommand(commandName) != null);
	}
	
	//Contains specific command that works in guilds.
	public static boolean containsGuildCommand(String commandName)
	{
		return (getGuildCommand(commandName) != null);
	}
	
	//Contains specific command that works in private
	public static boolean containsPrivateCommand(String commandName)
	{
		return (getPrivateCommand(commandName) != null);
	}
	
	//Contains a specific command that works in the console
	public static boolean containsConsoleCommand(String commandName)
	{
		return (getConsoleCommand(commandName) != null);
	}
	
	
	//Get specific command
	public static Command getCommand(String commandName)
	{
		for(Command command : getAllCommands())
		{
			if(command.getLabel().equalsIgnoreCase(commandName))
			{
				return command;
			}
		}
		return null;
	}
	
	//Get a specific command that works in guilds
	public static Command getGuildCommand(String commandName)
	{
		for(Command command : getAllGuildCommands())
		{
			if(command.getLabel().equalsIgnoreCase(commandName))
			{
				return command;
			}
		}
		return null;
	}
	
	//Get a specific command that works in private
	public static Command getPrivateCommand(String commandName)
	{
		for(Command command : getAllPrivateCommands())
		{
			if(command.getLabel().equalsIgnoreCase(commandName))
			{
				return command;
			}
		}
		return null;
	}
	
	//get a specific command that works in the console
	public static Command getConsoleCommand(String commandName)
	{
		for(Command command : getAllConsoleCommands())
		{
			if(command.getLabel().equalsIgnoreCase(commandName))
			{
				return command;
			}
		}
		return null;
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
	
	//Get all registered commands that work in guilds
	public static ArrayList<Command> getAllGuildCommands()
	{
		ArrayList<Command> commands = new ArrayList<Command>();
			
		for(Command command : getAllCommands())
		{
			if(command.canFireInGuild())
			{
				commands.add(command);
			}
		}
			
		return commands;
	}
	
	//Get all registered commands that work in pms
	public static ArrayList<Command> getAllPrivateCommands()
	{
		ArrayList<Command> commands = new ArrayList<Command>();
				
		for(Command command : getAllCommands())
		{
			if(command.canFireInPrivate())
			{
				commands.add(command);
			}
		}
				
		return commands;
	}
	
	//Get all registered commands that work in the console
	public static ArrayList<Command> getAllConsoleCommands()
	{
		ArrayList<Command> commands = new ArrayList<Command>();
				
		for(Command command : getAllCommands())
		{
			if(command.canFireInConsole())
			{
				commands.add(command);
			}
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
}

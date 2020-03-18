package src.me.gannonburks.micromanage.module;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Nonnull;

import net.dv8tion.jda.internal.utils.Checks;
import src.me.gannonburks.micromanage.command.BotCommand;
import src.me.gannonburks.micromanage.event.BotEvent;
import src.me.gannonburks.micromanage.util.Logger;

public final class ModuleRegistry
{
	private static ArrayList<Module> moduleRegistry = new ArrayList<Module>();	//Array of module instances.
	
	/**
	 * Method for registering a module instance
	 * into the registry.
	 * 
	 * @param moduleIn Module instance to be added.
	 */
	public static void register(Module module)
	{
		moduleRegistry.add(module);
	}
	
	/**
	 * Method for mass registering module instances
	 * into the registry.
	 * 
	 * @param modules	Vararg of module instances
	 * 					to be registered.
	 */
	public static void registerAll(@Nonnull Module... modules)
	{
		Checks.noneNull(modules, "modules");	//Ensure vararg of Modules is not empty
		
		for(Module entry : modules)
		{
			if(moduleRegistry.contains(entry))
			{
				Logger.error("ModuleRegistry already contains an instance of \"" + entry.getName() + "\"!");
				return;
			}
		}
		Collections.addAll(moduleRegistry, modules);
	}
	
	
	/**
	 * Method for checking whether or
	 * not the registry contains a specific
	 * module instance.
	 * 
	 * @param module	Module instance to check for.
	 * 
	 * @return If the registry contains the module instance.
	 */
	public static boolean containsModule(Module module)
	{
		return moduleRegistry.contains(module);
	}
	
	public static boolean containsModule(String module)
	{
		return moduleRegistry.contains(getModule(module));
	}
	
	/**
	 * Method for getting a specific module
	 * from the registry via name.
	 * 
	 * @param module	Name of module to retrieve.
	 * 
	 * @return stance of the module if it can be found.
	 */
	public static Module getModule(String module)
	{
		for(Module entry : moduleRegistry)
		{
			if(entry.getName().equalsIgnoreCase(module))
			{
				return entry;
			}
		}
		return null;
	}
	
	/**
	 * Method for getting all module instances
	 * currently registered in the registry.
	 * 
	 * @return	Mutable list of all registered
	 * 			module instances.
	 */
	public static ArrayList<Module> getAllModules()
	{
		return moduleRegistry;
	}
	
	/**
	 * Method for checking if the registry contains
	 * a specific command instance via name.
	 * 
	 * @param label Name to check for.
	 * 
	 * @return If the registry contains the command.
	 */
	public static boolean containsCommand(String label)
	{
		return (getCommand(label) != null);
	}
	
	/**
	 * Method for checking if the registry contains
	 * a specific command instance that works in
	 * guild channels via name.
	 * 
	 * @param label Label to check for.
	 * 
	 * @return If the registry contains the command.
	 */
	public static boolean containsGuildCommand(String label)
	{
		return (getGuildCommand(label) != null);
	}
	
	/**
	 * Method for checking if the registry contains
	 * a specific command instance that works in
	 * private channels via name.
	 * 
	 * @param label Label to check for.
	 * 
	 * @return If the registry contains the command.
	 */
	public static boolean containsPrivateCommand(String label)
	{
		return (getPrivateCommand(label) != null);
	}
	
	/**
	 * Method for checking if the registry contains
	 * a specific command instance that works in
	 * the console via name.
	 * 
	 * @param label Label to check for.
	 * 
	 * @return If the registry contains the command.
	 */
	public static boolean containsConsoleCommand(String label)
	{
		return (getConsoleCommand(label) != null);
	}
	
	
	/**
	 * Method for getting the instance of a
	 * specific command from the registry
	 * via name.
	 * 
	 * @param label Label to retrieve.
	 * 
	 * @return Instance of command if it is found.
	 */
	public static BotCommand getCommand(String label)
	{
		for(BotCommand command : getAllCommands())
		{
			if(command.getLabel().equalsIgnoreCase(label))
			{
				return command;
			}
		}
		return null;
	}
	
	/**
	 * Method for getting the instance of a
	 * specific command from the registry
	 * that works in guild channels via name.
	 * 
	 * @param label Label to retrieve.
	 * 
	 * @return Instance of command if it is found.
	 */
	public static BotCommand getGuildCommand(String label)
	{
		for(BotCommand command : getAllGuildCommands())
		{
			if(command.getLabel().equalsIgnoreCase(label))
			{
				return command;
			}
		}
		return null;
	}
	
	/**
	 * Method for getting the instance of a
	 * specific command from the registry
	 * that works in private channels via name.
	 * 
	 * @param label Label to retrieve.
	 * 
	 * @return Instance of command if it is found.
	 */
	public static BotCommand getPrivateCommand(String label)
	{
		for(BotCommand command : getAllPrivateCommands())
		{
			if(command.getLabel().equalsIgnoreCase(label))
			{
				return command;
			}
		}
		return null;
	}
	
	/**
	 * Method for getting the instance of a
	 * specific command from the registry
	 * that works in the console via name.
	 * 
	 * @param label Label to retrieve.
	 * 
	 * @return Instance of command if it is found.
	 */
	public static BotCommand getConsoleCommand(String label)
	{
		for(BotCommand command : getAllConsoleCommands())
		{
			if(command.getLabel().equalsIgnoreCase(label))
			{
				return command;
			}
		}
		return null;
	}
	
	/**
	 * Method for getting all command instances
	 * that have been registered.
	 * 
	 * @return Mutable list of command instances.
	 */
	public static ArrayList<BotCommand> getAllCommands()
	{
		ArrayList<BotCommand> commands = new ArrayList<BotCommand>();
		
		for(Module module : moduleRegistry)
		{
			commands.addAll(module.getCommands());
		}
		
		return commands;
	}
	
	/**
	 * Method for getting all command instances
	 * that work in guild channels that have
	 * been registered.
	 * 
	 * @return Mutable list of command instances.
	 */
	public static ArrayList<BotCommand> getAllGuildCommands()
	{
		ArrayList<BotCommand> commands = new ArrayList<BotCommand>();
			
		for(BotCommand command : getAllCommands())
		{
			if(command.canFireInGuild())
			{
				commands.add(command);
			}
		}
			
		return commands;
	}
	
	/**
	 * Method for getting all command instances
	 * that work in private channels that have
	 * been registered.
	 * 
	 * @return Mutable list of command instances.
	 */
	public static ArrayList<BotCommand> getAllPrivateCommands()
	{
		ArrayList<BotCommand> commands = new ArrayList<BotCommand>();
				
		for(BotCommand command : getAllCommands())
		{
			if(command.canFireInPrivate())
			{
				commands.add(command);
			}
		}
				
		return commands;
	}
	
	/**
	 * Method for getting all command instances
	 * that work in the console that have
	 * been registered.
	 * 
	 * @return Mutable list of command instances.
	 */
	public static ArrayList<BotCommand> getAllConsoleCommands()
	{
		ArrayList<BotCommand> commands = new ArrayList<BotCommand>();
				
		for(BotCommand command : getAllCommands())
		{
			if(command.canFireInConsole())
			{
				commands.add(command);
			}
		}
				
		return commands;
	}
	
	/**
	 * Method for getting all event instances
	 * that have been registered.
	 * 
	 * @return Mutable list of event instances.
	 */
	public static ArrayList<BotEvent> getAllEvents()
	{
		ArrayList<BotEvent> events = new ArrayList<BotEvent>();
		
		for(Module module : moduleRegistry)
		{
			events.addAll(module.getEvents());
		}
		
		return events;
	}
}

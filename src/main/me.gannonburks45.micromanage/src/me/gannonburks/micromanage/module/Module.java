package src.me.gannonburks.micromanage.module;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Nonnull;

import net.dv8tion.jda.internal.utils.Checks;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.event.BotEvent;

public class Module implements IModule {

	private String name;
	
	private ArrayList<Command> commands = new ArrayList<Command>();
	private ArrayList<BotEvent> events = new ArrayList<BotEvent>();
	
	public Module(String nameIn)
	{
		this.name = nameIn;
	}
	
	//Name getter
	public String getName()
	{
		return this.name;
	}
	
	//Commands Getter
	public ArrayList<Command> getCommands()
	{
		return this.commands;
	}
	
	//Event getter
	public ArrayList<BotEvent> getEvents()
	{
		return this.events;
	}
	
	//Register all commands
	public void registerAllCommands(@Nonnull Command... commandsIn)
	{
		Checks.noneNull(commandsIn, "commandsIn");
		
		Collections.addAll(commands, commandsIn);
	}
	
	//Register all commands
	public void registerAllEvents(@Nonnull BotEvent... botEventsIn)
	{
		Checks.noneNull(botEventsIn, "botEventsIn");
			
		Collections.addAll(events, botEventsIn);
	}
	
	//Get all commands
	public ArrayList<Command> getAllCommands()
	{
		return this.commands;
	}
	
	//Get all events
	public ArrayList<BotEvent> getAllEvents()
	{
		return this.events;
	}
}

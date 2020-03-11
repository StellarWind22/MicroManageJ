package src.me.gannonburks.micromanage.module;

import java.util.ArrayList;

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
	
	//Event
	public ArrayList<BotEvent> getEvents()
	{
		return this.events;
	}
}

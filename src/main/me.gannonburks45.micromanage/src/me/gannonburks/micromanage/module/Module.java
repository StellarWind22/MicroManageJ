package src.me.gannonburks.micromanage.module;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Nonnull;

import net.dv8tion.jda.internal.utils.Checks;
import src.me.gannonburks.micromanage.command.BotCommand;
import src.me.gannonburks.micromanage.event.BotEvent;

public final class Module
{
	private String name;
	private String version;
	private String author;
	private String description;
	private String issue_url;
	
	private ArrayList<BotCommand> moduleCommands = new ArrayList<BotCommand>();
	private ArrayList<BotEvent> moduleEvents = new ArrayList<BotEvent>();
	
	/**
	 * Constructor for Module class.
	 * 
	 * @param name			Name of the module.
	 * @param version		Version of the module.
	 * @param author		Author of the module.
	 * @param description	Description of the module.
	 * @param issue_url		Issue url for the module(Should be repo issues tab).
	 */
	public Module(String name, String version, String author, String description, String issue_url)
	{
		this.name = name;
		this.version = version;
		this.author = author;
		this.description = description;
		this.issue_url = issue_url;
	}
	
	/**
	 * Method for getting the name of the
	 * module.
	 * 
	 * @return Name of the module.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Method for getting the version of
	 * the module.
	 * 
	 * @return Version of the module.
	 */
	public String getVersion()
	{
		return this.version;
	}
	
	/**
	 * Method for getting the author of
	 * the module.
	 * 
	 * @return Author of the module.
	 */
	public String getAuthor()
	{
		return this.author;
	}
	
	/**
	 * Method for getting the description for
	 * the module.
	 * 
	 * @return Name description of the module.
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * Method for getting the issue tracker url
	 * for the module.
	 * 
	 * @return Issue tracker url of the module.
	 */
	public String getIssueUrl()
	{
		return this.issue_url;
	}
	
	/**
	 * Method for getting all commands packaged
	 * inside a module.
	 * 
	 * @return	Mutable list of all commands.
	 */
	public ArrayList<BotCommand> getCommands()
	{
		return this.moduleCommands;
	}
	
	/**
	 * Method for getting all events packaged
	 * inside a module.
	 * 
	 * @return Mutable list of all events.
	 */
	public ArrayList<BotEvent> getEvents()
	{
		return this.moduleEvents;
	}
	
	/**
	 * Method for mass packaging commands into
	 * the module.
	 * 
	 * @param	commands Vararg of command instances to
	 * 			be packaged into module.
	 */
	public void packageAllCommands(@Nonnull BotCommand... commands)
	{
		Checks.noneNull(commands, "commands");
		
		Collections.addAll(moduleCommands, commands);
	}
	
	/**
	 * Method for mass packaging events into
	 * the module.
	 * 
	 * @param	commands Vararg of event instances to
	 * 			be packaged into module.
	 */
	public void packageAllEvents(@Nonnull BotEvent... botEvents)
	{
		Checks.noneNull(botEvents, "botEvents");
			
		Collections.addAll(moduleEvents, botEvents);
	}
	
	/**
	 * Method for getting all commands that
	 * are packaged in the module.
	 * 
	 * @return	Mutable list of all packaged
	 * 			command instances.
	 */
	public ArrayList<BotCommand> getAllCommands()
	{
		return this.moduleCommands;
	}
	
	/**
	 * Method for getting all events that
	 * are packaged in the module
	 * 
	 * @return	Mutable list of all packaged
	 * 			event instances.
	 */
	public ArrayList<BotEvent> getAllEvents()
	{
		return this.moduleEvents;
	}
}

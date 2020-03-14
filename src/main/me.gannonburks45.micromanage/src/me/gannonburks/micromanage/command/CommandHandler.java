package src.me.gannonburks.micromanage.command;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.server.Server;
import src.me.gannonburks.micromanage.util.SettingsReader;

public final class CommandHandler {

	/*
	 * Execute from a guild channel method
	 * 
	 * @param label		Which command to fire.
	 * @param args		Arguments for the command.
	 * @param channel 	Channel to fire command in.
	 */
	public static void execute(String label, String[] args, User sender, TextChannel channel)
	{
		ModuleRegistry.getGuildCommand(label).fireInGuild(args, sender, channel);
	}
	
	/*
	 * Execute from a private channel method
	 * 
	 * @param label		Which command to fire.
	 * @param args		Arguments for the command.
	 * @param channel 	Channel to fire command in.
	 */
	public static void execute(String label, String[] args, User sender, PrivateChannel channel)
	{
		ModuleRegistry.getPrivateCommand(label).fireInPrivate(args, sender, channel);
	}
	
	/*
	 * Execute from the console method
	 * 
	 * @param label	Which command to fire.
	 * @param args	Arguments for the command.
	 */
	public static void execute(String label, String[] args)
	{
		ModuleRegistry.getConsoleCommand(label).fireInConsole(args);
	}
	
	/*
	 * getLabel method return the label from a raw command string.
	 * 
	 * @param rawCommand	Raw command string.
	 * @param server		Server to get prefix from, passing null will use default prefix.
	 */
	public static String getLabel(String rawCommand, Server server)
	{
		if(server != null)
		{
			return rawCommand.replaceFirst(SettingsReader.getPrefix(server), "").split(" ")[0];
		}
		else
		{
			return rawCommand.replaceFirst(Main.DEFAULT_PREFIX, "").split(" ")[0];
		}
	}
	
	/*
	 * getArgs method returns the arguments from a raw command string.
	 * 
	 * @param rawCommand	Raw command string.
	 * @param server		Server to get prefix from, passing null will use default prefix.
	 */
	public static String[] getArgs(String rawCommand, Server server)
	{
		if(server != null)
		{
			return rawCommand.replaceFirst(SettingsReader.getPrefix(server), "").replaceFirst(getLabel(rawCommand, server), "").trim().split(" ");
		}
		else
		{
			return rawCommand.replaceFirst(Main.DEFAULT_PREFIX, "").replaceFirst(getLabel(rawCommand, null), "").trim().split(" ");
		}
	}
	
	/*
	 * isCmd method returns whether or not a string is a command
	 * 
	 * @param rawCmd Raw command string.
	 * @param server Server to check in, passing null will check for console.
	 */
	public static boolean isCmd(String rawCommand, Server server)
	{
		if(server != null)
		{
			return rawCommand.startsWith(SettingsReader.getPrefix(server));
		}
		else
		{
			return rawCommand.startsWith(Main.DEFAULT_PREFIX);
		}
	}
}

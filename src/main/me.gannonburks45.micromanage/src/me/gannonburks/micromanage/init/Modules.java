package src.me.gannonburks.micromanage.init;

import src.me.gannonburks.micromanage.command.commands.DisableCommand;
import src.me.gannonburks.micromanage.command.commands.EchoCommand;
import src.me.gannonburks.micromanage.command.commands.EnableCommand;
import src.me.gannonburks.micromanage.command.commands.HelpCommand;
import src.me.gannonburks.micromanage.command.commands.PrivateMessageCommand;
import src.me.gannonburks.micromanage.command.commands.ServerMessageCommand;
import src.me.gannonburks.micromanage.command.commands.ServersCommand;
import src.me.gannonburks.micromanage.command.commands.ShutdownCommand;
import src.me.gannonburks.micromanage.command.commands.WhereAmICommand;
import src.me.gannonburks.micromanage.event.events.OnGuildReady;
import src.me.gannonburks.micromanage.event.events.OnGuildUpdate;
import src.me.gannonburks.micromanage.event.events.OnMessageReceivedEvent;
import src.me.gannonburks.micromanage.module.Module;
import src.me.gannonburks.micromanage.module.ModuleRegistry;

public class Modules {

	public static void init()
	{
		//Create Module
		Module def = new Module("default");
		
		//Populate Commands
		def.registerAllCommands
		(
				new HelpCommand("help", false, true, null),
				new WhereAmICommand("whereami", false, true, "Tells you where you are."),
				new ServersCommand("servers", false, true, "Lists all servers in the server registry."),
				new EchoCommand("echo", true, true, "Repeats message."),
				new PrivateMessageCommand("pm", true, true, "Sends a private message to someone."),
				new ServerMessageCommand("sm", true, true, "Sends a message to a specific server in a specific channel."),
				new EnableCommand("enable", false, false, "Enables a command."),
				new DisableCommand("disable", false, false, "Disables a command."),
				new ShutdownCommand("shutdown", false, true, "Shuts down the bot.")
		);
		
		def.registerAllEvents
		(
				new OnMessageReceivedEvent(false),
				new OnGuildReady(false),
				new OnGuildUpdate(false)
		);
		
		//Register It
		ModuleRegistry.registerAll(def);
	}
}

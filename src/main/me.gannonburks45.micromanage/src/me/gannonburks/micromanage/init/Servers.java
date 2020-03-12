package src.me.gannonburks.micromanage.init;

import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.CommandRegistry;
import src.me.gannonburks.micromanage.command.commands.DisableCommand;
import src.me.gannonburks.micromanage.command.commands.EchoCommand;
import src.me.gannonburks.micromanage.command.commands.EnableCommand;
import src.me.gannonburks.micromanage.command.commands.HelpCommand;
import src.me.gannonburks.micromanage.command.commands.PrivateMessageCommand;
import src.me.gannonburks.micromanage.command.commands.ServerMessageCommand;
import src.me.gannonburks.micromanage.command.commands.ServersCommand;
import src.me.gannonburks.micromanage.command.commands.ShutdownCommand;
import src.me.gannonburks.micromanage.command.commands.WhereAmICommand;
import src.me.gannonburks.micromanage.server.Server;
import src.me.gannonburks.micromanage.server.ServerRegistry;

public class Servers {
	
	private static CommandRegistry defCmdRegistry = new CommandRegistry();
	
	public static void init()
	{
		//Register Default Commands
		defCmdRegistry.register(new HelpCommand("help", false, true, null));
		defCmdRegistry.register(new WhereAmICommand("whereami", false, true, "Tells you where you are."));
		defCmdRegistry.register(new ServersCommand("servers", false, true, "Lists all servers in the server registry."));
		defCmdRegistry.register(new EchoCommand("echo", true, true, "Repeats message."));
		defCmdRegistry.register(new PrivateMessageCommand("pm", true, true, "Sends a private message to someone."));	
		defCmdRegistry.register(new ServerMessageCommand("sm", true, true, "Sends a message to a specific server in a specific channel."));
		defCmdRegistry.register(new EnableCommand("enable", false, false, "Enables a command."));
		defCmdRegistry.register(new DisableCommand("disable", false, false, "Disables a command."));
		defCmdRegistry.register(new ShutdownCommand("shutdown", false, true, "Shuts down the bot."));
		
		//Register Default Server
		ServerRegistry.register(new Server("default", Main.DEFAULT_PREFIX, defCmdRegistry));
	}
}

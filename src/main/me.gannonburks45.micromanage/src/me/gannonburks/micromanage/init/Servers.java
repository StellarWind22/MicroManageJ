package src.me.gannonburks.micromanage.init;

import net.dv8tion.jda.api.entities.Guild;
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
import src.me.gannonburks.micromanage.server.Server;
import src.me.gannonburks.micromanage.server.ServerRegistry;

public class Servers {
	
	private static CommandRegistry defaultCommandRegistry = new CommandRegistry();
	
	public static void preInit()
	{
		//Register Default Commands
		
		
		//Register Default Commands
		defaultCommandRegistry.register(new HelpCommand("help", false, true, null));
		defaultCommandRegistry.register(new ServersCommand("servers", false, true, "Lists all servers in the server registry."));
		defaultCommandRegistry.register(new EchoCommand("echo", true, true, "Repeats message."));
		defaultCommandRegistry.register(new PrivateMessageCommand("pm", true, true, "Sends a private message to someone."));	
		defaultCommandRegistry.register(new ServerMessageCommand("sm", true, true, "Sends a message to a specific server in a specific channel."));
		defaultCommandRegistry.register(new EnableCommand("enable", false, false, "Enables a command."));
		defaultCommandRegistry.register(new DisableCommand("disable", false, false, "Disables a command."));
		defaultCommandRegistry.register(new ShutdownCommand("shutdown", false, true, "Shuts down the bot."));
		
		//Register Default Server
		ServerRegistry.register(new Server("default", Main.DEFAULT_PREFIX, defaultCommandRegistry));
	}

	public static void init()
	{
		
		for(Guild guild : Main.bot.getGuilds())
		{
			ServerRegistry.register(new Server(guild, Main.DEFAULT_PREFIX, defaultCommandRegistry));
		}
	}
}

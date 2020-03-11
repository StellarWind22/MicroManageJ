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
import src.me.gannonburks.micromanage.command.commands.ShutdownCommand;
import src.me.gannonburks.micromanage.server.Server;
import src.me.gannonburks.micromanage.server.ServerRegistry;

public class Servers {

	public static void init()
	{
		
		for(Guild guild : Main.bot.getGuilds())
		{
			ServerRegistry.register(new Server(guild, ServerRegistry.get("default").getCommandRegistry()));
		}
	}
	
	public static void preInit()
	{
		//Register Default Commands
		CommandRegistry defaultCommandRegistry = new CommandRegistry();
		
		//Register Default Commands
		defaultCommandRegistry.register(new HelpCommand("help", false, null));
		defaultCommandRegistry.register(new EchoCommand("echo", true, "Repeats message."));
		defaultCommandRegistry.register(new PrivateMessageCommand("pm", true,"Sends a private message to someone."));	
		defaultCommandRegistry.register(new ServerMessageCommand("sm", true,"Sends a message to a specific server in a specific channel."));
		defaultCommandRegistry.register(new EnableCommand("enable", false, "Enables a command."));
		defaultCommandRegistry.register(new DisableCommand("disable", false, "Disables a command."));
		defaultCommandRegistry.register(new ShutdownCommand("shutdown", false, "Shuts down the bot."));
		
		//Register Default Server
		ServerRegistry.register(new Server("default",defaultCommandRegistry));
	}
}

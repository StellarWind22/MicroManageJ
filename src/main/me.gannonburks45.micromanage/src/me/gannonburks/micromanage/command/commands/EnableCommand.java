package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.server.Server;
import src.me.gannonburks.micromanage.server.ServerRegistry;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class EnableCommand extends Command {

	public EnableCommand(String labelIn, boolean canDisableIn, boolean canRunInPrivateIn, String descriptionIn)
	{
		super(labelIn, canDisableIn, canRunInPrivateIn, descriptionIn);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		String commandlabel = args[1];
		
		if(!(ServerRegistry.get(channel.getGuild().getName()).getCommandRegistry().contains(commandlabel, true)))
		{
			MessageHandler.sendMsgGuild(channel, "\"" + commandlabel + "\" is not a valid command, try " + Main.DEFAULT_PREFIX + "help for a list of commands!");
			return;
		}
		
		Command cmd = ServerRegistry.get(channel.getGuild().getName()).getCommandRegistry().get(commandlabel, true);
		
		if(!(cmd.isDisabled()))
		{	
			MessageHandler.sendMsgGuild(channel, "\"" + commandlabel + "\" is already enabled!");
			return;
		}
		else
		{
			cmd.setDisabled(false);
			MessageHandler.sendMsgGuild(channel, "\"" + commandlabel + "\" is now enabled!");
			return;
		}
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		
		String commandlabel = args[1];
		
		if(!(ServerRegistry.get("default").getCommandRegistry().contains(commandlabel, true)))
		{
			System.out.println("\"" + commandlabel + "\" is not a valid command, try " + Main.DEFAULT_PREFIX + "help for a list of commands!");
			return;
		}
		
		for(Server server : ServerRegistry.getAll())
		{
			Command cmd = ServerRegistry.get(server.getName()).getCommandRegistry().get(commandlabel, true);
			
			if(!(cmd.isDisabled()))
			{	
				System.out.println("\"" + commandlabel + "\" is already enabled in server: \"" + server.getName() + "\"!");
				continue;
			}
			else
			{
				cmd.setDisabled(false);
				System.out.println("\"" + commandlabel + "\" is now enabled in server: \"" + server.getName() + "\"!");
				continue;
			}
		}
		return;
	}
}

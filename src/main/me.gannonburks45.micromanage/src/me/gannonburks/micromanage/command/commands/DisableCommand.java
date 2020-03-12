package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.command.CommandRegistry;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.server.Server;
import src.me.gannonburks.micromanage.server.ServerRegistry;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class DisableCommand extends Command {

	public DisableCommand(String labelIn, boolean canDisableIn, boolean canRunInPrivateIn, String descriptionIn)
	{
		super(labelIn, canDisableIn, canRunInPrivateIn, descriptionIn);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		String commandlabel = args[1];
		
		CommandRegistry cmdReg = ModuleRegistry.toCommandRegistry();
		
		if(!(cmdReg.contains(commandlabel, true)))
		{
			MessageHandler.sendMsgGuild(channel, "\"" + commandlabel + "\" is not a valid command, try " + Main.DEFAULT_PREFIX + "help for a list of commands!");
			return;
		}
		
		Command cmd = cmdReg.get(commandlabel, true);
		
		if(cmd.canDisable())
		{
			
			if(cmd.isDisabled())
			{	
				MessageHandler.sendMsgGuild(channel, "\"" + commandlabel + "\" is already disabled!");
				return;
			}
			else
			{
				cmd.setDisabled(true);
				MessageHandler.sendMsgGuild(channel, "\"" + commandlabel + "\" is now disabled!");
				return;
			}
		}
		else
		{
			MessageHandler.sendMsgGuild(channel, "\"" + commandlabel + "\" cannot be disabled!");
			return;
		}
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		String commandlabel = args[1];
		
		CommandRegistry cmdReg = ModuleRegistry.toCommandRegistry();
		
		if(!(cmdReg.contains(commandlabel, true)))
		{
			System.out.println("\"" + commandlabel + "\" is not a valid command, try " + Main.DEFAULT_PREFIX + "help for a list of commands!");
			return;
		}
		
		for(Server server : ServerRegistry.getAll())
		{
			Command cmd = ServerRegistry.get(server.getName()).getCommandRegistry().get(commandlabel, true);
			
			if(cmd.canDisable())
			{
				
				if(cmd.isDisabled())
				{	
					System.out.println("\"" + commandlabel + "\" is already disabled in server: \"" + server.getName() + "\"!");
					return;
				}
				else
				{
					cmd.setDisabled(true);
					System.out.println("\"" + commandlabel + "\" is now disabled in server: \"" + server.getName() + "\"!");
					return;
				}
			}
			else
			{
				System.out.println("\"" + commandlabel + "\" cannot be disabled!");
				return;
			}
		}
		return;
	}
}

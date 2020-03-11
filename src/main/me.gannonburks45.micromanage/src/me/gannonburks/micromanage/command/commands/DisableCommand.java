package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.command.CommandRegistry;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class DisableCommand extends Command {

	public DisableCommand(String labelIn, boolean canDisableIn, String descriptionIn)
	{
		super(labelIn, canDisableIn, descriptionIn);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		String commandlabel = args[1];
		
		if(!(CommandRegistry.contains(commandlabel, true)))
		{
			MessageHandler.sendMsgGuild(channel, "\"" + commandlabel + "\" is not a valid command, try " + Main.PREFIX + "help for a list of commands!");
			return;
		}
		
		Command cmd = CommandRegistry.get(commandlabel, true);
		
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
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		String commandlabel = args[1];
		
		if(!(CommandRegistry.contains(commandlabel, true)))
		{
			MessageHandler.sendMsgPrivate(channel, "\"" + commandlabel + "\" is not a valid command, try " + Main.PREFIX + "help for a list of commands!");
			return;
		}
		
		Command cmd = CommandRegistry.get(commandlabel, true);
		
		if(cmd.canDisable())
		{
			
			if(cmd.isDisabled())
			{	
				MessageHandler.sendMsgPrivate(channel, "\"" + commandlabel + "\" is already disabled!");
				return;
			}
			else
			{
				cmd.setDisabled(true);
				MessageHandler.sendMsgPrivate(channel, "\"" + commandlabel + "\" is now disabled!");
				return;
			}
		}
		else
		{
			MessageHandler.sendMsgPrivate(channel, "\"" + commandlabel + "\" cannot be disabled!");
			return;
		}
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		String commandlabel = args[1];
		
		if(!(CommandRegistry.contains(commandlabel, true)))
		{
			System.out.println("\"" + commandlabel + "\" is not a valid command, try " + Main.PREFIX + "help for a list of commands!");
			return;
		}
		
		Command cmd = CommandRegistry.get(commandlabel, true);
		
		if(cmd.canDisable())
		{
			
			if(cmd.isDisabled())
			{	
				System.out.println("\"" + commandlabel + "\" is already disabled!");
				return;
			}
			else
			{
				cmd.setDisabled(true);
				System.out.println("\"" + commandlabel + "\" is now disabled!");
				return;
			}
		}
		else
		{
			System.out.println("\"" + commandlabel + "\" cannot be disabled!");
			return;
		}
	}
}

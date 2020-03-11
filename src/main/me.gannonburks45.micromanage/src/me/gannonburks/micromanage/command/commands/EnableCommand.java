package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.command.CommandRegistry;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class EnableCommand extends Command {

	public EnableCommand(String labelIn, boolean canDisableIn, String descriptionIn)
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
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		
		String commandlabel = args[1];
		
		if(!(CommandRegistry.contains(commandlabel, true)))
		{
			MessageHandler.sendMsgPrivate(channel, "\"" + commandlabel + "\" is not a valid command, try " + Main.PREFIX + "help for a list of commands!");
			return;
		}
		
		Command cmd = CommandRegistry.get(commandlabel, true);
		
		if(!(cmd.isDisabled()))
		{	
			MessageHandler.sendMsgPrivate(channel, "\"" + commandlabel + "\" is already enabled!");
			return;
		}
		else
		{
			cmd.setDisabled(false);
			MessageHandler.sendMsgPrivate(channel, "\"" + commandlabel + "\" is now enabled!");
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
		
		if(!(cmd.isDisabled()))
		{	
			System.out.println("\"" + commandlabel + "\" is already enabled!");
			return;
		}
		else
		{
			cmd.setDisabled(false);
			System.out.println("\"" + commandlabel + "\" is now enabled!");
			return;
		}
	}
}

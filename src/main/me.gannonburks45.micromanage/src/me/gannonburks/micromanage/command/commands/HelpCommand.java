package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.server.ServerRegistry;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class HelpCommand extends Command {

	public HelpCommand(String labelIn, boolean canDisableIn, boolean canRunInPrivateIn, String descriptionIn)
	{
		super(labelIn, canDisableIn, canRunInPrivateIn, descriptionIn);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		MessageHandler.sendMsgGuild(channel, "List of Commands:");
		
		for(Command cmd : ServerRegistry.get(channel.getGuild().getName()).getCommandRegistry().getAll(false))
		{
			if(cmd.getLabel() == this.getLabel()) continue;		//Ignore self
			
			MessageHandler.sendMsgGuild(channel,"   " +  Main.DEFAULT_PREFIX + cmd.getLabel() + "    " + cmd.getDescription());
		}
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		MessageHandler.sendMsgPrivate(channel, "List of Commands:");
		
		for(Command cmd : ModuleRegistry.toCommandRegistry().getAll(false))
		{
			if(cmd.getLabel() == this.getLabel()) continue;		//Ignore self
			
			MessageHandler.sendMsgPrivate(channel,"   " + Main.DEFAULT_PREFIX + cmd.getLabel() + "    " + cmd.getDescription());
		}
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		System.out.println("List of Commands:");
		
		for(Command cmd : ModuleRegistry.toCommandRegistry().getAll(false))
		{
			if(cmd.getLabel() == this.getLabel()) continue;		//Ignore self
			
			System.out.println("   " + Main.DEFAULT_PREFIX + cmd.getLabel() + "    " + cmd.getDescription());
		}
	}
}

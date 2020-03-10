package src.me.gannonburks.micromanage.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class PrivateMessageCommand extends Command {

	public PrivateMessageCommand(String labelIn, String descriptionIn)
	{
		super(labelIn, descriptionIn);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		String recipient = args[1];
		String msg = String.join(" ", args).replaceAll(Main.PREFIX + this.getLabel(), "").replaceFirst(recipient, "").trim();
		
		MessageHandler.sendMsgPrivateByName(recipient, msg, 1);
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		String recipient = args[1];
		String msg = String.join(" ", args).replaceAll(Main.PREFIX + this.getLabel(), "").replaceFirst(recipient, "").trim();
		
		MessageHandler.sendMsgPrivateByName(recipient, msg, 1);
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		String recipient = args[1];
		String msg = String.join(" ", args).replaceAll(Main.PREFIX + this.getLabel(), "").replaceFirst(recipient, "").trim();
		
		MessageHandler.sendMsgPrivateByName(recipient, msg, 1);
	}
}

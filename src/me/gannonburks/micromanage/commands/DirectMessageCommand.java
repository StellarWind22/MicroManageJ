package me.gannonburks.micromanage.commands;

import me.gannonburks.micromanage.Main;
import me.gannonburks.micromanage.command.Command;
import me.gannonburks.micromanage.command.ICommand;
import me.gannonburks.micromanage.util.MsgHandler;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class DirectMessageCommand extends Command implements ICommand{

	public DirectMessageCommand(String labelIn) {
		super(labelIn);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel) {
		
		String recipient = args[1];
		String msg = String.join(" ", args).replaceAll(Main.prefix + this.getLabel(), "").replaceFirst(recipient, "").trim();
		
		MsgHandler.sendMsgPrivateByName(recipient, msg, 1);
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel) {
		
		String recipient = args[1];
		String msg = String.join(" ", args).replaceAll(Main.prefix + this.getLabel(), "").replaceFirst(recipient, "").trim();
		
		MsgHandler.sendMsgPrivateByName(recipient, msg, 1);
	}
}

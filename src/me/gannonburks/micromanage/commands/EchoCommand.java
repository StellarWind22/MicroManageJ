package me.gannonburks.micromanage.commands;

import me.gannonburks.micromanage.Main;
import me.gannonburks.micromanage.command.Command;
import me.gannonburks.micromanage.util.MsgHandler;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class EchoCommand extends Command {

	public EchoCommand(String labelIn) {
		super(labelIn);
		
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel) {
		
		String msg = String.join(" ", args).replaceAll(Main.prefix + this.getLabel(), "");
		
		MsgHandler.sendMsgGuild(channel, msg);
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel) {
		
		String msg = String.join(" ", args).replaceAll(Main.prefix + this.getLabel(), "");
		
		MsgHandler.sendMsgPrivate(channel, msg);
	}
}

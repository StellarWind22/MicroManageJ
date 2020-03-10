package src.me.gannonburks.micromanage.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class EchoCommand extends Command {

	public EchoCommand(String labelIn) {
		super(labelIn);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel) {
		
		String msg = String.join(" ", args).replaceFirst(Main.prefix + this.getLabel(), "").trim();
		
		MessageHandler.sendMsgGuild(channel, msg);
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel) {
		
		String msg = String.join(" ", args).replaceFirst(Main.prefix + this.getLabel(), "").trim();
		
		MessageHandler.sendMsgPrivate(channel, msg);
	}
	
	@Override
	public void fireInConsole(String[] args) {
		
		String msg = String.join(" ", args).replaceFirst(Main.prefix + this.getLabel(), "").trim();
		
		MessageHandler.sendMsgBroadcast("general", msg, 1);
	}
}

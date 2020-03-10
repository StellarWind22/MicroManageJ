package src.me.gannonburks.micromanage.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class ServerMessageCommand extends Command {

	public ServerMessageCommand(String labelIn) {
		super(labelIn);
	}

	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel) {
		
		String server = args[1];
		String serverChannel = args[2];
		
		String msg = String.join(" ", args).replaceAll(Main.PREFIX + this.getLabel(), "").replaceFirst(server, "").replaceFirst(serverChannel, "").trim();
		
		MessageHandler.sendMsgGuildByName(server, serverChannel, msg, 1, 1);
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel) {
		
		String server = args[1];
		String serverChannel = args[2];
		
		String msg = String.join(" ", args).replaceAll(Main.PREFIX + this.getLabel(), "").replaceFirst(server, "").replaceFirst(serverChannel, "").trim();
		
		MessageHandler.sendMsgGuildByName(server, serverChannel, msg, 1, 1);
	}
	
	@Override
	public void fireInConsole(String[] args) {
		
		String server = args[1];
		String serverChannel = args[2];
		
		String msg = String.join(" ", args).replaceAll(Main.PREFIX + this.getLabel(), "").replaceFirst(server, "").replaceFirst(serverChannel, "").trim();
		
		MessageHandler.sendMsgGuildByName(server, serverChannel, msg, 1, 1);
	}
}

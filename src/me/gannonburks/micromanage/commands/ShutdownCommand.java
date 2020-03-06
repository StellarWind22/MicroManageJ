package me.gannonburks.micromanage.commands;

import me.gannonburks.micromanage.command.Command;
import me.gannonburks.micromanage.command.ICommand;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class ShutdownCommand extends Command implements ICommand {

	public ShutdownCommand(String labelIn) {
		super(labelIn);
	}

	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel) {
		
		System.exit(0);
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel) {
		
		System.exit(0);
	}
}

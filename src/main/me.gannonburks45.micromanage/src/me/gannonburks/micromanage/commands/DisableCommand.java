package src.me.gannonburks.micromanage.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.command.CommandRegistry;

public class DisableCommand extends Command {

	public DisableCommand(String labelIn, String descriptionIn) {
		super(labelIn, descriptionIn);
	}
	
	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel) {
		
		String commandlabel = args[1];
		
		CommandRegistry.deRegister(CommandRegistry.get(commandlabel));
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel) {
		
		String commandlabel = args[1];
		
		CommandRegistry.deRegister(CommandRegistry.get(commandlabel));
	}
	
	@Override
	public void fireInConsole(String[] args) {
		
		String commandlabel = args[1];
		
		CommandRegistry.deRegister(CommandRegistry.get(commandlabel));
	}
}
